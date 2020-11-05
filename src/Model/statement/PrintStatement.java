package Model.statement;
import Model.ADT.DictionaryInterface;
import Model.ADT.ListInterface;
import Model.ProgramState;
import Model.exceptions.StatementException;
import Model.expression.ExpressionInterface;
import Model.statement.StatementInterface;
import Model.value.ValueInterface;

import java.util.regex.PatternSyntaxException;

public class PrintStatement implements StatementInterface{
    ExpressionInterface expression;

    public PrintStatement(ExpressionInterface expression)
    {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "print(" + expression.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState programState) throws Exception {
        ListInterface<ValueInterface> out = programState.getOut();
        DictionaryInterface<String, ValueInterface> table = programState.getSymbolTable();
        out.add(this.expression.evaluate(table));

        return programState;
    }

    @Override
    public StatementInterface deepCopy() {
        ExpressionInterface copyExpression = this.expression.deepCopy();
        PrintStatement copy = new PrintStatement(copyExpression);

        return copy;
    }
}
