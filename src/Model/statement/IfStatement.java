package Model.statement;
import Model.ADT.ADTDictionary;
import Model.ADT.DictionaryInterface;
import Model.ProgramState;
import Model.exceptions.StatementException;
import Model.expression.ExpressionInterface;
import Model.statement.StatementInterface;
import Model.type.BoolType;
import Model.type.IntType;
import Model.value.BoolValue;
import Model.value.IntValue;
import Model.value.ValueInterface;

public class IfStatement implements StatementInterface{

    ExpressionInterface expression;
    StatementInterface thenStatement;
    StatementInterface elseStatement;

    public IfStatement(ExpressionInterface expression, StatementInterface thenStatement, StatementInterface elseStatement)
    {
        this.expression = expression;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    @Override
    public String toString() {
        return "(IF(" + expression.toString() + ") THEN(" + thenStatement.toString() + ") ELSE(" + elseStatement.toString() + "))";
    }

    @Override
    public ProgramState execute(ProgramState programState) throws Exception
    {
        DictionaryInterface<String, ValueInterface> symbolTable = programState.getSymbolTable();
        ValueInterface value = expression.evaluate((symbolTable));
        if(value.getType().equals(new IntType()))
        {
            // cast the ValueInterface to IntValue
            IntValue intValue = (IntValue) value;
            // get the value of intValue as int
            int intValue2 = intValue.getValue();

            // for integers, if the value > 0, consider it true
            if(intValue2 > 0)
                thenStatement.execute(programState);
            else
                elseStatement.execute(programState);
        }
        else if(value.getType().equals(new BoolType()))
        {
            // cast the ValueInterface to BoolValue
            BoolValue boolValue = (BoolValue) value;
            // get the value of boolValue as boolean
            boolean boolValue2 = boolValue.getValue();

            if(boolValue2)
                thenStatement.execute(programState);
            else
                elseStatement.execute(programState);
        }
        return programState;
    }

    @Override
    public StatementInterface deepCopy() {
        ExpressionInterface copyExpression = this.expression.deepCopy();
        StatementInterface copyThenStatement = this.thenStatement.deepCopy();
        StatementInterface copyElseStatement = this.elseStatement.deepCopy();
        IfStatement copy = new IfStatement(copyExpression, copyThenStatement, copyElseStatement);

        return copy;
    }
}
