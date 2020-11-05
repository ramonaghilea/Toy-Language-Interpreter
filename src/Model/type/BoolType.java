package Model.type;
import Model.type.TypeInterface;

public class BoolType implements TypeInterface{
    public boolean equals(Object anotherObject)
    {
        if(anotherObject instanceof BoolType)
            return true;
        else
            return false;
    }

    public boolean getDefaultValue()
    {
        return false;
    }

    public String toString()
    {
        return "bool";
    }

    @Override
    public TypeInterface deepCopy() {
        return new BoolType();
    }
}
