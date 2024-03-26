// Generated from /home/rio/Uni/3_ano/2_semestre/CP/PL5/Calculator-Listener/LExpr.g4 by ANTLR 4.13.1
package antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link LExprParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface LExprVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link LExprParser#s}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitS(LExprParser.SContext ctx);
	/**
	 * Visit a parse tree produced by {@link LExprParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruction(LExprParser.InstructionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Negation}
	 * labeled alternative in {@link LExprParser#e}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegation(LExprParser.NegationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NewLine}
	 * labeled alternative in {@link LExprParser#e}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewLine(LExprParser.NewLineContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link LExprParser#e}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSub(LExprParser.AddSubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LRParen}
	 * labeled alternative in {@link LExprParser#e}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLRParen(LExprParser.LRParenContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Pow}
	 * labeled alternative in {@link LExprParser#e}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPow(LExprParser.PowContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Double}
	 * labeled alternative in {@link LExprParser#e}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDouble(LExprParser.DoubleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MultDiv}
	 * labeled alternative in {@link LExprParser#e}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultDiv(LExprParser.MultDivContext ctx);
}