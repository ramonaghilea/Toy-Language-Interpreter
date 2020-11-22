package Model.statement;

import Model.ADT.DictionaryInterface;
import Model.ADT.HeapInterface;
import Model.ADT.StackInterface;
import Model.ProgramState;
import Model.exceptions.StatementException;
import Model.expression.ExpressionInterface;
import Model.type.StringType;
import Model.value.StringValue;
import Model.value.ValueInterface;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class openRFile implements StatementInterface{
    private ExpressionInterface expression;

    public openRFile(ExpressionInterface expression) { this.expression = expression; }

    @Override
    public ProgramState execute(ProgramState programState) throws Exception {
        DictionaryInterface<String, ValueInterface> symbolTable = programState.getSymbolTable();
        DictionaryInterface<StringValue, BufferedReader> fileTable = programState.getFileTable();
        HeapInterface<Integer, ValueInterface> heap = programState.getHeap();

        // evaluate the expression
        ValueInterface value = this.expression.evaluate(symbolTable, heap);
        // check if it is a StringValue
        if(value.getType().equals(new StringType()))
        {
            StringValue fileName = (StringValue) value;
            // check if the file has been defined in the file table before
            if(!fileTable.isDefined(fileName))
            {
                BufferedReader reader = null;

                try{
                    reader = new BufferedReader(new FileReader(fileName.getValue()));
                }
                catch (FileNotFoundException exception) { throw new StatementException("file does not exist"); }
                catch (IOException exception) { throw new StatementException("IO error"); }
                finally {
                    if(reader != null)
                    {
                        // create a new entrance in fileTable which maps the stringValue and the reader(BufferedReader class)
                        fileTable.add(fileName, reader);
                    }
                }
            }
            else
                throw new StatementException("string value already exists in the file table");
        }
        else
            throw new StatementException("the type is not string");

        return programState;
    }

    @Override
    public StatementInterface deepCopy() {
        ExpressionInterface copyExpression = this.expression.deepCopy();
        openRFile copy = new openRFile(copyExpression);

        return copy;
    }
}
