
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.*;
import java.sql.SQLOutput;
import java.util.*;

import Antlr.*;

public class tAssembler
{
    private static class Visitor extends SolBaseVisitor<Class<?>>
    {

        protected final LinkedList<Instruction> instructions;
        protected final LinkedList<Instruction> constantPool;

        protected final HashMap<Object, Integer> constantPoolCache;

        public Visitor()
        {
            this.instructions = new LinkedList<>();
            this.constantPool = new LinkedList<>();
            this.constantPoolCache = new HashMap<>();
        }

        private Class<?> mult(Class<?> left, Class<?> right)
        {
            if(!(left == int.class || left == double.class) && !(right == int.class || right == double.class))
            {
                Flaw.Error("Cannot multiply a " + left.getName() + "with a " + right.getName());
            }

            Class<?> result;

            if(left == int.class && right == int.class)
            {
                this.instructions.add(new Instruction(OpCode.imult));
                result = int.class;
            }
            else if(left == int.class)
            {
            }

            return null;
        }

        @Override
        public Class<?> visitMultDivMod(SolParser.MultDivModContext ctx)
        {
            Class<?> result;

            if(ctx.op.getText().equals('*'))
            {

            }
            else if(ctx.op.getText().equals('/'))
            {

            }
            else
            {

            }

            return null;
        }

        private Class<?> unaryMinus(Class<?> argumentClass)
        {
            Class<?> result;

            if(argumentClass == int.class)
            {
                this.instructions.add(new Instruction(OpCode.isub));
                result = int.class;
            }
            else if(argumentClass == double.class)
            {
                this.instructions.add(new Instruction(OpCode.dsub));
                result = double.class;
            }
            else
            {
                Flaw.Error("Cannot apply the unary operation '-' to " + argumentClass.getName());
                result = null;
            }

            return result;
        }

        private Class<?> unaryNot(Class<?> argumentClass)
        {
            if(argumentClass != boolean.class)
            {
                Flaw.Error("Cannot apply the unary operation 'not' to " + argumentClass.getName());
            }

            this.instructions.add(new Instruction(OpCode.not));
            return boolean.class;
        }

        @Override
        public Class<?> visitUnary(SolParser.UnaryContext ctx)
        {
            Class<?> result;

            if(ctx.op.getText().equals("-"))
            {
                result = unaryMinus(visit(ctx.expression()));
            }
            else
            {
                result = unaryNot(visit(ctx.expression()));
            }

            return result;
        }

        @Override
        public Class<?> visitAnd(SolParser.AndContext ctx)
        {
            Class<?> left = visit(ctx.expression(0));
            Class<?> right = visit(ctx.expression(1));

            if(left != boolean.class || right != boolean.class)
            {
                Flaw.Error("The 'and' operation only accepts two booleans");
            }

            this.instructions.add(new Instruction(OpCode.and));

            return boolean.class;
        }

        @Override
        public Class<?> visitOr(SolParser.OrContext ctx)
        {
            Class<?> left = visit(ctx.expression(0));
            Class<?> right = visit(ctx.expression(1));

            if(left != boolean.class || right != boolean.class)
            {
                Flaw.Error("The 'or' operation only accepts two booleans");
            }

            this.instructions.add(new Instruction(OpCode.or));

            return boolean.class;
        }

        @Override
        public Class<?> visitInt(SolParser.IntContext ctx)
        {
            int integer = Integer.parseInt(ctx.INT().getText());
            this.instructions.add(new Instruction(OpCode.iconst, new Value(integer)));

            return int.class;
        }

        @Override
        public Class<?> visitDouble(SolParser.DoubleContext ctx)
        {
            double real = Double.parseDouble(ctx.DOUBLE().getText());

            this.constantPool.add(new Instruction(OpCode.dconst, new Value(real)));
            this.instructions.add(new Instruction(OpCode.dconst, new Value(this.constantPool.size()-1)));

            return double.class;
        }

        @Override
        public Class<?> visitString(SolParser.StringContext ctx)
        {
            String string = ctx.STRING().getText();

            this.constantPool.add(new Instruction(OpCode.sconst, new Value(string)));
            this.instructions.add(new Instruction(OpCode.sconst, new Value(this.constantPool.size()-1)));

            return String.class;
        }

        @Override
        public Class<?> visitBool(SolParser.BoolContext ctx)
        {
            boolean bool = Boolean.parseBoolean(ctx.BOOL().getText());

            if(bool)
                this.instructions.add(new Instruction(OpCode.tconst, new Value(true)));

            else
                this.instructions.add(new Instruction(OpCode.fconst, new Value(false)));

            return boolean.class;
        }

        @Override
        public String toString()
        {
            StringBuilder result = new StringBuilder();

            for(int i = 0; i < this.instructions.size(); i++)
                result.append(i).append(": ").append(this.instructions.get(i).toString()).append('\n');

            return result.toString();
        }
    }

    private SolParser generateParser(InputStream inputStream) throws Exception{

        SolLexer lexer = new SolLexer(CharStreams.fromStream(inputStream));
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        return new SolParser(tokens);
    }

    private void generateByteCode(LinkedList<Instruction> instructions, LinkedList<Instruction> constantPool, String outputFile) throws Exception
    {
        DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(outputFile));

        outputStream.writeInt(constantPool.size());

        //Generates Bytes for constant pool
        for(Instruction instruction : constantPool)
        {
            OpCode instructionOpCode = instruction.getInstruction();
            outputStream.writeByte(instructionOpCode.ordinal());

            if(instructionOpCode == OpCode.dconst)
            {
                outputStream.writeDouble(instruction.getArgument().getDouble());
            }

            else if(instructionOpCode == OpCode.sconst)
            {
                String argument = instruction.getArgument().getString();
                outputStream.writeInt(argument.length());
                outputStream.writeChars(argument);
            }
        }

        //Generates bytes for instructions
        for (Instruction instruction : instructions)
        {
            OpCode instructionOpCode = instruction.getInstruction();
            outputStream.writeByte(instructionOpCode.ordinal());

            if(instruction.hasArgument())
            {
                outputStream.writeInt(instruction.getArgument().getInteger());
            }

        }

        outputStream.close();
    }

    public void compile(String inputFile, String outputFile) throws Exception
    {
        InputStream inputStream = inputFile == null ? System.in : new FileInputStream(inputFile);

        SolParser parser = generateParser(inputStream);

        ParseTree tree = parser.sol();

        if(parser.getNumberOfSyntaxErrors() > 0)
            System.exit(1);

        Visitor visitor = new Visitor();
        visitor.visit(tree);

        this.generateByteCode(visitor.instructions, visitor.constantPool, outputFile);

    }
}