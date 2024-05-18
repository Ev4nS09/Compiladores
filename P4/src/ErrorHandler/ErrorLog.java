package ErrorHandler;

import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;


public class ErrorLog
{
    private final ArrayList<Error> errors;

    public ErrorLog()
    {
        this.errors = new ArrayList<>();
    }

    public void throwError(int line, String code, String message)
    {
        Error error = new Error(line, code, message);

        this.errors.add(error);
        System.err.println(error);
    }

    public void throwError(ParserRuleContext node, String message)
    {
        throwError(node.start.getLine(), "", message);
    }

    public static void fatalError(String message)
    {
        System.err.println("Fatal Error: " + message);
        System.exit(1);
    }

    public int getNumberOfErrors()
    {
       return this.errors.size();
    }
}
