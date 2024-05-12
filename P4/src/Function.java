public record Function(String name, int numberOfArgs, Class<?> returnType)
{
    @Override
    public boolean equals(Object that)
    {
        if(this == that)
            return true;
        if(that == null)
            return false;
        if(this.getClass() != that.getClass())
            return false;

        Function thatFunction = (Function) that;

        return this.name.equals(thatFunction.name) &&
                this.numberOfArgs == thatFunction.numberOfArgs &&
                this.returnType == thatFunction.returnType;
    }
}
