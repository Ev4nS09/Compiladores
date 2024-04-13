public class Cast
{
    public static String toString(Object object)
    {
        if(!(object instanceof String))
            Flaw.Error("Couldn't cast to String, because the object provided is not an instance of String");

        return (String) object;
    }

    public static Integer toInteger(Object object)
    {
        if(!(object instanceof Integer))
            Flaw.Error("Couldn't cast to Integer, because the object provided is not an instance of Integer");

        return (Integer) object;
    }

    public static Double toDouble(Object object)
    {
        if(!(object instanceof Double))
            Flaw.Error("Couldn't cast to Double, because the object provided is not an instance of Double");

        return (Double) object;
    }


    public static Boolean toBoolean(Object object)
    {
        if(!(object instanceof Boolean))
            Flaw.Error("Couldn't cast to Boolean, because the object provided is not an instance of Boolean");

        return (Boolean) object;
    }

}
