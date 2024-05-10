import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class tasmGenerator {

    public tasmGenerator(LinkedList<Instruction> intruction, ConstantPool<Value> pool, String outputFile) throws Exception {
        generateTasm_HASH(intruction, pool, outputFile);
    }

    public void generateTasm_HASH(LinkedList<Instruction> instructions, ConstantPool<Value> pool, String outputFile) throws Exception {
        FileWriter outputStream = new FileWriter(outputFile);
        ArrayList<String> instructions_String = new ArrayList<>();
        HashSet<String> hashJump = new HashSet<>();

        //instruction em String
        for(Instruction instruction : instructions)
        {
            String line;

            if(instruction.getInstruction() == OpCode.dconst)
                line = "dconst " + pool.getPoolValue(instruction.getArgument().getInteger());
            else if(instruction.getInstruction() == OpCode.sconst)
                line = "sconst " + pool.getPoolValue(instruction.getArgument().getInteger());
            else
                line = instruction.toString();

            instructions_String.add(line);
        }

        for (int i = 0; i < instructions_String.size(); i++)
        {
            String line = instructions_String.get(i);

            if (isJump(line.split(" ")[0]))
            { //confirma se há um jump na linha
                int jump_position = Integer.parseInt(line.split(" ")[1]); //numero da linha onde vai para o jump
                instructions_String.set(i, line.split(" ")[0] + " Tag_" + (jump_position+1)); //linha onde esta neste momento

                if (!hashJump.contains("Tag_"+ jump_position+1)) //impede que a Tag seja escrita de novo
                    instructions_String.set(jump_position,"Tag_" + (jump_position+1) + ": " +  instructions_String.get(jump_position)); //mudar a linha onde vai parar

                hashJump.add("Tag_" + jump_position+1);
            }
        }
        for (String line : instructions_String){
            outputStream.write(line + "\n");
        }
        outputStream.close();
    }



    public boolean isJump(String jump) {
        return jump.equals("jumpf") || jump.equals("jump") || jump.equals("jumpt");
    }
}