package Model.type;

import Model.value.StringValue;
import Model.value.ValueInterface;

public class StringType implements TypeInterface{
    public boolean equals(Object anotherObject)
    {
        if(anotherObject instanceof StringType)
            return true;
        else
            return false;
    }
    @Override
    public TypeInterface deepCopy() {
        return new StringType();
    }

    @Override
    public ValueInterface defaultValue() {
        return new StringValue("");
    }

    @Override
    public String toString() {
        return "string";
    }
}
