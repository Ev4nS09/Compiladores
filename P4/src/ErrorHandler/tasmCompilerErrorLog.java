package ErrorHandler;

import org.antlr.v4.runtime.ParserRuleContext;

public class tasmCompilerErrorLog extends ErrorLog
{

    public void redefinedTag(ParserRuleContext node, String label)
    {
        String message = "Tag '" + label + "' is already defined.";

        super.throwError(node, message);
    }
    public void undefinedTag(ParserRuleContext node, String label)
    {
        String message = "Tag '" + label + "' has not been defined.";

        super.throwError(node, message);
    }
}
