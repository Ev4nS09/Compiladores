
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.*;
import java.util.*;

import Antlr.*;

public class solCompiler
{
    private static class Visitor extends SolBaseVisitor<Class<?>>
    {
        protected final LinkedList<Instruction> instructions;
        protected final ConstantPool pool;

        public Visitor()
        {
            this.instructions = new LinkedList<>();
            this.pool = new ConstantPool();
        }

        private boolean isNumber(Class<?> left, Class<?> right)
        {
            return (left == double.class || left == int.class) && (right == double.class || right == int.class);
        }

        private Class<?> doubleInt(int index, OpCode integerInstruction, OpCode doubleInstruction, Class<?> left, Class<?> right)
        {

            Class<?> result;

            if(left == int.class && right == int.class)
            {
                this.instructions.add(new Instruction(integerInstruction));
                result = int.class;
            }
            else if(left == int.class)
            {
                this.instructions.add(index, new Instruction(OpCode.itod));
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

        private Class<?> stringAdd(int index, Class<?> left, Class<?> right)
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
                    this.instructions.add(index, new Instruction(OpCode.itos));
                }
                else if(left == double.class)
                {
                    this.instructions.add(index, new Instruction(OpCode.dtos));
                }
                else if(left == boolean.class)
                {
                    this.instructions.add(index, new Instruction(OpCode.btos));
                }
            }

            this.instructions.add(new Instruction(OpCode.sadd));

            return String.class;
        }

        private Class<?> add(int index, Class<?> left, Class<?> right)
        {
            if((left != String.class && right != String.class) && (left == boolean.class || right == boolean.class))
            {
                Flaw.Error("Cannot add a " + left.getName() + "with a " + right.getName());
            }

            Class<?> result;

            if(isNumber(left, right))
            {
                result = doubleInt(index, OpCode.iadd, OpCode.dadd, left, right);
            }
            else
            {
                result = stringAdd(index, left, right);
            }

            return result;

        }

        private Class<?> sub(int index, Class<?> left, Class<?> right)
        {
            if(!isNumber(left, right))
            {
                Flaw.Error("Cannot sub a " + left.getName() + "with a " + right.getName());
            }

            return doubleInt(index, OpCode.isub, OpCode.dsub, left, right);
        }

        @Override
        public Class<?> visitAddSub(SolParser.AddSubContext ctx)
        {
            Class<?> result;

            Class<?> left = visit(ctx.expression(0));
            int index = this.instructions.size();
            Class<?> right = visit(ctx.expression(1));

            if(ctx.op.getText().equals("+"))
            {
                result = add(index, left, right);
            }
            else
            {
                result = sub(index, left, right);
            }

            return result;
        }

        public Class<?> mult(int index, Class<?> left, Class<?> right)
        {
            if(!isNumber(left, right))
            {
                Flaw.Error("Cannot multiply a " + left.getName() + " with a " + right.getName());
            }

            return doubleInt(index, OpCode.imult, OpCode.dmult, left, right);
        }

        public Class<?> div(int index, Class<?> left, Class<?> right)
        {
            if(!isNumber(left, right))
            {
                Flaw.Error("Cannot divide a " + left.getName() + "with a " + right.getName());
            }

            return doubleInt(index, OpCode.idiv, OpCode.ddiv, left, right);
        }

        public Class<?> mod(int index, Class<?> left, Class<?> right)
        {
            if(left != int.class || right != int.class)
            {
                Flaw.Error("Cannot multiply a " + left.getName() + "with a " + right.getName());
            }

            return doubleInt(index, OpCode.imod, null, left, right);
        }

