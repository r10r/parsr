package de.entwicklerland.codeblockparser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.StringWriter;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Set;
import java.util.Stack;

import javax.sql.rowset.FilteredRowSet;

import org.apache.commons.io.IOUtils;
import org.mockito.cglib.core.CollectionUtils;
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

	private final BufferedReader inputReader;
	private final PipedInputStream pipedInput = new PipedInputStream();
	protected final OutputStream output;
	protected final Map<String, Set<ContentHandler>> contentHandlers = new HashMap<String,Set<ContentHandler>>();
	protected final Set<ContentHandler> wildcardContentHandlers = new HashSet<ContentHandler>();
	
	protected StringBuilder buffer = new StringBuilder();
	private final Stack<Match> markerStack = new Stack<Match>();
	
	private static final String ALL = "*";
	
	// ragel variables
	  /* Current state. This must be an integer and it should persist across invocations of the
	  machine when the data is broken into blocks that are processed independently. This variable
	  may be modified from outside the execution loop, but not from within. */
	 int cs;
	 int ts;
	 int te;
	 int act;
	 int p;
	 
	 char[] data;

	private PipedOutputStream pipedOutput; 
	 
	 public char[] getData() {
		return data;
	}
	 
	 public Map<String, Set<ContentHandler>> getContentHandlers() {
		 return contentHandlers;
	 }
	 
	public Set<ContentHandler> getWildcardContentHandlers() {
		return wildcardContentHandlers;
	}
	
	public void parse(String input) throws IOException {
		for (char c : input.toCharArray()) {
			pipedOutput.write(c);
			pipedOutput.flush();
		}
		parse();
	}
	
	/**
	 * 
	 * @param contentHandler
	 * @param bufferCacheSize size of cache used for maintaining subsequent buffers
	 */
	public Parser(InputStream input, OutputStream output) {
		inputReader = new BufferedReader(new InputStreamReader(pipedInput));
		pipedOutput = new PipedOutputStream();
		try {
			pipedInput.connect(pipedOutput);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		this.output = output;
	}

	public void parse() throws IOException {
		String line;
		while ((line = inputReader.readLine()) != null) {
			buffer.append(line);
			parse(line.toCharArray());
		}
	}

	abstract void parse(char[] text) throws IOException;
	
	public void mopen(String label) {
		this.markerStack.push(new Match(label, buffer, p));
	}
	
	public void mclose() {
		Match match = this.markerStack.pop();
		match.setEndPointer(p);
		try {
			notifyHandlers(match);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void write() {
		try {
			this.output.write(data[p]);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	protected void shrink(int end) {
		buffer.delete(0, end);
	}
	
	public void reset() {
		buffer = new StringBuilder();
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
			handler.process(match, output);
		}
		
		Set<ContentHandler> specificListerns = contentHandlers.get(match.getEvent());
		if (specificListerns != null) {
			for(ContentHandler handler: specificListerns) {
				handler.process(match, output);
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
