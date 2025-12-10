// $ANTLR 3.5.2 simple.g 2025-12-09 18:08:51

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class simpleLexer extends Lexer {
	public static final int EOF=-1;
	public static final int T__24=24;
	public static final int T__25=25;
	public static final int T__26=26;
	public static final int T__27=27;
	public static final int T__28=28;
	public static final int T__29=29;
	public static final int T__30=30;
	public static final int T__31=31;
	public static final int T__32=32;
	public static final int T__33=33;
	public static final int T__34=34;
	public static final int T__35=35;
	public static final int BOOLEAN=4;
	public static final int CCURLYB=5;
	public static final int CFLOAT=6;
	public static final int CINT=7;
	public static final int CLASS=8;
	public static final int DO=9;
	public static final int DOT=10;
	public static final int DOUBLE=11;
	public static final int ID=12;
	public static final int INT=13;
	public static final int LPAREN=14;
	public static final int OCURLYB=15;
	public static final int PRIVATE=16;
	public static final int PROTECTED=17;
	public static final int PUBLIC=18;
	public static final int RETURN=19;
	public static final int RPAREN=20;
	public static final int SEMICOLON=21;
	public static final int WHILE=22;
	public static final int WS=23;

	// delegates
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public simpleLexer() {} 
	public simpleLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public simpleLexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "simple.g"; }

	// $ANTLR start "T__24"
	public final void mT__24() throws RecognitionException {
		try {
			int _type = T__24;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// simple.g:2:7: ( '!=' )
			// simple.g:2:9: '!='
			{
			match("!="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__24"

	// $ANTLR start "T__25"
	public final void mT__25() throws RecognitionException {
		try {
			int _type = T__25;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// simple.g:3:7: ( '*' )
			// simple.g:3:9: '*'
			{
			match('*'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__25"

	// $ANTLR start "T__26"
	public final void mT__26() throws RecognitionException {
		try {
			int _type = T__26;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// simple.g:4:7: ( '+' )
			// simple.g:4:9: '+'
			{
			match('+'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__26"

	// $ANTLR start "T__27"
	public final void mT__27() throws RecognitionException {
		try {
			int _type = T__27;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// simple.g:5:7: ( ',' )
			// simple.g:5:9: ','
			{
			match(','); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__27"

	// $ANTLR start "T__28"
	public final void mT__28() throws RecognitionException {
		try {
			int _type = T__28;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// simple.g:6:7: ( '-' )
			// simple.g:6:9: '-'
			{
			match('-'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__28"

	// $ANTLR start "T__29"
	public final void mT__29() throws RecognitionException {
		try {
			int _type = T__29;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// simple.g:7:7: ( '/' )
			// simple.g:7:9: '/'
			{
			match('/'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__29"

	// $ANTLR start "T__30"
	public final void mT__30() throws RecognitionException {
		try {
			int _type = T__30;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// simple.g:8:7: ( '<' )
			// simple.g:8:9: '<'
			{
			match('<'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__30"

	// $ANTLR start "T__31"
	public final void mT__31() throws RecognitionException {
		try {
			int _type = T__31;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// simple.g:9:7: ( '<=' )
			// simple.g:9:9: '<='
			{
			match("<="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__31"

	// $ANTLR start "T__32"
	public final void mT__32() throws RecognitionException {
		try {
			int _type = T__32;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// simple.g:10:7: ( '=' )
			// simple.g:10:9: '='
			{
			match('='); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__32"

	// $ANTLR start "T__33"
	public final void mT__33() throws RecognitionException {
		try {
			int _type = T__33;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// simple.g:11:7: ( '==' )
			// simple.g:11:9: '=='
			{
			match("=="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__33"

	// $ANTLR start "T__34"
	public final void mT__34() throws RecognitionException {
		try {
			int _type = T__34;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// simple.g:12:7: ( '>' )
			// simple.g:12:9: '>'
			{
			match('>'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__34"

	// $ANTLR start "T__35"
	public final void mT__35() throws RecognitionException {
		try {
			int _type = T__35;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// simple.g:13:7: ( '>=' )
			// simple.g:13:9: '>='
			{
			match(">="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__35"

	// $ANTLR start "CLASS"
	public final void mCLASS() throws RecognitionException {
		try {
			int _type = CLASS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// simple.g:354:13: ( 'class' )
			// simple.g:354:15: 'class'
			{
			match("class"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CLASS"

	// $ANTLR start "INT"
	public final void mINT() throws RecognitionException {
		try {
			int _type = INT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// simple.g:355:13: ( 'int' )
			// simple.g:355:15: 'int'
			{
			match("int"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INT"

	// $ANTLR start "DOUBLE"
	public final void mDOUBLE() throws RecognitionException {
		try {
			int _type = DOUBLE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// simple.g:356:13: ( 'double' )
			// simple.g:356:15: 'double'
			{
			match("double"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DOUBLE"

	// $ANTLR start "BOOLEAN"
	public final void mBOOLEAN() throws RecognitionException {
		try {
			int _type = BOOLEAN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// simple.g:357:13: ( 'boolean' )
			// simple.g:357:15: 'boolean'
			{
			match("boolean"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "BOOLEAN"

	// $ANTLR start "PUBLIC"
	public final void mPUBLIC() throws RecognitionException {
		try {
			int _type = PUBLIC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// simple.g:358:13: ( 'public' )
			// simple.g:358:15: 'public'
			{
			match("public"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PUBLIC"

	// $ANTLR start "PRIVATE"
	public final void mPRIVATE() throws RecognitionException {
		try {
			int _type = PRIVATE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// simple.g:359:13: ( 'private' )
			// simple.g:359:15: 'private'
			{
			match("private"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PRIVATE"

	// $ANTLR start "PROTECTED"
	public final void mPROTECTED() throws RecognitionException {
		try {
			int _type = PROTECTED;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// simple.g:360:13: ( 'protected' )
			// simple.g:360:15: 'protected'
			{
			match("protected"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PROTECTED"

	// $ANTLR start "DO"
	public final void mDO() throws RecognitionException {
		try {
			int _type = DO;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// simple.g:361:13: ( 'do' )
			// simple.g:361:15: 'do'
			{
			match("do"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DO"

	// $ANTLR start "WHILE"
	public final void mWHILE() throws RecognitionException {
		try {
			int _type = WHILE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// simple.g:362:13: ( 'while' )
			// simple.g:362:15: 'while'
			{
			match("while"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WHILE"

	// $ANTLR start "RETURN"
	public final void mRETURN() throws RecognitionException {
		try {
			int _type = RETURN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// simple.g:363:13: ( 'return' )
			// simple.g:363:15: 'return'
			{
			match("return"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RETURN"

	// $ANTLR start "OCURLYB"
	public final void mOCURLYB() throws RecognitionException {
		try {
			int _type = OCURLYB;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// simple.g:364:13: ( '{' )
			// simple.g:364:15: '{'
			{
			match('{'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "OCURLYB"

	// $ANTLR start "CCURLYB"
	public final void mCCURLYB() throws RecognitionException {
		try {
			int _type = CCURLYB;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// simple.g:365:13: ( '}' )
			// simple.g:365:15: '}'
			{
			match('}'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CCURLYB"

	// $ANTLR start "LPAREN"
	public final void mLPAREN() throws RecognitionException {
		try {
			int _type = LPAREN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// simple.g:366:13: ( '(' )
			// simple.g:366:15: '('
			{
			match('('); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LPAREN"

	// $ANTLR start "RPAREN"
	public final void mRPAREN() throws RecognitionException {
		try {
			int _type = RPAREN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// simple.g:367:13: ( ')' )
			// simple.g:367:15: ')'
			{
			match(')'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RPAREN"

	// $ANTLR start "SEMICOLON"
	public final void mSEMICOLON() throws RecognitionException {
		try {
			int _type = SEMICOLON;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// simple.g:368:13: ( ';' )
			// simple.g:368:15: ';'
			{
			match(';'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SEMICOLON"

	// $ANTLR start "CFLOAT"
	public final void mCFLOAT() throws RecognitionException {
		try {
			int _type = CFLOAT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// simple.g:369:13: ( CINT DOT CINT )
			// simple.g:369:15: CINT DOT CINT
			{
			mCINT(); 

			mDOT(); 

			mCINT(); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CFLOAT"

	// $ANTLR start "CINT"
	public final void mCINT() throws RecognitionException {
		try {
			int _type = CINT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// simple.g:370:13: ( ( '0' .. '9' )+ )
			// simple.g:370:15: ( '0' .. '9' )+
			{
			// simple.g:370:15: ( '0' .. '9' )+
			int cnt1=0;
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( ((LA1_0 >= '0' && LA1_0 <= '9')) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// simple.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt1 >= 1 ) break loop1;
					EarlyExitException eee = new EarlyExitException(1, input);
					throw eee;
				}
				cnt1++;
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CINT"

	// $ANTLR start "ID"
	public final void mID() throws RecognitionException {
		try {
			int _type = ID;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// simple.g:371:13: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
			// simple.g:371:15: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
			{
			if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			// simple.g:371:44: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( ((LA2_0 >= '0' && LA2_0 <= '9')||(LA2_0 >= 'A' && LA2_0 <= 'Z')||LA2_0=='_'||(LA2_0 >= 'a' && LA2_0 <= 'z')) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// simple.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop2;
				}
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ID"

	// $ANTLR start "DOT"
	public final void mDOT() throws RecognitionException {
		try {
			int _type = DOT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// simple.g:372:13: ( '.' )
			// simple.g:372:15: '.'
			{
			match('.'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DOT"

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException {
		try {
			int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// simple.g:373:13: ( ( ' ' | '\\n' | '\\t' | '\\r' )+ )
			// simple.g:373:15: ( ' ' | '\\n' | '\\t' | '\\r' )+
			{
			// simple.g:373:15: ( ' ' | '\\n' | '\\t' | '\\r' )+
			int cnt3=0;
			loop3:
			while (true) {
				int alt3=2;
				int LA3_0 = input.LA(1);
				if ( ((LA3_0 >= '\t' && LA3_0 <= '\n')||LA3_0=='\r'||LA3_0==' ') ) {
					alt3=1;
				}

				switch (alt3) {
				case 1 :
					// simple.g:
					{
					if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt3 >= 1 ) break loop3;
					EarlyExitException eee = new EarlyExitException(3, input);
					throw eee;
				}
				cnt3++;
			}

			 _channel=HIDDEN; 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WS"

	@Override
	public void mTokens() throws RecognitionException {
		// simple.g:1:8: ( T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | CLASS | INT | DOUBLE | BOOLEAN | PUBLIC | PRIVATE | PROTECTED | DO | WHILE | RETURN | OCURLYB | CCURLYB | LPAREN | RPAREN | SEMICOLON | CFLOAT | CINT | ID | DOT | WS )
		int alt4=32;
		alt4 = dfa4.predict(input);
		switch (alt4) {
			case 1 :
				// simple.g:1:10: T__24
				{
				mT__24(); 

				}
				break;
			case 2 :
				// simple.g:1:16: T__25
				{
				mT__25(); 

				}
				break;
			case 3 :
				// simple.g:1:22: T__26
				{
				mT__26(); 

				}
				break;
			case 4 :
				// simple.g:1:28: T__27
				{
				mT__27(); 

				}
				break;
			case 5 :
				// simple.g:1:34: T__28
				{
				mT__28(); 

				}
				break;
			case 6 :
				// simple.g:1:40: T__29
				{
				mT__29(); 

				}
				break;
			case 7 :
				// simple.g:1:46: T__30
				{
				mT__30(); 

				}
				break;
			case 8 :
				// simple.g:1:52: T__31
				{
				mT__31(); 

				}
				break;
			case 9 :
				// simple.g:1:58: T__32
				{
				mT__32(); 

				}
				break;
			case 10 :
				// simple.g:1:64: T__33
				{
				mT__33(); 

				}
				break;
			case 11 :
				// simple.g:1:70: T__34
				{
				mT__34(); 

				}
				break;
			case 12 :
				// simple.g:1:76: T__35
				{
				mT__35(); 

				}
				break;
			case 13 :
				// simple.g:1:82: CLASS
				{
				mCLASS(); 

				}
				break;
			case 14 :
				// simple.g:1:88: INT
				{
				mINT(); 

				}
				break;
			case 15 :
				// simple.g:1:92: DOUBLE
				{
				mDOUBLE(); 

				}
				break;
			case 16 :
				// simple.g:1:99: BOOLEAN
				{
				mBOOLEAN(); 

				}
				break;
			case 17 :
				// simple.g:1:107: PUBLIC
				{
				mPUBLIC(); 

				}
				break;
			case 18 :
				// simple.g:1:114: PRIVATE
				{
				mPRIVATE(); 

				}
				break;
			case 19 :
				// simple.g:1:122: PROTECTED
				{
				mPROTECTED(); 

				}
				break;
			case 20 :
				// simple.g:1:132: DO
				{
				mDO(); 

				}
				break;
			case 21 :
				// simple.g:1:135: WHILE
				{
				mWHILE(); 

				}
				break;
			case 22 :
				// simple.g:1:141: RETURN
				{
				mRETURN(); 

				}
				break;
			case 23 :
				// simple.g:1:148: OCURLYB
				{
				mOCURLYB(); 

				}
				break;
			case 24 :
				// simple.g:1:156: CCURLYB
				{
				mCCURLYB(); 

				}
				break;
			case 25 :
				// simple.g:1:164: LPAREN
				{
				mLPAREN(); 

				}
				break;
			case 26 :
				// simple.g:1:171: RPAREN
				{
				mRPAREN(); 

				}
				break;
			case 27 :
				// simple.g:1:178: SEMICOLON
				{
				mSEMICOLON(); 

				}
				break;
			case 28 :
				// simple.g:1:188: CFLOAT
				{
				mCFLOAT(); 

				}
				break;
			case 29 :
				// simple.g:1:195: CINT
				{
				mCINT(); 

				}
				break;
			case 30 :
				// simple.g:1:200: ID
				{
				mID(); 

				}
				break;
			case 31 :
				// simple.g:1:203: DOT
				{
				mDOT(); 

				}
				break;
			case 32 :
				// simple.g:1:207: WS
				{
				mWS(); 

				}
				break;

		}
	}


	protected DFA4 dfa4 = new DFA4(this);
	static final String DFA4_eotS =
		"\7\uffff\1\33\1\35\1\37\7\27\5\uffff\1\50\11\uffff\2\27\1\55\5\27\2\uffff"+
		"\1\27\1\65\1\27\1\uffff\7\27\1\uffff\7\27\1\105\5\27\1\113\1\27\1\uffff"+
		"\1\115\1\27\1\117\2\27\1\uffff\1\122\1\uffff\1\123\1\uffff\1\124\1\27"+
		"\3\uffff\1\27\1\127\1\uffff";
	static final String DFA4_eofS =
		"\130\uffff";
	static final String DFA4_minS =
		"\1\11\6\uffff\3\75\1\154\1\156\2\157\1\162\1\150\1\145\5\uffff\1\56\11"+
		"\uffff\1\141\1\164\1\60\1\157\1\142\2\151\1\164\2\uffff\1\163\1\60\1\142"+
		"\1\uffff\2\154\1\166\1\164\1\154\1\165\1\163\1\uffff\1\154\1\145\1\151"+
		"\1\141\2\145\1\162\1\60\1\145\1\141\1\143\1\164\1\143\1\60\1\156\1\uffff"+
		"\1\60\1\156\1\60\1\145\1\164\1\uffff\1\60\1\uffff\1\60\1\uffff\1\60\1"+
		"\145\3\uffff\1\144\1\60\1\uffff";
	static final String DFA4_maxS =
		"\1\175\6\uffff\3\75\1\154\1\156\2\157\1\165\1\150\1\145\5\uffff\1\71\11"+
		"\uffff\1\141\1\164\1\172\1\157\1\142\1\157\1\151\1\164\2\uffff\1\163\1"+
		"\172\1\142\1\uffff\2\154\1\166\1\164\1\154\1\165\1\163\1\uffff\1\154\1"+
		"\145\1\151\1\141\2\145\1\162\1\172\1\145\1\141\1\143\1\164\1\143\1\172"+
		"\1\156\1\uffff\1\172\1\156\1\172\1\145\1\164\1\uffff\1\172\1\uffff\1\172"+
		"\1\uffff\1\172\1\145\3\uffff\1\144\1\172\1\uffff";
	static final String DFA4_acceptS =
		"\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\12\uffff\1\27\1\30\1\31\1\32\1\33\1\uffff"+
		"\1\36\1\37\1\40\1\10\1\7\1\12\1\11\1\14\1\13\10\uffff\1\35\1\34\3\uffff"+
		"\1\24\7\uffff\1\16\17\uffff\1\15\5\uffff\1\25\1\uffff\1\17\1\uffff\1\21"+
		"\2\uffff\1\26\1\20\1\22\2\uffff\1\23";
	static final String DFA4_specialS =
		"\130\uffff}>";
	static final String[] DFA4_transitionS = {
			"\2\31\2\uffff\1\31\22\uffff\1\31\1\1\6\uffff\1\23\1\24\1\2\1\3\1\4\1"+
			"\5\1\30\1\6\12\26\1\uffff\1\25\1\7\1\10\1\11\2\uffff\32\27\4\uffff\1"+
			"\27\1\uffff\1\27\1\15\1\12\1\14\4\27\1\13\6\27\1\16\1\27\1\20\4\27\1"+
			"\17\3\27\1\21\1\uffff\1\22",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\32",
			"\1\34",
			"\1\36",
			"\1\40",
			"\1\41",
			"\1\42",
			"\1\43",
			"\1\45\2\uffff\1\44",
			"\1\46",
			"\1\47",
			"",
			"",
			"",
			"",
			"",
			"\1\51\1\uffff\12\26",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\52",
			"\1\53",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\24\27\1\54\5\27",
			"\1\56",
			"\1\57",
			"\1\60\5\uffff\1\61",
			"\1\62",
			"\1\63",
			"",
			"",
			"\1\64",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
			"\1\66",
			"",
			"\1\67",
			"\1\70",
			"\1\71",
			"\1\72",
			"\1\73",
			"\1\74",
			"\1\75",
			"",
			"\1\76",
			"\1\77",
			"\1\100",
			"\1\101",
			"\1\102",
			"\1\103",
			"\1\104",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
			"\1\106",
			"\1\107",
			"\1\110",
			"\1\111",
			"\1\112",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
			"\1\114",
			"",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
			"\1\116",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
			"\1\120",
			"\1\121",
			"",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
			"",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
			"",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
			"\1\125",
			"",
			"",
			"",
			"\1\126",
			"\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
			""
	};

	static final short[] DFA4_eot = DFA.unpackEncodedString(DFA4_eotS);
	static final short[] DFA4_eof = DFA.unpackEncodedString(DFA4_eofS);
	static final char[] DFA4_min = DFA.unpackEncodedStringToUnsignedChars(DFA4_minS);
	static final char[] DFA4_max = DFA.unpackEncodedStringToUnsignedChars(DFA4_maxS);
	static final short[] DFA4_accept = DFA.unpackEncodedString(DFA4_acceptS);
	static final short[] DFA4_special = DFA.unpackEncodedString(DFA4_specialS);
	static final short[][] DFA4_transition;

	static {
		int numStates = DFA4_transitionS.length;
		DFA4_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA4_transition[i] = DFA.unpackEncodedString(DFA4_transitionS[i]);
		}
	}

	protected class DFA4 extends DFA {

		public DFA4(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 4;
			this.eot = DFA4_eot;
			this.eof = DFA4_eof;
			this.min = DFA4_min;
			this.max = DFA4_max;
			this.accept = DFA4_accept;
			this.special = DFA4_special;
			this.transition = DFA4_transition;
		}
		@Override
		public String getDescription() {
			return "1:1: Tokens : ( T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | CLASS | INT | DOUBLE | BOOLEAN | PUBLIC | PRIVATE | PROTECTED | DO | WHILE | RETURN | OCURLYB | CCURLYB | LPAREN | RPAREN | SEMICOLON | CFLOAT | CINT | ID | DOT | WS );";
		}
	}

}
