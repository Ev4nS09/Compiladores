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
		T__0=1, T__1=2, T__2=3, HALT=4, BOOL=5, INT=6, DOUBLE=7, STRING=8, ICONST=9, 
		IPRINT=10, IUMINUS=11, IADD=12, ISUB=13, IMULT=14, IDIV=15, IMOD=16, IEQ=17, 
		INEQ=18, ILT=19, ILEQ=20, ITOD=21, ITOS=22, DCONST=23, DPRINT=24, DUMINUS=25, 
		DADD=26, DSUB=27, DMULT=28, DDIV=29, DEQ=30, DNEQ=31, DLT=32, DLEQ=33, 
		DTOS=34, SCONST=35, SPRINT=36, SADD=37, SEQ=38, SNEQ=39, TCONST=40, FCONST=41, 
		BPRINT=42, BEQ=43, BNEQ=44, AND=45, OR=46, NOT=47, BTOS=48, JUMP=49, JUMPT=50, 
		JUMPF=51, GALLOC=52, GLOAD=53, GSTORE=54, TAG=55, WS=56;
	public static final int
		RULE_tasm = 0, RULE_line = 1, RULE_instruction = 2, RULE_constant = 3, 
		RULE_allocation = 4, RULE_condition = 5, RULE_cast = 6, RULE_operation = 7, 
		RULE_printf = 8, RULE_jump = 9;
	private static String[] makeRuleNames() {
		return new String[] {
			"tasm", "line", "instruction", "constant", "allocation", "condition", 
			"cast", "operation", "printf", "jump"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "','", "':'", "'\\n'", "'halt'", null, null, null, null, "'iconst'", 
			"'iprint'", "'iuminus'", "'iadd'", "'isub'", "'imult'", "'idiv'", "'imod'", 
			"'ieq'", "'ineq'", "'ilt'", "'ileq'", "'itod'", "'itos'", "'dconst'", 
			"'dprint'", "'duminus'", "'dadd'", "'dsub'", "'dmult'", "'ddiv'", "'deq'", 
			"'dneq'", "'dlt'", "'dleq'", "'dtos'", "'sconst'", "'sprint'", "'sadd'", 
			"'seq'", "'sneq'", "'tconst'", "'fconst'", "'bprint'", "'beq'", "'bneq'", 
			"'and'", "'or'", "'not'", "'btos'", "'jump'", "'jumpt'", "'jumpf'", "'galloc'", 
			"'gload'", "'gstore'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, "HALT", "BOOL", "INT", "DOUBLE", "STRING", "ICONST", 
			"IPRINT", "IUMINUS", "IADD", "ISUB", "IMULT", "IDIV", "IMOD", "IEQ", 
			"INEQ", "ILT", "ILEQ", "ITOD", "ITOS", "DCONST", "DPRINT", "DUMINUS", 
			"DADD", "DSUB", "DMULT", "DDIV", "DEQ", "DNEQ", "DLT", "DLEQ", "DTOS", 
			"SCONST", "SPRINT", "SADD", "SEQ", "SNEQ", "TCONST", "FCONST", "BPRINT", 
			"BEQ", "BNEQ", "AND", "OR", "NOT", "BTOS", "JUMP", "JUMPT", "JUMPF", 
			"GALLOC", "GLOAD", "GSTORE", "TAG", "WS"
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
		public List<LineContext> line() {
			return getRuleContexts(LineContext.class);
		}
		public LineContext line(int i) {
			return getRuleContext(LineContext.class,i);
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
			enterOuterAlt(_localctx, 1);
			{
			setState(21); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(20);
				line();
				}
				}
				setState(23); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 72057585447992840L) != 0) );
			setState(25);
			match(HALT);
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
	public static class LineContext extends ParserRuleContext {
		public LineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_line; }
	 
		public LineContext() { }
		public void copyFrom(LineContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TagInstructionContext extends LineContext {
		public List<TerminalNode> TAG() { return getTokens(TasmParser.TAG); }
		public TerminalNode TAG(int i) {
			return getToken(TasmParser.TAG, i);
		}
		public InstructionContext instruction() {
			return getRuleContext(InstructionContext.class,0);
		}
		public TagInstructionContext(LineContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).enterTagInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).exitTagInstruction(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InstContext extends LineContext {
		public InstructionContext instruction() {
			return getRuleContext(InstructionContext.class,0);
		}
		public InstContext(LineContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).enterInst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).exitInst(this);
		}
	}

	public final LineContext line() throws RecognitionException {
		LineContext _localctx = new LineContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_line);
		int _la;
		try {
			setState(38);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
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
			case TCONST:
			case FCONST:
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
				_localctx = new InstContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(27);
				instruction();
				}
				break;
			case TAG:
				_localctx = new TagInstructionContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(28);
				match(TAG);
				setState(33);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__0) {
					{
					{
					setState(29);
					match(T__0);
					setState(30);
					match(TAG);
					}
					}
					setState(35);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(36);
				match(T__1);
				setState(37);
				instruction();
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
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public AllocationContext allocation() {
			return getRuleContext(AllocationContext.class,0);
		}
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public CastContext cast() {
			return getRuleContext(CastContext.class,0);
		}
		public OperationContext operation() {
			return getRuleContext(OperationContext.class,0);
		}
		public PrintfContext printf() {
			return getRuleContext(PrintfContext.class,0);
		}
		public JumpContext jump() {
			return getRuleContext(JumpContext.class,0);
		}
		public InstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).enterInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).exitInstruction(this);
		}
	}

	public final InstructionContext instruction() throws RecognitionException {
		InstructionContext _localctx = new InstructionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_instruction);
		try {
			setState(62);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(40);
				constant();
				setState(41);
				match(T__2);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(43);
				allocation();
				setState(44);
				match(T__2);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(46);
				condition();
				setState(47);
				match(T__2);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(49);
				cast();
				setState(50);
				match(T__2);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(52);
				operation();
				setState(53);
				match(T__2);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(55);
				printf();
				setState(56);
				match(T__2);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(58);
				jump();
				setState(59);
				match(T__2);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(61);
				match(T__2);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ConstantContext extends ParserRuleContext {
		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant; }
	 
		public ConstantContext() { }
		public void copyFrom(ConstantContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TconstContext extends ConstantContext {
		public TerminalNode TCONST() { return getToken(TasmParser.TCONST, 0); }
		public TconstContext(ConstantContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).enterTconst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).exitTconst(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IconstContext extends ConstantContext {
		public TerminalNode ICONST() { return getToken(TasmParser.ICONST, 0); }
		public TerminalNode INT() { return getToken(TasmParser.INT, 0); }
		public IconstContext(ConstantContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).enterIconst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).exitIconst(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FconstContext extends ConstantContext {
		public TerminalNode FCONST() { return getToken(TasmParser.FCONST, 0); }
		public FconstContext(ConstantContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).enterFconst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).exitFconst(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SconstContext extends ConstantContext {
		public TerminalNode SCONST() { return getToken(TasmParser.SCONST, 0); }
		public TerminalNode STRING() { return getToken(TasmParser.STRING, 0); }
		public SconstContext(ConstantContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).enterSconst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).exitSconst(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DconstContext extends ConstantContext {
		public TerminalNode DCONST() { return getToken(TasmParser.DCONST, 0); }
		public TerminalNode DOUBLE() { return getToken(TasmParser.DOUBLE, 0); }
		public TerminalNode INT() { return getToken(TasmParser.INT, 0); }
		public DconstContext(ConstantContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).enterDconst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).exitDconst(this);
		}
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_constant);
		int _la;
		try {
			setState(72);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ICONST:
				_localctx = new IconstContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(64);
				match(ICONST);
				setState(65);
				match(INT);
				}
				break;
			case DCONST:
				_localctx = new DconstContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(66);
				match(DCONST);
				setState(67);
				_la = _input.LA(1);
				if ( !(_la==INT || _la==DOUBLE) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case SCONST:
				_localctx = new SconstContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(68);
				match(SCONST);
				setState(69);
				match(STRING);
				}
				break;
			case TCONST:
				_localctx = new TconstContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(70);
				match(TCONST);
				}
				break;
			case FCONST:
				_localctx = new FconstContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(71);
				match(FCONST);
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
	public static class AllocationContext extends ParserRuleContext {
		public AllocationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_allocation; }
	 
		public AllocationContext() { }
		public void copyFrom(AllocationContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class GlobalContext extends AllocationContext {
		public Token alloc;
		public TerminalNode INT() { return getToken(TasmParser.INT, 0); }
		public TerminalNode GALLOC() { return getToken(TasmParser.GALLOC, 0); }
		public TerminalNode GLOAD() { return getToken(TasmParser.GLOAD, 0); }
		public TerminalNode GSTORE() { return getToken(TasmParser.GSTORE, 0); }
		public GlobalContext(AllocationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).enterGlobal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).exitGlobal(this);
		}
	}

	public final AllocationContext allocation() throws RecognitionException {
		AllocationContext _localctx = new AllocationContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_allocation);
		int _la;
		try {
			_localctx = new GlobalContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			((GlobalContext)_localctx).alloc = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 31525197391593472L) != 0)) ) {
				((GlobalContext)_localctx).alloc = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(75);
			match(INT);
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
	public static class ConditionContext extends ParserRuleContext {
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
	 
		public ConditionContext() { }
		public void copyFrom(ConditionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ConditionsContext extends ConditionContext {
		public Token cd;
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
		public ConditionsContext(ConditionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).enterConditions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).exitConditions(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_condition);
		int _la;
		try {
			_localctx = new ConditionsContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			((ConditionsContext)_localctx).cd = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 273528215437312L) != 0)) ) {
				((ConditionsContext)_localctx).cd = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
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
	public static class CastContext extends ParserRuleContext {
		public CastContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cast; }
	 
		public CastContext() { }
		public void copyFrom(CastContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ChangeContext extends CastContext {
		public Token change;
		public TerminalNode ITOD() { return getToken(TasmParser.ITOD, 0); }
		public TerminalNode ITOS() { return getToken(TasmParser.ITOS, 0); }
		public TerminalNode DTOS() { return getToken(TasmParser.DTOS, 0); }
		public TerminalNode BTOS() { return getToken(TasmParser.BTOS, 0); }
		public ChangeContext(CastContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).enterChange(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).exitChange(this);
		}
	}

	public final CastContext cast() throws RecognitionException {
		CastContext _localctx = new CastContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_cast);
		int _la;
		try {
			_localctx = new ChangeContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			((ChangeContext)_localctx).change = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 281492162871296L) != 0)) ) {
				((ChangeContext)_localctx).change = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
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
	public static class OperationContext extends ParserRuleContext {
		public OperationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operation; }
	 
		public OperationContext() { }
		public void copyFrom(OperationContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class OperationsContext extends OperationContext {
		public Token op;
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
		public OperationsContext(OperationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).enterOperations(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).exitOperations(this);
		}
	}

	public final OperationContext operation() throws RecognitionException {
		OperationContext _localctx = new OperationContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_operation);
		int _la;
		try {
			_localctx = new OperationsContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			((OperationsContext)_localctx).op = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 138479269888L) != 0)) ) {
				((OperationsContext)_localctx).op = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
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
	public static class PrintfContext extends ParserRuleContext {
		public PrintfContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_printf; }
	 
		public PrintfContext() { }
		public void copyFrom(PrintfContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PrintContext extends PrintfContext {
		public Token print;
		public TerminalNode IPRINT() { return getToken(TasmParser.IPRINT, 0); }
		public TerminalNode DPRINT() { return getToken(TasmParser.DPRINT, 0); }
		public TerminalNode SPRINT() { return getToken(TasmParser.SPRINT, 0); }
		public TerminalNode BPRINT() { return getToken(TasmParser.BPRINT, 0); }
		public PrintContext(PrintfContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).enterPrint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).exitPrint(this);
		}
	}

	public final PrintfContext printf() throws RecognitionException {
		PrintfContext _localctx = new PrintfContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_printf);
		int _la;
		try {
			_localctx = new PrintContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			((PrintContext)_localctx).print = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 4466782766080L) != 0)) ) {
				((PrintContext)_localctx).print = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
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
	public static class JumpContext extends ParserRuleContext {
		public JumpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jump; }
	 
		public JumpContext() { }
		public void copyFrom(JumpContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class JpContext extends JumpContext {
		public Token jp;
		public Token tag;
		public TerminalNode TAG() { return getToken(TasmParser.TAG, 0); }
		public TerminalNode JUMP() { return getToken(TasmParser.JUMP, 0); }
		public TerminalNode JUMPF() { return getToken(TasmParser.JUMPF, 0); }
		public TerminalNode JUMPT() { return getToken(TasmParser.JUMPT, 0); }
		public JpContext(JumpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).enterJp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TasmListener ) ((TasmListener)listener).exitJp(this);
		}
	}

	public final JumpContext jump() throws RecognitionException {
		JumpContext _localctx = new JumpContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_jump);
		int _la;
		try {
			_localctx = new JpContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			((JpContext)_localctx).jp = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 3940649673949184L) != 0)) ) {
				((JpContext)_localctx).jp = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(86);
			((JpContext)_localctx).tag = match(TAG);
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
		"\u0004\u00018Y\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002\u0002"+
		"\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002\u0005"+
		"\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002\b\u0007"+
		"\b\u0002\t\u0007\t\u0001\u0000\u0004\u0000\u0016\b\u0000\u000b\u0000\f"+
		"\u0000\u0017\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0005\u0001 \b\u0001\n\u0001\f\u0001#\t\u0001\u0001\u0001"+
		"\u0001\u0001\u0003\u0001\'\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0003\u0002?\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003"+
		"I\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0000\u0000\n\u0000\u0002\u0004\u0006\b\n\f"+
		"\u000e\u0010\u0012\u0000\u0007\u0001\u0000\u0006\u0007\u0001\u000046\u0005"+
		"\u0000\u0011\u0014\u001e \"\"&\'+/\u0003\u0000\u0015\u0016\"\"00\u0003"+
		"\u0000\u000b\u0010\u0019\u001d%%\u0004\u0000\n\n\u0018\u0018$$**\u0001"+
		"\u000013\\\u0000\u0015\u0001\u0000\u0000\u0000\u0002&\u0001\u0000\u0000"+
		"\u0000\u0004>\u0001\u0000\u0000\u0000\u0006H\u0001\u0000\u0000\u0000\b"+
		"J\u0001\u0000\u0000\u0000\nM\u0001\u0000\u0000\u0000\fO\u0001\u0000\u0000"+
		"\u0000\u000eQ\u0001\u0000\u0000\u0000\u0010S\u0001\u0000\u0000\u0000\u0012"+
		"U\u0001\u0000\u0000\u0000\u0014\u0016\u0003\u0002\u0001\u0000\u0015\u0014"+
		"\u0001\u0000\u0000\u0000\u0016\u0017\u0001\u0000\u0000\u0000\u0017\u0015"+
		"\u0001\u0000\u0000\u0000\u0017\u0018\u0001\u0000\u0000\u0000\u0018\u0019"+
		"\u0001\u0000\u0000\u0000\u0019\u001a\u0005\u0004\u0000\u0000\u001a\u0001"+
		"\u0001\u0000\u0000\u0000\u001b\'\u0003\u0004\u0002\u0000\u001c!\u0005"+
		"7\u0000\u0000\u001d\u001e\u0005\u0001\u0000\u0000\u001e \u00057\u0000"+
		"\u0000\u001f\u001d\u0001\u0000\u0000\u0000 #\u0001\u0000\u0000\u0000!"+
		"\u001f\u0001\u0000\u0000\u0000!\"\u0001\u0000\u0000\u0000\"$\u0001\u0000"+
		"\u0000\u0000#!\u0001\u0000\u0000\u0000$%\u0005\u0002\u0000\u0000%\'\u0003"+
		"\u0004\u0002\u0000&\u001b\u0001\u0000\u0000\u0000&\u001c\u0001\u0000\u0000"+
		"\u0000\'\u0003\u0001\u0000\u0000\u0000()\u0003\u0006\u0003\u0000)*\u0005"+
		"\u0003\u0000\u0000*?\u0001\u0000\u0000\u0000+,\u0003\b\u0004\u0000,-\u0005"+
		"\u0003\u0000\u0000-?\u0001\u0000\u0000\u0000./\u0003\n\u0005\u0000/0\u0005"+
		"\u0003\u0000\u00000?\u0001\u0000\u0000\u000012\u0003\f\u0006\u000023\u0005"+
		"\u0003\u0000\u00003?\u0001\u0000\u0000\u000045\u0003\u000e\u0007\u0000"+
		"56\u0005\u0003\u0000\u00006?\u0001\u0000\u0000\u000078\u0003\u0010\b\u0000"+
		"89\u0005\u0003\u0000\u00009?\u0001\u0000\u0000\u0000:;\u0003\u0012\t\u0000"+
		";<\u0005\u0003\u0000\u0000<?\u0001\u0000\u0000\u0000=?\u0005\u0003\u0000"+
		"\u0000>(\u0001\u0000\u0000\u0000>+\u0001\u0000\u0000\u0000>.\u0001\u0000"+
		"\u0000\u0000>1\u0001\u0000\u0000\u0000>4\u0001\u0000\u0000\u0000>7\u0001"+
		"\u0000\u0000\u0000>:\u0001\u0000\u0000\u0000>=\u0001\u0000\u0000\u0000"+
		"?\u0005\u0001\u0000\u0000\u0000@A\u0005\t\u0000\u0000AI\u0005\u0006\u0000"+
		"\u0000BC\u0005\u0017\u0000\u0000CI\u0007\u0000\u0000\u0000DE\u0005#\u0000"+
		"\u0000EI\u0005\b\u0000\u0000FI\u0005(\u0000\u0000GI\u0005)\u0000\u0000"+
		"H@\u0001\u0000\u0000\u0000HB\u0001\u0000\u0000\u0000HD\u0001\u0000\u0000"+
		"\u0000HF\u0001\u0000\u0000\u0000HG\u0001\u0000\u0000\u0000I\u0007\u0001"+
		"\u0000\u0000\u0000JK\u0007\u0001\u0000\u0000KL\u0005\u0006\u0000\u0000"+
		"L\t\u0001\u0000\u0000\u0000MN\u0007\u0002\u0000\u0000N\u000b\u0001\u0000"+
		"\u0000\u0000OP\u0007\u0003\u0000\u0000P\r\u0001\u0000\u0000\u0000QR\u0007"+
		"\u0004\u0000\u0000R\u000f\u0001\u0000\u0000\u0000ST\u0007\u0005\u0000"+
		"\u0000T\u0011\u0001\u0000\u0000\u0000UV\u0007\u0006\u0000\u0000VW\u0005"+
		"7\u0000\u0000W\u0013\u0001\u0000\u0000\u0000\u0005\u0017!&>H";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}