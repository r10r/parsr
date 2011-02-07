  %% machine codeparser;
package de.entwicklerland.codeblockparser;

public class CodeBlockParser extends AbstractParser {

  %% write data;
  
public CodeBlockParser (AbstractContentHandler contentHandler) {
	super(contentHandler);	
}

  %% write init;
  
public void parse(char[] data) {
  
  // data stream start pointer
  p = 0;
  // data stream end pointer
  int pe = data.length;

  %% write exec;

  %%{
  
    action attr_name_open {
    	mopen("attribute_name");
    }
    
    action attr_name_close {
    	mclose("attribute_name");
    }
  
  	action attr_value_open {
  		mopen("attr_value");
  	}
	
	action attr_value_close {
  		mclose("attr_value");
  	}
  	
  	action namespace_open {
  		mopen("namespace");
  	}

	action namespace_close {
  		mopen("namespace");
  	}

	action tag_open {
  		mopen("tag");
  	}
  	
  	action tag_close {
  		mclose("tag");
  	}

	action tag_name_open {
  		mopen("tagname");
  	}
  	
  	action tag_name_close {
  		mclose("tagname");
  	}

    # HTML 4.01 p.30
    attribute_name = (lower+) >attr_name_open %attr_name_open;
    # HTML 4.01 p.30
    # TODO implement double and single quote mechanism
    attribute_value = (any - '"')+ >attr_value_open %attr_value_close;
    attribute = attribute_name '="' attribute_value '"'; 
    
    tag_open = '<' >tag_open;
    tag_close = '>' >tag_close;
    tag_finish = '</' >tag_close;
    namespace = lower+ >namespace_open %namespace_close;
    tagname = lower+ >tag_name_open %tag_name_close;
    
    attributes = ( attribute . space*)*;
    opening_tag = tag_open . namespace . ':' tagname . space+ . attributes . tag_close;
    closing_tag = tag_finish . namespace . ':' . tagname . tag_close;
    tag = opening_tag . any*  . closing_tag;
#    main := (any - tag) . tag . (any - tag)*;
    main := tag;

  }%%
}


}
