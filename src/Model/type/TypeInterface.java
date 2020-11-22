package Model.type;

import Model.value.ValueInterface;

public interface TypeInterface {
    public TypeInterface deepCopy();
    public ValueInterface defaultValue();
}
