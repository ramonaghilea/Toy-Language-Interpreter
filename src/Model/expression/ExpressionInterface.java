package Model.expression;
import Model.ADT.ADTDictionary;
import Model.ADT.DictionaryInterface;
import Model.ADT.HeapInterface;
import Model.exceptions.ExpressionEvaluationException;
import Model.type.TypeInterface;
import Model.value.ValueInterface;

public interface ExpressionInterface {
    ValueInterface evaluate(DictionaryInterface<String, ValueInterface> table, HeapInterface<Integer, ValueInterface> heap) throws Exception;
    ExpressionInterface deepCopy();
    TypeInterface typeCheck (ADTDictionary<String, TypeInterface> typeEnv) throws ExpressionEvaluationException;
}
