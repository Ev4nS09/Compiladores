/***
 * Excerpted from "The Definitive ANTLR 4 Reference",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/tpantlr2 for more book information.
***/
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.*;
import java.util.*;


public class LExprCompiler {
    /** Sample "calculator" */

    public static class Evaluator extends LExprBaseListener {

        protected ArrayList<Integer> instructions = new ArrayList<>();

        public void exitPow(LExprParser.PowContext ctx) {
            instructions.add(OpCode.ipow.ordinal());
        }

        public void exitMultDiv(LExprParser.MultDivContext ctx) {

            if(ctx.op.getText().equals("*"))
                instructions.add(OpCode.imult.ordinal());
            else
                instructions.add(OpCode.idiv.ordinal());

        }

        public void exitAddSub(LExprParser.AddSubContext ctx) {
            instructions.add(ctx.op.getText().equals("+") ? OpCode.iadd.ordinal() : OpCode.isub.ordinal());
        }

        public void exitNegation(LExprParser.NegationContext ctx) {
            instructions.add(OpCode.iuminus.ordinal());
        }

        public void exitDouble(LExprParser.DoubleContext ctx) {
            instructions.add(OpCode.iconst.ordinal());
            instructions.add((Integer.valueOf(ctx.DOUBLE().getText())));
        }

        public void exitInstruction(LExprParser.InstructionContext ctx){
            if(ctx.ins.getText().equals("print"))
                instructions.add(OpCode.iprint.ordinal());
        }

        @Override
        public String toString(){
            String result = "";

            for(Integer instruction : instructions)
                result += String.valueOf(instruction) + ' ';

            return result;
        }
    }


    public void generateByteCode(Evaluator evaluator, String outputFile) throws Exception {

        DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(outputFile));
        for (int i = 0; i < evaluator.instructions.size(); i++) {

            outputStream.writeByte(evaluator.instructions.get(i));
            if (OpCode.values()[evaluator.instructions.get(i)] == OpCode.iconst) {
                outputStream.writeInt(evaluator.instructions.get(i + 1));
                i++;
            }
        }
        outputStream.close();
    }

    public void compile(String inputFile, String outputFile) throws Exception{
        InputStream is = System.in;
        if (inputFile != null)
            is = new FileInputStream(inputFile);

        CharStream input = CharStreams.fromStream(is);
        LExprLexer lexer = new LExprLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LExprParser parser = new LExprParser(tokens);

        ParseTree tree = parser.s();
        ParseTreeWalker walker = new ParseTreeWalker();

        Evaluator evaluator = new Evaluator();
        walker.walk(evaluator, tree);

        generateByteCode(evaluator, outputFile);

        VirtualMachine virtualMachine = new VirtualMachine();

    }
}