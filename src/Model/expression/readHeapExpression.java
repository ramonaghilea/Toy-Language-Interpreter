package Model.expression;

import Model.ADT.ADTDictionary;
import Model.ADT.ADTHeap;
import Model.ADT.DictionaryInterface;
import Model.ADT.HeapInterface;
import Model.exceptions.ExpressionEvaluationException;
import Model.type.IntType;
import Model.type.ReferenceType;
import Model.type.TypeInterface;
import Model.value.ReferenceValue;
import Model.value.ValueInterface;

public class readHeapExpression implements ExpressionInterface{
    private ExpressionInterface expression;

    public readHeapExpression(ExpressionInterface expression) { this.expression = expression; }

    @Override
    public ValueInterface evaluate(DictionaryInterface<String, ValueInterface> table, HeapInterface<Integer, ValueInterface> heap) throws Exception {
        ValueInterface value;
        value = this.expression.evaluate(table, heap);

        ValueInterface valueFromHeap;
        // check if the type of the value is reference type
        if(value.getType() instanceof ReferenceType)
        {
            // cast value from ValueInterface to ReferenceValue
            ReferenceValue referenceValue = (ReferenceValue) value;
            // get the value associated to the address of the referenceValue
            int address = referenceValue.getAddress();
            if(heap.isDefined(address))
            {
                valueFromHeap = heap.getValue(address);
            }
            else throw new ExpressionEvaluationException("the address does not exist in the heap");
        }
        else throw new ExpressionEvaluationException("expression is not a reference");

        return valueFromHeap;
    }

    @Override
    public String toString() {
        return "readHeap(" + this.expression.toString() + ")";
    }

    @Override
    public ExpressionInterface deepCopy() {
        ExpressionInterface copyExpression = this.expression.deepCopy();
        readHeapExpression copy = new readHeapExpression(copyExpression);

        return copy;
    }

    @Override
    public TypeInterface typeCheck(ADTDictionary<String, TypeInterface> typeEnv) throws ExpressionEvaluationException {
        TypeInterface type = this.expression.typeCheck(typeEnv);
        if(type instanceof ReferenceType)
        {
            ReferenceType referenceType = (ReferenceType) type;
            return referenceType.getInnerType();
        }
        else
            throw new ExpressionEvaluationException("the readHeap argument is not a reference type");
    }
}
