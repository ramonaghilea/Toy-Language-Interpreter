package Model.statement;
import Model.ADT.StackInterface;
import Model.ProgramState;
import Model.exceptions.StatementException;
import Model.statement.StatementInterface;


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
        return programState;
    }

    @Override
    public StatementInterface deepCopy() {
        StatementInterface copyFirst = this.first.deepCopy();
        StatementInterface copySecond = this.second.deepCopy();
        CompoundStatement copy = new CompoundStatement(copyFirst, copySecond);

        return copy;
    }
}
