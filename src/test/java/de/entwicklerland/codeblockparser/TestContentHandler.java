package de.entwicklerland.codeblockparser;

import java.util.LinkedList;
import java.util.List;

public class TestContentHandler extends AbstractContentHandler {

	private List<Marker> markerHistory = new LinkedList<Marker>();
	
	
	public TestContentHandler(int bufferSize) {
		super(bufferSize);
	}

	@Override
	public void processEvent(Marker marker) {
		markerHistory.add(marker);
	}
}
