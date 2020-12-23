package Model.statement;
import Model.ADT.ADTDictionary;
import Model.ProgramState;
import Model.exceptions.ExpressionEvaluationException;
import Model.exceptions.StatementException;
import Model.statement.StatementInterface;
import Model.type.TypeInterface;

public class NoOperationStatement implements StatementInterface{

    public NoOperationStatement() {}

    @Override
    public ProgramState execute(ProgramState programState) throws StatementException {
        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        NoOperationStatement copy = new NoOperationStatement();

        return copy;
    }

    @Override
    public ADTDictionary<String, TypeInterface> typeCheck(ADTDictionary<String, TypeInterface> typeEnv) throws ExpressionEvaluationException {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "nop";
    }
}
