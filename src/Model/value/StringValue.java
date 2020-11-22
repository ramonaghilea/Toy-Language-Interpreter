package Model.value;

import Model.type.BoolType;
import Model.type.StringType;
import Model.type.TypeInterface;

public class StringValue implements ValueInterface{
    private String value;

    public StringValue(String value) { this.value = value; }

    public String getValue() { return this.value; }

    @Override
    public TypeInterface getType() {
        return new StringType();
    }

    @Override
    public ValueInterface deepCopy() {
        return new StringValue(this.value);
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object anotherObject) {
        if(anotherObject instanceof StringValue)
        {
            StringValue stringObject = (StringValue) anotherObject;
            return value.equals(stringObject.getValue());
        }
        return false;
    }
}
