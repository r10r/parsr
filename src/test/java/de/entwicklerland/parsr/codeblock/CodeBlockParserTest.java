package de.entwicklerland.parsr.codeblock;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.junit.Test;

import de.entwicklerland.parsr.ContentHandler;
import de.entwicklerland.parsr.Match;
import de.entwicklerland.parsr.Parser;
import de.entwicklerland.parsr.ParserValidator;
import de.entwicklerland.parsr.codeblock.AttributesParser;
import de.entwicklerland.parsr.codeblock.CodeBlockParser;

public class CodeBlockParserTest {
	
	@Test
	public void testAttribute() throws IOException {
		Parser parser = new AttributesParser();
		String input = "class=\"lang:java\"";
		
		// compare event and content
		ParserValidator validator = new ParserValidator(parser, input);
		validator.addExpectedMatch("attribute_name", "class");
		validator.addExpectedMatch("attribute_value", "lang:java");
		validator.validate();
	}
	
	@Test
	public void testMultipleAttributes() throws IOException {
		Parser parser = new AttributesParser();
		String input = "class=\"lang:java\" classx=\"othervalue\"";
		
		// compare event and content
		ParserValidator validator = new ParserValidator(parser, input);
		validator.addExpectedMatch("attribute_name", "class");
		validator.addExpectedMatch("attribute_value", "lang:java");
		validator.addExpectedMatch("attribute_name", "classx");
		validator.addExpectedMatch("attribute_value", "othervalue");
		validator.validate();
	}
	
	@Test
	public void testCodeBlockParser() throws IOException {
		Parser parser = new CodeBlockParser();
		String input = "<blog:pre class=\"lang:java\" classx=\"blabla\"></blog:pre>";

		// compare event and content
		ParserValidator validator = new ParserValidator(parser, input);
		validator.addExpectedMatch("namespace", "blog");
		validator.addExpectedMatch("tag", "pre");
		validator.addExpectedMatch("attribute_name", "class");
		validator.addExpectedMatch("attribute_value", "lang:java");
		validator.addExpectedMatch("attribute_name", "classx");
		validator.addExpectedMatch("attribute_value", "blabla");
		validator.addExpectedMatch("namespace", "blog");
		validator.addExpectedMatch("tag", "pre");
		validator.validate();
	}
	
	@Test
	public void testCodeBlockParser1() throws IOException {
		Parser parser = new CodeBlockParser();
		String input = "aaaaa<blog:pre class=\"lang:java\" classx=\"blabla\"></blog:pre>bbbbbb";
		String expected = "aaaaabbbbbb";
		
		ContentHandler contentHandler = new ContentHandler() {
			
			public void process(Match match, StringBuilder buffer, OutputStream output)
					throws IOException {
			}
		};
		
		parser.registerToAll(contentHandler);
		ByteArrayOutputStream output =  new ByteArrayOutputStream();
		parser.parse(input, output);
		assertEquals(expected, output.toString());
	}
	
}
