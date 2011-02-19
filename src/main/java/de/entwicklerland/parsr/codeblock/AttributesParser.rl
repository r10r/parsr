%% machine attributesParser;
%% include attributes "Attributes.rl";
package de.entwicklerland.parsr.codeblock;

import de.entwicklerland.parsr.Parser;

public class AttributesParser extends Parser {

  %% write data;
  
  %% write init;
  
  public void parse(char[] data) {
    
    // data stream end pointer
    int pe = data.length;

    %% write exec;

    %%{
      main := attrs;
    }%%
  }

}
