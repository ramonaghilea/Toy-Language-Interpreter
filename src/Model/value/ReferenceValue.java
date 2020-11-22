package Model.value;

import Model.type.ReferenceType;
import Model.type.TypeInterface;

public class ReferenceValue implements ValueInterface{
    private int address;
    private TypeInterface locationType;

    public ReferenceValue(int address, TypeInterface locationType)
    {
        this.address = address;
        this.locationType = locationType;
    }

    public int getAddress() { return this.address; }

    @Override
    public TypeInterface getType() {
        return new ReferenceType(locationType);
        //return  new ReferenceType();
    }

    @Override
    public ValueInterface deepCopy() {
        int copyAddress = address;
        TypeInterface copyLocationType = locationType.deepCopy();
        ReferenceValue copy = new ReferenceValue(copyAddress, copyLocationType);

        return copy;
    }

    @Override
    public String toString() {
        return "(" + String.valueOf(this.address) + "," + this.locationType.toString() + ")";
    }
}
