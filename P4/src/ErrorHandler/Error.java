package ErrorHandler;

public class Error
{
   private final int errorLine;
   private final String errorString;
   private final String errorMessage;

   public Error(int errorLine, String errorString, String errorMessage)
   {
      this.errorLine = errorLine;
      this.errorString = errorString;
      this.errorMessage = errorMessage;
   }

    public int getErrorLine()
    {
        return errorLine;
    }

    public String getErrorString()
    {
        return errorString;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }

    @Override
    public String toString()
    {
        return "line " + errorLine + ": " + errorMessage + " " + "\n" + errorString;
    }
}
