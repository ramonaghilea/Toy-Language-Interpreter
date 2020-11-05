package Model.expression;
import Model.ADT.DictionaryInterface;
import Model.exceptions.ExpressionEvaluationException;
import Model.value.ValueInterface;

public interface ExpressionInterface {
    ValueInterface evaluate(DictionaryInterface<String, ValueInterface> table) throws Exception;
    ExpressionInterface deepCopy();
}
