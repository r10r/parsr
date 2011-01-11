package de.entwicklerland.codeblockparser;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MarkerCache {
	
	private static final Logger LOG = LoggerFactory.getLogger(MarkerCache.class);
	
	private final Map<String,Marker> markers = new HashMap<String, Marker>();
	private int openMarkers;
	private AbstractParser parser;
	
	public MarkerCache(AbstractParser parser) {
		this.parser = parser;
	}
	
	public int getOpenMarkers() {
		return openMarkers;
	}
	
	public void open(String label, int startBuffer, int startPointer) {
		markers.put(label, new Marker(label, startBuffer, startPointer));
		openMarkers++;
	}
	
	/**
	 * @return the lowest buffer in use by a marker, or -1 if no marker is set
	 */
	public int getLowestUsedBuffer() {
		int lowestUsedBuffer = -1;
		
		for (Marker marker : markers.values()) {
			if (marker.getStartBuffer() < lowestUsedBuffer || lowestUsedBuffer == -1) {
				lowestUsedBuffer = marker.getStartBuffer();
			}
		}
		return lowestUsedBuffer;
	}
	
	public void close(String label, int endBuffer, int endPointer) {
		Marker marker = markers.get(label);
		if (marker == null) {
			LOG.error("No such marker[{}]", label);
		} else {
			marker.close(endBuffer, endPointer);
			parser.getContentHandler().processEvent(marker);
			openMarkers--;
		}
	}
	
	public void reset() {
		markers.clear();
		openMarkers = 0;
	}
	
}
