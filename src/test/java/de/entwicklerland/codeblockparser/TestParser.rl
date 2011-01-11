package de.entwicklerland.codeblockparser;

public class TestParser extends AbstractParser {

  %% machine codeparser;
  %% write data;
  
public TestParser (AbstractContentHandler contentHandler) {
	super(contentHandler);	
}

  /* Current state. This must be an integer and it should persist across invocations of the
  machine when the data is broken into blocks that are processed independently. This variable
  may be modified from outside the execution loop, but not from within. */

 private int cs;

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
  
    lo = (lower**) %{processEvent();};
    up = (upper**) %{processEvent();};
    di = (digit**) %{processEvent();};
    
    main := (lo | up | di)**;

  }%%
}


}
