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
  
  	action beginMatchNamespace {
	  beginMatch("namespace");
  	}

	action beginMatchTag {
	  beginMatch("tag");
  	}

	action beginMatchTagname {
	  beginMatch("tagname");
  	}

	action endLastMatch {
	  endLastMatch();
	}

	action writeBack {
	  write();	
	}
  	
    tag_open = '<';
    tagname = lower+ >beginMatchTag %endLastMatch;

    tag_finish = '</';
    tag_close = '>';

    namespace = lower+ >beginMatchNamespace %endLastMatch;
    
    opening_tag = tag_open . namespace . ':' . tagname . space+ . attrs . tag_close;
    
    closing_tag = tag_finish . namespace . ':' . tagname . tag_close;
    
    tag = opening_tag . any*  . closing_tag;
    main := (any - tag)* . tag . (any - tag)*;

  }%%
}


}
