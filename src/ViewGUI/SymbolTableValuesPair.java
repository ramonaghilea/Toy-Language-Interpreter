package ViewGUI;

import Model.value.ValueInterface;

public class SymbolTableValuesPair {
    private String variableName;
    private ValueInterface value;

    public SymbolTableValuesPair(String variableName, ValueInterface value)
    {
        this.variableName = variableName;
        this.value = value;
    }

    public String getVariableName()
    {
        return this.variableName;
    }
    public ValueInterface getValue()
    {
        return this.value;
    }
}
