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

    tag_finish = '</';
    tag_close = '>';

    namespace = lower+ >beginMatchNamespace %endLastMatch;
    tagname = lower+ >beginMatchTag %endLastMatch;
    
    opening_tag = tag_open . namespace . ':' . tagname . space+ . attrs . tag_close;
    
    closing_tag = tag_finish . namespace . ':' . tagname . tag_close;
    
    tag = opening_tag . any*  . closing_tag;
    main := (any - tag)* $writeBack . tag . (any - tag)* $writeBack;

  }%%
