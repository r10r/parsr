  %% machine codeparser;
package de.entwicklerland.codeblockparser;

public class CodeBlockParser extends AbstractParser {

  %% write data;
  
public CodeBlockParser (AbstractContentHandler contentHandler) {
	super(contentHandler);	
}

  /* Current state. This must be an integer and it should persist across invocations of the
  machine when the data is broken into blocks that are processed independently. This variable
  may be modified from outside the execution loop, but not from within. */

 private int cs;
 
 private int startPointer;

  %% write init;
  
public void parse(char[] input) {
  
  char[] attributeName;
  char[] attributeValue;

  /* data must be read into a char array, that can be processed by 
     the parser
  */
  char[] data = input; 

  // data start pointer
  int p = 0;

  // data stream end pointer
  int pe = data.length;

  %% write exec;

  %%{
  
    action print {
    	System.out.println(fc);
    }
  
    namespace = 'blog';
    namespace_separator = ':';

	action attribute_name {
		markers.close("UPPER", bp(), p);
	}

    # HTML 4.01 p.30
    attribute_name = (lower+) >{markers.open("ATTRIBUTE_NAME", bp(), p);} %{markers.close("ATTRIBUTE_NAME", bp(), p);};
    # HTML 4.01 p.30
    # TODO implement double and single quote mechanism
    attribute_value = (any - '"')+ >{markers.open("ATTRIBUTE_NAME", bp(), p);} %{markers.close("ATTRIBUTE_NAME", bp(), p);};
    attribute = attribute_name '="' attribute_value '"'; 
    tag_open = '<';
    tag_close = '>';
    
    pre_tag = tag_open namespace namespace_separator 'pre' (any -- tag_close);
#   main := /[a-z][a-z]*/ - ( 'for' | 'int' ); 
    main := attribute ${ startPointer = p };

  }%%
}


}
