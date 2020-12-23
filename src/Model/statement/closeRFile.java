package Model.statement;

import Model.ADT.ADTDictionary;
import Model.ADT.DictionaryInterface;
import Model.ADT.HeapInterface;
import Model.ProgramState;
import Model.exceptions.ExpressionEvaluationException;
import Model.exceptions.StatementException;
import Model.expression.ExpressionInterface;
import Model.type.BoolType;
import Model.type.StringType;
import Model.type.TypeInterface;
import Model.value.StringValue;
import Model.value.ValueInterface;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class closeRFile implements StatementInterface{
    private ExpressionInterface expression;

    public closeRFile(ExpressionInterface expression) { this.expression = expression; }

    @Override
    public ProgramState execute(ProgramState programState) throws Exception {
        //StackInterface<StatementInterface> stack = programState.getStack();
        DictionaryInterface<String, ValueInterface> symbolTable = programState.getSymbolTable();
        DictionaryInterface<StringValue, BufferedReader> fileTable = programState.getFileTable();
        HeapInterface<Integer, ValueInterface> heap = programState.getHeap();

        // evaluate the expression
        ValueInterface value = this.expression.evaluate(symbolTable, heap);
        // check if it is a String
        if(value.getType().equals(new StringType()))
        {
            StringValue fileName = (StringValue) value;
            // check if the file is defined in the file table
            if(fileTable.isDefined(fileName))
            {
                BufferedReader reader = fileTable.lookUp(fileName);
                // close the file
                try{
                    reader.close();
                }
                catch (IOException exception) { throw new StatementException("Error at closing the file"); }

                // delete the entry in the file table
                fileTable.delete(fileName);
            }
            else
                throw new StatementException("string value does not exist in the file table");
        }
        else
            throw new StatementException("the type is not string");

        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        ExpressionInterface copyExpression = this.expression.deepCopy();
        closeRFile copy = new closeRFile(copyExpression);

        return copy;
    }

    @Override
    public ADTDictionary<String, TypeInterface> typeCheck(ADTDictionary<String, TypeInterface> typeEnv) throws ExpressionEvaluationException {
        TypeInterface typeExpression = this.expression.typeCheck(typeEnv);
        if(typeExpression.equals(new StringType()))
            return typeEnv;
        else
            throw new ExpressionEvaluationException("The expression of CLOSERFILE has not the string type");
    }
}
