
// line 1 "CodeBlockParser.rl"
  
// line 2 "CodeBlockParser.rl"
package de.entwicklerland.codeblockparser;

public class CodeBlockParser extends AbstractParser {

  
// line 11 "CodeBlockParser.java"
private static byte[] init__codeparser_actions_0()
{
	return new byte [] {
	    0,    1,    0,    1,    1,    1,    2,    1,    3,    1,    4,    1,
	    5,    1,    6,    1,    7,    1,    8,    1,    9,    2,    9,    5
	};
}

private static final byte _codeparser_actions[] = init__codeparser_actions_0();


private static byte[] init__codeparser_key_offsets_0()
{
	return new byte [] {
	    0,    0,    1,    2,    4,    7,    9,   14,   20,   21,   24,   28,
	   31,   35,   38,   39,   40,   41,   42,   45,   49,   52
	};
}

private static final byte _codeparser_key_offsets[] = init__codeparser_key_offsets_0();


private static char[] init__codeparser_trans_keys_0()
{
	return new char [] {
	   60,   60,   97,  122,   58,   97,  122,   97,  122,   32,    9,   13,
	   97,  122,   32,   62,    9,   13,   97,  122,   60,   60,   97,  122,
	   58,   60,   97,  122,   60,   97,  122,   60,   62,   97,  122,   61,
	   97,  122,   34,   34,   34,   60,   60,   97,  122,   58,   60,   97,
	  122,   60,   97,  122,   60,   62,   97,  122,    0
	};
}

private static final char _codeparser_trans_keys[] = init__codeparser_trans_keys_0();


private static byte[] init__codeparser_single_lengths_0()
{
	return new byte [] {
	    0,    1,    1,    0,    1,    0,    1,    2,    1,    1,    2,    1,
	    2,    1,    1,    1,    1,    1,    1,    2,    1,    2
	};
}

private static final byte _codeparser_single_lengths[] = init__codeparser_single_lengths_0();


private static byte[] init__codeparser_range_lengths_0()
{
	return new byte [] {
	    0,    0,    0,    1,    1,    1,    2,    2,    0,    1,    1,    1,
	    1,    1,    0,    0,    0,    0,    1,    1,    1,    1
	};
}

private static final byte _codeparser_range_lengths[] = init__codeparser_range_lengths_0();


private static byte[] init__codeparser_index_offsets_0()
{
	return new byte [] {
	    0,    0,    2,    4,    6,    9,   11,   15,   20,   22,   25,   29,
	   32,   36,   39,   41,   43,   45,   47,   50,   54,   57
	};
}

private static final byte _codeparser_index_offsets[] = init__codeparser_index_offsets_0();


private static byte[] init__codeparser_trans_targs_0()
{
	return new byte [] {
	    2,    2,    3,    0,    4,    0,    5,    4,    0,    6,    0,    7,
	    7,    6,    0,    7,    8,    7,   13,    0,    9,    8,    9,   10,
	    8,   11,    9,   10,    8,    9,   12,    8,    9,   17,   12,    8,
	   14,   13,    0,   15,    0,    0,   16,    7,   16,   18,   17,   18,
	   19,   17,   20,   18,   19,   17,   18,   21,   17,   18,   17,   21,
	   17,    0
	};
}

private static final byte _codeparser_trans_targs[] = init__codeparser_trans_targs_0();


private static byte[] init__codeparser_trans_actions_0()
{
	return new byte [] {
	    9,    0,    9,    0,   13,    0,   15,    0,    0,   17,    0,   19,
	   19,    0,    0,    0,   11,    0,    1,    0,    9,    0,    9,   13,
	    0,   15,    9,    0,    0,    9,   17,    0,    9,   21,    0,    0,
	    3,    0,    0,    0,    0,    0,    5,    7,    0,    9,    0,    9,
	   13,    0,   15,    9,    0,    0,    9,   17,    0,    9,   21,    0,
	    0,    0
	};
}

private static final byte _codeparser_trans_actions[] = init__codeparser_trans_actions_0();


static final int codeparser_start = 1;
static final int codeparser_first_final = 17;
static final int codeparser_error = 0;

static final int codeparser_en_main = 1;


// line 7 "CodeBlockParser.rl"
  
public CodeBlockParser (AbstractContentHandler contentHandler) {
	super(contentHandler);	
}

  
// line 125 "CodeBlockParser.java"
	{
	cs = codeparser_start;
	}

// line 13 "CodeBlockParser.rl"
  
public void parse(char[] data) {
  
  // data stream start pointer
  p = 0;
  // data stream end pointer
  int pe = data.length;

  
// line 140 "CodeBlockParser.java"
	{
	int _klen;
	int _trans = 0;
	int _acts;
	int _nacts;
	int _keys;
	int _goto_targ = 0;

	_goto: while (true) {
	switch ( _goto_targ ) {
	case 0:
	if ( p == pe ) {
		_goto_targ = 4;
		continue _goto;
	}
	if ( cs == 0 ) {
		_goto_targ = 5;
		continue _goto;
	}
case 1:
	_match: do {
	_keys = _codeparser_key_offsets[cs];
	_trans = _codeparser_index_offsets[cs];
	_klen = _codeparser_single_lengths[cs];
	if ( _klen > 0 ) {
		int _lower = _keys;
		int _mid;
		int _upper = _keys + _klen - 1;
		while (true) {
			if ( _upper < _lower )
				break;

			_mid = _lower + ((_upper-_lower) >> 1);
			if ( data[p] < _codeparser_trans_keys[_mid] )
				_upper = _mid - 1;
			else if ( data[p] > _codeparser_trans_keys[_mid] )
				_lower = _mid + 1;
			else {
				_trans += (_mid - _keys);
				break _match;
			}
		}
		_keys += _klen;
		_trans += _klen;
	}

	_klen = _codeparser_range_lengths[cs];
	if ( _klen > 0 ) {
		int _lower = _keys;
		int _mid;
		int _upper = _keys + (_klen<<1) - 2;
		while (true) {
			if ( _upper < _lower )
				break;

			_mid = _lower + (((_upper-_lower) >> 1) & ~1);
			if ( data[p] < _codeparser_trans_keys[_mid] )
				_upper = _mid - 2;
			else if ( data[p] > _codeparser_trans_keys[_mid+1] )
				_lower = _mid + 2;
			else {
				_trans += ((_mid - _keys)>>1);
				break _match;
			}
		}
		_trans += _klen;
	}
	} while (false);

	cs = _codeparser_trans_targs[_trans];

	if ( _codeparser_trans_actions[_trans] != 0 ) {
		_acts = _codeparser_trans_actions[_trans];
		_nacts = (int) _codeparser_actions[_acts++];
		while ( _nacts-- > 0 )
	{
			switch ( _codeparser_actions[_acts++] )
			{
	case 0:
// line 27 "CodeBlockParser.rl"
	{mopen("attribute_name");}
	break;
	case 1:
// line 27 "CodeBlockParser.rl"
	{mclose("attribute_name");}
	break;
	case 2:
// line 30 "CodeBlockParser.rl"
	{mopen("attribute_value");}
	break;
	case 3:
// line 30 "CodeBlockParser.rl"
	{mclose("attribute_value");}
	break;
	case 4:
// line 33 "CodeBlockParser.rl"
	{mopen("TAG");}
	break;
	case 5:
// line 34 "CodeBlockParser.rl"
	{mclose("TAG");}
	break;
	case 6:
// line 36 "CodeBlockParser.rl"
	{mopen("namespace");}
	break;
	case 7:
// line 36 "CodeBlockParser.rl"
	{mclose("namespace");}
	break;
	case 8:
// line 37 "CodeBlockParser.rl"
	{mopen("tagname");}
	break;
	case 9:
// line 37 "CodeBlockParser.rl"
	{mclose("tagname");}
	break;
// line 259 "CodeBlockParser.java"
			}
		}
	}

case 2:
	if ( cs == 0 ) {
		_goto_targ = 5;
		continue _goto;
	}
	if ( ++p != pe ) {
		_goto_targ = 1;
		continue _goto;
	}
case 4:
case 5:
	}
	break; }
	}

// line 22 "CodeBlockParser.rl"

  
// line 45 "CodeBlockParser.rl"

}


}
