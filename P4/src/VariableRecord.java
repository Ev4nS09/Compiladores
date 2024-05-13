import Antlr.SolBaseListener;
import ErrorHandler.ErrorLog;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.util.HashMap;

public class VariableRecord extends SolBaseListener
{
    private HashMap<String, Variable> variableCache;
    private ErrorLog errorLog;

    public VariableRecord(ErrorLog errorLog)
    {
        this.variableCache =  new HashMap<>();
        this.errorLog = errorLog;
    }

    public VariableRecord()
    {
        this(new ErrorLog());
    }




    public void checkVariables(ParseTree tree)
    {
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(this, tree);

        return this.variableCache;
    }


}
