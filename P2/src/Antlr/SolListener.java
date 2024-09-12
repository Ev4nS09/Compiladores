// Generated from C:/Users/afons/Desktop/Compiladores/P2/src/Sol.g4 by ANTLR 4.13.1
package Antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SolParser}.
 */
public interface SolListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SolParser#sol}.
	 * @param ctx the parse tree
	 */
	void enterSol(SolParser.SolContext ctx);
	/**
	 * Exit a parse tree produced by {@link SolParser#sol}.
	 * @param ctx the parse tree
	 */
	void exitSol(SolParser.SolContext ctx);
	/**
	 * Enter a parse tree produced by {@link SolParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterInstruction(SolParser.InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SolParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitInstruction(SolParser.InstructionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Or}
	 * labeled alternative in {@link SolParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterOr(SolParser.OrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Or}
	 * labeled alternative in {@link SolParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitOr(SolParser.OrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Iguality}
	 * labeled alternative in {@link SolParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIguality(SolParser.IgualityContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Iguality}
	 * labeled alternative in {@link SolParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIguality(SolParser.IgualityContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Bool}
	 * labeled alternative in {@link SolParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBool(SolParser.BoolContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Bool}
	 * labeled alternative in {@link SolParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBool(SolParser.BoolContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link SolParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAddSub(SolParser.AddSubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link SolParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAddSub(SolParser.AddSubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MultDivMod}
	 * labeled alternative in {@link SolParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMultDivMod(SolParser.MultDivModContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MultDivMod}
	 * labeled alternative in {@link SolParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMultDivMod(SolParser.MultDivModContext ctx);
	/**
	 * Enter a parse tree produced by the {@code And}
	 * labeled alternative in {@link SolParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAnd(SolParser.AndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code And}
	 * labeled alternative in {@link SolParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAnd(SolParser.AndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LRParen}
	 * labeled alternative in {@link SolParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLRParen(SolParser.LRParenContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LRParen}
	 * labeled alternative in {@link SolParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLRParen(SolParser.LRParenContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Relational}
	 * labeled alternative in {@link SolParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterRelational(SolParser.RelationalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Relational}
	 * labeled alternative in {@link SolParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitRelational(SolParser.RelationalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code String}
	 * labeled alternative in {@link SolParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterString(SolParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code String}
	 * labeled alternative in {@link SolParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitString(SolParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Double}
	 * labeled alternative in {@link SolParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterDouble(SolParser.DoubleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Double}
	 * labeled alternative in {@link SolParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitDouble(SolParser.DoubleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Unary}
	 * labeled alternative in {@link SolParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterUnary(SolParser.UnaryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Unary}
	 * labeled alternative in {@link SolParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitUnary(SolParser.UnaryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Int}
	 * labeled alternative in {@link SolParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterInt(SolParser.IntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Int}
	 * labeled alternative in {@link SolParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitInt(SolParser.IntContext ctx);
}