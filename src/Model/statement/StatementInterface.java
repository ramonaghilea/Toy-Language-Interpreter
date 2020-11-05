package Model.statement;
import Model.exceptions.StatementException;
import Model.type.TypeInterface;
import Model.ProgramState;

public interface StatementInterface {
    ProgramState execute(ProgramState programState) throws Exception;
    public StatementInterface deepCopy();
}