        @Override
        public Class<?> visitMultDivMod(SolParser.MultDivModContext ctx)
        {
            Class<?> result;

            Class<?> left = visit(ctx.expression(0));
            int index = this.instructions.size();
            Class<?> right = visit(ctx.expression(1));

            if(ctx.op.getText().equals("*"))
            {
                result = mult(index, left, right);
            }
            else if(ctx.op.getText().equals("/"))
            {
                result = div(index, left, right);
            }
            else
            {
                result = mod(index, left, right);
            }

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
                this.instructions.add(new Instruction(OpCode.iuminus));
                result = int.class;
            }
            else
            {
                this.instructions.add(new Instruction(OpCode.duminus));
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
            int index = this.instructions.size();
            Class<?> right = visit(ctx.expression(1));

            if(!isNumber(left, right))
            {
                Flaw.Error("Cannot compare a " + left.getName() + "with a " + right.getName());
            }

            if(ctx.op.getText().equals("<"))
            {
                doubleInt(index, OpCode.ilt, OpCode.dlt, left, right);

            }
            else if(ctx.op.getText().equals("<="))
            {
                doubleInt(index, OpCode.ileq, OpCode.dleq, left, right);
            }
            else if(ctx.op.getText().equals(">"))
            {
                doubleInt(index, OpCode.ileq, OpCode.dleq, left, right);
                this.instructions.add(new Instruction(OpCode.not));
            }
            else
            {
                doubleInt(index, OpCode.ilt, OpCode.dlt, left, right);
                this.instructions.add(new Instruction(OpCode.not));
            }

            return boolean.class;
        }

        private void equal(int index, Class<?> left, Class<?> right)
        {
            if(left == boolean.class)
            {
                this.instructions.add(new Instruction(OpCode.beq));
            }
            else if(left == String.class)
            {
                this.instructions.add(new Instruction(OpCode.seq));
            }
            else
            {
                doubleInt(index, OpCode.ieq, OpCode.deq, left, right);
            }
        }

        private void notEqual(int index, Class<?> left, Class<?> right)
        {
            if(left == boolean.class)
            {
                this.instructions.add(new Instruction(OpCode.bneq));
            }
            else if(left == String.class)
            {
                this.instructions.add(new Instruction(OpCode.sneq));
            }
            else
            {
                doubleInt(index, OpCode.ineq, OpCode.dneq, left, right);
            }
        }

        @Override
        public Class<?> visitIguality(SolParser.IgualityContext ctx)
        {
            Class<?> left = visit(ctx.expression(0));
            int index = this.instructions.size();
            Class<?> right = visit(ctx.expression(1));

            if(!isNumber(left, right) && !(left == boolean.class && right == boolean.class || left == String.class && right == String.class))
            {
                Flaw.Error("Cannot compare a " + left.getName() + " with a " + right.getName());
            }

            if(ctx.op.getText().equals("=="))
            {
                equal(index, left, right);
            }
            else
            {
                notEqual(index, left, right);
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
            Value real = new Value(Double.parseDouble(ctx.DOUBLE().getText()));

            this.pool.add(real);
            this.instructions.add(new Instruction(OpCode.dconst, new Value(this.pool.getPoolPosition(real))));

            return double.class;
        }

        @Override
        public Class<?> visitString(SolParser.StringContext ctx)
        {
            Value string = new Value(ctx.STRING().getText());

            this.pool.add(string);
            this.instructions.add(new Instruction(OpCode.dconst, new Value(this.pool.getPoolPosition(string))));

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

            else if(type == boolean.class)
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

    private void generateByteCode(LinkedList<Instruction> instructions, LinkedList<Value> constantPool, String outputFile) throws Exception
    {
        DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(outputFile));

        outputStream.writeInt(constantPool.size());

        //Generates Bytes for constant pool
        for(Value value : constantPool)
        {
            if(value.getValueType() == Double.class)
            {
                outputStream.writeByte(OpCode.dconst.ordinal());
                outputStream.writeDouble(value.getDouble());
            }

            else if(value.getValueType() == String.class)
            {
                outputStream.writeByte(OpCode.sconst.ordinal());
                String argument = value.getString();
                outputStream.writeInt(argument.length());
                outputStream.writeChars(argument);
            }
        }

        //Generates bytes for instructions
        for (Instruction instruction : instructions)
        {
            outputStream.writeByte(instruction.getInstruction().ordinal());

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

        this.generateByteCode(visitor.instructions, visitor.pool.getValueList(), outputFile);

    }
}