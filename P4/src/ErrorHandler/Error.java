package ErrorHandler;

public record Error(int errorLine, String errorString, String errorMessage)
{
    @Override
    public String toString()
    {
        return "line " + errorLine + ": " + errorMessage + " \n" + errorString;
    }
}
