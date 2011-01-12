package de.entwicklerland.codeblockparser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CodeBlockContentHandler extends AbstractContentHandler {

	public CodeBlockContentHandler(int bufferSize) {
		super(bufferSize);
	}
	
	private Map<String,String> attributes = new HashMap<String, String>();
	private String lastAttributeName;
	
	public Map<String, String> getAttributes() {
		return attributes;
	}
	
	@Override
	public void processEvent(Marker marker) {
		
		System.out.println(marker);
		
		if (marker.getName().equals("ATTRIBUTE_NAME")) {
			lastAttributeName = parser.getContent(marker);
		}
		
		if (marker.getName().equals("ATTRIBUTE_VALUE")) {
			String attributeValue = parser.getContent(marker);
			attributes.put(lastAttributeName, attributeValue);
		}
		

	}

}
