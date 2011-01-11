package de.entwicklerland.codeblockparser;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

import de.entwicklerland.codeblockparser.CodeBlockParser;


public class ParserTest {
	
	private AbstractParser parser;
	
	@Before
	public void setupParser() {
		this.parser = new CodeBlockParser(new SimpleContentHandler(256));		
	}

//	/**
//	 * Test parsing in chunks
//	 * 
//	 * @throws IOException 
//	 */
//	@Test
//	public void testChunkParsing() throws IOException {
//		assertEmittedEvents(6, 1, "aaAA11", TestEvent.LOWER_END, TestEvent.UPPER_END);
//		assertEmittedEvents(6, 1, "AAAAAA");
//		assertEmittedEvents(6, 1, "1234567AAAAAA", TestEvent.DIGIT_END);
//	}
//	
//	@Test
//	public void testScannerParsing() throws IOException {
//		
//		assertEmittedScannerEvents(6, 1, "aaAA11", TestEvent.LOWER_END, TestEvent.UPPER_END);
//		assertEmittedScannerEvents(6, 1, "AAAAAA");
//		assertEmittedScannerEvents(6, 1, "1234567AAAAAA", TestEvent.DIGIT_END);
//	}
	
	/**
	 * TODO mock ContentHandler,
	 * expect content handler calls
	 * expect return value of 
	 * 
	 * @throws IOException 
	 */
	@Test
	public void testPassingPointers() throws IOException {
		
		AbstractContentHandler contentHandler = 
		new AbstractContentHandler(1) {
			
			@Override
			public void processEvent(Marker marker) {
				System.out.println(marker);
				System.out.println(parser.getContent(marker));
			}
			
		};	
//			Mockito.mock(AbstractContentHandler.class);
		TestPointerParser parser = new TestPointerParser(contentHandler);
		// create content handler that accepts 
		// an event with pointers
		parser.parse(IOUtils.toInputStream("aaAAaa"));
		
		
		// invoke parser
		
		// 
		
		
	}
	
	@Test
	public void testSimpleContentHandler() throws IOException {
		SimpleContentHandler sch = new SimpleContentHandler(21);
		TestPointerParser parser = new TestPointerParser(sch);
		parser.parse(IOUtils.toInputStream("aaaaaaaaaABCDEFGHIJKLaaaa"));
		System.out.println(sch.markers);
	}
	
	/**
	 * HELPER METHODS
	 */
	
//	public void assertEmittedScannerEvents(int bufferFirst, int bufferSecond, String input, ParseEvent... expectedEvents) throws IOException {
//		TestScanner parser1 = new TestScanner(new TestContentHandler(6));
//		TestScanner parser2 = new TestScanner(new TestContentHandler(1));
//		
//		verifyEmittedEvents(input, parser1, parser2, expectedEvents);
//	}
//	
//	public void assertEmittedEvents(int bufferFirst, int bufferSecond, String input, ParseEvent... expectedEvents) throws IOException {
//		TestParser parser1 = new TestParser(new TestContentHandler(6));
//		TestParser parser2 = new TestParser(new TestContentHandler(1));
//		
//		verifyEmittedEvents(input, parser1, parser2, expectedEvents);
//	}

//	private void verifyEmittedEvents(String input, AbstractParser parser1, AbstractParser parser2, ParseEvent... expectedEvents) throws IOException {
//		parser1.parse(IOUtils.toInputStream(input));
//		parser2.parse(IOUtils.toInputStream(input));
//		
//		// TODO this is ugly code -> is there a better way to do this 
//		List<ParseEvent> history1 = ((TestContentHandler) parser1.getContentHandler()).getEventHistory();
//		List<ParseEvent> history2 = ((TestContentHandler) parser2.getContentHandler()).getEventHistory();
//		
//		assertCollectionEquals(expectedEvents, history1);
//		assertCollectionEquals(expectedEvents, history2);
//	}
	
	public void assertCollectionEquals(Object[] expected, Collection<?> actual) {
		assertCollectionEquals(Arrays.asList(expected), actual);
	}
	
	public void assertCollectionEquals(Collection<?> expected, Collection<?> actual) {
		assertEquals("Collection should match the expected size", expected.size(), actual.size());
		Iterator<?> iterExpected = expected.iterator();
		Iterator<?> iterActual = actual.iterator();
		int counter = 0;
		while (iterExpected.hasNext() && iterActual.hasNext()) {
			Object expectedElement = iterExpected.next();
			Object actualElement = iterActual.next();
			assertEquals(String.format("element[%d] should match", counter), expectedElement, actualElement);
			counter++;
		}
	}
	
}