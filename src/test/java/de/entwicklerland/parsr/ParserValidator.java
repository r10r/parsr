package de.entwicklerland.parsr;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

import de.entwicklerland.parsr.ContentHandler;
import de.entwicklerland.parsr.Match;
import de.entwicklerland.parsr.Parser;

/**
 * Simple validator for a parser that checks if parsing works
 * correctly.
 * 
 * @author ruben
 *
 */
public class ParserValidator {

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
	
	int matchCount = 0;
	
	public void assertNextMatch(String event, String content) {
		int count = matchCount++;
		if (expectedMatches.isEmpty()) {
			throw new IllegalStateException(String.format("Missing expectation for expected match[%s]", count));
		}
		ExpectedMatch expectedMatch = expectedMatches.remove(0);
		assertEquals(String.format("event[%d] name should equal", count), expectedMatch.event, event);
		assertEquals(String.format("content[%d] of match should equal", count), expectedMatch.content, content);
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
		
		assertTrue(String.format("Missing match for [%d]expectations", expectedMatches.size()),expectedMatches.isEmpty());
		
	}
}