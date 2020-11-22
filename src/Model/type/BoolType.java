package Model.type;
import Model.type.TypeInterface;
import Model.value.BoolValue;
import Model.value.ValueInterface;

public class BoolType implements TypeInterface{
    public boolean equals(Object anotherObject)
    {
        if(anotherObject instanceof BoolType)
            return true;
        else
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

    @Override
    public ValueInterface defaultValue() {
        return new BoolValue(false);
    }
}
