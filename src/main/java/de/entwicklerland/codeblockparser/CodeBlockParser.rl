  %% machine codeparser;
package de.entwicklerland.codeblockparser;

import java.io.InputStream;
import java.io.OutputStream;

public class CodeBlockParser extends Parser {

public CodeBlockParser(InputStream input, OutputStream output) {
		super(input, output);
}

  %% write data;
  
  %% write init;
  
public void parse(char[] data) {
  
  // data stream end pointer
  int pe = data.length;

  %% write exec;

  %%{
  
    action attr_name_open {
    	mopen("attribute_name");
    }
    
  	action attr_value_open {
  		mopen("attr_value");
  	}
	
  	action namespace_open {
  		mopen("namespace");
  	}

	action tag_open {
  		mopen("tag");
  	}

	action tag_name_open {
  		mopen("tagname");
  	}
  	
    # HTML 4.01 p.30
    attribute_name = (lower+) >attr_name_open %attr_name_open;
    # HTML 4.01 p.30
    # TODO implement double and single quote mechanism
    attribute_value = (any - '"')+ >attr_value_open %{ mclose();};
    attribute = attribute_name '="' attribute_value '"'; 
    
    tag_open = '<' >tag_open;
    tag_close = '>' > { mclose();};
    tag_finish = '</' >{ mclose();};
    namespace = lower+ >namespace_open %{ mclose();};
    tagname = lower+ >tag_name_open %{ mclose();};
    
    attributes = ( attribute . space*)*;
    opening_tag = tag_open . namespace . ':' tagname . space+ . attributes . tag_close;
    closing_tag = tag_finish . namespace . ':' . tagname . tag_close;
    tag = opening_tag . any*  . closing_tag;
#    main := (any - tag) . tag . (any - tag)*;
    main := opening_tag;

  }%%
}


}
