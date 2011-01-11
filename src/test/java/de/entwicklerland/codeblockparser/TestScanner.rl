package de.entwicklerland.codeblockparser;

public class TestScanner extends AbstractParser {

  %% machine testscanner;
  %% write data;
  
public TestScanner (AbstractContentHandler contentHandler) {
	super(contentHandler);	
}

  /* Current state. This must be an integer and it should persist across invocations of the
  machine when the data is broken into blocks that are processed independently. This variable
  may be modified from outside the execution loop, but not from within. */

 private int cs;
 private int ts;
 private int te;
 private int act;

  %% write init;

public void parse(char[] input) {
  
  /* data must be read into a char array, that can be processed by 
     the parser
  */
  char[] data = input; 

  // data start pointer
  int p = 0;

  // data stream end pointer
  int pe = data.length;

  int eof = -1;

  %% write exec;

  %%{
  
    lo = lower+;
    up = upper+;
    di = digit+;

    token :=  |*
    	lo => {processEvent(TestEvent.LOWER_END);};
		up => {processEvent(TestEvent.UPPER_END);};
		di => {processEvent(TestEvent.DIGIT_END);};
    *|;
    
  }%%
}


}
