package Model.statement;
import Model.ADT.ADTDictionary;
import Model.ADT.DictionaryInterface;
import Model.ADT.HeapInterface;
import Model.ADT.ListInterface;
import Model.ProgramState;
import Model.exceptions.ExpressionEvaluationException;
import Model.exceptions.StatementException;
import Model.expression.ExpressionInterface;
import Model.statement.StatementInterface;
import Model.type.TypeInterface;
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
        HeapInterface<Integer, ValueInterface> heap = programState.getHeap();

        out.add(this.expression.evaluate(table, heap));

        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        ExpressionInterface copyExpression = this.expression.deepCopy();
        PrintStatement copy = new PrintStatement(copyExpression);

        return copy;
    }

    @Override
    public ADTDictionary<String, TypeInterface> typeCheck(ADTDictionary<String, TypeInterface> typeEnv) throws ExpressionEvaluationException {
        this.expression.typeCheck(typeEnv);
        return typeEnv;
    }
}
