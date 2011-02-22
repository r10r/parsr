package de.entwicklerland.parsr.codeblock;

import java.io.IOException;

import org.junit.Test;

import de.entwicklerland.parsr.Parser;
import de.entwicklerland.parsr.ParserValidator;
import de.entwicklerland.parsr.codeblock.AttributesParser;
import de.entwicklerland.parsr.codeblock.CodeBlockParser;

public class CodeBlockParserTest {
	
	@Test
	public void testAttribute() throws IOException {
		String input = "class=\"lang:java\"";
		
		// compare event and content
		Parser parser = new AttributesParser();
		ParserValidator validator = new ParserValidator(parser, input);
		validator.addExpectedMatch("attribute_name", "class");
		validator.addExpectedMatch("attribute_value", "lang:java");
		validator.validate();
	}
	
	@Test
	public void testMultipleAttributes() throws IOException {
		String input = "class=\"lang:java\" classx=\"othervalue\"";
		
		// compare event and content
		Parser parser = new AttributesParser();
		ParserValidator validator = new ParserValidator(parser, input);
		validator.addExpectedMatch("attribute_name", "class");
		validator.addExpectedMatch("attribute_value", "lang:java");
		validator.addExpectedMatch("attribute_name", "classx");
		validator.addExpectedMatch("attribute_value", "othervalue");
		validator.validate();
	}

	@Test
	public void testTagWithoutContentAndAttributes() throws IOException {
		String input = "<blog:pre></blog:pre>";
		
		// expect no events to be thrown since empty content notification is disabled by default
		Parser parser = new CodeBlockParser();
		parser.disableWriteBack();
		ParserValidator validator = new ParserValidator(parser, input);
		validator.validate();
	}

	@Test
	public void testTagWithAttributes() throws IOException {
		String input = "<blog:pre class=\"lang:java\" classx=\"blabla\"></blog:pre>";
		
		// compare event and content
		Parser parser = new CodeBlockParser();
		ParserValidator validator = new ParserValidator(parser, input);
		validator.addExpectedMatch("attribute_name", "class");
		validator.addExpectedMatch("attribute_value", "lang:java");
		validator.addExpectedMatch("attribute_name", "classx");
		validator.addExpectedMatch("attribute_value", "blabla");
		validator.expectEmptyOutput();
		validator.validate();
	}

	@Test
	public void testNotifyOnEmptyContent() throws IOException {
		String input = "<blog:pre class=\"lang:java\" classx=\"blabla\"></blog:pre>";
		
		// compare event and content
		Parser parser = new CodeBlockParser();
		parser.enableNotifyOnEmptyContent();
		ParserValidator validator = new ParserValidator(parser, input);
		validator.addExpectedMatch("attribute_name", "class");
		validator.addExpectedMatch("attribute_value", "lang:java");
		validator.addExpectedMatch("attribute_name", "classx");
		validator.addExpectedMatch("attribute_value", "blabla");
		validator.addExpectedMatch("code", "");
		validator.expectEmptyOutput();
		validator.validate();
	}

	
	@Test
	public void testEmbeddedTag() throws IOException {
		String input = "aa<blog:pre></blog:pre>bb";
		
		Parser parser = new CodeBlockParser();
		ParserValidator validator = new ParserValidator(parser, input);
		validator.expectOutput("aabb");
		validator.validate();
	}
	
	
	@Test
	public void testMultipleEmbeddedTagsWithCode() throws IOException {
		
		String input = "aa<blog:pre class=\"lang:java\" classx=\"blabla\">some</blog:pre>bb";
		input += "cc<blog:pre class=\"lang:java\" classx=\"blabla\">some code</blog:pre>dd";
		
		// compare event and content
		Parser parser = new CodeBlockParser();
		ParserValidator validator = new ParserValidator(parser, input);
		validator.addExpectedMatch("attribute_name", "class");
		validator.addExpectedMatch("attribute_value", "lang:java");
		validator.addExpectedMatch("attribute_name", "classx");
		validator.addExpectedMatch("attribute_value", "blabla");
		validator.addExpectedMatch("code", "some");
		validator.addExpectedMatch("attribute_name", "class");
		validator.addExpectedMatch("attribute_value", "lang:java");
		validator.addExpectedMatch("attribute_name", "classx");
		validator.addExpectedMatch("attribute_value", "blabla");
		validator.addExpectedMatch("code", "some code");
		validator.expectOutput("aabbccdd");
		validator.validate();
	}
	
	@Test
	public void testTagWithXMLContent() throws IOException {
		Parser parser = new CodeBlockParser();
		String input = "<blog:pre><hello></hello></blog:pre>";
		
		ParserValidator validator = new ParserValidator(parser, input);
		validator.expectEmptyOutput();
		validator.addExpectedMatch("code", "<hello></hello>");
		validator.validate();
	}
}
