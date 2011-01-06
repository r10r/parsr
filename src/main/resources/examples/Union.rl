%% machine highlight;

public class Sample2 {

  %% write data;

public static void main(String... argv) {

  /* Current state. This must be an integer and it should persist across invocations of the
  machine when the data is broken into blocks that are processed independently. This variable
  may be modified from outside the execution loop, but not from within. */

  int cs; 


  /* data must be read into a char array, that can be processed by 
     the parser
  */

  // data
  char[] data = argv[0].toCharArray(); 

  // data start pointer
  int p = 0;

  // data stream end pointer
  int pe = data.length;

  %% write init;
  %% write exec;

  %%{
    # Hex digits, decimal digits, or identifiers
    main := '0x' xdigit+ | digit+ | alpha alnum*;
  }%%


}


}
