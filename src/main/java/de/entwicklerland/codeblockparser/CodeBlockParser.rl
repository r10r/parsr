  %% machine codeparser;
package de.entwicklerland.codeblockparser;

public class CodeBlockParser extends Parser {

  %% write data;
  
  %% write init;
  
public void parse(char[] data) {
  
  // data stream end pointer
  int pe = data.length;

  %% write exec;

  %%{
    	include attributes "AttributesParser.rl";
  
  	action namespace_open {
  		mopen("namespace");
  	}

	action tag_open {
  		mopen("tag");
  	}

	action tag_name_open {
  		mopen("tagname");
  	}
  	
    tag_open = '<' >tag_open;
    tagname = lower+ >tag_name_open %{ mclose();};

    tag_finish = '</' >{ mclose();};
    tag_close = '>' >{ mclose();};

    namespace = lower+ >namespace_open %{ mclose();};
    
    opening_tag = tag_open . namespace . ':' . tagname . space+ . attrs . tag_close;
    
    closing_tag = tag_finish . namespace . ':' . tagname . tag_close;
    
    tag = opening_tag . any*  . closing_tag;
    main := (any - tag)* ${write();} . tag . (any - tag)* ${write();};

  }%%
}


}
