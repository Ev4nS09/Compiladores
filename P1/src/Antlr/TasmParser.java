// Generated from /home/rio/Compiladores/P1/src/Tasm.g4 by ANTLR 4.13.1
package Antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class TasmParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, TAG=2, BOOL=3, INT=4, DOUBLE=5, STRING=6, ICONST=7, IPRINT=8, 
		IUMINUS=9, IADD=10, ISUB=11, IMULT=12, IDIV=13, IMOD=14, IEQ=15, INEQ=16, 
		ILT=17, ILEQ=18, ITOD=19, ITOS=20, DCONST=21, DPRINT=22, DUMINUS=23, DADD=24, 
		DSUB=25, DMULT=26, DDIV=27, DEQ=28, DNEQ=29, DLT=30, DLEQ=31, DTOS=32, 
		SCONST=33, SPRINT=34, SADD=35, SEQ=36, SNEQ=37, BCONST=38, BPRINT=39, 
		BEQ=40, BNEQ=41, AND=42, OR=43, NOT=44, BTOS=45, JUMP=46, JUMPT=47, JUMPF=48, 
		GALLOC=49, GLOAD=50, GSTORE=51, HALT=52, WS=53;
	public static final int
		RULE_s = 0, RULE_instruction = 1;
	private static String[] makeRuleNames() {
		return new String[] {
			"s", "instruction"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "':'", null, null, null, null, null, "'iconst'", "'iprint'", "'iuminus'", 
			"'iadd'", "'isub'", "'imult'", "'idiv'", "'imod'", "'ieq'", "'ineq'", 
			"'ilt'", "'ileq'", "'itod'", "'itos'", "'dconst'", "'dprint'", "'duminus'", 
			"'dadd'", "'dsub'", "'dmult'", "'ddiv'", "'deq'", "'dneq'", "'dlt'", 
			"'dleq'", "'dtos'", "'sconst'", "'sprint'", "'sadd'", "'seq'", "'sneq'", 
			"'bconst'", "'bprint'", "'beq'", "'bneq'", "'and'", "'or'", "'not'", 
			"'btos'", "'jump'", "'jumpt'", "'jumpf'", "'galloc'", "'gload'", "'gstore'", 
			"'halt'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, "TAG", "BOOL", "INT", "DOUBLE", "STRING", "ICONST", "IPRINT", 
			"IUMINUS", "IADD", "ISUB", "IMULT", "IDIV", "IMOD", "IEQ", "INEQ", "ILT", 
			"ILEQ", "ITOD", "ITOS", "DCONST", "DPRINT", "DUMINUS", "DADD", "DSUB", 
			"DMULT", "DDIV", "DEQ", "DNEQ", "DLT", "DLEQ", "DTOS", "SCONST", "SPRINT", 
			"SADD", "SEQ", "SNEQ", "BCONST", "BPRINT", "BEQ", "BNEQ", "AND", "OR", 
			"NOT", "BTOS", "JUMP", "JUMPT", "JUMPF", "GALLOC", "GLOAD", "GSTORE", 
			"HALT", "WS"
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

	@Override
	public String getGrammarFileName() { return "Tasm.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public TasmParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SContext extends ParserRuleContext {
		public TerminalNode HALT() { return getToken(TasmParser.HALT, 0); }
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public SContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_s; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).enterS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).exitS(this);
		}
	}

	public final SContext s() throws RecognitionException {
		SContext _localctx = new SContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_s);
		int _la;
		try {
			setState(12);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TAG:
			case ICONST:
			case IPRINT:
			case IUMINUS:
			case IADD:
			case ISUB:
			case IMULT:
			case IDIV:
			case IMOD:
			case IEQ:
			case INEQ:
			case ILT:
			case ILEQ:
			case ITOD:
			case ITOS:
			case DCONST:
			case DPRINT:
			case DUMINUS:
			case DADD:
			case DSUB:
			case DMULT:
			case DDIV:
			case DEQ:
			case DNEQ:
			case DLT:
			case DTOS:
			case SCONST:
			case SPRINT:
			case SADD:
			case SEQ:
			case SNEQ:
			case BCONST:
			case BPRINT:
			case BEQ:
			case BNEQ:
			case AND:
			case OR:
			case NOT:
			case BTOS:
			case JUMP:
			case JUMPT:
			case JUMPF:
			case GALLOC:
			case GLOAD:
			case GSTORE:
				enterOuterAlt(_localctx, 1);
				{
				setState(5); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(4);
					instruction();
					}
					}
					setState(7); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 4503597479886724L) != 0) );
				setState(9);
				match(HALT);
				}
				break;
			case HALT:
				enterOuterAlt(_localctx, 2);
				{
				setState(11);
				match(HALT);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InstructionContext extends ParserRuleContext {
		public InstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruction; }
	 
		public InstructionContext() { }
		public void copyFrom(InstructionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PrintContext extends InstructionContext {
		public Token print;
		public TerminalNode IPRINT() { return getToken(TasmParser.IPRINT, 0); }
		public TerminalNode DPRINT() { return getToken(TasmParser.DPRINT, 0); }
		public TerminalNode SPRINT() { return getToken(TasmParser.SPRINT, 0); }
		public TerminalNode BPRINT() { return getToken(TasmParser.BPRINT, 0); }
		public PrintContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).enterPrint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).exitPrint(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ConstContext extends InstructionContext {
		public TerminalNode ICONST() { return getToken(TasmParser.ICONST, 0); }
		public TerminalNode INT() { return getToken(TasmParser.INT, 0); }
		public TerminalNode DCONST() { return getToken(TasmParser.DCONST, 0); }
		public TerminalNode DOUBLE() { return getToken(TasmParser.DOUBLE, 0); }
		public TerminalNode SCONST() { return getToken(TasmParser.SCONST, 0); }
		public TerminalNode STRING() { return getToken(TasmParser.STRING, 0); }
		public TerminalNode BCONST() { return getToken(TasmParser.BCONST, 0); }
		public TerminalNode BOOL() { return getToken(TasmParser.BOOL, 0); }
		public ConstContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).enterConst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).exitConst(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TagContext extends InstructionContext {
		public TerminalNode TAG() { return getToken(TasmParser.TAG, 0); }
		public InstructionContext instruction() {
			return getRuleContext(InstructionContext.class,0);
		}
		public TagContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).enterTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).exitTag(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ChangeContext extends InstructionContext {
		public Token change;
		public TerminalNode ITOD() { return getToken(TasmParser.ITOD, 0); }
		public TerminalNode ITOS() { return getToken(TasmParser.ITOS, 0); }
		public TerminalNode DTOS() { return getToken(TasmParser.DTOS, 0); }
		public TerminalNode BTOS() { return getToken(TasmParser.BTOS, 0); }
		public ChangeContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).enterChange(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).exitChange(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class OperationsContext extends InstructionContext {
		public Token operation;
		public TerminalNode IUMINUS() { return getToken(TasmParser.IUMINUS, 0); }
		public TerminalNode IADD() { return getToken(TasmParser.IADD, 0); }
		public TerminalNode ISUB() { return getToken(TasmParser.ISUB, 0); }
		public TerminalNode IMULT() { return getToken(TasmParser.IMULT, 0); }
		public TerminalNode IDIV() { return getToken(TasmParser.IDIV, 0); }
		public TerminalNode IMOD() { return getToken(TasmParser.IMOD, 0); }
		public TerminalNode DUMINUS() { return getToken(TasmParser.DUMINUS, 0); }
		public TerminalNode DADD() { return getToken(TasmParser.DADD, 0); }
		public TerminalNode DSUB() { return getToken(TasmParser.DSUB, 0); }
		public TerminalNode DMULT() { return getToken(TasmParser.DMULT, 0); }
		public TerminalNode DDIV() { return getToken(TasmParser.DDIV, 0); }
		public TerminalNode SADD() { return getToken(TasmParser.SADD, 0); }
		public OperationsContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).enterOperations(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).exitOperations(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class JumpContext extends InstructionContext {
		public Token jump;
		public TerminalNode TAG() { return getToken(TasmParser.TAG, 0); }
		public TerminalNode JUMP() { return getToken(TasmParser.JUMP, 0); }
		public TerminalNode JUMPF() { return getToken(TasmParser.JUMPF, 0); }
		public TerminalNode JUMPT() { return getToken(TasmParser.JUMPT, 0); }
		public JumpContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).enterJump(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).exitJump(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class GlobalContext extends InstructionContext {
		public Token alloc;
		public TerminalNode INT() { return getToken(TasmParser.INT, 0); }
		public TerminalNode GALLOC() { return getToken(TasmParser.GALLOC, 0); }
		public TerminalNode GLOAD() { return getToken(TasmParser.GLOAD, 0); }
		public TerminalNode GSTORE() { return getToken(TasmParser.GSTORE, 0); }
		public GlobalContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).enterGlobal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).exitGlobal(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ConditionsContext extends InstructionContext {
		public Token condition;
		public TerminalNode IEQ() { return getToken(TasmParser.IEQ, 0); }
		public TerminalNode INEQ() { return getToken(TasmParser.INEQ, 0); }
		public TerminalNode ILT() { return getToken(TasmParser.ILT, 0); }
		public TerminalNode ILEQ() { return getToken(TasmParser.ILEQ, 0); }
		public TerminalNode DEQ() { return getToken(TasmParser.DEQ, 0); }
		public TerminalNode DNEQ() { return getToken(TasmParser.DNEQ, 0); }
		public TerminalNode DLT() { return getToken(TasmParser.DLT, 0); }
		public TerminalNode DTOS() { return getToken(TasmParser.DTOS, 0); }
		public TerminalNode SEQ() { return getToken(TasmParser.SEQ, 0); }
		public TerminalNode SNEQ() { return getToken(TasmParser.SNEQ, 0); }
		public TerminalNode BEQ() { return getToken(TasmParser.BEQ, 0); }
		public TerminalNode BNEQ() { return getToken(TasmParser.BNEQ, 0); }
		public TerminalNode AND() { return getToken(TasmParser.AND, 0); }
		public TerminalNode OR() { return getToken(TasmParser.OR, 0); }
		public TerminalNode NOT() { return getToken(TasmParser.NOT, 0); }
		public ConditionsContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).enterConditions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).exitConditions(this);
		}
	}

	public final InstructionContext instruction() throws RecognitionException {
		InstructionContext _localctx = new InstructionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_instruction);
		int _la;
		try {
			setState(35);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				_localctx = new TagContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(14);
				match(TAG);
				setState(15);
				match(T__0);
				setState(16);
				instruction();
				}
				break;
			case 2:
				_localctx = new ConstContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(25);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ICONST:
					{
					setState(17);
					match(ICONST);
					setState(18);
					match(INT);
					}
					break;
				case DCONST:
					{
					setState(19);
					match(DCONST);
					setState(20);
					match(DOUBLE);
					}
					break;
				case SCONST:
					{
					setState(21);
					match(SCONST);
					setState(22);
					match(STRING);
					}
					break;
				case BCONST:
					{
					setState(23);
					match(BCONST);
					setState(24);
					match(BOOL);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 3:
				_localctx = new GlobalContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(27);
				((GlobalContext)_localctx).alloc = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 3940649673949184L) != 0)) ) {
					((GlobalContext)_localctx).alloc = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(28);
				match(INT);
				}
				break;
			case 4:
				_localctx = new ConditionsContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(29);
				((ConditionsContext)_localctx).condition = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 34297193398272L) != 0)) ) {
					((ConditionsContext)_localctx).condition = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 5:
				_localctx = new ChangeContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(30);
				((ChangeContext)_localctx).change = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 35188668628992L) != 0)) ) {
					((ChangeContext)_localctx).change = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 6:
				_localctx = new OperationsContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(31);
				((OperationsContext)_localctx).operation = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 34619817472L) != 0)) ) {
					((OperationsContext)_localctx).operation = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 7:
				_localctx = new PrintContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(32);
				((PrintContext)_localctx).print = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 566939877632L) != 0)) ) {
					((PrintContext)_localctx).print = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 8:
				_localctx = new JumpContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(33);
				((JumpContext)_localctx).jump = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 492581209243648L) != 0)) ) {
					((JumpContext)_localctx).jump = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(34);
				match(TAG);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u00015&\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0001\u0000"+
		"\u0004\u0000\u0006\b\u0000\u000b\u0000\f\u0000\u0007\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0003\u0000\r\b\u0000\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0003\u0001\u001a\b\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0003\u0001$\b\u0001\u0001\u0001\u0000\u0000\u0002\u0000\u0002"+
		"\u0000\u0006\u0001\u000013\u0005\u0000\u000f\u0012\u001c\u001e  $%(,\u0003"+
		"\u0000\u0013\u0014  --\u0003\u0000\t\u000e\u0017\u001b##\u0004\u0000\b"+
		"\b\u0016\u0016\"\"\'\'\u0001\u0000.0/\u0000\f\u0001\u0000\u0000\u0000"+
		"\u0002#\u0001\u0000\u0000\u0000\u0004\u0006\u0003\u0002\u0001\u0000\u0005"+
		"\u0004\u0001\u0000\u0000\u0000\u0006\u0007\u0001\u0000\u0000\u0000\u0007"+
		"\u0005\u0001\u0000\u0000\u0000\u0007\b\u0001\u0000\u0000\u0000\b\t\u0001"+
		"\u0000\u0000\u0000\t\n\u00054\u0000\u0000\n\r\u0001\u0000\u0000\u0000"+
		"\u000b\r\u00054\u0000\u0000\f\u0005\u0001\u0000\u0000\u0000\f\u000b\u0001"+
		"\u0000\u0000\u0000\r\u0001\u0001\u0000\u0000\u0000\u000e\u000f\u0005\u0002"+
		"\u0000\u0000\u000f\u0010\u0005\u0001\u0000\u0000\u0010$\u0003\u0002\u0001"+
		"\u0000\u0011\u0012\u0005\u0007\u0000\u0000\u0012\u001a\u0005\u0004\u0000"+
		"\u0000\u0013\u0014\u0005\u0015\u0000\u0000\u0014\u001a\u0005\u0005\u0000"+
		"\u0000\u0015\u0016\u0005!\u0000\u0000\u0016\u001a\u0005\u0006\u0000\u0000"+
		"\u0017\u0018\u0005&\u0000\u0000\u0018\u001a\u0005\u0003\u0000\u0000\u0019"+
		"\u0011\u0001\u0000\u0000\u0000\u0019\u0013\u0001\u0000\u0000\u0000\u0019"+
		"\u0015\u0001\u0000\u0000\u0000\u0019\u0017\u0001\u0000\u0000\u0000\u001a"+
		"$\u0001\u0000\u0000\u0000\u001b\u001c\u0007\u0000\u0000\u0000\u001c$\u0005"+
		"\u0004\u0000\u0000\u001d$\u0007\u0001\u0000\u0000\u001e$\u0007\u0002\u0000"+
		"\u0000\u001f$\u0007\u0003\u0000\u0000 $\u0007\u0004\u0000\u0000!\"\u0007"+
		"\u0005\u0000\u0000\"$\u0005\u0002\u0000\u0000#\u000e\u0001\u0000\u0000"+
		"\u0000#\u0019\u0001\u0000\u0000\u0000#\u001b\u0001\u0000\u0000\u0000#"+
		"\u001d\u0001\u0000\u0000\u0000#\u001e\u0001\u0000\u0000\u0000#\u001f\u0001"+
		"\u0000\u0000\u0000# \u0001\u0000\u0000\u0000#!\u0001\u0000\u0000\u0000"+
		"$\u0003\u0001\u0000\u0000\u0000\u0004\u0007\f\u0019#";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}