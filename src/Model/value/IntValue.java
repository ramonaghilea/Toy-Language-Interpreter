package Model.value;
import Model.type.IntType;
import Model.type.TypeInterface;
import Model.value.ValueInterface;

public class IntValue implements ValueInterface{
    int value;

    public IntValue(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return this.value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public TypeInterface getType()
    {
        return new IntType();
    }

    @Override
    public ValueInterface deepCopy() {
        return new IntValue(this.value);
    }
}
