package de.entwicklerland.codeblockparser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParserTest {
	
	@Test
	public void testContentHandlerRegistration() throws IOException {
		
		String inputData = "aaaaAAAA\n";
		InputStream input = IOUtils.toInputStream(inputData);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		// create simple content handler
		ContentHandler contentHandler = new ContentHandler() {
			
			@Override
			public void process(Match match, OutputStream output) throws IOException {
				OutputStreamWriter writer = new OutputStreamWriter(output);
				writer.write(match.getContent());
				writer.flush();
			}
		};
		
		// create pushback parser
		final String event = "pushback";
		String event2 = "someother";
		
		Parser parser = new Parser(input, output) {
			@Override
			void parse(char[] text) throws IOException {
				Match match = new Match(event, buffer, 0, text.length);
				notifyHandlers(match);
			}
		};
		
		// check deregistration from specific event
		parser.registerTo(event, contentHandler);
		assertTrue(parser.isRegistered(event, contentHandler));
		parser.unregisterFrom(event, contentHandler);
		assertTrue(!parser.isRegistered(event, contentHandler));
		
		// check deregistration from wildcard event
		parser.registerTo(event, contentHandler);
		parser.registerTo(event2, contentHandler);
		assertTrue(parser.isRegistered(event, contentHandler));
		assertTrue(parser.isRegistered(event2, contentHandler));
		parser.unregisterFromAll(contentHandler);
		assertTrue(!parser.isRegistered(event, contentHandler));
		assertTrue(!parser.isRegistered(event2, contentHandler));
		
		// check registration to wildcard event
		parser.registerToAll(contentHandler);
		assertTrue(parser.isRegistered(event, contentHandler));
		assertTrue(parser.isRegistered(event2, contentHandler));
		parser.unregisterFromAll(contentHandler);
		assertTrue(!parser.isRegistered(event, contentHandler));
		assertTrue(!parser.isRegistered(event, contentHandler));
		
		
		// check event handling for specific event
		parser.registerTo(event, contentHandler);
		parser.parse();
		assertEquals(inputData, output.toString());
		parser.reset();
		
		// check event handling for wildcard events
		parser.unregisterFromAll(contentHandler);
		parser.registerToAll(contentHandler);
		parser.parse();
		assertEquals(inputData, output.toString());
		
		parser.parse(inputData);
		assertEquals(inputData, output.toString());
		
		
	}
	
}
