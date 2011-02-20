  %% machine codeparser;
package de.entwicklerland.parsr.codeblock;

import de.entwicklerland.parsr.Parser;

public class CodeBlockParser extends Parser {

  %% write data;
  %% write init;
  
public void parse(char[] data) {
  
  // data stream end pointer
  int pe = data.length;

  %% write exec;

  %%{
    	include attributes "Attributes.rl";
  
	action endLastMatch {
	  endLastMatch();
	}

	action writeBack {
	  write();	
	}

	action enableWriteBack {
	  enableWriteBack();
	}

	action disableWriteBack {
	  disableWriteBack();
	}
    
    action beginMatchCode {
    	beginMatch("code");
    }
    
    action logMark {
    	logMark();
    }
    	
    other = any* >enableWriteBack $writeBack %disableWriteBack >2 $0 %1;
    code = any* >beginMatchCode %logMark %endLastMatch;
    attributes = (space+ . attrs)*; 
    opening_tag = '<blog:pre' . attributes . '>' %logMark;
    closing_tag = '</blog:pre>' %logMark;
    main := (other . opening_tag . code . closing_tag)*;

  }%%
}


}
