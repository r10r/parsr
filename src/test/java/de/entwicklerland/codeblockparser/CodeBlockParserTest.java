package de.entwicklerland.codeblockparser;

import java.io.IOException;

import org.junit.Test;

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
	
}
