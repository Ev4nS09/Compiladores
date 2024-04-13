public class Value
{
    Class<?>[] VALID_TYPES = {Integer.class, Double.class, String.class, Boolean.class};
    Object value;

    public Value(Object value)
    {
        this.value = value;
    }

    public Object getObject()
    {
        return this.value;
    }

    public Integer getInteger()
    {
        if(!(this.value instanceof Integer))
            Flaw.Error("Couldn't cast to Integer, because the value is not an instance of Integer");

        return (Integer) this.value;
    }

    public Double getDouble()
    {
        if(!(this.value instanceof Double))
            Flaw.Error("Couldn't cast to Double, because the value is not an instance of Double");

        return (Double) this.value;
    }

    public String getString()
    {
        if(!(this.value instanceof String))
            Flaw.Error("Couldn't cast to String, because the value is not an instance of String");

        return (String) this.value;
    }

    public Boolean getBoolean()
    {
        if(!(this.value instanceof Boolean))
            Flaw.Error("Couldn't cast to Boolean, because the value is not an instance of Boolean");

        return (Boolean) this.value;
    }


    @Override
    public String toString()
    {
        return this.value.toString();
    }
}
