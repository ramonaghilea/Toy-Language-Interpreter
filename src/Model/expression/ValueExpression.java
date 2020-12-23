package Model.expression;
import Model.ADT.ADTDictionary;
import Model.ADT.DictionaryInterface;
import Model.ADT.HeapInterface;
import Model.exceptions.ExpressionEvaluationException;
import Model.expression.ExpressionInterface;
import Model.type.BoolType;
import Model.type.IntType;
import Model.type.TypeInterface;
import Model.value.IntValue;
import Model.value.ValueInterface;

public class ValueExpression implements ExpressionInterface{
    ValueInterface value;

    public ValueExpression(ValueInterface value)
    {
        this.value = value;
    }

    public ValueInterface evaluate(DictionaryInterface<String, ValueInterface> table, HeapInterface<Integer, ValueInterface> heap) throws Exception
    {
//        if(value.getType().equals(new IntType())) {
//            IntValue intValue = (IntValue) value;
//            int resultValue = intValue.getValue();
//            return resultValue;
//        }
//        else if(value.getType().equals((new BoolType())))
//        {
//
//        }
        return this.value;
    }

    @Override
    public ExpressionInterface deepCopy() {
        ValueInterface copyValue = this.value.deepCopy();
        ValueExpression copy = new ValueExpression(copyValue);

        return copy;
    }

    @Override
    public TypeInterface typeCheck(ADTDictionary<String, TypeInterface> typeEnv) throws ExpressionEvaluationException {
        return this.value.getType();
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}
