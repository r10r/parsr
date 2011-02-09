package de.entwicklerland.codeblockparser;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

public class CodeBlockParserTest {
	
	private CodeBlockContentHandler contentHandler;
	private CodeBlockParser parser;

	@Before
	public void setup() {
		contentHandler = new CodeBlockContentHandler();
		InputStream input = IOUtils.toInputStream(inputData);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		parser = new CodeBlockParser(input,output);
	}
	
//	@Test
//	public void testCodeBlockParser() throws IOException {
//		InputStream input = IOUtils.toInputStream("aaaa=\"bbbb\" cccc=\"dddd\"");
//		parser.parse(input);
//		assertEquals(2, contentHandler.getAttributes().size());
//		assertEquals(contentHandler.getAttributes().get("aaaa"), "bbbb");
//		assertEquals(contentHandler.getAttributes().get("cccc"), "dddd");
//	}
	
	@Test
	public void testCodeBlockParser1() throws IOException {
//		char[] data = IOUtils.toCharArray(getClass().getClassLoader().getResourceAsStream("test1.xml"));
		char [] data = "<blog:pre class=\"lang:java\"></blog:pre>".toCharArray();
		parser.parse(data);
	}
}
