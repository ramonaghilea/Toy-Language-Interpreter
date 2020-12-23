package Model.statement;

import Model.ADT.ADTDictionary;
import Model.ADT.DictionaryInterface;
import Model.ADT.HeapInterface;
import Model.ProgramState;
import Model.exceptions.ExpressionEvaluationException;
import Model.exceptions.StatementException;
import Model.expression.ExpressionInterface;
import Model.type.IntType;
import Model.type.StringType;
import Model.type.TypeInterface;
import Model.value.IntValue;
import Model.value.StringValue;
import Model.value.ValueInterface;

import java.io.BufferedReader;

public class readFile implements StatementInterface{
    private ExpressionInterface expression;
    private String variableName;

    public readFile(ExpressionInterface expression, String variableName)
    {
        this.expression = expression;
        this.variableName = variableName;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws Exception {
        DictionaryInterface<String, ValueInterface> symbolTable = programState.getSymbolTable();
        DictionaryInterface<StringValue, BufferedReader> fileTable = programState.getFileTable();
        HeapInterface<Integer, ValueInterface> heap = programState.getHeap();

        // check if the variable which will store the read line is defined in the symbol table
        if(symbolTable.isDefined(variableName))
        {
            // check if the variable type is int
            if(symbolTable.lookUp(variableName).getType().equals(new IntType()))
            {
                // evaluate the expression
                ValueInterface value = this.expression.evaluate(symbolTable, heap);
                // check whether the value of the expression is a string
                if(value.getType().equals(new StringType()))
                {
                    // cast from ValueInterface to StringValue
                    StringValue fileName = (StringValue) value;
                    // get the reader associated to the fileName
                    BufferedReader reader = null;

                    if(fileTable.isDefined(fileName))
                        reader = fileTable.lookUp(fileName);
                    else
                        throw new StatementException("file does not exist in teh file table");

                    // read a line
                    String readLine = reader.readLine();
                    IntValue readLineAsIntValue = null;
                    if(readLine == null)
                        readLineAsIntValue = new IntValue(0);
                    else
                    {
                        int readInt = Integer.parseInt((readLine));
                        readLineAsIntValue = new IntValue(readInt);
                    }

                    // update the symbol table such that variableName is mapped to the readLineAsIntValue
                    symbolTable.update(variableName, readLineAsIntValue);

                }
                else
                    throw new StatementException("the value is not a string");
            }
            else
                throw new StatementException("the variable type is different form int");
        }
        else
            throw new StatementException("the variable is not defined in the symbol table");

        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        ExpressionInterface copyExpression = this.expression.deepCopy();
        String copyVariableName = this.variableName;
        readFile copy = new readFile(copyExpression, copyVariableName);

        return copy;
    }

    @Override
    public ADTDictionary<String, TypeInterface> typeCheck(ADTDictionary<String, TypeInterface> typeEnv) throws ExpressionEvaluationException {
        TypeInterface typeVariable = typeEnv.lookUp(this.variableName);
        TypeInterface typeExpression = this.expression.typeCheck(typeEnv);
        if(typeVariable.equals(new IntType()))
            if(typeExpression.equals(new StringType()))
                return typeEnv;
            else
                throw new ExpressionEvaluationException("The expression of OPENRFILE has not the string type");
        else
            throw new ExpressionEvaluationException("The variable is not an integer");
    }
}
