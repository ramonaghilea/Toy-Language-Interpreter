package Model.value;
import Model.type.TypeInterface;

public interface ValueInterface {
    TypeInterface getType();
    ValueInterface deepCopy();
}
