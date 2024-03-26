// Generated from /home/rio/Uni/3_ano/2_semestre/CP/PL5/Calculator-Listener/LExpr.g4 by ANTLR 4.13.1
package antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class LExprParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		POW=1, MULT=2, DIV=3, ADD=4, SUB=5, LPAREN=6, RPAREN=7, NEWLINE=8, INSTRUCTION=9, 
		DOUBLE=10, WS=11;
	public static final int
		RULE_s = 0, RULE_instruction = 1, RULE_e = 2;
	private static String[] makeRuleNames() {
		return new String[] {
			"s", "instruction", "e"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'^'", "'*'", "'/'", "'+'", "'-'", "'('", "')'", "'\\n'", "'print'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "POW", "MULT", "DIV", "ADD", "SUB", "LPAREN", "RPAREN", "NEWLINE", 
			"INSTRUCTION", "DOUBLE", "WS"
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
	public String getGrammarFileName() { return "LExpr.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public LExprParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SContext extends ParserRuleContext {
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
			if ( listener instanceof LExprListener ) ((LExprListener)listener).enterS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LExprListener ) ((LExprListener)listener).exitS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LExprVisitor ) return ((LExprVisitor<? extends T>)visitor).visitS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SContext s() throws RecognitionException {
		SContext _localctx = new SContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_s);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(7); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(6);
				instruction();
				}
				}
				setState(9); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==INSTRUCTION );
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
		public Token ins;
		public EContext e() {
			return getRuleContext(EContext.class,0);
		}
		public TerminalNode INSTRUCTION() { return getToken(LExprParser.INSTRUCTION, 0); }
		public InstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LExprListener ) ((LExprListener)listener).enterInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LExprListener ) ((LExprListener)listener).exitInstruction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LExprVisitor ) return ((LExprVisitor<? extends T>)visitor).visitInstruction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstructionContext instruction() throws RecognitionException {
		InstructionContext _localctx = new InstructionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_instruction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(11);
			((InstructionContext)_localctx).ins = match(INSTRUCTION);
			setState(12);
			e(0);
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
	public static class EContext extends ParserRuleContext {
		public EContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_e; }
	 
		public EContext() { }
		public void copyFrom(EContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NegationContext extends EContext {
		public TerminalNode SUB() { return getToken(LExprParser.SUB, 0); }
		public EContext e() {
			return getRuleContext(EContext.class,0);
		}
		public NegationContext(EContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LExprListener ) ((LExprListener)listener).enterNegation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LExprListener ) ((LExprListener)listener).exitNegation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LExprVisitor ) return ((LExprVisitor<? extends T>)visitor).visitNegation(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NewLineContext extends EContext {
		public EContext e() {
			return getRuleContext(EContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(LExprParser.NEWLINE, 0); }
		public NewLineContext(EContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LExprListener ) ((LExprListener)listener).enterNewLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LExprListener ) ((LExprListener)listener).exitNewLine(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LExprVisitor ) return ((LExprVisitor<? extends T>)visitor).visitNewLine(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AddSubContext extends EContext {
		public Token op;
		public List<EContext> e() {
			return getRuleContexts(EContext.class);
		}
		public EContext e(int i) {
			return getRuleContext(EContext.class,i);
		}
		public TerminalNode ADD() { return getToken(LExprParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(LExprParser.SUB, 0); }
		public AddSubContext(EContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LExprListener ) ((LExprListener)listener).enterAddSub(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LExprListener ) ((LExprListener)listener).exitAddSub(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LExprVisitor ) return ((LExprVisitor<? extends T>)visitor).visitAddSub(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LRParenContext extends EContext {
		public TerminalNode LPAREN() { return getToken(LExprParser.LPAREN, 0); }
		public EContext e() {
			return getRuleContext(EContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(LExprParser.RPAREN, 0); }
		public LRParenContext(EContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LExprListener ) ((LExprListener)listener).enterLRParen(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LExprListener ) ((LExprListener)listener).exitLRParen(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LExprVisitor ) return ((LExprVisitor<? extends T>)visitor).visitLRParen(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PowContext extends EContext {
		public List<EContext> e() {
			return getRuleContexts(EContext.class);
		}
		public EContext e(int i) {
			return getRuleContext(EContext.class,i);
		}
		public TerminalNode POW() { return getToken(LExprParser.POW, 0); }
		public PowContext(EContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LExprListener ) ((LExprListener)listener).enterPow(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LExprListener ) ((LExprListener)listener).exitPow(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LExprVisitor ) return ((LExprVisitor<? extends T>)visitor).visitPow(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DoubleContext extends EContext {
		public TerminalNode DOUBLE() { return getToken(LExprParser.DOUBLE, 0); }
		public DoubleContext(EContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LExprListener ) ((LExprListener)listener).enterDouble(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LExprListener ) ((LExprListener)listener).exitDouble(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LExprVisitor ) return ((LExprVisitor<? extends T>)visitor).visitDouble(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MultDivContext extends EContext {
		public Token op;
		public List<EContext> e() {
			return getRuleContexts(EContext.class);
		}
		public EContext e(int i) {
			return getRuleContext(EContext.class,i);
		}
		public TerminalNode MULT() { return getToken(LExprParser.MULT, 0); }
		public TerminalNode DIV() { return getToken(LExprParser.DIV, 0); }
		public MultDivContext(EContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LExprListener ) ((LExprListener)listener).enterMultDiv(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LExprListener ) ((LExprListener)listener).exitMultDiv(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LExprVisitor ) return ((LExprVisitor<? extends T>)visitor).visitMultDiv(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EContext e() throws RecognitionException {
		return e(0);
	}

	private EContext e(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		EContext _localctx = new EContext(_ctx, _parentState);
		EContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_e, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(22);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LPAREN:
				{
				_localctx = new LRParenContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(15);
				match(LPAREN);
				setState(16);
				e(0);
				setState(17);
				match(RPAREN);
				}
				break;
			case SUB:
				{
				_localctx = new NegationContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(19);
				match(SUB);
				setState(20);
				e(5);
				}
				break;
			case DOUBLE:
				{
				_localctx = new DoubleContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(21);
				match(DOUBLE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(37);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(35);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
					case 1:
						{
						_localctx = new PowContext(new EContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_e);
						setState(24);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(25);
						match(POW);
						setState(26);
						e(6);
						}
						break;
					case 2:
						{
						_localctx = new MultDivContext(new EContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_e);
						setState(27);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(28);
						((MultDivContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==MULT || _la==DIV) ) {
							((MultDivContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(29);
						e(5);
						}
						break;
					case 3:
						{
						_localctx = new AddSubContext(new EContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_e);
						setState(30);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(31);
						((AddSubContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==ADD || _la==SUB) ) {
							((AddSubContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(32);
						e(4);
						}
						break;
					case 4:
						{
						_localctx = new NewLineContext(new EContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_e);
						setState(33);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(34);
						match(NEWLINE);
						}
						break;
					}
					} 
				}
				setState(39);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 2:
			return e_sempred((EContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean e_sempred(EContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 6);
		case 1:
			return precpred(_ctx, 4);
		case 2:
			return precpred(_ctx, 3);
		case 3:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u000b)\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0001\u0000\u0004\u0000\b\b\u0000\u000b\u0000\f\u0000"+
		"\t\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002"+
		"\u0017\b\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0005\u0002$\b\u0002\n\u0002\f\u0002\'\t\u0002\u0001\u0002\u0000\u0001"+
		"\u0004\u0003\u0000\u0002\u0004\u0000\u0002\u0001\u0000\u0002\u0003\u0001"+
		"\u0000\u0004\u0005,\u0000\u0007\u0001\u0000\u0000\u0000\u0002\u000b\u0001"+
		"\u0000\u0000\u0000\u0004\u0016\u0001\u0000\u0000\u0000\u0006\b\u0003\u0002"+
		"\u0001\u0000\u0007\u0006\u0001\u0000\u0000\u0000\b\t\u0001\u0000\u0000"+
		"\u0000\t\u0007\u0001\u0000\u0000\u0000\t\n\u0001\u0000\u0000\u0000\n\u0001"+
		"\u0001\u0000\u0000\u0000\u000b\f\u0005\t\u0000\u0000\f\r\u0003\u0004\u0002"+
		"\u0000\r\u0003\u0001\u0000\u0000\u0000\u000e\u000f\u0006\u0002\uffff\uffff"+
		"\u0000\u000f\u0010\u0005\u0006\u0000\u0000\u0010\u0011\u0003\u0004\u0002"+
		"\u0000\u0011\u0012\u0005\u0007\u0000\u0000\u0012\u0017\u0001\u0000\u0000"+
		"\u0000\u0013\u0014\u0005\u0005\u0000\u0000\u0014\u0017\u0003\u0004\u0002"+
		"\u0005\u0015\u0017\u0005\n\u0000\u0000\u0016\u000e\u0001\u0000\u0000\u0000"+
		"\u0016\u0013\u0001\u0000\u0000\u0000\u0016\u0015\u0001\u0000\u0000\u0000"+
		"\u0017%\u0001\u0000\u0000\u0000\u0018\u0019\n\u0006\u0000\u0000\u0019"+
		"\u001a\u0005\u0001\u0000\u0000\u001a$\u0003\u0004\u0002\u0006\u001b\u001c"+
		"\n\u0004\u0000\u0000\u001c\u001d\u0007\u0000\u0000\u0000\u001d$\u0003"+
		"\u0004\u0002\u0005\u001e\u001f\n\u0003\u0000\u0000\u001f \u0007\u0001"+
		"\u0000\u0000 $\u0003\u0004\u0002\u0004!\"\n\u0001\u0000\u0000\"$\u0005"+
		"\b\u0000\u0000#\u0018\u0001\u0000\u0000\u0000#\u001b\u0001\u0000\u0000"+
		"\u0000#\u001e\u0001\u0000\u0000\u0000#!\u0001\u0000\u0000\u0000$\'\u0001"+
		"\u0000\u0000\u0000%#\u0001\u0000\u0000\u0000%&\u0001\u0000\u0000\u0000"+
		"&\u0005\u0001\u0000\u0000\u0000\'%\u0001\u0000\u0000\u0000\u0004\t\u0016"+
		"#%";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}