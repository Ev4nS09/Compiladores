
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

        private boolean isNumber(Class<?> left, Class<?> right)
        {
            return (left == double.class || left == int.class) && (right == double.class || right == int.class);
        }

        private Class<?> doubleInt(OpCode integerInstruction, OpCode doubleInstruction, Class<?> left, Class<?> right)
        {

            Class<?> result;

            if(left == int.class && right == int.class)
            {
                this.instructions.add(new Instruction(integerInstruction));
                result = int.class;
            }
            else if(left == int.class)
            {
                this.instructions.add(this.instructions.size()-1, new Instruction(OpCode.itod));
                this.instructions.add(new Instruction(doubleInstruction));
                result = double.class;
            }
            else if(right == int.class)
            {
                this.instructions.add(new Instruction(OpCode.itod));
                this.instructions.add(new Instruction(doubleInstruction));
                result = double.class;
            }
            else
            {
                this.instructions.add(new Instruction(doubleInstruction));
                result = double.class;
            }

            return result;
        }

        private Class<?> stringAdd(Class<?> left, Class<?> right)
        {
            if(left == String.class)
            {
                if(right == int.class)
                {
                    this.instructions.add(new Instruction(OpCode.itos));
                }
                else if(right == double.class)
                {
                    this.instructions.add(new Instruction(OpCode.dtos));
                }
                else if(right == boolean.class)
                {
                    this.instructions.add(new Instruction(OpCode.btos));
                }
            }
            else
            {
                if(left == int.class)
                {
                    this.instructions.add(this.instructions.size()-1, new Instruction(OpCode.itos));
                }
                else if(left == double.class)
                {
                    this.instructions.add(this.instructions.size()-1, new Instruction(OpCode.dtos));
                }
                else if(left == boolean.class)
                {
                    this.instructions.add(this.instructions.size()-1, new Instruction(OpCode.btos));
                }
            }

            this.instructions.add(new Instruction(OpCode.sadd));

            return String.class;
        }

        private Class<?> add(Class<?> left, Class<?> right)
        {
            if((left != String.class && right != String.class) && (left == boolean.class || right == boolean.class))
            {
                Flaw.Error("Cannot add a " + left.getName() + "with a " + right.getName());
            }

            Class<?> result;

            if(isNumber(left, right))
            {
                result = doubleInt(OpCode.iadd, OpCode.dadd, left, right);
            }
            else
            {
                result = stringAdd(left, right);
            }

            return result;

        }

        private Class<?> sub(Class<?> left, Class<?> right)
        {
            if(!isNumber(left, right))
            {
                Flaw.Error("Cannot sub a " + left.getName() + "with a " + right.getName());
            }

            return doubleInt(OpCode.isub, OpCode.dsub, left, right);
        }

        @Override
        public Class<?> visitAddSub(SolParser.AddSubContext ctx)
        {
            Class<?> result;

            Class<?> left = visit(ctx.expression(0));
            Class<?> right = visit(ctx.expression(1));

            if(ctx.op.getText().equals("+"))
            {
                result = add(left, right);
            }
            else
            {
                result = sub(left, right);
            }

            return result;
        }

        public Class<?> mult(Class<?> left, Class<?> right)
        {
            if(!(left == int.class || left == double.class) && !(right == int.class || right == double.class))
            {
                Flaw.Error("Cannot multiply a " + left.getName() + "with a " + right.getName());
            }

            return doubleInt(OpCode.imult, OpCode.dmult, left, right);
        }

        public Class<?> div(Class<?> left, Class<?> right)
        {
            if(!(left == int.class || left == double.class) && !(right == int.class || right == double.class))
            {
                Flaw.Error("Cannot divide a " + left.getName() + "with a " + right.getName());
            }

            return doubleInt(OpCode.idiv, OpCode.ddiv, left, right);
        }

        public Class<?> mod(Class<?> left, Class<?> right)
        {
            if(left != int.class || right != int.class)
            {
                Flaw.Error("Cannot multiply a " + left.getName() + "with a " + right.getName());
            }

            return doubleInt(OpCode.imod, null, left, right);
        }

        @Override
        public Class<?> visitMultDivMod(SolParser.MultDivModContext ctx)
        {
            Class<?> result;

            Class<?> left = visit(ctx.expression(0));
            Class<?> right = visit(ctx.expression(1));

            if(ctx.op.getText().equals("*"))
            {
                result = mult(left, right);
            }
            else if(ctx.op.getText().equals("/"))
            {
                result = div(left, right);
            }
            else
            {
                result = mod(left, right);
            }

            System.out.println(result);
            return result;
        }

        private Class<?> unaryMinus(Class<?> argumentClass)
        {
            if(argumentClass != int.class && argumentClass != double.class)
            {
                Flaw.Error("Cannot apply the unary operation '-' to " + argumentClass.getName());
            }

            Class<?> result;

            if(argumentClass == int.class)
            {
                this.instructions.add(new Instruction(OpCode.isub));
                result = int.class;
            }
            else
            {
                this.instructions.add(new Instruction(OpCode.dsub));
                result = double.class;
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
        public Class<?> visitRelational(SolParser.RelationalContext ctx)
        {
            Class<?> left = visit(ctx.expression(0));
            Class<?> right = visit(ctx.expression(1));

            if(!isNumber(left, right))
            {
                Flaw.Error("Cannot compare a " + left.getName() + "with a " + right.getName());
            }

            if(ctx.op.getText().equals("<"))
            {
                doubleInt(OpCode.ileq, OpCode.dleq, left, right);
                this.instructions.add(new Instruction(OpCode.not));

            }
            else if(ctx.op.getText().equals("<="))
            {
                doubleInt(OpCode.ilt, OpCode.dlt, left, right);
                this.instructions.add(new Instruction(OpCode.not));
            }
            else if(ctx.op.getText().equals(">"))
            {
                doubleInt(OpCode.ilt, OpCode.dlt, left, right);
            }
            else
            {
                doubleInt(OpCode.ileq, OpCode.dleq, left, right);
            }

            return boolean.class;
        }

        @Override
        public Class<?> visitIguality(SolParser.IgualityContext ctx)
        {
            Class<?> left = visit(ctx.expression(0));
            Class<?> right = visit(ctx.expression(1));

            if(!((left == boolean.class && right == boolean.class) || (left == String.class && right == String.class)))
            {
                Flaw.Error("Cannot compare a " + left.getName() + "with a " + right.getName());
            }

            if(left == boolean.class)
            {
                OpCode opCode = ctx.op.getText().equals("==") ? OpCode.beq : OpCode.bneq;
                this.instructions.add(new Instruction(opCode));
            }
            else if(left == String.class)
            {
                OpCode opCode = ctx.op.getText().equals("==") ? OpCode.seq : OpCode.sneq;
                this.instructions.add(new Instruction(opCode));
            }
            else
            {
               if(ctx.op.getText().equals("=="))
                   doubleInt(OpCode.ieq, OpCode.deq, left, right);
               else
                   doubleInt(OpCode.ineq, OpCode.dneq, left, right);
            }

            return boolean.class;
        }

        @Override
        public Class<?> visitLRParen(SolParser.LRParenContext ctx)
        {
            return visit(ctx.expression());
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

            if(!this.constantPoolCache.containsKey(real))
            {
                this.constantPoolCache.put(real, this.constantPool.size());
            }


            this.constantPool.add(new Instruction(OpCode.dconst, new Value(real)));
            this.instructions.add(new Instruction(OpCode.dconst, new Value(this.constantPoolCache.get(real))));

            return double.class;
        }

        @Override
        public Class<?> visitString(SolParser.StringContext ctx)
        {
            String string = ctx.STRING().getText();

            if(!this.constantPoolCache.containsKey(string))
            {
                this.constantPoolCache.put(string, this.constantPool.size());
            }

            this.constantPool.add(new Instruction(OpCode.sconst, new Value(string)));
            this.instructions.add(new Instruction(OpCode.sconst, new Value(this.constantPoolCache.get(string))));

            return String.class;
        }

        @Override
        public Class<?> visitBool(SolParser.BoolContext ctx)
        {
            boolean bool = Boolean.parseBoolean(ctx.BOOL().getText());

            if(bool)
                this.instructions.add(new Instruction(OpCode.tconst));

            else
                this.instructions.add(new Instruction(OpCode.fconst));

            return boolean.class;
        }

        @Override
        public Class<?> visitInstruction(SolParser.InstructionContext ctx)
        {
            Class<?> type = visit(ctx.expression());

            if(type == int.class)
                this.instructions.add(new Instruction(OpCode.iprint));
            else if(type == double.class)
                this.instructions.add(new Instruction(OpCode.dprint));
            else if(type == String.class)
                this.instructions.add(new Instruction(OpCode.sprint));
            else
                this.instructions.add(new Instruction(OpCode.bprint));

            return type;
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

        System.out.println(visitor.instructions);

        this.generateByteCode(visitor.instructions, visitor.constantPool, outputFile);

    }
}