package de.entwicklerland.codeblockparser;

public class TestPointerParser extends AbstractParser {

  %% machine testscanner;
  %% write data;
  
public TestPointerParser (AbstractContentHandler contentHandler) {
	super(contentHandler);	
}

  %% write init;

public void parse(char[] data) {
  
  /* data must be read into a char array, that can be processed by 
     the parser
  */
  p = 0;

  // data stream end pointer
  int pe = data.length;

  int eof = -1;

  %% write exec;

  %%{

    main := (^upper)** . (upper+) >{mopen("UPPER");} %{mclose("UPPER");} . (^upper)**;
    
  }%%
}


}
