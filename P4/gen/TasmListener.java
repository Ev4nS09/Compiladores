// Generated from /home/rio/Compiladores/P4/src/Tasm.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TasmParser}.
 */
public interface TasmListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TasmParser#tasm}.
	 * @param ctx the parse tree
	 */
	void enterTasm(TasmParser.TasmContext ctx);
	/**
	 * Exit a parse tree produced by {@link TasmParser#tasm}.
	 * @param ctx the parse tree
	 */
	void exitTasm(TasmParser.TasmContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Inst}
	 * labeled alternative in {@link TasmParser#line}.
	 * @param ctx the parse tree
	 */
	void enterInst(TasmParser.InstContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Inst}
	 * labeled alternative in {@link TasmParser#line}.
	 * @param ctx the parse tree
	 */
	void exitInst(TasmParser.InstContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TagInstruction}
	 * labeled alternative in {@link TasmParser#line}.
	 * @param ctx the parse tree
	 */
	void enterTagInstruction(TasmParser.TagInstructionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TagInstruction}
	 * labeled alternative in {@link TasmParser#line}.
	 * @param ctx the parse tree
	 */
	void exitTagInstruction(TasmParser.TagInstructionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TasmParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterInstruction(TasmParser.InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TasmParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitInstruction(TasmParser.InstructionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Iconst}
	 * labeled alternative in {@link TasmParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterIconst(TasmParser.IconstContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Iconst}
	 * labeled alternative in {@link TasmParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitIconst(TasmParser.IconstContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Dconst}
	 * labeled alternative in {@link TasmParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterDconst(TasmParser.DconstContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Dconst}
	 * labeled alternative in {@link TasmParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitDconst(TasmParser.DconstContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Sconst}
	 * labeled alternative in {@link TasmParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterSconst(TasmParser.SconstContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Sconst}
	 * labeled alternative in {@link TasmParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitSconst(TasmParser.SconstContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Tconst}
	 * labeled alternative in {@link TasmParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterTconst(TasmParser.TconstContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Tconst}
	 * labeled alternative in {@link TasmParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitTconst(TasmParser.TconstContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Fconst}
	 * labeled alternative in {@link TasmParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterFconst(TasmParser.FconstContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Fconst}
	 * labeled alternative in {@link TasmParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitFconst(TasmParser.FconstContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Global}
	 * labeled alternative in {@link TasmParser#allocation}.
	 * @param ctx the parse tree
	 */
	void enterGlobal(TasmParser.GlobalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Global}
	 * labeled alternative in {@link TasmParser#allocation}.
	 * @param ctx the parse tree
	 */
	void exitGlobal(TasmParser.GlobalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Local}
	 * labeled alternative in {@link TasmParser#allocation}.
	 * @param ctx the parse tree
	 */
	void enterLocal(TasmParser.LocalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Local}
	 * labeled alternative in {@link TasmParser#allocation}.
	 * @param ctx the parse tree
	 */
	void exitLocal(TasmParser.LocalContext ctx);
	/**
	 * Enter a parse tree produced by {@link TasmParser#call}.
	 * @param ctx the parse tree
	 */
	void enterCall(TasmParser.CallContext ctx);
	/**
	 * Exit a parse tree produced by {@link TasmParser#call}.
	 * @param ctx the parse tree
	 */
	void exitCall(TasmParser.CallContext ctx);
	/**
	 * Enter a parse tree produced by {@link TasmParser#return}.
	 * @param ctx the parse tree
	 */
	void enterReturn(TasmParser.ReturnContext ctx);
	/**
	 * Exit a parse tree produced by {@link TasmParser#return}.
	 * @param ctx the parse tree
	 */
	void exitReturn(TasmParser.ReturnContext ctx);
	/**
	 * Enter a parse tree produced by {@link TasmParser#pop}.
	 * @param ctx the parse tree
	 */
	void enterPop(TasmParser.PopContext ctx);
	/**
	 * Exit a parse tree produced by {@link TasmParser#pop}.
	 * @param ctx the parse tree
	 */
	void exitPop(TasmParser.PopContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Conditions}
	 * labeled alternative in {@link TasmParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterConditions(TasmParser.ConditionsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Conditions}
	 * labeled alternative in {@link TasmParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitConditions(TasmParser.ConditionsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Change}
	 * labeled alternative in {@link TasmParser#cast}.
	 * @param ctx the parse tree
	 */
	void enterChange(TasmParser.ChangeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Change}
	 * labeled alternative in {@link TasmParser#cast}.
	 * @param ctx the parse tree
	 */
	void exitChange(TasmParser.ChangeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Operations}
	 * labeled alternative in {@link TasmParser#operation}.
	 * @param ctx the parse tree
	 */
	void enterOperations(TasmParser.OperationsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Operations}
	 * labeled alternative in {@link TasmParser#operation}.
	 * @param ctx the parse tree
	 */
	void exitOperations(TasmParser.OperationsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Print}
	 * labeled alternative in {@link TasmParser#printf}.
	 * @param ctx the parse tree
	 */
	void enterPrint(TasmParser.PrintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Print}
	 * labeled alternative in {@link TasmParser#printf}.
	 * @param ctx the parse tree
	 */
	void exitPrint(TasmParser.PrintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Jp}
	 * labeled alternative in {@link TasmParser#jump}.
	 * @param ctx the parse tree
	 */
	void enterJp(TasmParser.JpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Jp}
	 * labeled alternative in {@link TasmParser#jump}.
	 * @param ctx the parse tree
	 */
	void exitJp(TasmParser.JpContext ctx);
}