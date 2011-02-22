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

    tag_name = 'blog:pre';
    opening_tag = '<' tag_name . space+ . attrs . '>' %beginMatchCode;
    closing_tag = '</' tag_name '>' >endLastMatch;
    other = any;
    #main := (opening_tag | closing_tag | other)*;
    main := |*
    	opening_tag => logMark;
    	closing_tag => logMark;
    	other => logMark;
    *|;
	
  }%%
}


}
