
// line 1 "CodeBlockParser.rl"
  
// line 2 "CodeBlockParser.rl"
package de.entwicklerland.codeblockparser;

import java.io.InputStream;
import java.io.OutputStream;

public class CodeBlockParser extends Parser {

public CodeBlockParser(InputStream input, OutputStream output) {
		super(input, output);
}

  
// line 18 "CodeBlockParser.java"
private static byte[] init__codeparser_actions_0()
{
	return new byte [] {
	    0,    1,    0,    1,    1,    1,    2,    1,    3,    1,    4,    1,
	    5,    1,    6,    1,    7,    1,    8
	};
}

private static final byte _codeparser_actions[] = init__codeparser_actions_0();


private static byte[] init__codeparser_key_offsets_0()
{
	return new byte [] {
	    0,    0,    1,    3,    6,    8,   13,   19,   22,   23,   24,   25
	};
}

private static final byte _codeparser_key_offsets[] = init__codeparser_key_offsets_0();


private static char[] init__codeparser_trans_keys_0()
{
	return new char [] {
	   60,   97,  122,   58,   97,  122,   97,  122,   32,    9,   13,   97,
	  122,   32,   62,    9,   13,   97,  122,   61,   97,  122,   34,   34,
	   34,    0
	};
}

private static final char _codeparser_trans_keys[] = init__codeparser_trans_keys_0();


private static byte[] init__codeparser_single_lengths_0()
{
	return new byte [] {
	    0,    1,    0,    1,    0,    1,    2,    1,    1,    1,    1,    0
	};
}

private static final byte _codeparser_single_lengths[] = init__codeparser_single_lengths_0();


private static byte[] init__codeparser_range_lengths_0()
{
	return new byte [] {
	    0,    0,    1,    1,    1,    2,    2,    1,    0,    0,    0,    0
	};
}

private static final byte _codeparser_range_lengths[] = init__codeparser_range_lengths_0();


private static byte[] init__codeparser_index_offsets_0()
{
	return new byte [] {
	    0,    0,    2,    4,    7,    9,   13,   18,   21,   23,   25,   27
	};
}

private static final byte _codeparser_index_offsets[] = init__codeparser_index_offsets_0();


private static byte[] init__codeparser_trans_targs_0()
{
	return new byte [] {
	    2,    0,    3,    0,    4,    3,    0,    5,    0,    6,    6,    5,
	    0,    6,   11,    6,    7,    0,    8,    7,    0,    9,    0,    0,
	   10,    6,   10,    0,    0
	};
}

private static final byte _codeparser_trans_targs[] = init__codeparser_trans_targs_0();


private static byte[] init__codeparser_trans_actions_0()
{
	return new byte [] {
	    7,    0,    5,    0,   15,    0,    0,    9,    0,   17,   17,    0,
	    0,    0,   13,    0,    1,    0,    1,    0,    0,    0,    0,    0,
	    3,   11,    0,    0,    0
	};
}

private static final byte _codeparser_trans_actions[] = init__codeparser_trans_actions_0();


static final int codeparser_start = 1;
static final int codeparser_first_final = 11;
static final int codeparser_error = 0;

static final int codeparser_en_main = 1;


// line 14 "CodeBlockParser.rl"
  
  
// line 116 "CodeBlockParser.java"
	{
	cs = codeparser_start;
	}

// line 16 "CodeBlockParser.rl"
  
public void parse(char[] data) {
  
  // data stream end pointer
  int pe = data.length;

  
// line 129 "CodeBlockParser.java"
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
// line 26 "CodeBlockParser.rl"
	{
    	mopen("attribute_name");
    }
	break;
	case 1:
// line 30 "CodeBlockParser.rl"
	{
  		mopen("attr_value");
  	}
	break;
	case 2:
// line 34 "CodeBlockParser.rl"
	{
  		mopen("namespace");
  	}
	break;
	case 3:
// line 38 "CodeBlockParser.rl"
	{
  		mopen("tag");
  	}
	break;
	case 4:
// line 42 "CodeBlockParser.rl"
	{
  		mopen("tagname");
  	}
	break;
	case 5:
// line 50 "CodeBlockParser.rl"
	{ mclose();}
	break;
	case 6:
// line 54 "CodeBlockParser.rl"
	{ mclose();}
	break;
	case 7:
// line 56 "CodeBlockParser.rl"
	{ mclose();}
	break;
	case 8:
// line 57 "CodeBlockParser.rl"
	{ mclose();}
	break;
// line 254 "CodeBlockParser.java"
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

// line 23 "CodeBlockParser.rl"

  
// line 66 "CodeBlockParser.rl"

}


}
