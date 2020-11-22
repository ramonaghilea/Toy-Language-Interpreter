package Model.statement;

import Model.ADT.DictionaryInterface;
import Model.ADT.HeapInterface;
import Model.ADT.StackInterface;
import Model.ProgramState;
import Model.exceptions.StatementException;
import Model.expression.ExpressionInterface;
import Model.type.IntType;
import Model.type.ReferenceType;
import Model.type.TypeInterface;
import Model.value.ReferenceValue;
import Model.value.StringValue;
import Model.value.ValueInterface;

import java.io.BufferedReader;

public class NewStatement implements StatementInterface{
    private String variableName;
    private ExpressionInterface expression;

    public NewStatement(String variableName, ExpressionInterface expression)
    {
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws Exception {
        //StackInterface<StatementInterface> stack = programState.getStack();
        DictionaryInterface<String, ValueInterface> symbolTable = programState.getSymbolTable();
        //DictionaryInterface<StringValue, BufferedReader> fileTable = programState.getFileTable();
        HeapInterface<Integer, ValueInterface> heap = programState.getHeap();

        // check if the variable has been defined
        if(symbolTable.isDefined(variableName))
        {
            // check if its type is reference
            TypeInterface typeVariable = (symbolTable.lookUp(variableName)).getType();
            //if(typeVariable.equals(new ReferenceType(new IntType())))
            if(typeVariable instanceof ReferenceType)
            {
                // evaluate the expression and check if the type of the value and the location type
                // of the reference variable match
                ValueInterface value = expression.evaluate(symbolTable, heap);
                ReferenceType referenceVariable = (ReferenceType) typeVariable;
                if(value.getType().equals(referenceVariable.getInnerType()))
                {
                    // create a new entry in the Heap Table
                    heap.add(value);
                    // update in the symbol table the reference value associated to variableName
                    ReferenceValue newReferenceValue = new ReferenceValue(heap.getAddress(value), value.getType());
                    symbolTable.update(variableName, newReferenceValue);
                }
                else throw new StatementException("The type of the variable and the type of the expression do not match");
            }
            else
                throw new StatementException("The type of the variable is not reference type");
        }
        else
            throw new StatementException("The variable name is not defined in the symbol table");

        return programState;
    }

    @Override
    public String toString() {
        return "new(" + this.variableName + "," + this.expression.toString() + ")";
    }

    @Override
    public StatementInterface deepCopy()
    {
        String copyVariableName = this.variableName;
        ExpressionInterface copyExpression = this.expression.deepCopy();
        NewStatement copy = new NewStatement(copyVariableName, copyExpression);

        return copy;
    }
}
