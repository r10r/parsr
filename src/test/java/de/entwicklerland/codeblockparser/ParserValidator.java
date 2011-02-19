package de.entwicklerland.codeblockparser;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * Simple validator for a parser that checks if parsing works
 * correctly.
 * 
 * @author ruben
 *
 */
class ParserValidator {

	private Parser parser;
	private String input;
	
	public ParserValidator(Parser parser, String input) {
		this.parser = parser;
		this.input = input;
	}
	
	List<ExpectedMatch> expectedMatches = new LinkedList<ExpectedMatch>();
	
	private class ExpectedMatch {
		String event;
		String content;
		
		public ExpectedMatch(String event, String content) {
			this.event = event;
			this.content = content;
		}
	}
	
	public void addExpectedMatch(String event, String content) {
		this.expectedMatches.add(new ExpectedMatch(event, content));
	}
	
	public void assertNextMatch(String event, String content) {
		ExpectedMatch expectedMatch = expectedMatches.remove(0);
		assertEquals("event name should equal", expectedMatch.event, event);
		assertEquals("content of match should equal", expectedMatch.content, content);
	}
	
	public void validate() throws IOException {
		
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		ContentHandler handler = new ContentHandler() {
			@Override
			public void process(Match match, StringBuilder buffer, OutputStream output)
			throws IOException {
				String content = match.getContent(buffer);
				String event = match.getEvent();
				
				// log match and content
				System.out.println(String.format("%s: %s", match, content));
				
				assertNextMatch(event, content);
			}
		};
		parser.registerToAll(handler);
		parser.parse(input, output);
		
	}
}