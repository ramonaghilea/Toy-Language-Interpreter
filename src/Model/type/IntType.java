package Model.type;
import Model.type.TypeInterface;

public class IntType implements TypeInterface{
    public boolean equals(Object anotherObject)
    {
        if(anotherObject instanceof IntType)
            return true;
        else
            return false;
    }

    public int getDefaultValue()
    {
        return 0;
    }

    public String toString()
    {
        return "int";
    }

    @Override
    public TypeInterface deepCopy() {
        return new IntType();
    }
}
