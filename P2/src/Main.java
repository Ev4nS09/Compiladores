import java.util.ArrayList;

public class Main {

    public void main(String[] args) throws Exception
    {
        tAssembler compiler = new tAssembler();
        compiler.compile("inputs/multTest.sol", "inputs/multTest.tbc");

        tVM vm = new tVM();
        vm.execute("inputs/multTest.tbc");
    }
}
