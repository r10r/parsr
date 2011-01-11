package de.entwicklerland.codeblockparser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractParser {
	
	private static final Logger LOG = LoggerFactory.getLogger(AbstractParser.class);

	private final AbstractContentHandler contentHandler;
	private final int bufferSize;
	private final List<char[]> bufferCache = new LinkedList<char[]>();
	private int nextBuffer = 0;
	public final MarkerCache markers = new MarkerCache(this);
	
	/**
	 * 
	 * @return the buffer pointer or -1 if no buffer exists
	 */
	public int bp() {
		return nextBuffer-1;
	}
	
	/**
	 * 
	 * @param contentHandler
	 * @param bufferCacheSize size of cache used for maintaining subsequent buffers
	 */
	public AbstractParser(AbstractContentHandler contentHandler) {
		this.contentHandler = contentHandler;
		// make parser available to the content handler for retrieving content for a marker
		contentHandler.setParser(this);
		
		this.bufferSize = contentHandler.getBufferSize();
	}

	public AbstractContentHandler getContentHandler() {
		return contentHandler;
	}
	
	// TODO discard buffers up the last used buffer
	private void addBuffer(char[] buf) {
//		if (markers.getOpenMarkers() > 0) {
			bufferCache.add(buf);
			nextBuffer++;
//		} else {
//			bufferCache.clear();
//			nextBuffer = 0;
//		}
	}
	
	public boolean isContentAvailable(Marker marker) {
		return (marker.getStartBuffer() < nextBuffer) || (marker.getEndBuffer() < nextBuffer);
	}
	
	/**
	 * Content must be retrieved after receiving the marker
	 * event, but before the next block is processed
	 * 
	 * @param marker
	 * @return
	 * @throws IllegalStateException if buffers for retrieving content are not available
	 */
	public String getContent(Marker marker) {
		
		if (isContentAvailable(marker)) {
			StringBuffer sb = new StringBuffer();
			
			if (marker.getStartBuffer() == marker.getEndBuffer()) {
				// content exists in a single buffer
				char[] buf = bufferCache.get(marker.getStartBuffer());
				char[] region = Arrays.copyOfRange(buf, marker.getStartPointer(), marker.getEndPointer());
				sb.append(region);
			} else {
				char [] buf = bufferCache.get(marker.getStartBuffer());
				char[] region = Arrays.copyOfRange(buf, marker.getStartPointer(), buf.length);
				sb.append(region);
				
				for (int i = marker.getStartBuffer()+1; i < marker.getEndBuffer(); i++) {
					sb.append(bufferCache.get(i));
				}
				
				buf = bufferCache.get(marker.getEndBuffer());
				region = Arrays.copyOfRange(buf, 0, marker.getEndPointer());
				sb.append(region);
			}
			
			return sb.toString();
			
		} else {
			throw new IllegalStateException("Requested buffer does not exist");
		}
		
	}
	
	public void parse(InputStream in) throws IOException {
		
		BufferedReader bin = new BufferedReader(new InputStreamReader(in), bufferSize);

		char[] buf = new char[bufferSize];
		int count; 
		while ((count = bin.read(buf)) != -1) {
			if (count < bufferSize) {
				buf = Arrays.copyOf(buf, count);
			}
			addBuffer(buf);
			parse(buf);
			buf = new char[bufferSize];
		}
		
		// TODO reset parser
		bufferCache.clear();
		markers.reset();
		
	}
	
	public void parse(String input) {
		parse(input.toCharArray());
	}
	
	abstract void parse(char[] text);
	
	
}
