public class Variable
{
    public String name;
    public Class<?> type;
    public boolean isInitialized;

    public Variable(String name, Class<?> type, boolean isInitialized)
    {
        this.name = name;
        this.type = type;
        this.isInitialized = isInitialized;
    }

    @Override
    public String toString()
    {
        return this.type.getName() + " " + this.name + ": " + this.isInitialized;
    }
}

