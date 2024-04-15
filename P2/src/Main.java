public class Main {

    public void main(String[] args) throws Exception
    {
        System.out.println( 1 + 2.0 + "Ola" + true + false + 1);
        solCompiler compiler = new solCompiler();
        compiler.compile("inputs/multTest.sol", "inputs/multTest.tbc");

        tVM vm = new tVM();
        vm.execute("inputs/multTest.tbc");
    }
}
