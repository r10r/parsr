package de.entwicklerland.codeblockparser;

public class CodeBlockParser {

  %% machine highlight;
  %% write data;

public void parse(String input) {
  parse(input.toCharArray());
}

public void parse(char[] input) {

  /* Current state. This must be an integer and it should persist across invocations of the
  machine when the data is broken into blocks that are processed independently. This variable
  may be modified from outside the execution loop, but not from within. */

  int cs; 


  /* data must be read into a char array, that can be processed by 
     the parser
  */

  // data
  char[] data = input; 

  // data start pointer
  int p = 0;

  // data stream end pointer
  int pe = data.length;

  %% write init;
  %% write exec;

  %%{
    namespace = 'blog';
    namespace_separator = ':';

    attribute_name = /[\w]/;
    attribute_value = (any - '"') | '\\"';
    attribute = attribute_name '="' attribute_value '"'; 
    tag_open = '<';
    tag_close = '>';
    
    pre_tag = tag_open namespace namespace_separator 'pre' (any -- tag_close);
#   main := /[a-z][a-z]*/ - ( 'for' | 'int' ); 
    main := attribute > { System.out.println("Attribute Detected"); };

  }%%


}


}
