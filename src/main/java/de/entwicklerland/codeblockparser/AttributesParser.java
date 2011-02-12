
// line 1 "AttributesParser.rl"

// line 2 "AttributesParser.rl"

// line 3 "AttributesParser.rl"
package de.entwicklerland.codeblockparser;

public class AttributesParser extends Parser {

  
// line 13 "AttributesParser.java"
private static byte[] init__attributesParser_actions_0()
{
	return new byte [] {
	    0,    1,    0,    1,    1,    1,    2
	};
}

private static final byte _attributesParser_actions[] = init__attributesParser_actions_0();


private static byte[] init__attributesParser_key_offsets_0()
{
	return new byte [] {
	    0,    0,    3,    4,    5,    6,    9,   11
	};
}

private static final byte _attributesParser_key_offsets[] = init__attributesParser_key_offsets_0();


private static char[] init__attributesParser_trans_keys_0()
{
	return new char [] {
	   61,   97,  122,   34,   34,   34,   32,    9,   13,   97,  122,   32,
	    9,   13,   97,  122,    0
	};
}

private static final char _attributesParser_trans_keys[] = init__attributesParser_trans_keys_0();


private static byte[] init__attributesParser_single_lengths_0()
{
	return new byte [] {
	    0,    1,    1,    1,    1,    1,    0,    1
	};
}

private static final byte _attributesParser_single_lengths[] = init__attributesParser_single_lengths_0();


private static byte[] init__attributesParser_range_lengths_0()
{
	return new byte [] {
	    0,    1,    0,    0,    0,    1,    1,    2
	};
}

private static final byte _attributesParser_range_lengths[] = init__attributesParser_range_lengths_0();


private static byte[] init__attributesParser_index_offsets_0()
{
	return new byte [] {
	    0,    0,    3,    5,    7,    9,   12,   14
	};
}

private static final byte _attributesParser_index_offsets[] = init__attributesParser_index_offsets_0();


private static byte[] init__attributesParser_trans_targs_0()
{
	return new byte [] {
	    2,    1,    0,    3,    0,    0,    4,    5,    4,    7,    7,    0,
	    1,    0,    7,    7,    1,    0,    0
	};
}

private static final byte _attributesParser_trans_targs[] = init__attributesParser_trans_targs_0();


private static byte[] init__attributesParser_trans_actions_0()
{
	return new byte [] {
	    5,    0,    0,    0,    0,    0,    3,    5,    0,    0,    0,    0,
	    1,    0,    0,    0,    1,    0,    0
	};
}

private static final byte _attributesParser_trans_actions[] = init__attributesParser_trans_actions_0();


static final int attributesParser_start = 6;
static final int attributesParser_first_final = 6;
static final int attributesParser_error = 0;

static final int attributesParser_en_main = 6;


// line 8 "AttributesParser.rl"
  
  
// line 107 "AttributesParser.java"
	{
	cs = attributesParser_start;
	}

// line 10 "AttributesParser.rl"
  
  public void parse(char[] data) {
    
    // data stream end pointer
    int pe = data.length;

    
// line 120 "AttributesParser.java"
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
	_keys = _attributesParser_key_offsets[cs];
	_trans = _attributesParser_index_offsets[cs];
	_klen = _attributesParser_single_lengths[cs];
	if ( _klen > 0 ) {
		int _lower = _keys;
		int _mid;
		int _upper = _keys + _klen - 1;
		while (true) {
			if ( _upper < _lower )
				break;

			_mid = _lower + ((_upper-_lower) >> 1);
			if ( data[p] < _attributesParser_trans_keys[_mid] )
				_upper = _mid - 1;
			else if ( data[p] > _attributesParser_trans_keys[_mid] )
				_lower = _mid + 1;
			else {
				_trans += (_mid - _keys);
				break _match;
			}
		}
		_keys += _klen;
		_trans += _klen;
	}

	_klen = _attributesParser_range_lengths[cs];
	if ( _klen > 0 ) {
		int _lower = _keys;
		int _mid;
		int _upper = _keys + (_klen<<1) - 2;
		while (true) {
			if ( _upper < _lower )
				break;

			_mid = _lower + (((_upper-_lower) >> 1) & ~1);
			if ( data[p] < _attributesParser_trans_keys[_mid] )
				_upper = _mid - 2;
			else if ( data[p] > _attributesParser_trans_keys[_mid+1] )
				_lower = _mid + 2;
			else {
				_trans += ((_mid - _keys)>>1);
				break _match;
			}
		}
		_trans += _klen;
	}
	} while (false);

	cs = _attributesParser_trans_targs[_trans];

	if ( _attributesParser_trans_actions[_trans] != 0 ) {
		_acts = _attributesParser_trans_actions[_trans];
		_nacts = (int) _attributesParser_actions[_acts++];
		while ( _nacts-- > 0 )
	{
			switch ( _attributesParser_actions[_acts++] )
			{
	case 0:
// line 4 "Attributes.rl"
	{
    beginMatch("attribute_name");
  }
	break;
	case 1:
// line 8 "Attributes.rl"
	{
    beginMatch("attr_value");
  }
	break;
	case 2:
// line 12 "Attributes.rl"
	{
    endLastMatch();
  }
	break;
// line 217 "AttributesParser.java"
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

// line 17 "AttributesParser.rl"

    
// line 20 "AttributesParser.rl"

  }

}
