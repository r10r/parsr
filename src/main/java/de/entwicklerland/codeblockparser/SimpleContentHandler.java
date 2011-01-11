package de.entwicklerland.codeblockparser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SimpleContentHandler extends AbstractContentHandler {


	public SimpleContentHandler(int bufferSize) {
		super(bufferSize);
	}

//	private int startTextMarker;
//	private boolean inPreTag;
//	private int startAttributeNameMarker;
//	private int endAttributeNameMarker;
//	private int startAttributeValueMarker;
//	private int startCodeMarker;
	
	List<String> textSections = new ArrayList<String>(5);
	List<String> codeSections = new ArrayList<String>(5);
	Map<String,String> attributes = new HashMap<String, String>(2);
	
//	@Override
//	public void processEvent(ParseEvent event) {
//		
//		CodeParseEvent e = (CodeParseEvent) event;
//		switch (e) {
//			case START_TAG_PRE:
//				this.inPreTag = true;
//				String section = new String(Arrays.copyOfRange(input, startTextMarker, pointer-1));
//				this.textSections.add(section);
//				break;
//			case START_ATTRIBUTE_NAME:
//				this.startAttributeNameMarker = pointer;
//				break;
//			case END_ATTRIBUTE_NAME:
//				this.endAttributeNameMarker = pointer;
//				break;
//			case START_ATTRIBUTE_VALUE:
//				this.startAttributeValueMarker = pointer;
//				break;
//			case END_ATTRIBUTE_VALUE:
//				if (inPreTag) {
//					String key = new String(Arrays.copyOfRange(input, startAttributeNameMarker, endAttributeNameMarker));
//					String value = new String(Arrays.copyOfRange(input, startAttributeValueMarker, pointer));
//					attributes.put(key, value);
//				}
//				break;
//			case START_CODE:
//				startCodeMarker = pointer;
//				break;
//			case END_CODE:
//				if (inPreTag) {
//					this.startTextMarker = pointer;
//					String code = new String(Arrays.copyOfRange(input, startCodeMarker, pointer));
//					this.codeSections.add(code);
//				}
//				break;
//			case END_TAG_PRE:
//				this.inPreTag = false;
//				break;
//		}
//	}

	public List<Marker> markers = new LinkedList<Marker>();
	
	@Override
	public void processEvent(Marker marker) {
		markers.add(marker);
		String content = parser.getContent(marker);
		System.out.println(content);
		
	}
	
}
