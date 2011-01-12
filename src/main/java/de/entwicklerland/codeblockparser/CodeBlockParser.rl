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
  
    namespace = 'blog';
    namespace_separator = ':';

    # HTML 4.01 p.30
    attribute_name = (lower+) >{mopen("ATTRIBUTE_NAME");} %{mclose("ATTRIBUTE_NAME");};
    # HTML 4.01 p.30
    # TODO implement double and single quote mechanism
    attribute_value = (any - '"')+ >{mopen("ATTRIBUTE_VALUE");} %{mclose("ATTRIBUTE_VALUE");};
    attribute = attribute_name '="' attribute_value '"'; 
    tag_open = '<';
    tag_close = '>';
    
    pre_tag = tag_open namespace namespace_separator 'pre' (any -- tag_close);
#   main := /[a-z][a-z]*/ - ( 'for' | 'int' ); 
    main := ( attribute . space*)*;

  }%%
}


}
