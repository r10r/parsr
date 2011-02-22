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
    	beginScan();
    }
      
	action endMatchCode {
	  endScan();
	}
    
    tag_name = 'blog:pre';
    opening_tag = '<' tag_name . space+ . attrs . '>';
    closing_tag = '</' tag_name '>';
    other = any;
    #main := (opening_tag | closing_tag | other)*;
    main := |*
    	opening_tag => beginMatchCode;
    	closing_tag => endMatchCode;
    	other => writeBack;
    *|;
	
  }%%
}


}
