package Model.statement;
import Model.ADT.ADTDictionary;
import Model.exceptions.ExpressionEvaluationException;
import Model.exceptions.StatementException;
import Model.type.TypeInterface;
import Model.ProgramState;

public interface StatementInterface {
    ProgramState execute(ProgramState programState) throws Exception;
    public StatementInterface deepCopy();
    ADTDictionary<String, TypeInterface> typeCheck(ADTDictionary<String, TypeInterface> typeEnv) throws ExpressionEvaluationException;
}
