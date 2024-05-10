// Generated from /home/rio/Compiladores/P4/src/Antlr/Tasm.g4 by ANTLR 4.13.1
package Antlr;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class TasmLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, HALT=6, BOOL=7, INT=8, DOUBLE=9, 
		STRING=10, ICONST=11, IPRINT=12, IUMINUS=13, IADD=14, ISUB=15, IMULT=16, 
		IDIV=17, IMOD=18, IEQ=19, INEQ=20, ILT=21, ILEQ=22, ITOD=23, ITOS=24, 
		DCONST=25, DPRINT=26, DUMINUS=27, DADD=28, DSUB=29, DMULT=30, DDIV=31, 
		DEQ=32, DNEQ=33, DLT=34, DLEQ=35, DTOS=36, SCONST=37, SPRINT=38, SADD=39, 
		SEQ=40, SNEQ=41, TCONST=42, FCONST=43, BPRINT=44, BEQ=45, BNEQ=46, AND=47, 
		OR=48, NOT=49, BTOS=50, JUMP=51, JUMPT=52, JUMPF=53, GALLOC=54, GLOAD=55, 
		GSTORE=56, LALLOC=57, LLOAD=58, LSTORE=59, POP=60, CALL=61, RETVAL=62, 
		RET=63, TAG=64, WS=65;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "HALT", "BOOL", "INT", "DOUBLE", 
			"STRING", "ICONST", "IPRINT", "IUMINUS", "IADD", "ISUB", "IMULT", "IDIV", 
			"IMOD", "IEQ", "INEQ", "ILT", "ILEQ", "ITOD", "ITOS", "DCONST", "DPRINT", 
			"DUMINUS", "DADD", "DSUB", "DMULT", "DDIV", "DEQ", "DNEQ", "DLT", "DLEQ", 
			"DTOS", "SCONST", "SPRINT", "SADD", "SEQ", "SNEQ", "TCONST", "FCONST", 
			"BPRINT", "BEQ", "BNEQ", "AND", "OR", "NOT", "BTOS", "JUMP", "JUMPT", 
			"JUMPF", "GALLOC", "GLOAD", "GSTORE", "LALLOC", "LLOAD", "LSTORE", "POP", 
			"CALL", "RETVAL", "RET", "TAG", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'\\n'", "'\\r'", "','", "':'", "'-'", "'halt'", null, null, null, 
			null, "'iconst'", "'iprint'", "'iuminus'", "'iadd'", "'isub'", "'imult'", 
			"'idiv'", "'imod'", "'ieq'", "'ineq'", "'ilt'", "'ileq'", "'itod'", "'itos'", 
			"'dconst'", "'dprint'", "'duminus'", "'dadd'", "'dsub'", "'dmult'", "'ddiv'", 
			"'deq'", "'dneq'", "'dlt'", "'dleq'", "'dtos'", "'sconst'", "'sprint'", 
			"'sadd'", "'seq'", "'sneq'", "'tconst'", "'fconst'", "'bprint'", "'beq'", 
			"'bneq'", "'and'", "'or'", "'not'", "'btos'", "'jump'", "'jumpt'", "'jumpf'", 
			"'galloc'", "'gload'", "'gstore'", "'lalloc'", "'lload'", "'lstore'", 
			"'pop'", "'call'", "'retval'", "'ret'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, "HALT", "BOOL", "INT", "DOUBLE", 
			"STRING", "ICONST", "IPRINT", "IUMINUS", "IADD", "ISUB", "IMULT", "IDIV", 
			"IMOD", "IEQ", "INEQ", "ILT", "ILEQ", "ITOD", "ITOS", "DCONST", "DPRINT", 
			"DUMINUS", "DADD", "DSUB", "DMULT", "DDIV", "DEQ", "DNEQ", "DLT", "DLEQ", 
			"DTOS", "SCONST", "SPRINT", "SADD", "SEQ", "SNEQ", "TCONST", "FCONST", 
			"BPRINT", "BEQ", "BNEQ", "AND", "OR", "NOT", "BTOS", "JUMP", "JUMPT", 
			"JUMPF", "GALLOC", "GLOAD", "GSTORE", "LALLOC", "LLOAD", "LSTORE", "POP", 
			"CALL", "RETVAL", "RET", "TAG", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public TasmLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Tasm.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000A\u01ed\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002"+
		"\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002"+
		"\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002"+
		"\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002"+
		"\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002"+
		"\u001e\u0007\u001e\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007"+
		"!\u0002\"\u0007\"\u0002#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007"+
		"&\u0002\'\u0007\'\u0002(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007"+
		"+\u0002,\u0007,\u0002-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u0007"+
		"0\u00021\u00071\u00022\u00072\u00023\u00073\u00024\u00074\u00025\u0007"+
		"5\u00026\u00076\u00027\u00077\u00028\u00078\u00029\u00079\u0002:\u0007"+
		":\u0002;\u0007;\u0002<\u0007<\u0002=\u0007=\u0002>\u0007>\u0002?\u0007"+
		"?\u0002@\u0007@\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001"+
		"\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0003\u0006\u009c\b\u0006\u0001\u0007\u0004\u0007\u009f"+
		"\b\u0007\u000b\u0007\f\u0007\u00a0\u0001\b\u0004\b\u00a4\b\b\u000b\b\f"+
		"\b\u00a5\u0001\b\u0001\b\u0004\b\u00aa\b\b\u000b\b\f\b\u00ab\u0003\b\u00ae"+
		"\b\b\u0001\t\u0001\t\u0001\t\u0001\t\u0005\t\u00b4\b\t\n\t\f\t\u00b7\t"+
		"\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001"+
		"\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001"+
		"\u001a\u0001\u001a\u0001\u001a\u0001\u001b\u0001\u001b\u0001\u001b\u0001"+
		"\u001b\u0001\u001b\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001"+
		"\u001c\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001"+
		"\u001d\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001"+
		"\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001 \u0001 \u0001 \u0001"+
		" \u0001 \u0001!\u0001!\u0001!\u0001!\u0001\"\u0001\"\u0001\"\u0001\"\u0001"+
		"\"\u0001#\u0001#\u0001#\u0001#\u0001#\u0001$\u0001$\u0001$\u0001$\u0001"+
		"$\u0001$\u0001$\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001"+
		"&\u0001&\u0001&\u0001&\u0001&\u0001\'\u0001\'\u0001\'\u0001\'\u0001(\u0001"+
		"(\u0001(\u0001(\u0001(\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001"+
		")\u0001*\u0001*\u0001*\u0001*\u0001*\u0001*\u0001*\u0001+\u0001+\u0001"+
		"+\u0001+\u0001+\u0001+\u0001+\u0001,\u0001,\u0001,\u0001,\u0001-\u0001"+
		"-\u0001-\u0001-\u0001-\u0001.\u0001.\u0001.\u0001.\u0001/\u0001/\u0001"+
		"/\u00010\u00010\u00010\u00010\u00011\u00011\u00011\u00011\u00011\u0001"+
		"2\u00012\u00012\u00012\u00012\u00013\u00013\u00013\u00013\u00013\u0001"+
		"3\u00014\u00014\u00014\u00014\u00014\u00014\u00015\u00015\u00015\u0001"+
		"5\u00015\u00015\u00015\u00016\u00016\u00016\u00016\u00016\u00016\u0001"+
		"7\u00017\u00017\u00017\u00017\u00017\u00017\u00018\u00018\u00018\u0001"+
		"8\u00018\u00018\u00018\u00019\u00019\u00019\u00019\u00019\u00019\u0001"+
		":\u0001:\u0001:\u0001:\u0001:\u0001:\u0001:\u0001;\u0001;\u0001;\u0001"+
		";\u0001<\u0001<\u0001<\u0001<\u0001<\u0001=\u0001=\u0001=\u0001=\u0001"+
		"=\u0001=\u0001=\u0001>\u0001>\u0001>\u0001>\u0001?\u0001?\u0005?\u01e2"+
		"\b?\n?\f?\u01e5\t?\u0001@\u0004@\u01e8\b@\u000b@\f@\u01e9\u0001@\u0001"+
		"@\u0001\u00b5\u0000A\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t"+
		"\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f"+
		"\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013\'\u0014"+
		")\u0015+\u0016-\u0017/\u00181\u00193\u001a5\u001b7\u001c9\u001d;\u001e"+
		"=\u001f? A!C\"E#G$I%K&M\'O(Q)S*U+W,Y-[.]/_0a1c2e3g4i5k6m7o8q9s:u;w<y="+
		"{>}?\u007f@\u0081A\u0001\u0000\u0004\u0001\u000009\u0003\u0000AZ__az\u0005"+
		"\u0000--09AZ__az\u0003\u0000\t\n\r\r  \u01f5\u0000\u0001\u0001\u0000\u0000"+
		"\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000"+
		"\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000"+
		"\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000"+
		"\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000"+
		"\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000"+
		"\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000"+
		"\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000"+
		"\u001f\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001"+
		"\u0000\u0000\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000"+
		"\u0000\u0000\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000"+
		"\u0000-\u0001\u0000\u0000\u0000\u0000/\u0001\u0000\u0000\u0000\u00001"+
		"\u0001\u0000\u0000\u0000\u00003\u0001\u0000\u0000\u0000\u00005\u0001\u0000"+
		"\u0000\u0000\u00007\u0001\u0000\u0000\u0000\u00009\u0001\u0000\u0000\u0000"+
		"\u0000;\u0001\u0000\u0000\u0000\u0000=\u0001\u0000\u0000\u0000\u0000?"+
		"\u0001\u0000\u0000\u0000\u0000A\u0001\u0000\u0000\u0000\u0000C\u0001\u0000"+
		"\u0000\u0000\u0000E\u0001\u0000\u0000\u0000\u0000G\u0001\u0000\u0000\u0000"+
		"\u0000I\u0001\u0000\u0000\u0000\u0000K\u0001\u0000\u0000\u0000\u0000M"+
		"\u0001\u0000\u0000\u0000\u0000O\u0001\u0000\u0000\u0000\u0000Q\u0001\u0000"+
		"\u0000\u0000\u0000S\u0001\u0000\u0000\u0000\u0000U\u0001\u0000\u0000\u0000"+
		"\u0000W\u0001\u0000\u0000\u0000\u0000Y\u0001\u0000\u0000\u0000\u0000["+
		"\u0001\u0000\u0000\u0000\u0000]\u0001\u0000\u0000\u0000\u0000_\u0001\u0000"+
		"\u0000\u0000\u0000a\u0001\u0000\u0000\u0000\u0000c\u0001\u0000\u0000\u0000"+
		"\u0000e\u0001\u0000\u0000\u0000\u0000g\u0001\u0000\u0000\u0000\u0000i"+
		"\u0001\u0000\u0000\u0000\u0000k\u0001\u0000\u0000\u0000\u0000m\u0001\u0000"+
		"\u0000\u0000\u0000o\u0001\u0000\u0000\u0000\u0000q\u0001\u0000\u0000\u0000"+
		"\u0000s\u0001\u0000\u0000\u0000\u0000u\u0001\u0000\u0000\u0000\u0000w"+
		"\u0001\u0000\u0000\u0000\u0000y\u0001\u0000\u0000\u0000\u0000{\u0001\u0000"+
		"\u0000\u0000\u0000}\u0001\u0000\u0000\u0000\u0000\u007f\u0001\u0000\u0000"+
		"\u0000\u0000\u0081\u0001\u0000\u0000\u0000\u0001\u0083\u0001\u0000\u0000"+
		"\u0000\u0003\u0085\u0001\u0000\u0000\u0000\u0005\u0087\u0001\u0000\u0000"+
		"\u0000\u0007\u0089\u0001\u0000\u0000\u0000\t\u008b\u0001\u0000\u0000\u0000"+
		"\u000b\u008d\u0001\u0000\u0000\u0000\r\u009b\u0001\u0000\u0000\u0000\u000f"+
		"\u009e\u0001\u0000\u0000\u0000\u0011\u00a3\u0001\u0000\u0000\u0000\u0013"+
		"\u00af\u0001\u0000\u0000\u0000\u0015\u00ba\u0001\u0000\u0000\u0000\u0017"+
		"\u00c1\u0001\u0000\u0000\u0000\u0019\u00c8\u0001\u0000\u0000\u0000\u001b"+
		"\u00d0\u0001\u0000\u0000\u0000\u001d\u00d5\u0001\u0000\u0000\u0000\u001f"+
		"\u00da\u0001\u0000\u0000\u0000!\u00e0\u0001\u0000\u0000\u0000#\u00e5\u0001"+
		"\u0000\u0000\u0000%\u00ea\u0001\u0000\u0000\u0000\'\u00ee\u0001\u0000"+
		"\u0000\u0000)\u00f3\u0001\u0000\u0000\u0000+\u00f7\u0001\u0000\u0000\u0000"+
		"-\u00fc\u0001\u0000\u0000\u0000/\u0101\u0001\u0000\u0000\u00001\u0106"+
		"\u0001\u0000\u0000\u00003\u010d\u0001\u0000\u0000\u00005\u0114\u0001\u0000"+
		"\u0000\u00007\u011c\u0001\u0000\u0000\u00009\u0121\u0001\u0000\u0000\u0000"+
		";\u0126\u0001\u0000\u0000\u0000=\u012c\u0001\u0000\u0000\u0000?\u0131"+
		"\u0001\u0000\u0000\u0000A\u0135\u0001\u0000\u0000\u0000C\u013a\u0001\u0000"+
		"\u0000\u0000E\u013e\u0001\u0000\u0000\u0000G\u0143\u0001\u0000\u0000\u0000"+
		"I\u0148\u0001\u0000\u0000\u0000K\u014f\u0001\u0000\u0000\u0000M\u0156"+
		"\u0001\u0000\u0000\u0000O\u015b\u0001\u0000\u0000\u0000Q\u015f\u0001\u0000"+
		"\u0000\u0000S\u0164\u0001\u0000\u0000\u0000U\u016b\u0001\u0000\u0000\u0000"+
		"W\u0172\u0001\u0000\u0000\u0000Y\u0179\u0001\u0000\u0000\u0000[\u017d"+
		"\u0001\u0000\u0000\u0000]\u0182\u0001\u0000\u0000\u0000_\u0186\u0001\u0000"+
		"\u0000\u0000a\u0189\u0001\u0000\u0000\u0000c\u018d\u0001\u0000\u0000\u0000"+
		"e\u0192\u0001\u0000\u0000\u0000g\u0197\u0001\u0000\u0000\u0000i\u019d"+
		"\u0001\u0000\u0000\u0000k\u01a3\u0001\u0000\u0000\u0000m\u01aa\u0001\u0000"+
		"\u0000\u0000o\u01b0\u0001\u0000\u0000\u0000q\u01b7\u0001\u0000\u0000\u0000"+
		"s\u01be\u0001\u0000\u0000\u0000u\u01c4\u0001\u0000\u0000\u0000w\u01cb"+
		"\u0001\u0000\u0000\u0000y\u01cf\u0001\u0000\u0000\u0000{\u01d4\u0001\u0000"+
		"\u0000\u0000}\u01db\u0001\u0000\u0000\u0000\u007f\u01df\u0001\u0000\u0000"+
		"\u0000\u0081\u01e7\u0001\u0000\u0000\u0000\u0083\u0084\u0005\n\u0000\u0000"+
		"\u0084\u0002\u0001\u0000\u0000\u0000\u0085\u0086\u0005\r\u0000\u0000\u0086"+
		"\u0004\u0001\u0000\u0000\u0000\u0087\u0088\u0005,\u0000\u0000\u0088\u0006"+
		"\u0001\u0000\u0000\u0000\u0089\u008a\u0005:\u0000\u0000\u008a\b\u0001"+
		"\u0000\u0000\u0000\u008b\u008c\u0005-\u0000\u0000\u008c\n\u0001\u0000"+
		"\u0000\u0000\u008d\u008e\u0005h\u0000\u0000\u008e\u008f\u0005a\u0000\u0000"+
		"\u008f\u0090\u0005l\u0000\u0000\u0090\u0091\u0005t\u0000\u0000\u0091\f"+
		"\u0001\u0000\u0000\u0000\u0092\u0093\u0005t\u0000\u0000\u0093\u0094\u0005"+
		"r\u0000\u0000\u0094\u0095\u0005u\u0000\u0000\u0095\u009c\u0005e\u0000"+
		"\u0000\u0096\u0097\u0005f\u0000\u0000\u0097\u0098\u0005a\u0000\u0000\u0098"+
		"\u0099\u0005l\u0000\u0000\u0099\u009a\u0005s\u0000\u0000\u009a\u009c\u0005"+
		"e\u0000\u0000\u009b\u0092\u0001\u0000\u0000\u0000\u009b\u0096\u0001\u0000"+
		"\u0000\u0000\u009c\u000e\u0001\u0000\u0000\u0000\u009d\u009f\u0007\u0000"+
		"\u0000\u0000\u009e\u009d\u0001\u0000\u0000\u0000\u009f\u00a0\u0001\u0000"+
		"\u0000\u0000\u00a0\u009e\u0001\u0000\u0000\u0000\u00a0\u00a1\u0001\u0000"+
		"\u0000\u0000\u00a1\u0010\u0001\u0000\u0000\u0000\u00a2\u00a4\u0007\u0000"+
		"\u0000\u0000\u00a3\u00a2\u0001\u0000\u0000\u0000\u00a4\u00a5\u0001\u0000"+
		"\u0000\u0000\u00a5\u00a3\u0001\u0000\u0000\u0000\u00a5\u00a6\u0001\u0000"+
		"\u0000\u0000\u00a6\u00ad\u0001\u0000\u0000\u0000\u00a7\u00a9\u0005.\u0000"+
		"\u0000\u00a8\u00aa\u0007\u0000\u0000\u0000\u00a9\u00a8\u0001\u0000\u0000"+
		"\u0000\u00aa\u00ab\u0001\u0000\u0000\u0000\u00ab\u00a9\u0001\u0000\u0000"+
		"\u0000\u00ab\u00ac\u0001\u0000\u0000\u0000\u00ac\u00ae\u0001\u0000\u0000"+
		"\u0000\u00ad\u00a7\u0001\u0000\u0000\u0000\u00ad\u00ae\u0001\u0000\u0000"+
		"\u0000\u00ae\u0012\u0001\u0000\u0000\u0000\u00af\u00b5\u0005\"\u0000\u0000"+
		"\u00b0\u00b1\u0005\\\u0000\u0000\u00b1\u00b4\u0005\"\u0000\u0000\u00b2"+
		"\u00b4\t\u0000\u0000\u0000\u00b3\u00b0\u0001\u0000\u0000\u0000\u00b3\u00b2"+
		"\u0001\u0000\u0000\u0000\u00b4\u00b7\u0001\u0000\u0000\u0000\u00b5\u00b6"+
		"\u0001\u0000\u0000\u0000\u00b5\u00b3\u0001\u0000\u0000\u0000\u00b6\u00b8"+
		"\u0001\u0000\u0000\u0000\u00b7\u00b5\u0001\u0000\u0000\u0000\u00b8\u00b9"+
		"\u0005\"\u0000\u0000\u00b9\u0014\u0001\u0000\u0000\u0000\u00ba\u00bb\u0005"+
		"i\u0000\u0000\u00bb\u00bc\u0005c\u0000\u0000\u00bc\u00bd\u0005o\u0000"+
		"\u0000\u00bd\u00be\u0005n\u0000\u0000\u00be\u00bf\u0005s\u0000\u0000\u00bf"+
		"\u00c0\u0005t\u0000\u0000\u00c0\u0016\u0001\u0000\u0000\u0000\u00c1\u00c2"+
		"\u0005i\u0000\u0000\u00c2\u00c3\u0005p\u0000\u0000\u00c3\u00c4\u0005r"+
		"\u0000\u0000\u00c4\u00c5\u0005i\u0000\u0000\u00c5\u00c6\u0005n\u0000\u0000"+
		"\u00c6\u00c7\u0005t\u0000\u0000\u00c7\u0018\u0001\u0000\u0000\u0000\u00c8"+
		"\u00c9\u0005i\u0000\u0000\u00c9\u00ca\u0005u\u0000\u0000\u00ca\u00cb\u0005"+
		"m\u0000\u0000\u00cb\u00cc\u0005i\u0000\u0000\u00cc\u00cd\u0005n\u0000"+
		"\u0000\u00cd\u00ce\u0005u\u0000\u0000\u00ce\u00cf\u0005s\u0000\u0000\u00cf"+
		"\u001a\u0001\u0000\u0000\u0000\u00d0\u00d1\u0005i\u0000\u0000\u00d1\u00d2"+
		"\u0005a\u0000\u0000\u00d2\u00d3\u0005d\u0000\u0000\u00d3\u00d4\u0005d"+
		"\u0000\u0000\u00d4\u001c\u0001\u0000\u0000\u0000\u00d5\u00d6\u0005i\u0000"+
		"\u0000\u00d6\u00d7\u0005s\u0000\u0000\u00d7\u00d8\u0005u\u0000\u0000\u00d8"+
		"\u00d9\u0005b\u0000\u0000\u00d9\u001e\u0001\u0000\u0000\u0000\u00da\u00db"+
		"\u0005i\u0000\u0000\u00db\u00dc\u0005m\u0000\u0000\u00dc\u00dd\u0005u"+
		"\u0000\u0000\u00dd\u00de\u0005l\u0000\u0000\u00de\u00df\u0005t\u0000\u0000"+
		"\u00df \u0001\u0000\u0000\u0000\u00e0\u00e1\u0005i\u0000\u0000\u00e1\u00e2"+
		"\u0005d\u0000\u0000\u00e2\u00e3\u0005i\u0000\u0000\u00e3\u00e4\u0005v"+
		"\u0000\u0000\u00e4\"\u0001\u0000\u0000\u0000\u00e5\u00e6\u0005i\u0000"+
		"\u0000\u00e6\u00e7\u0005m\u0000\u0000\u00e7\u00e8\u0005o\u0000\u0000\u00e8"+
		"\u00e9\u0005d\u0000\u0000\u00e9$\u0001\u0000\u0000\u0000\u00ea\u00eb\u0005"+
		"i\u0000\u0000\u00eb\u00ec\u0005e\u0000\u0000\u00ec\u00ed\u0005q\u0000"+
		"\u0000\u00ed&\u0001\u0000\u0000\u0000\u00ee\u00ef\u0005i\u0000\u0000\u00ef"+
		"\u00f0\u0005n\u0000\u0000\u00f0\u00f1\u0005e\u0000\u0000\u00f1\u00f2\u0005"+
		"q\u0000\u0000\u00f2(\u0001\u0000\u0000\u0000\u00f3\u00f4\u0005i\u0000"+
		"\u0000\u00f4\u00f5\u0005l\u0000\u0000\u00f5\u00f6\u0005t\u0000\u0000\u00f6"+
		"*\u0001\u0000\u0000\u0000\u00f7\u00f8\u0005i\u0000\u0000\u00f8\u00f9\u0005"+
		"l\u0000\u0000\u00f9\u00fa\u0005e\u0000\u0000\u00fa\u00fb\u0005q\u0000"+
		"\u0000\u00fb,\u0001\u0000\u0000\u0000\u00fc\u00fd\u0005i\u0000\u0000\u00fd"+
		"\u00fe\u0005t\u0000\u0000\u00fe\u00ff\u0005o\u0000\u0000\u00ff\u0100\u0005"+
		"d\u0000\u0000\u0100.\u0001\u0000\u0000\u0000\u0101\u0102\u0005i\u0000"+
		"\u0000\u0102\u0103\u0005t\u0000\u0000\u0103\u0104\u0005o\u0000\u0000\u0104"+
		"\u0105\u0005s\u0000\u0000\u01050\u0001\u0000\u0000\u0000\u0106\u0107\u0005"+
		"d\u0000\u0000\u0107\u0108\u0005c\u0000\u0000\u0108\u0109\u0005o\u0000"+
		"\u0000\u0109\u010a\u0005n\u0000\u0000\u010a\u010b\u0005s\u0000\u0000\u010b"+
		"\u010c\u0005t\u0000\u0000\u010c2\u0001\u0000\u0000\u0000\u010d\u010e\u0005"+
		"d\u0000\u0000\u010e\u010f\u0005p\u0000\u0000\u010f\u0110\u0005r\u0000"+
		"\u0000\u0110\u0111\u0005i\u0000\u0000\u0111\u0112\u0005n\u0000\u0000\u0112"+
		"\u0113\u0005t\u0000\u0000\u01134\u0001\u0000\u0000\u0000\u0114\u0115\u0005"+
		"d\u0000\u0000\u0115\u0116\u0005u\u0000\u0000\u0116\u0117\u0005m\u0000"+
		"\u0000\u0117\u0118\u0005i\u0000\u0000\u0118\u0119\u0005n\u0000\u0000\u0119"+
		"\u011a\u0005u\u0000\u0000\u011a\u011b\u0005s\u0000\u0000\u011b6\u0001"+
		"\u0000\u0000\u0000\u011c\u011d\u0005d\u0000\u0000\u011d\u011e\u0005a\u0000"+
		"\u0000\u011e\u011f\u0005d\u0000\u0000\u011f\u0120\u0005d\u0000\u0000\u0120"+
		"8\u0001\u0000\u0000\u0000\u0121\u0122\u0005d\u0000\u0000\u0122\u0123\u0005"+
		"s\u0000\u0000\u0123\u0124\u0005u\u0000\u0000\u0124\u0125\u0005b\u0000"+
		"\u0000\u0125:\u0001\u0000\u0000\u0000\u0126\u0127\u0005d\u0000\u0000\u0127"+
		"\u0128\u0005m\u0000\u0000\u0128\u0129\u0005u\u0000\u0000\u0129\u012a\u0005"+
		"l\u0000\u0000\u012a\u012b\u0005t\u0000\u0000\u012b<\u0001\u0000\u0000"+
		"\u0000\u012c\u012d\u0005d\u0000\u0000\u012d\u012e\u0005d\u0000\u0000\u012e"+
		"\u012f\u0005i\u0000\u0000\u012f\u0130\u0005v\u0000\u0000\u0130>\u0001"+
		"\u0000\u0000\u0000\u0131\u0132\u0005d\u0000\u0000\u0132\u0133\u0005e\u0000"+
		"\u0000\u0133\u0134\u0005q\u0000\u0000\u0134@\u0001\u0000\u0000\u0000\u0135"+
		"\u0136\u0005d\u0000\u0000\u0136\u0137\u0005n\u0000\u0000\u0137\u0138\u0005"+
		"e\u0000\u0000\u0138\u0139\u0005q\u0000\u0000\u0139B\u0001\u0000\u0000"+
		"\u0000\u013a\u013b\u0005d\u0000\u0000\u013b\u013c\u0005l\u0000\u0000\u013c"+
		"\u013d\u0005t\u0000\u0000\u013dD\u0001\u0000\u0000\u0000\u013e\u013f\u0005"+
		"d\u0000\u0000\u013f\u0140\u0005l\u0000\u0000\u0140\u0141\u0005e\u0000"+
		"\u0000\u0141\u0142\u0005q\u0000\u0000\u0142F\u0001\u0000\u0000\u0000\u0143"+
		"\u0144\u0005d\u0000\u0000\u0144\u0145\u0005t\u0000\u0000\u0145\u0146\u0005"+
		"o\u0000\u0000\u0146\u0147\u0005s\u0000\u0000\u0147H\u0001\u0000\u0000"+
		"\u0000\u0148\u0149\u0005s\u0000\u0000\u0149\u014a\u0005c\u0000\u0000\u014a"+
		"\u014b\u0005o\u0000\u0000\u014b\u014c\u0005n\u0000\u0000\u014c\u014d\u0005"+
		"s\u0000\u0000\u014d\u014e\u0005t\u0000\u0000\u014eJ\u0001\u0000\u0000"+
		"\u0000\u014f\u0150\u0005s\u0000\u0000\u0150\u0151\u0005p\u0000\u0000\u0151"+
		"\u0152\u0005r\u0000\u0000\u0152\u0153\u0005i\u0000\u0000\u0153\u0154\u0005"+
		"n\u0000\u0000\u0154\u0155\u0005t\u0000\u0000\u0155L\u0001\u0000\u0000"+
		"\u0000\u0156\u0157\u0005s\u0000\u0000\u0157\u0158\u0005a\u0000\u0000\u0158"+
		"\u0159\u0005d\u0000\u0000\u0159\u015a\u0005d\u0000\u0000\u015aN\u0001"+
		"\u0000\u0000\u0000\u015b\u015c\u0005s\u0000\u0000\u015c\u015d\u0005e\u0000"+
		"\u0000\u015d\u015e\u0005q\u0000\u0000\u015eP\u0001\u0000\u0000\u0000\u015f"+
		"\u0160\u0005s\u0000\u0000\u0160\u0161\u0005n\u0000\u0000\u0161\u0162\u0005"+
		"e\u0000\u0000\u0162\u0163\u0005q\u0000\u0000\u0163R\u0001\u0000\u0000"+
		"\u0000\u0164\u0165\u0005t\u0000\u0000\u0165\u0166\u0005c\u0000\u0000\u0166"+
		"\u0167\u0005o\u0000\u0000\u0167\u0168\u0005n\u0000\u0000\u0168\u0169\u0005"+
		"s\u0000\u0000\u0169\u016a\u0005t\u0000\u0000\u016aT\u0001\u0000\u0000"+
		"\u0000\u016b\u016c\u0005f\u0000\u0000\u016c\u016d\u0005c\u0000\u0000\u016d"+
		"\u016e\u0005o\u0000\u0000\u016e\u016f\u0005n\u0000\u0000\u016f\u0170\u0005"+
		"s\u0000\u0000\u0170\u0171\u0005t\u0000\u0000\u0171V\u0001\u0000\u0000"+
		"\u0000\u0172\u0173\u0005b\u0000\u0000\u0173\u0174\u0005p\u0000\u0000\u0174"+
		"\u0175\u0005r\u0000\u0000\u0175\u0176\u0005i\u0000\u0000\u0176\u0177\u0005"+
		"n\u0000\u0000\u0177\u0178\u0005t\u0000\u0000\u0178X\u0001\u0000\u0000"+
		"\u0000\u0179\u017a\u0005b\u0000\u0000\u017a\u017b\u0005e\u0000\u0000\u017b"+
		"\u017c\u0005q\u0000\u0000\u017cZ\u0001\u0000\u0000\u0000\u017d\u017e\u0005"+
		"b\u0000\u0000\u017e\u017f\u0005n\u0000\u0000\u017f\u0180\u0005e\u0000"+
		"\u0000\u0180\u0181\u0005q\u0000\u0000\u0181\\\u0001\u0000\u0000\u0000"+
		"\u0182\u0183\u0005a\u0000\u0000\u0183\u0184\u0005n\u0000\u0000\u0184\u0185"+
		"\u0005d\u0000\u0000\u0185^\u0001\u0000\u0000\u0000\u0186\u0187\u0005o"+
		"\u0000\u0000\u0187\u0188\u0005r\u0000\u0000\u0188`\u0001\u0000\u0000\u0000"+
		"\u0189\u018a\u0005n\u0000\u0000\u018a\u018b\u0005o\u0000\u0000\u018b\u018c"+
		"\u0005t\u0000\u0000\u018cb\u0001\u0000\u0000\u0000\u018d\u018e\u0005b"+
		"\u0000\u0000\u018e\u018f\u0005t\u0000\u0000\u018f\u0190\u0005o\u0000\u0000"+
		"\u0190\u0191\u0005s\u0000\u0000\u0191d\u0001\u0000\u0000\u0000\u0192\u0193"+
		"\u0005j\u0000\u0000\u0193\u0194\u0005u\u0000\u0000\u0194\u0195\u0005m"+
		"\u0000\u0000\u0195\u0196\u0005p\u0000\u0000\u0196f\u0001\u0000\u0000\u0000"+
		"\u0197\u0198\u0005j\u0000\u0000\u0198\u0199\u0005u\u0000\u0000\u0199\u019a"+
		"\u0005m\u0000\u0000\u019a\u019b\u0005p\u0000\u0000\u019b\u019c\u0005t"+
		"\u0000\u0000\u019ch\u0001\u0000\u0000\u0000\u019d\u019e\u0005j\u0000\u0000"+
		"\u019e\u019f\u0005u\u0000\u0000\u019f\u01a0\u0005m\u0000\u0000\u01a0\u01a1"+
		"\u0005p\u0000\u0000\u01a1\u01a2\u0005f\u0000\u0000\u01a2j\u0001\u0000"+
		"\u0000\u0000\u01a3\u01a4\u0005g\u0000\u0000\u01a4\u01a5\u0005a\u0000\u0000"+
		"\u01a5\u01a6\u0005l\u0000\u0000\u01a6\u01a7\u0005l\u0000\u0000\u01a7\u01a8"+
		"\u0005o\u0000\u0000\u01a8\u01a9\u0005c\u0000\u0000\u01a9l\u0001\u0000"+
		"\u0000\u0000\u01aa\u01ab\u0005g\u0000\u0000\u01ab\u01ac\u0005l\u0000\u0000"+
		"\u01ac\u01ad\u0005o\u0000\u0000\u01ad\u01ae\u0005a\u0000\u0000\u01ae\u01af"+
		"\u0005d\u0000\u0000\u01afn\u0001\u0000\u0000\u0000\u01b0\u01b1\u0005g"+
		"\u0000\u0000\u01b1\u01b2\u0005s\u0000\u0000\u01b2\u01b3\u0005t\u0000\u0000"+
		"\u01b3\u01b4\u0005o\u0000\u0000\u01b4\u01b5\u0005r\u0000\u0000\u01b5\u01b6"+
		"\u0005e\u0000\u0000\u01b6p\u0001\u0000\u0000\u0000\u01b7\u01b8\u0005l"+
		"\u0000\u0000\u01b8\u01b9\u0005a\u0000\u0000\u01b9\u01ba\u0005l\u0000\u0000"+
		"\u01ba\u01bb\u0005l\u0000\u0000\u01bb\u01bc\u0005o\u0000\u0000\u01bc\u01bd"+
		"\u0005c\u0000\u0000\u01bdr\u0001\u0000\u0000\u0000\u01be\u01bf\u0005l"+
		"\u0000\u0000\u01bf\u01c0\u0005l\u0000\u0000\u01c0\u01c1\u0005o\u0000\u0000"+
		"\u01c1\u01c2\u0005a\u0000\u0000\u01c2\u01c3\u0005d\u0000\u0000\u01c3t"+
		"\u0001\u0000\u0000\u0000\u01c4\u01c5\u0005l\u0000\u0000\u01c5\u01c6\u0005"+
		"s\u0000\u0000\u01c6\u01c7\u0005t\u0000\u0000\u01c7\u01c8\u0005o\u0000"+
		"\u0000\u01c8\u01c9\u0005r\u0000\u0000\u01c9\u01ca\u0005e\u0000\u0000\u01ca"+
		"v\u0001\u0000\u0000\u0000\u01cb\u01cc\u0005p\u0000\u0000\u01cc\u01cd\u0005"+
		"o\u0000\u0000\u01cd\u01ce\u0005p\u0000\u0000\u01cex\u0001\u0000\u0000"+
		"\u0000\u01cf\u01d0\u0005c\u0000\u0000\u01d0\u01d1\u0005a\u0000\u0000\u01d1"+
		"\u01d2\u0005l\u0000\u0000\u01d2\u01d3\u0005l\u0000\u0000\u01d3z\u0001"+
		"\u0000\u0000\u0000\u01d4\u01d5\u0005r\u0000\u0000\u01d5\u01d6\u0005e\u0000"+
		"\u0000\u01d6\u01d7\u0005t\u0000\u0000\u01d7\u01d8\u0005v\u0000\u0000\u01d8"+
		"\u01d9\u0005a\u0000\u0000\u01d9\u01da\u0005l\u0000\u0000\u01da|\u0001"+
		"\u0000\u0000\u0000\u01db\u01dc\u0005r\u0000\u0000\u01dc\u01dd\u0005e\u0000"+
		"\u0000\u01dd\u01de\u0005t\u0000\u0000\u01de~\u0001\u0000\u0000\u0000\u01df"+
		"\u01e3\u0007\u0001\u0000\u0000\u01e0\u01e2\u0007\u0002\u0000\u0000\u01e1"+
		"\u01e0\u0001\u0000\u0000\u0000\u01e2\u01e5\u0001\u0000\u0000\u0000\u01e3"+
		"\u01e1\u0001\u0000\u0000\u0000\u01e3\u01e4\u0001\u0000\u0000\u0000\u01e4"+
		"\u0080\u0001\u0000\u0000\u0000\u01e5\u01e3\u0001\u0000\u0000\u0000\u01e6"+
		"\u01e8\u0007\u0003\u0000\u0000\u01e7\u01e6\u0001\u0000\u0000\u0000\u01e8"+
		"\u01e9\u0001\u0000\u0000\u0000\u01e9\u01e7\u0001\u0000\u0000\u0000\u01e9"+
		"\u01ea\u0001\u0000\u0000\u0000\u01ea\u01eb\u0001\u0000\u0000\u0000\u01eb"+
		"\u01ec\u0006@\u0000\u0000\u01ec\u0082\u0001\u0000\u0000\u0000\n\u0000"+
		"\u009b\u00a0\u00a5\u00ab\u00ad\u00b3\u00b5\u01e3\u01e9\u0001\u0006\u0000"+
		"\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}