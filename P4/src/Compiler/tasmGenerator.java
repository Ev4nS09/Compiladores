package Compiler;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

import solUtils.*;


public class tasmGenerator {

    public tasmGenerator(LinkedList<Instruction> intruction, ConstantPool<Value> pool, String outputFile) throws Exception {
        generateTasm_HASH(intruction, pool, outputFile);
    }

    public void generateTasm_HASH(LinkedList<Instruction> instructions, ConstantPool<Value> pool, String outputFile) throws Exception {
        FileWriter outputStream = new FileWriter(outputFile);
        ArrayList<String> instructions_String = new ArrayList<>();
//        HashSet<String> hashJump = new HashSet<>();

        //instruction em String
        for (int i = 0; i < instructions.size(); i++) {
            Instruction instruction = instructions.get(i);
            String line = "L" + i + ": ";


            if (instruction.getInstruction() == OpCode.dconst)
                line += "dconst " + pool.getPoolValue(instruction.getArgument().getInteger());
            else if (instruction.getInstruction() == OpCode.sconst)
              line += "sconst " + pool.getPoolValue(instruction.getArgument().getInteger());
            else
            line += instruction.toString();

            instructions_String.add(line);
        }

        for (int i = 0; i < instructions_String.size(); i++)
        {
            String line = instructions_String.get(i);

            if (isJump(line.split(" ")[1]))
            { //confirma se hÃ¡ um jump na linha

                int jump_position = Integer.parseInt(line.split(" ")[2]); //numero da linha onde vai para o jump
                String newLine = "L"+i+": " + line.split(" ")[1] + " L" + (jump_position);

                instructions_String.set(i, newLine); //linha onde esta neste momento
            }
        }
        for (String line : instructions_String){
            outputStream.write(line + "\n");
        }
        outputStream.close();
    }

    public boolean isJump(String jump) {
        return jump.equals("call") || jump.equals("jumpf") || jump.equals("jump") || jump.equals("jumpt");
    }
}