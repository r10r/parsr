# how to use this project
The buildr buildfile contains to rake tasks
for generating ragel parsers and cleaning up the 
generated ragel parsers:
*buildr ragel* : generate ragel parsers
*buildr ragel_clean* : remove generated ragel parsers

Remember to call *buildr ragel_clean* before you do 
a commit.

# introduction

The parsr projekt is a framework for event based parsing using
the ragel parser generation framework. You can write your 
parser definition in ragel and use the framework for generating
and processing parsing events.

Parsing events are thrown as user actions in the ragel machine definition,
(see chapter 3 of the ragel user guide) and are processed by the 
ContentHandler which is implemented by you.

# how to create a parser

To create a Parser you have to create a file 
with the following contents and the ending *.rl* for ragel.
The filename should match the class name and the
folder should match the package. In the example the
filename would be *SomeParser* and the package *com.foo.bar*. 

<pre>
%% machine someparser;
package com.foo.bar;

import de.entwicklerland.parsr.Parser;

public class SomeParser extends Parser {

%% write data;
%% write init;
  
  public void parse(char[] data) {

    int pe = data.length;

    %% write exec;

    %%{
      # machine definition
      action anyChar {
    	  write();
      }
      
      # machine instantiation
      main := any $anyChar;
    }%%
  }

}
</pre>

After that call *buildr ragel* to generate the parser class from the parsr definition.
You may now modify the maschine definition.

## content processing methods

To process the input stream you may use the following methods of the parser:

- *Parser.write()* to write the current processed character to the outputstream,
  which is a sort of echo back the input stream to the output stream.

- *Parser.beginMatch("eventname")*  to start matching a region

- *Parser.endMatch("eventname")* to end matching a region, 
  calling *endMatch* generates an event for the content handler.

- *Parser.shrink(int)* method to shrink the buffer of the buffered input stream 

## how to use the parser

the data sink is filled by the content handler

- create a data sink (OutputStream)
- instantiate the parser
- create a content handler by implementing the ContentHandler interface
- register the content handler on the parser to the events it should process 
- call one of the *parse* methods on the parser to process the input

<pre>
// create data sink
ByteArrayOutputStream output = new ByteArrayOutputStream();
  
// instantiate parser 
Parser parser = new SomeParser();

// create content handler implementation  
ContentHandler handler = new ContentHandler() {
	@Override
	public void process(Match match, StringBuilder buffer, OutputStream output) throws IOException {
	  String content = match.getContent(buffer);
		String event = match.getEvent();
				
		// write event and content to output, separated by a colon
		output.write(content.getBytes());
		output.write(':');
		output.write(event.getBytes());
			
	}
};
		
// register content handler to all parsing events
parser.registerToAll(handler);
		
// process input 
parser.parse(input, output);
</pre>

## test the parser

You may use the ParserValidator.java class to validate the parsing behaviour. 
Have a look at the existing tests on how to use the ParserValidator.java.

## debugging

An *endLastMatch()* that is executed before a *beginMatch()* will result in an
an IllegalStateException. You may want to use the *logMark()* method to debug such
a case.

<pre>
java.lang.IllegalStateException: empty marker stack: trying to close marker at position [75]
   input consumed[aaaaa<blog:pre class="lang:java" classx="blabla">some</blog:pre>bbbbbbccccc]
	at de.entwicklerland.parsr.Parser.endLastMatch(Parser.java:100)
	at de.entwicklerland.parsr.codeblock.CodeBlockParser.parse(CodeBlockParser.java:327)
	at de.entwicklerland.parsr.Parser.parse(Parser.java:70)
</pre>

Since the user actions are executed in sequence, you have to put *logMark()* before
the *endLastMatch()* action in the user actions.

# enable vim ragel syntax highlighting

1. copy ragel.vim to *~/.vim/syntax*
2. create/modify file *~/.vim/filetype.vim* and add:

<pre>
augroup filetypedetect
au BufNewFile,BufRead *.rl setf ragel
augroup END
</pre>

# ragel specifics

## priorites 

- unnamed priorities are valid for the whole instantiation of a maschine !


## guarded concatenation

- to escape from an *any* matching sequence one can use (entry-)guarded concatenation of maschines
