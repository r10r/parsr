package de.entwicklerland.codeblockparser;

public class TestScanner extends Parser {

  %% machine testscanner;
  
  %% write data;
  %% write init;

public void parse(char[] data) {
  
  // data stream end pointer
  int pe = data.length;
  int eof = -1;

  %% write exec;

  %%{
  
    lo = lower+;
    up = upper+;
    di = digit+;

    token :=  |*
    	lo => {};
		up => {};
		di => {};
    *|;
    
  }%%
}


}
