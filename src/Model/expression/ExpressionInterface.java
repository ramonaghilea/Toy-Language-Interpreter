package Model.expression;
import Model.ADT.DictionaryInterface;
import Model.ADT.HeapInterface;
import Model.exceptions.ExpressionEvaluationException;
import Model.value.ValueInterface;

public interface ExpressionInterface {
    ValueInterface evaluate(DictionaryInterface<String, ValueInterface> table, HeapInterface<Integer, ValueInterface> heap) throws Exception;
    ExpressionInterface deepCopy();
}
