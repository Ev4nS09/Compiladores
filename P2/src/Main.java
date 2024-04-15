import java.util.ArrayList;

public class Main {

    public void main(String[] args) throws Exception
    {
        System.out.println("ola" + true);
        tAssembler compiler = new tAssembler();
        compiler.compile("inputs/multTest.sol", "inputs/multTest.tbc");

        tVM vm = new tVM();
        vm.execute("inputs/multTest.tbc");
    }
}
