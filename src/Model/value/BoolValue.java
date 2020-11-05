package Model.value;
import Model.type.BoolType;
import Model.type.TypeInterface;
import Model.value.ValueInterface;

public class BoolValue implements ValueInterface{
    boolean value;

    public BoolValue(boolean value)
    {
        this.value = value;
    }

    public boolean getValue()
    {
        return this.value;
    }

    @Override
    public String toString()
    {
        return String.valueOf(value);
    }

    @Override
    public TypeInterface getType() {
        return new BoolType();
    }

    @Override
    public ValueInterface deepCopy() {
        return new BoolValue(this.value);
    }
}
