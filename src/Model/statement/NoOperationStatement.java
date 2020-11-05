package Model.statement;
import Model.ProgramState;
import Model.exceptions.StatementException;
import Model.statement.StatementInterface;

public class NoOperationStatement implements StatementInterface{

    public NoOperationStatement() {}

    @Override
    public ProgramState execute(ProgramState programState) throws StatementException {
        return programState;
    }

    @Override
    public StatementInterface deepCopy() {
        NoOperationStatement copy = new NoOperationStatement();

        return copy;
    }

    @Override
    public String toString() {
        return "nop";
    }
}
