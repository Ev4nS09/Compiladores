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
		T__0=1, HALT=2, BOOL=3, INT=4, DOUBLE=5, STRING=6, TAG=7, ICONST=8, IPRINT=9, 
		IUMINUS=10, IADD=11, ISUB=12, IMULT=13, IDIV=14, IMOD=15, IEQ=16, INEQ=17, 
		ILT=18, ILEQ=19, ITOD=20, ITOS=21, DCONST=22, DPRINT=23, DUMINUS=24, DADD=25, 
		DSUB=26, DMULT=27, DDIV=28, DEQ=29, DNEQ=30, DLT=31, DLEQ=32, DTOS=33, 
		SCONST=34, SPRINT=35, SADD=36, SEQ=37, SNEQ=38, BCONST=39, BPRINT=40, 
		BEQ=41, BNEQ=42, AND=43, OR=44, NOT=45, BTOS=46, JUMP=47, JUMPT=48, JUMPF=49, 
		GALLOC=50, GLOAD=51, GSTORE=52, WS=53;
	public static final int
		RULE_tasm = 0, RULE_instruction = 1;
	private static String[] makeRuleNames() {
		return new String[] {
			"tasm", "instruction"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'\\n'", "'halt'", null, null, null, null, null, "'iconst'", "'iprint'", 
			"'iuminus'", "'iadd'", "'isub'", "'imult'", "'idiv'", "'imod'", "'ieq'", 
			"'ineq'", "'ilt'", "'ileq'", "'itod'", "'itos'", "'dconst'", "'dprint'", 
			"'duminus'", "'dadd'", "'dsub'", "'dmult'", "'ddiv'", "'deq'", "'dneq'", 
			"'dlt'", "'dleq'", "'dtos'", "'sconst'", "'sprint'", "'sadd'", "'seq'", 
			"'sneq'", "'bconst'", "'bprint'", "'beq'", "'bneq'", "'and'", "'or'", 
			"'not'", "'btos'", "'jump'", "'jumpt'", "'jumpf'", "'galloc'", "'gload'", 
			"'gstore'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, "HALT", "BOOL", "INT", "DOUBLE", "STRING", "TAG", "ICONST", 
			"IPRINT", "IUMINUS", "IADD", "ISUB", "IMULT", "IDIV", "IMOD", "IEQ", 
			"INEQ", "ILT", "ILEQ", "ITOD", "ITOS", "DCONST", "DPRINT", "DUMINUS", 
			"DADD", "DSUB", "DMULT", "DDIV", "DEQ", "DNEQ", "DLT", "DLEQ", "DTOS", 
			"SCONST", "SPRINT", "SADD", "SEQ", "SNEQ", "BCONST", "BPRINT", "BEQ", 
			"BNEQ", "AND", "OR", "NOT", "BTOS", "JUMP", "JUMPT", "JUMPF", "GALLOC", 
			"GLOAD", "GSTORE", "WS"
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
	public static class TasmContext extends ParserRuleContext {
		public TerminalNode HALT() { return getToken(TasmParser.HALT, 0); }
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public TasmContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tasm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).enterTasm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).exitTasm(this);
		}
	}

	public final TasmContext tasm() throws RecognitionException {
		TasmContext _localctx = new TasmContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_tasm);
		int _la;
		try {
			setState(14);
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
				setState(7); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(4);
					instruction();
					setState(5);
					match(T__0);
					}
					}
					setState(9); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 9007194959773568L) != 0) );
				setState(11);
				match(HALT);
				}
				break;
			case HALT:
				enterOuterAlt(_localctx, 2);
				{
				setState(13);
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
	public static class TagaContext extends InstructionContext {
		public Token tag;
		public InstructionContext instruction() {
			return getRuleContext(InstructionContext.class,0);
		}
		public TerminalNode TAG() { return getToken(TasmParser.TAG, 0); }
		public TagaContext(InstructionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).enterTaga(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).exitTaga(this);
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
			setState(36);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				_localctx = new ConstContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(24);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ICONST:
					{
					setState(16);
					match(ICONST);
					setState(17);
					match(INT);
					}
					break;
				case DCONST:
					{
					setState(18);
					match(DCONST);
					setState(19);
					match(DOUBLE);
					}
					break;
				case SCONST:
					{
					setState(20);
					match(SCONST);
					setState(21);
					match(STRING);
					}
					break;
				case BCONST:
					{
					setState(22);
					match(BCONST);
					setState(23);
					match(BOOL);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 2:
				_localctx = new GlobalContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(26);
				((GlobalContext)_localctx).alloc = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 7881299347898368L) != 0)) ) {
					((GlobalContext)_localctx).alloc = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(27);
				match(INT);
				}
				break;
			case 3:
				_localctx = new ConditionsContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(28);
				((ConditionsContext)_localctx).condition = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 68594386796544L) != 0)) ) {
					((ConditionsContext)_localctx).condition = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 4:
				_localctx = new ChangeContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(29);
				((ChangeContext)_localctx).change = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 70377337257984L) != 0)) ) {
					((ChangeContext)_localctx).change = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 5:
				_localctx = new OperationsContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(30);
				((OperationsContext)_localctx).operation = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 69239634944L) != 0)) ) {
					((OperationsContext)_localctx).operation = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 6:
				_localctx = new PrintContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(31);
				((PrintContext)_localctx).print = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1133879755264L) != 0)) ) {
					((PrintContext)_localctx).print = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 7:
				_localctx = new JumpContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(32);
				((JumpContext)_localctx).jump = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 985162418487296L) != 0)) ) {
					((JumpContext)_localctx).jump = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(33);
				match(TAG);
				}
				break;
			case 8:
				_localctx = new TagaContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(34);
				((TagaContext)_localctx).tag = match(TAG);
				setState(35);
				instruction();
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
		"\u0004\u00015\'\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0004\u0000\b\b\u0000\u000b\u0000\f\u0000"+
		"\t\u0001\u0000\u0001\u0000\u0001\u0000\u0003\u0000\u000f\b\u0000\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0003\u0001\u0019\b\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0003\u0001%\b\u0001\u0001\u0001\u0000\u0000\u0002"+
		"\u0000\u0002\u0000\u0006\u0001\u000024\u0005\u0000\u0010\u0013\u001d\u001f"+
		"!!%&)-\u0003\u0000\u0014\u0015!!..\u0003\u0000\n\u000f\u0018\u001c$$\u0004"+
		"\u0000\t\t\u0017\u0017##((\u0001\u0000/10\u0000\u000e\u0001\u0000\u0000"+
		"\u0000\u0002$\u0001\u0000\u0000\u0000\u0004\u0005\u0003\u0002\u0001\u0000"+
		"\u0005\u0006\u0005\u0001\u0000\u0000\u0006\b\u0001\u0000\u0000\u0000\u0007"+
		"\u0004\u0001\u0000\u0000\u0000\b\t\u0001\u0000\u0000\u0000\t\u0007\u0001"+
		"\u0000\u0000\u0000\t\n\u0001\u0000\u0000\u0000\n\u000b\u0001\u0000\u0000"+
		"\u0000\u000b\f\u0005\u0002\u0000\u0000\f\u000f\u0001\u0000\u0000\u0000"+
		"\r\u000f\u0005\u0002\u0000\u0000\u000e\u0007\u0001\u0000\u0000\u0000\u000e"+
		"\r\u0001\u0000\u0000\u0000\u000f\u0001\u0001\u0000\u0000\u0000\u0010\u0011"+
		"\u0005\b\u0000\u0000\u0011\u0019\u0005\u0004\u0000\u0000\u0012\u0013\u0005"+
		"\u0016\u0000\u0000\u0013\u0019\u0005\u0005\u0000\u0000\u0014\u0015\u0005"+
		"\"\u0000\u0000\u0015\u0019\u0005\u0006\u0000\u0000\u0016\u0017\u0005\'"+
		"\u0000\u0000\u0017\u0019\u0005\u0003\u0000\u0000\u0018\u0010\u0001\u0000"+
		"\u0000\u0000\u0018\u0012\u0001\u0000\u0000\u0000\u0018\u0014\u0001\u0000"+
		"\u0000\u0000\u0018\u0016\u0001\u0000\u0000\u0000\u0019%\u0001\u0000\u0000"+
		"\u0000\u001a\u001b\u0007\u0000\u0000\u0000\u001b%\u0005\u0004\u0000\u0000"+
		"\u001c%\u0007\u0001\u0000\u0000\u001d%\u0007\u0002\u0000\u0000\u001e%"+
		"\u0007\u0003\u0000\u0000\u001f%\u0007\u0004\u0000\u0000 !\u0007\u0005"+
		"\u0000\u0000!%\u0005\u0007\u0000\u0000\"#\u0005\u0007\u0000\u0000#%\u0003"+
		"\u0002\u0001\u0000$\u0018\u0001\u0000\u0000\u0000$\u001a\u0001\u0000\u0000"+
		"\u0000$\u001c\u0001\u0000\u0000\u0000$\u001d\u0001\u0000\u0000\u0000$"+
		"\u001e\u0001\u0000\u0000\u0000$\u001f\u0001\u0000\u0000\u0000$ \u0001"+
		"\u0000\u0000\u0000$\"\u0001\u0000\u0000\u0000%\u0003\u0001\u0000\u0000"+
		"\u0000\u0004\t\u000e\u0018$";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}