package Model.type;
import Model.type.TypeInterface;
import Model.value.IntValue;
import Model.value.ValueInterface;

public class IntType implements TypeInterface{
    public boolean equals(Object anotherObject)
    {
        if(anotherObject instanceof IntType)
            return true;
        else
            return false;
    }

    public String toString()
    {
        return "int";
    }

    @Override
    public TypeInterface deepCopy() {
        return new IntType();
    }

    @Override
    public ValueInterface defaultValue() {
        return new IntValue(0);
    }
}
