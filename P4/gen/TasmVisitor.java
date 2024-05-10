// Generated from /home/rio/Compiladores/P4/src/Tasm.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link TasmParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface TasmVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link TasmParser#tasm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTasm(TasmParser.TasmContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Inst}
	 * labeled alternative in {@link TasmParser#line}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInst(TasmParser.InstContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TagInstruction}
	 * labeled alternative in {@link TasmParser#line}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTagInstruction(TasmParser.TagInstructionContext ctx);
	/**
	 * Visit a parse tree produced by {@link TasmParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruction(TasmParser.InstructionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Iconst}
	 * labeled alternative in {@link TasmParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIconst(TasmParser.IconstContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Dconst}
	 * labeled alternative in {@link TasmParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDconst(TasmParser.DconstContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Sconst}
	 * labeled alternative in {@link TasmParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSconst(TasmParser.SconstContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Tconst}
	 * labeled alternative in {@link TasmParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTconst(TasmParser.TconstContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Fconst}
	 * labeled alternative in {@link TasmParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFconst(TasmParser.FconstContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Global}
	 * labeled alternative in {@link TasmParser#allocation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGlobal(TasmParser.GlobalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Local}
	 * labeled alternative in {@link TasmParser#allocation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLocal(TasmParser.LocalContext ctx);
	/**
	 * Visit a parse tree produced by {@link TasmParser#call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCall(TasmParser.CallContext ctx);
	/**
	 * Visit a parse tree produced by {@link TasmParser#return}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn(TasmParser.ReturnContext ctx);
	/**
	 * Visit a parse tree produced by {@link TasmParser#pop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPop(TasmParser.PopContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Conditions}
	 * labeled alternative in {@link TasmParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditions(TasmParser.ConditionsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Change}
	 * labeled alternative in {@link TasmParser#cast}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChange(TasmParser.ChangeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Operations}
	 * labeled alternative in {@link TasmParser#operation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperations(TasmParser.OperationsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Print}
	 * labeled alternative in {@link TasmParser#printf}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint(TasmParser.PrintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Jp}
	 * labeled alternative in {@link TasmParser#jump}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJp(TasmParser.JpContext ctx);
}