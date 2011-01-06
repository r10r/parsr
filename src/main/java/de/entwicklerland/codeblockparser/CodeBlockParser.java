
// line 1 "CodeBlockParser.rl"
package de.entwicklerland.codeblockparser;

public class CodeBlockParser {

  
// line 6 "CodeBlockParser.rl"
  
// line 11 "CodeBlockParser.java"
private static byte[] init__highlight_actions_0()
{
	return new byte [] {
	    0,    1,    0
	};
}

private static final byte _highlight_actions[] = init__highlight_actions_0();


private static byte[] init__highlight_key_offsets_0()
{
	return new byte [] {
	    0,    0,    1,    2,    3,    5,    6,    7,    7
	};
}

private static final byte _highlight_key_offsets[] = init__highlight_key_offsets_0();


private static char[] init__highlight_trans_keys_0()
{
	return new char [] {
	  119,   61,   34,   34,   92,   34,   34,   34,    0
	};
}

private static final char _highlight_trans_keys[] = init__highlight_trans_keys_0();


private static byte[] init__highlight_single_lengths_0()
{
	return new byte [] {
	    0,    1,    1,    1,    2,    1,    1,    0,    1
	};
}

private static final byte _highlight_single_lengths[] = init__highlight_single_lengths_0();


private static byte[] init__highlight_range_lengths_0()
{
	return new byte [] {
	    0,    0,    0,    0,    0,    0,    0,    0,    0
	};
}

private static final byte _highlight_range_lengths[] = init__highlight_range_lengths_0();


private static byte[] init__highlight_index_offsets_0()
{
	return new byte [] {
	    0,    0,    2,    4,    6,    9,   11,   13,   14
	};
}

private static final byte _highlight_index_offsets[] = init__highlight_index_offsets_0();


private static byte[] init__highlight_trans_targs_0()
{
	return new byte [] {
	    2,    0,    3,    0,    4,    0,    0,    6,    5,    7,    0,    8,
	    0,    0,    7,    0,    0
	};
}

private static final byte _highlight_trans_targs[] = init__highlight_trans_targs_0();


private static byte[] init__highlight_trans_actions_0()
{
	return new byte [] {
	    1,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0
	};
}

private static final byte _highlight_trans_actions[] = init__highlight_trans_actions_0();


static final int highlight_start = 1;
static final int highlight_first_final = 7;
static final int highlight_error = 0;

static final int highlight_en_main = 1;


// line 7 "CodeBlockParser.rl"

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

  
// line 130 "CodeBlockParser.java"
	{
	cs = highlight_start;
	}

// line 35 "CodeBlockParser.rl"
  
// line 137 "CodeBlockParser.java"
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
	_keys = _highlight_key_offsets[cs];
	_trans = _highlight_index_offsets[cs];
	_klen = _highlight_single_lengths[cs];
	if ( _klen > 0 ) {
		int _lower = _keys;
		int _mid;
		int _upper = _keys + _klen - 1;
		while (true) {
			if ( _upper < _lower )
				break;

			_mid = _lower + ((_upper-_lower) >> 1);
			if ( data[p] < _highlight_trans_keys[_mid] )
				_upper = _mid - 1;
			else if ( data[p] > _highlight_trans_keys[_mid] )
				_lower = _mid + 1;
			else {
				_trans += (_mid - _keys);
				break _match;
			}
		}
		_keys += _klen;
		_trans += _klen;
	}

	_klen = _highlight_range_lengths[cs];
	if ( _klen > 0 ) {
		int _lower = _keys;
		int _mid;
		int _upper = _keys + (_klen<<1) - 2;
		while (true) {
			if ( _upper < _lower )
				break;

			_mid = _lower + (((_upper-_lower) >> 1) & ~1);
			if ( data[p] < _highlight_trans_keys[_mid] )
				_upper = _mid - 2;
			else if ( data[p] > _highlight_trans_keys[_mid+1] )
				_lower = _mid + 2;
			else {
				_trans += ((_mid - _keys)>>1);
				break _match;
			}
		}
		_trans += _klen;
	}
	} while (false);

	cs = _highlight_trans_targs[_trans];

	if ( _highlight_trans_actions[_trans] != 0 ) {
		_acts = _highlight_trans_actions[_trans];
		_nacts = (int) _highlight_actions[_acts++];
		while ( _nacts-- > 0 )
	{
			switch ( _highlight_actions[_acts++] )
			{
	case 0:
// line 49 "CodeBlockParser.rl"
	{ System.out.println("Attribute Detected"); }
	break;
// line 220 "CodeBlockParser.java"
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

// line 36 "CodeBlockParser.rl"

  
// line 51 "CodeBlockParser.rl"



}


}
