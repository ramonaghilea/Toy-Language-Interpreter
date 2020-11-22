package Model.type;

import Model.value.ReferenceValue;
import Model.value.ValueInterface;

public class ReferenceType implements TypeInterface{
    private TypeInterface innerType;

    public ReferenceType() {};
    public ReferenceType(TypeInterface innerType) { this.innerType = innerType; }

    public TypeInterface getInnerType() {
        return innerType;
    }

    public boolean equals(Object anotherObject)
    {
        if(anotherObject instanceof ReferenceType)
            return this.innerType.equals(((ReferenceType) anotherObject).getInnerType());
        else
            return false;
    }

    @Override
    public TypeInterface deepCopy() {
        TypeInterface copyInner = this.innerType.deepCopy();
        ReferenceType copy = new ReferenceType(copyInner);
        return copy;
    }

    @Override
    public ValueInterface defaultValue() {
        return new ReferenceValue(0, innerType);
    }

    @Override
    public String toString() {
        return "Reference(" + innerType.toString() + ")";
    }

}
