package Model.statement;
import Model.ADT.ADTDictionary;
import Model.ADT.StackInterface;
import Model.ProgramState;
import Model.exceptions.ExpressionEvaluationException;
import Model.exceptions.StatementException;
import Model.statement.StatementInterface;
import Model.type.TypeInterface;


public class CompoundStatement implements StatementInterface{
    StatementInterface first;
    StatementInterface second;

    public CompoundStatement(StatementInterface first, StatementInterface second)
    {
        this.first = first;
        this.second = second;
    }

    public String toString()
    {
        return "(" + first.toString() + ";" + second.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState programState) throws StatementException {
        StackInterface<StatementInterface> stack = programState.getStack();
        stack.push(second);
        stack.push(first);
        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        StatementInterface copyFirst = this.first.deepCopy();
        StatementInterface copySecond = this.second.deepCopy();
        CompoundStatement copy = new CompoundStatement(copyFirst, copySecond);

        return copy;
    }

    @Override
    public ADTDictionary<String, TypeInterface> typeCheck(ADTDictionary<String, TypeInterface> typeEnv) throws ExpressionEvaluationException {
//        ADTDictionary<String, TypeInterface> typeEnv1 = this.first.typeCheck(typeEnv);
//        ADTDictionary<String, TypeInterface> typeEnv2 = this.second.typeCheck(typeEnv1);
//        return typeEnv2;
        return second.typeCheck(first.typeCheck(typeEnv));
    }
}
