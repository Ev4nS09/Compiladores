// Generated from /home/rio/Compiladores/P4/src/Antlr/Sol.g4 by ANTLR 4.13.1
package Antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SolParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SolVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SolParser#sol}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSol(SolParser.SolContext ctx);
	/**
	 * Visit a parse tree produced by {@link SolParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(SolParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link SolParser#line}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLine(SolParser.LineContext ctx);
	/**
	 * Visit a parse tree produced by {@link SolParser#break}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreak(SolParser.BreakContext ctx);
	/**
	 * Visit a parse tree produced by the {@code While}
	 * labeled alternative in {@link SolParser#loop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile(SolParser.WhileContext ctx);
	/**
	 * Visit a parse tree produced by the {@code For}
	 * labeled alternative in {@link SolParser#loop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFor(SolParser.ForContext ctx);
	/**
	 * Visit a parse tree produced by {@link SolParser#if}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf(SolParser.IfContext ctx);
	/**
	 * Visit a parse tree produced by {@link SolParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(SolParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link SolParser#labelExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabelExpression(SolParser.LabelExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SolParser#affectation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAffectation(SolParser.AffectationContext ctx);
	/**
	 * Visit a parse tree produced by {@link SolParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruction(SolParser.InstructionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Or}
	 * labeled alternative in {@link SolParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOr(SolParser.OrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Iguality}
	 * labeled alternative in {@link SolParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIguality(SolParser.IgualityContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link SolParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSub(SolParser.AddSubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Relational}
	 * labeled alternative in {@link SolParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelational(SolParser.RelationalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code String}
	 * labeled alternative in {@link SolParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(SolParser.StringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Double}
	 * labeled alternative in {@link SolParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDouble(SolParser.DoubleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Unary}
	 * labeled alternative in {@link SolParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnary(SolParser.UnaryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Int}
	 * labeled alternative in {@link SolParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInt(SolParser.IntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Bool}
	 * labeled alternative in {@link SolParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBool(SolParser.BoolContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MultDivMod}
	 * labeled alternative in {@link SolParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultDivMod(SolParser.MultDivModContext ctx);
	/**
	 * Visit a parse tree produced by the {@code And}
	 * labeled alternative in {@link SolParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd(SolParser.AndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LRParen}
	 * labeled alternative in {@link SolParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLRParen(SolParser.LRParenContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Lable}
	 * labeled alternative in {@link SolParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLable(SolParser.LableContext ctx);
}