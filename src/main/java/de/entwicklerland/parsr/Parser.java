package de.entwicklerland.parsr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * TODO 
 * - multi threaded implementation?
 * - make parser buffer agnostic?
 * 
 * @author ruben
 * @author vincent
 *
 */
public abstract class Parser {
	
	private static final Logger LOG = LoggerFactory.getLogger(Parser.class);

	private BufferedReader inputReader;
	private OutputStream output;
	private final Map<String, Set<ContentHandler>> contentHandlers = new HashMap<String,Set<ContentHandler>>();
	private final Set<ContentHandler> wildcardContentHandlers = new HashSet<ContentHandler>();
	
	protected StringBuilder buffer = new StringBuilder();
	private final Stack<Match> markerStack = new Stack<Match>();
	
	private static final String ALL = "*";
	
	private int processingPointer;
	
	// ragel variables
	  /* Current state. This must be an integer and it should persist across invocations of the
	  machine when the data is broken into blocks that are processed independently. This variable
	  may be modified from outside the execution loop, but not from within. */
	 protected int cs;
	 protected int ts;
	 protected int te;
	 protected int act;
	 protected int p;
	 protected int eof;
	 
	 public void setCs(int cs) {
		this.cs = cs;
	}
	 
	 public Map<String, Set<ContentHandler>> getContentHandlers() {
		 return contentHandlers;
	 }
	 
	public Set<ContentHandler> getWildcardContentHandlers() {
		return wildcardContentHandlers;
	}
	
	public void parse(String input, OutputStream output) throws IOException {
		this.output = output;
		processingPointer = buffer.length();
		buffer.append(input);
		parse(input.toCharArray());
		reset();
	}
	
	public void parse(InputStream input, OutputStream output) throws IOException {
		this.output = output;
		this.inputReader = new BufferedReader(new InputStreamReader(input));
		processingPointer = buffer.length();
		
		// TODO include or exclude line terminators?
		String line;
		while ((line = inputReader.readLine()) != null) {
			buffer.append(line);
			parse(line.toCharArray());
		}
		reset();
	}

	protected abstract void parse(char[] text) throws IOException;
	
	public void beginMatch(String event) {
		LOG.debug("begin match: label[{}], position[{}], state[{}]", new Object[]{event, p, cs});
		this.markerStack.push(new Match(event, buffer, p));
	}
	
	public void endLastMatch() {
		Match match = this.markerStack.pop();
		match.setEndPointer(p);
		LOG.debug("end match: label[{}], position[{}], state[{}], content[{}]", 
				new Object[]{match.getEvent(), p, cs, match.getContent(buffer)});
		try {
			notifyHandlers(match);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private boolean writeBack = false;
	
	public void setWriteBack(boolean writeBack) {
		this.writeBack = writeBack;
	}
	
	public void enableWriteBack() {
		this.writeBack = true;
	}
	
	public void disableWriteBack() {
		this.writeBack = false;
	}
	
	/**
	 * Write current character to output stream
	 */
	public void write() {
		if (writeBack) {
			try {
				this.output.write(buffer.charAt(processingPointer+p));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	protected void shrink(int end) {
		buffer.delete(0, end);
	}
	
	private void reset() {
		buffer = new StringBuilder();
		this.writeBack = false;
	}

	/**
	 * Implementation requires {@link #wildcardContentHandlers} and {@link #contentHandlers} to 
	 * stay disjunct, else a ContentHandler will be notified twice.
	 * 
	 * @param match
	 * @throws IOException
	 */
	public void notifyHandlers(Match match) throws IOException {

		for (ContentHandler handler : wildcardContentHandlers) {
			handler.process(match, buffer, output);
		}
		
		Set<ContentHandler> specificListerns = contentHandlers.get(match.getEvent());
		if (specificListerns != null) {
			for(ContentHandler handler: specificListerns) {
				handler.process(match, buffer, output);
			}
		}
		
	}
	
	public void unregisterFrom(String event, ContentHandler... handlers) {
		if (handlers != null && handlers.length > 0) {
			Set<ContentHandler> listeningHandlers = contentHandlers.get(event);
			if (listeningHandlers != null) {
				for (ContentHandler h : handlers) {					
					listeningHandlers.remove(h);
				}
			}
		}
	}
	
	public void unregisterFromAll(ContentHandler... handlers) {
		if (handlers != null && handlers.length > 0) {
			for (ContentHandler handler : handlers) {
				if (!wildcardContentHandlers.remove(handler)) {
					for (Set<ContentHandler> set : contentHandlers.values()) {
						set.remove(handler);
					}
				}
			}
		}
	}
	
	public void registerTo(String event, ContentHandler... handlers) {
		
		Set<ContentHandler> listeningHandlers;
		
		if (event.equals(ALL)) {
			listeningHandlers = wildcardContentHandlers;
		} else {
			listeningHandlers = contentHandlers.get(event);
			if (listeningHandlers == null) {
				listeningHandlers = new HashSet<ContentHandler>(handlers.length);
				contentHandlers.put(event, listeningHandlers);
			}
		}
		
		for (ContentHandler handler : handlers) {
			listeningHandlers.add(handler);							
		}
	}
	
	public void registerToAll(ContentHandler... handlers) {

		// remove content handlers from specific events
		for (ContentHandler contentHandler : handlers) {
			for (Set<ContentHandler> list : contentHandlers.values()) {
				list.remove(contentHandler);
			}
		}
		registerTo(ALL, handlers);
	}
	
	public boolean isRegistered(String event, ContentHandler handler) {
		if (wildcardContentHandlers.contains(handler)) {
			return true;
		} else {
			Set<ContentHandler> handlers = contentHandlers.get(event);
			if (handlers != null) {
				return handlers.contains(handler);
			} else {
				return false;
			}
		}
	}
}