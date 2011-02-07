package de.entwicklerland.codeblockparser;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class CodeBlockParserTest {
	
	@Test
	public void testCodeBlockParser() throws IOException {
		CodeBlockContentHandler contentHandler = new CodeBlockContentHandler(10);
		CodeBlockParser parser = new CodeBlockParser(contentHandler);
		InputStream input = IOUtils.toInputStream("aaaa=\"bbbb\" cccc=\"dddd\"");
		parser.parse(input);
		assertEquals(2, contentHandler.getAttributes().size());
		assertEquals(contentHandler.getAttributes().get("aaaa"), "bbbb");
		assertEquals(contentHandler.getAttributes().get("cccc"), "dddd");
		
	}
}
