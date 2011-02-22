%%{
  machine attributes;

  action match_name {
    beginMatch("attribute_name");
  }

  action match_value {
    beginMatch("attribute_value");
  }

  action end_last_match {
    endLastMatch();
  }
      
# HTML 4.01 p.30
  attr_name = lower+ >match_name %end_last_match;
# HTML 4.01 p.30
# TODO implement double and single quote mechanism
  attr_value = (any - '"')+ >match_value %end_last_match;
  attr = attr_name '="' attr_value '"'; 

  attrs = attr (space+ attr)*;

}%%
