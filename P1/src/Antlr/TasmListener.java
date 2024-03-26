// Generated from /home/rio/Compiladores/P1/src/Tasm.g4 by ANTLR 4.13.1
package Antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TasmParser}.
 */
public interface TasmListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TasmParser#s}.
	 * @param ctx the parse tree
	 */
	void enterS(TasmParser.SContext ctx);
	/**
	 * Exit a parse tree produced by {@link TasmParser#s}.
	 * @param ctx the parse tree
	 */
	void exitS(TasmParser.SContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Tag}
	 * labeled alternative in {@link TasmParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterTag(TasmParser.TagContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Tag}
	 * labeled alternative in {@link TasmParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitTag(TasmParser.TagContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Const}
	 * labeled alternative in {@link TasmParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterConst(TasmParser.ConstContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Const}
	 * labeled alternative in {@link TasmParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitConst(TasmParser.ConstContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Global}
	 * labeled alternative in {@link TasmParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterGlobal(TasmParser.GlobalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Global}
	 * labeled alternative in {@link TasmParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitGlobal(TasmParser.GlobalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Conditions}
	 * labeled alternative in {@link TasmParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterConditions(TasmParser.ConditionsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Conditions}
	 * labeled alternative in {@link TasmParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitConditions(TasmParser.ConditionsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Change}
	 * labeled alternative in {@link TasmParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterChange(TasmParser.ChangeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Change}
	 * labeled alternative in {@link TasmParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitChange(TasmParser.ChangeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Operations}
	 * labeled alternative in {@link TasmParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterOperations(TasmParser.OperationsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Operations}
	 * labeled alternative in {@link TasmParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitOperations(TasmParser.OperationsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Print}
	 * labeled alternative in {@link TasmParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterPrint(TasmParser.PrintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Print}
	 * labeled alternative in {@link TasmParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitPrint(TasmParser.PrintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Jump}
	 * labeled alternative in {@link TasmParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterJump(TasmParser.JumpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Jump}
	 * labeled alternative in {@link TasmParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitJump(TasmParser.JumpContext ctx);
}