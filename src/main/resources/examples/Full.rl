%% machine highlight;

public class Sample1 {

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

  // data stream block indicator
  int eof = -1;

  // stack holding the values representing the states, section 5.6/5.7
  int[] stack  = new int[20];
  
  // stack next pointer
  int top = 0;

  // cache for most recent sucessful pattern match
  int act;

  // pointer to character data, see 6.3 
  int ts;

  // pointer to character data  ?
  int te;

  %% write init;
  %% write exec fcall;

  %%{
      action some_action {
	    System.out.println("Hello World!");
      }

      main := 'aaa' | 'bbb' >some_action;

      prepush {
	/* stack growing code */
	// increment stack by 10
	stack = Arrays.copyOf(stack, stack.length()+10);
      }

      postpop {
	/* stack growing code */
	int free = stack.length - top;

	if (free > 20) {
	      // keep a minimum amount of 10 free elements
	      Arrays.copyOf(stack, free - 10);
	}
      }
  }%%


}


}
