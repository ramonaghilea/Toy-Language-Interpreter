package Model.statement;

import Model.ADT.ADTDictionary;
import Model.ADT.DictionaryInterface;
import Model.ADT.HeapInterface;
import Model.ProgramState;
import Model.exceptions.ExpressionEvaluationException;
import Model.exceptions.StatementException;
import Model.expression.ExpressionInterface;
import Model.type.IntType;
import Model.type.ReferenceType;
import Model.type.TypeInterface;
import Model.value.ReferenceValue;
import Model.value.ValueInterface;

import java.sql.Ref;

public class writeHeapStatement implements StatementInterface{
    private String variableName;
    private ExpressionInterface expression;

    public writeHeapStatement(String variableName, ExpressionInterface expression)
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

        // check if the variableName is defined in the symbol table
        if(symbolTable.isDefined(variableName))
        {
            // check if its type is a reference type
            TypeInterface typeVariable = (symbolTable.lookUp(variableName)).getType();
            if(typeVariable instanceof ReferenceType)
            {
                // check if the address from the reference value associated to variableName in symbol table
                // is a key in heap table
                ReferenceValue valueVariableName = (ReferenceValue) symbolTable.lookUp(variableName);
                int address = valueVariableName.getAddress();
                if(heap.isDefined(address))
                {
                    // evaluate the expression
                    ValueInterface valueExpression = this.expression.evaluate(symbolTable, heap);
                    // check if the type of the valueExpression and the location type of
                    // the reference variable match
                    ReferenceType referenceTypeValueVariableName = (ReferenceType) typeVariable;
                    TypeInterface innerTypeValueVariableName = referenceTypeValueVariableName.getInnerType();
                    if(valueExpression.getType().equals(innerTypeValueVariableName))
                    {
                        // update in the heap the address from variableName with the result of the expression evaluation
                        heap.update(address, valueExpression);
                    }
                    else throw new StatementException("The type of the variable and the type of the expression do not match");
                }
                else throw new StatementException("the address does not exist in the heap");
            }
            else throw new StatementException("the variable type is not a reference type");
        }
        else throw new StatementException("the variable is not defined in the symbol table");

        return null;
    }

    @Override
    public String toString() {
        return "writeHeap(" + this.variableName + "," + this.expression.toString() + ")";
    }

    @Override
    public StatementInterface deepCopy() {
        String copyVariableName = this.variableName;
        ExpressionInterface copyExpression = this.expression.deepCopy();
        writeHeapStatement copy = new writeHeapStatement(copyVariableName, copyExpression);

        return copy;
    }

    @Override
    public ADTDictionary<String, TypeInterface> typeCheck(ADTDictionary<String, TypeInterface> typeEnv) throws ExpressionEvaluationException {
        TypeInterface typeVariable = typeEnv.lookUp(this.variableName);
        TypeInterface typeExpression = this.expression.typeCheck(typeEnv);
        //if(typeVariable instanceof ReferenceType)
        if(typeVariable.equals(new ReferenceType(typeExpression)))
            return typeEnv;
        else
            throw new ExpressionEvaluationException("WRITEHEAP statement: right hand side and left hand side have different types");
    }
}
