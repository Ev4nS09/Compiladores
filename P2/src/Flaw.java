public abstract class Flaw
{
    public static void Error(String message)
    {
        System.err.println("Error: " + message);
        System.exit(1);
    }
}
