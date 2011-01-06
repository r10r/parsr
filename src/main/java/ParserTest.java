import org.junit.Test;

import de.entwicklerland.codeblockparser.CodeBlockParser;


public class ParserTest {

	@Test
	public void simpleParserTest() {
		CodeBlockParser parser = new CodeBlockParser();
		
		parser.parse("aaa=\"bla\"");
	}
	
}
