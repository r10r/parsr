
// line 1 "TestPointerParser.rl"
package de.entwicklerland.codeblockparser;

public class TestPointerParser extends AbstractParser {

  
// line 6 "TestPointerParser.rl"
  
// line 11 "TestPointerParser.java"
private static byte[] init__testscanner_actions_0()
{
	return new byte [] {
	    0,    1,    0,    1,    1
	};
}

private static final byte _testscanner_actions[] = init__testscanner_actions_0();


private static byte[] init__testscanner_key_offsets_0()
{
	return new byte [] {
	    0,    0,    2,    4
	};
}

private static final byte _testscanner_key_offsets[] = init__testscanner_key_offsets_0();


private static char[] init__testscanner_trans_keys_0()
{
	return new char [] {
	   65,   90,   65,   90,   65,   90,    0
	};
}

private static final char _testscanner_trans_keys[] = init__testscanner_trans_keys_0();


private static byte[] init__testscanner_single_lengths_0()
{
	return new byte [] {
	    0,    0,    0,    0
	};
}

private static final byte _testscanner_single_lengths[] = init__testscanner_single_lengths_0();


private static byte[] init__testscanner_range_lengths_0()
{
	return new byte [] {
	    0,    1,    1,    1
	};
}

private static final byte _testscanner_range_lengths[] = init__testscanner_range_lengths_0();


private static byte[] init__testscanner_index_offsets_0()
{
	return new byte [] {
	    0,    0,    2,    4
	};
}

private static final byte _testscanner_index_offsets[] = init__testscanner_index_offsets_0();


private static byte[] init__testscanner_trans_targs_0()
{
	return new byte [] {
	    2,    1,    2,    3,    0,    3,    0
	};
}

private static final byte _testscanner_trans_targs[] = init__testscanner_trans_targs_0();


private static byte[] init__testscanner_trans_actions_0()
{
	return new byte [] {
	    3,    0,    0,    1,    0,    0,    0
	};
}

private static final byte _testscanner_trans_actions[] = init__testscanner_trans_actions_0();


private static byte[] init__testscanner_eof_actions_0()
{
	return new byte [] {
	    0,    0,    1,    0
	};
}

private static final byte _testscanner_eof_actions[] = init__testscanner_eof_actions_0();


static final int testscanner_start = 1;
static final int testscanner_first_final = 2;
static final int testscanner_error = 0;

static final int testscanner_en_main = 1;


// line 7 "TestPointerParser.rl"
  
public TestPointerParser (AbstractContentHandler contentHandler) {
	super(contentHandler);	
}

  /* Current state. This must be an integer and it should persist across invocations of the
  machine when the data is broken into blocks that are processed independently. This variable
  may be modified from outside the execution loop, but not from within. */

 private int cs;
 private int ts;
 private int te;
 private int act;

  
// line 125 "TestPointerParser.java"
	{
	cs = testscanner_start;
	}

// line 22 "TestPointerParser.rl"

public void parse(char[] input) {
  
  /* data must be read into a char array, that can be processed by 
     the parser
  */
  char[] data = input; 

  // data start pointer
  int p = 0;

  // data stream end pointer
  int pe = data.length;

  int eof = -1;

  int marker;

  
// line 150 "TestPointerParser.java"
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
	_keys = _testscanner_key_offsets[cs];
	_trans = _testscanner_index_offsets[cs];
	_klen = _testscanner_single_lengths[cs];
	if ( _klen > 0 ) {
		int _lower = _keys;
		int _mid;
		int _upper = _keys + _klen - 1;
		while (true) {
			if ( _upper < _lower )
				break;

			_mid = _lower + ((_upper-_lower) >> 1);
			if ( data[p] < _testscanner_trans_keys[_mid] )
				_upper = _mid - 1;
			else if ( data[p] > _testscanner_trans_keys[_mid] )
				_lower = _mid + 1;
			else {
				_trans += (_mid - _keys);
				break _match;
			}
		}
		_keys += _klen;
		_trans += _klen;
	}

	_klen = _testscanner_range_lengths[cs];
	if ( _klen > 0 ) {
		int _lower = _keys;
		int _mid;
		int _upper = _keys + (_klen<<1) - 2;
		while (true) {
			if ( _upper < _lower )
				break;

			_mid = _lower + (((_upper-_lower) >> 1) & ~1);
			if ( data[p] < _testscanner_trans_keys[_mid] )
				_upper = _mid - 2;
			else if ( data[p] > _testscanner_trans_keys[_mid+1] )
				_lower = _mid + 2;
			else {
				_trans += ((_mid - _keys)>>1);
				break _match;
			}
		}
		_trans += _klen;
	}
	} while (false);

	cs = _testscanner_trans_targs[_trans];

	if ( _testscanner_trans_actions[_trans] != 0 ) {
		_acts = _testscanner_trans_actions[_trans];
		_nacts = (int) _testscanner_actions[_acts++];
		while ( _nacts-- > 0 )
	{
			switch ( _testscanner_actions[_acts++] )
			{
	case 0:
// line 44 "TestPointerParser.rl"
	{
      markers.close("UPPER", bp(), p);
    }
	break;
	case 1:
// line 47 "TestPointerParser.rl"
	{
      markers.open("UPPER", bp(), p);
    }
	break;
// line 241 "TestPointerParser.java"
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
	if ( p == eof )
	{
	int __acts = _testscanner_eof_actions[cs];
	int __nacts = (int) _testscanner_actions[__acts++];
	while ( __nacts-- > 0 ) {
		switch ( _testscanner_actions[__acts++] ) {
	case 0:
// line 44 "TestPointerParser.rl"
	{
      markers.close("UPPER", bp(), p);
    }
	break;
// line 268 "TestPointerParser.java"
		}
	}
	}

case 5:
	}
	break; }
	}

// line 41 "TestPointerParser.rl"

  
// line 53 "TestPointerParser.rl"

}


}
