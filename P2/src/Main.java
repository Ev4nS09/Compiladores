public class Main {

    public static void main(String[] args) throws Exception
    {
        Value v = new Value("Ola!");
        Value v1 = new Value("Ola!");
        Value v2 = new Value(new Thread());

        System.out.println(v.equals(v1));

        System.out.println( 1 + 2.0 + "Ola" + true + false + 1);
        solCompiler compiler = new solCompiler();
        compiler.compile("inputs/multTest.sol", "inputs/multTest.tbc");

        tVM vm = new tVM();
        vm.execute("inputs/multTest.tbc");
    }
}
