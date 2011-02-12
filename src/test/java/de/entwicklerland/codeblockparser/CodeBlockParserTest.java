package de.entwicklerland.codeblockparser;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.junit.Test;

public class CodeBlockParserTest {
	
	@Test
	public void testCodeBlockParser1() throws IOException {
		String input = "<blog:pre class=\"lang:java\"></blog:pre>";
//		String input = "AAAAaaa";
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		Parser parser = new CodeBlockParser();
		ContentHandler handler = new ContentHandler() {
			
			@Override
			public void process(Match match, StringBuilder buffer, OutputStream output)
					throws IOException {
				System.out.println(String.format("%s[%s]",match, match.getContent(buffer)));
			}
		};
		parser.registerToAll(handler);
		parser.parse(input, output);
		
		System.out.println(output.toString());
	}
	
	@Test
	public void testAttributesParser() throws IOException {
		String input = "class=\"lang:java\"";
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		Parser parser = new AttributesParser();
		ContentHandler handler = new ContentHandler() {
			
			@Override
			public void process(Match match, StringBuilder buffer, OutputStream output)
					throws IOException {
				System.out.println(String.format("%s[%s]",match, match.getContent(buffer)));
			}
		};
		parser.registerToAll(handler);
		parser.parse(input, output);
		
		System.out.println(output.toString());
	}
	
}
