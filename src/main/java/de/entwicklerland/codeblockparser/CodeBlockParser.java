
// line 1 "CodeBlockParser.rl"
  
// line 2 "CodeBlockParser.rl"
package de.entwicklerland.codeblockparser;

public class CodeBlockParser extends Parser {

  
// line 11 "CodeBlockParser.java"
private static byte[] init__codeparser_actions_0()
{
	return new byte [] {
	    0,    1,    6,    2,    3,    6,    2,    6,    0,    2,    6,    1,
	    2,    6,    2,    2,    6,    3,    2,    6,    4,    2,    6,    5,
	    3,    3,    6,    1
	};
}

private static final byte _codeparser_actions[] = init__codeparser_actions_0();


private static short[] init__codeparser_key_offsets_0()
{
	return new short [] {
	    0,    1,    4,    8,   11,   17,   24,   25,   29,   32,   36,   39,
	   43,   47,   50,   56,   62,   66,   68,   70,   72,   76,   81,   85,
	   90,   94,   99,  104,  108,  115,  122,  127,  129,  133,  135,  137,
	  139,  144,  148,  153,  157,  164,  172,  177,  179,  182,  183,  187,
	  190,  194,  197,  201,  205,  208,  214,  220,  224,  226,  228,  230,
	  234,  239,  243,  248,  252,  257,  262,  266,  273,  280,  285
	};
}

private static final short _codeparser_key_offsets[] = init__codeparser_key_offsets_0();


private static char[] init__codeparser_trans_keys_0()
{
	return new char [] {
	   60,   60,   97,  122,   58,   60,   97,  122,   60,   97,  122,   32,
	   60,    9,   13,   97,  122,   32,   60,   62,    9,   13,   97,  122,
	   60,   47,   60,   97,  122,   60,   97,  122,   58,   60,   97,  122,
	   60,   97,  122,   60,   62,   97,  122,   58,   60,   97,  122,   60,
	   97,  122,   32,   60,    9,   13,   97,  122,   32,   60,    9,   13,
	   97,  122,   60,   61,   97,  122,   34,   60,   34,   60,   34,   60,
	   32,   60,    9,   13,   34,   47,   60,   97,  122,   34,   60,   97,
	  122,   34,   58,   60,   97,  122,   34,   60,   97,  122,   34,   60,
	   62,   97,  122,   34,   58,   60,   97,  122,   34,   60,   97,  122,
	   32,   34,   60,    9,   13,   97,  122,   32,   34,   60,    9,   13,
	   97,  122,   34,   60,   61,   97,  122,   34,   60,   60,   61,   97,
	  122,   34,   60,   34,   60,   34,   60,   32,   60,   62,    9,   13,
	   34,   60,   97,  122,   34,   58,   60,   97,  122,   34,   60,   97,
	  122,   32,   34,   60,    9,   13,   97,  122,   32,   34,   60,   62,
	    9,   13,   97,  122,   34,   60,   61,   97,  122,   34,   60,   34,
	   60,   62,   60,   47,   60,   97,  122,   60,   97,  122,   58,   60,
	   97,  122,   60,   97,  122,   60,   62,   97,  122,   58,   60,   97,
	  122,   60,   97,  122,   32,   60,    9,   13,   97,  122,   32,   60,
	    9,   13,   97,  122,   60,   61,   97,  122,   34,   60,   34,   60,
	   34,   60,   32,   60,    9,   13,   34,   47,   60,   97,  122,   34,
	   60,   97,  122,   34,   58,   60,   97,  122,   34,   60,   97,  122,
	   34,   60,   62,   97,  122,   34,   58,   60,   97,  122,   34,   60,
	   97,  122,   32,   34,   60,    9,   13,   97,  122,   32,   34,   60,
	    9,   13,   97,  122,   34,   60,   61,   97,  122,   34,   60,    0
	};
}

private static final char _codeparser_trans_keys[] = init__codeparser_trans_keys_0();


private static byte[] init__codeparser_single_lengths_0()
{
	return new byte [] {
	    1,    1,    2,    1,    2,    3,    1,    2,    1,    2,    1,    2,
	    2,    1,    2,    2,    2,    2,    2,    2,    2,    3,    2,    3,
	    2,    3,    3,    2,    3,    3,    3,    2,    2,    2,    2,    2,
	    3,    2,    3,    2,    3,    4,    3,    2,    3,    1,    2,    1,
	    2,    1,    2,    2,    1,    2,    2,    2,    2,    2,    2,    2,
	    3,    2,    3,    2,    3,    3,    2,    3,    3,    3,    2
	};
}

private static final byte _codeparser_single_lengths[] = init__codeparser_single_lengths_0();


private static byte[] init__codeparser_range_lengths_0()
{
	return new byte [] {
	    0,    1,    1,    1,    2,    2,    0,    1,    1,    1,    1,    1,
	    1,    1,    2,    2,    1,    0,    0,    0,    1,    1,    1,    1,
	    1,    1,    1,    1,    2,    2,    1,    0,    1,    0,    0,    0,
	    1,    1,    1,    1,    2,    2,    1,    0,    0,    0,    1,    1,
	    1,    1,    1,    1,    1,    2,    2,    1,    0,    0,    0,    1,
	    1,    1,    1,    1,    1,    1,    1,    2,    2,    1,    0
	};
}

private static final byte _codeparser_range_lengths[] = init__codeparser_range_lengths_0();


private static short[] init__codeparser_index_offsets_0()
{
	return new short [] {
	    0,    2,    5,    9,   12,   17,   23,   25,   29,   32,   36,   39,
	   43,   47,   50,   55,   60,   64,   67,   70,   73,   77,   82,   86,
	   91,   95,  100,  105,  109,  115,  121,  126,  129,  133,  136,  139,
	  142,  147,  151,  156,  160,  166,  173,  178,  181,  185,  187,  191,
	  194,  198,  201,  205,  209,  212,  217,  222,  226,  229,  232,  235,
	  239,  244,  248,  253,  257,  262,  267,  271,  277,  283,  288
	};
}

private static final short _codeparser_index_offsets[] = init__codeparser_index_offsets_0();


private static byte[] init__codeparser_indicies_0()
{
	return new byte [] {
	    1,    0,    1,    2,    0,    3,    1,    4,    0,    1,    5,    0,
	    6,    1,    6,    7,    0,    8,    1,    9,    8,   10,    0,   11,
	    9,   12,   11,   13,    9,   11,   14,    9,   15,   11,   16,    9,
	   11,   17,    9,   11,   18,   19,    9,   20,   11,   21,    9,   11,
	   22,    9,   23,   11,   23,   24,    9,   25,   11,   25,   26,    9,
	   11,   27,   28,    9,   29,   11,    9,    9,   31,   30,   33,   34,
	   32,   25,   11,   25,    9,   33,   35,   34,   36,   32,   33,   34,
	   37,   32,   33,   38,   34,   39,   32,   33,   34,   40,   32,   33,
	   34,   41,   42,   32,   33,   43,   34,   44,   32,   33,   34,   45,
	   32,   46,   33,   34,   46,   47,   32,   48,   33,   34,   48,   49,
	   32,   33,   34,   50,   51,   32,   52,   34,   32,    1,   53,   54,
	    0,   55,    1,    0,    0,   57,   56,   59,   60,   58,    8,    1,
	    9,    8,    0,   59,   60,   61,   58,   59,   62,   60,   63,   58,
	   59,   60,   64,   58,   65,   59,   60,   65,   66,   58,   67,   59,
	   60,   32,   67,   68,   58,   59,   60,   69,   70,   58,   71,   60,
	   58,    0,   57,   30,   56,   73,   72,   74,   73,   75,   72,   73,
	   76,   72,   77,   73,   78,   72,   73,   79,   72,   73,   18,   80,
	   72,   81,   73,   82,   72,   73,   83,   72,   84,   73,   84,   85,
	   72,   86,   73,   86,   87,   72,   73,   88,   89,   72,   90,   73,
	   72,   72,   92,   91,   94,   95,   93,   86,   73,   86,   72,   94,
	   96,   95,   97,   93,   94,   95,   98,   93,   94,   99,   95,  100,
	   93,   94,   95,  101,   93,   94,   95,   41,  102,   93,   94,  103,
	   95,  104,   93,   94,   95,  105,   93,  106,   94,   95,  106,  107,
	   93,  108,   94,   95,  108,  109,   93,   94,   95,  110,  111,   93,
	  112,   95,   93,    0
	};
}

private static final byte _codeparser_indicies[] = init__codeparser_indicies_0();


private static byte[] init__codeparser_trans_targs_0()
{
	return new byte [] {
	    0,    1,    2,    3,    2,    4,    5,    4,    5,    6,   32,    7,
	    8,   12,    9,   10,    9,   11,   45,   11,   13,   12,   14,   15,
	   14,   15,   16,   17,   16,   18,   19,   21,   19,   20,   21,   22,
	   26,   23,   24,   23,   25,   58,   25,   27,   26,   28,   29,   28,
	   29,   30,   31,   30,   18,   33,   32,   34,   35,   37,   35,   36,
	   37,   38,   39,   38,   40,   41,   40,   41,   42,   43,   42,   44,
	   45,   46,   47,   51,   48,   49,   48,   50,   50,   52,   51,   53,
	   54,   53,   54,   55,   56,   55,   57,   58,   60,   58,   59,   60,
	   61,   65,   62,   63,   62,   64,   64,   66,   65,   67,   68,   67,
	   68,   69,   70,   69,   57
	};
}

private static final byte _codeparser_trans_targs[] = init__codeparser_trans_targs_0();


private static byte[] init__codeparser_trans_actions_0()
{
	return new byte [] {
	    1,    3,   15,   21,    1,   18,   21,    1,    1,    1,    6,    3,
	    1,   15,   15,   21,    1,   18,   21,    1,   21,    1,   18,   21,
	    1,    1,    6,   12,    1,    1,    9,   24,    1,   12,    3,    1,
	   15,   15,   21,    1,   18,   21,    1,   21,    1,   18,   21,    1,
	    1,    6,   12,    1,   12,   12,    1,    1,    9,   24,    1,   12,
	    3,   15,   21,    1,   18,   21,    1,    1,    6,   12,    1,   12,
	    1,    3,    1,   15,   15,   21,    1,   18,    1,   21,    1,   18,
	   21,    1,    1,    6,   12,    1,    1,    9,   24,    1,   12,    3,
	    1,   15,   15,   21,    1,   18,    1,   21,    1,   18,   21,    1,
	    1,    6,   12,    1,   12
	};
}

private static final byte _codeparser_trans_actions[] = init__codeparser_trans_actions_0();


static final int codeparser_start = 0;
static final int codeparser_first_final = 45;
static final int codeparser_error = -1;

static final int codeparser_en_main = 0;


// line 7 "CodeBlockParser.rl"
  
  
// line 199 "CodeBlockParser.java"
	{
	cs = codeparser_start;
	}

// line 9 "CodeBlockParser.rl"
  
public void parse(char[] data) {
  
  // data stream end pointer
  int pe = data.length;

  
// line 212 "CodeBlockParser.java"
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

	_trans = _codeparser_indicies[_trans];
	cs = _codeparser_trans_targs[_trans];

	if ( _codeparser_trans_actions[_trans] != 0 ) {
		_acts = _codeparser_trans_actions[_trans];
		_nacts = (int) _codeparser_actions[_acts++];
		while ( _nacts-- > 0 )
	{
			switch ( _codeparser_actions[_acts++] )
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
	case 3:
// line 20 "CodeBlockParser.rl"
	{
	  beginMatch("namespace");
  	}
	break;
	case 4:
// line 24 "CodeBlockParser.rl"
	{
	  beginMatch("tag");
  	}
	break;
	case 5:
// line 32 "CodeBlockParser.rl"
	{
	  endLastMatch();
	}
	break;
	case 6:
// line 36 "CodeBlockParser.rl"
	{
	  write();	
	}
	break;
// line 330 "CodeBlockParser.java"
			}
		}
	}

case 2:
	if ( ++p != pe ) {
		_goto_targ = 1;
		continue _goto;
	}
case 4:
case 5:
	}
	break; }
	}

// line 16 "CodeBlockParser.rl"

  
// line 55 "CodeBlockParser.rl"

}


}
