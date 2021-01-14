package ViewGUI;

import Model.value.ValueInterface;

public class HeapValuesPair {
    private Integer address;
    private ValueInterface value;

    public HeapValuesPair(Integer address, ValueInterface value)
    {
        this.address = address;
        this.value = value;
    }

    public Integer getAddress()
    {
        return this.address;
    }

    public ValueInterface getValue()
    {
        return this.value;
    }
}
