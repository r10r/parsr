package de.entwicklerland.codeblockparser;

public class BufferTestParser extends AbstractParser {

  %% machine codeparser;
  %% write data;
  
public BufferTestParser (AbstractContentHandler contentHandler) {
	super(contentHandler);	
}
  %% write init;
  
public void parse(char[] data) {
  
  // data stream end pointer
  int pe = data.length;
  int eof = -1;

  %% write exec;

  %%{
  	action start_upper {
  		mopen("upper");
  	}
  	
  	action end_upper {
  		mclose("upper");
  	}
  	
  	action start_lower {
  		mopen("lower");
  	}
  	
  	action end_lower {
  		mclose("lower");
  	}
  
    lo = (lower**) >start_lower %end_lower;
    up = (upper**) >start_upper %end_upper;
    
    main := (lo | up)**;

  }%%
}


}
