package Model.expression;
import Model.ADT.DictionaryInterface;
import Model.ADT.HeapInterface;
import Model.exceptions.ExpressionEvaluationException;
import Model.expression.ExpressionInterface;
import Model.type.BoolType;
import Model.type.IntType;
import Model.value.BoolValue;
import Model.value.IntValue;
import Model.value.ValueInterface;

public class LogicExpression implements ExpressionInterface{
    ExpressionInterface expression1;
    ExpressionInterface expression2;
    int operator;

    public LogicExpression(String operatorAsString, ExpressionInterface expression1, ExpressionInterface expression2)
    {
        this.expression1 = expression1;
        this.expression2 = expression2;
        if(operatorAsString.equals("and"))
            this.operator = 1;
        else
            this.operator = 2;
    }
    public ValueInterface evaluate(DictionaryInterface<String, ValueInterface> table, HeapInterface<Integer, ValueInterface> heap) throws Exception
    {
        ValueInterface value1, value2;

        value1 = expression1.evaluate(table, heap);

        if (value1.getType().equals(new BoolType())) {
            value2 = expression2.evaluate(table, heap);
            if (value2.getType().equals(new BoolType())) {
                BoolValue bool1 = (BoolValue) value1;
                BoolValue bool2 = (BoolValue) value2;

                boolean operand1, operand2;
                operand1 = bool1.getValue();
                operand2 = bool2.getValue();

                if (operator == 1)
                    return new BoolValue(operand1 && operand2);
                if (operator == 2)
                    return new BoolValue(operand1 || operand2);
            } else
                throw new ExpressionEvaluationException("second operand is not an bool");
        } else
            throw new ExpressionEvaluationException("first operand is not an bool");

        return value1;
    }

    @Override
    public ExpressionInterface deepCopy() {
        ExpressionInterface copyExpression1 = this.expression1.deepCopy();
        ExpressionInterface copyExpression2 = this.expression2.deepCopy();
        String operatorAsString = "";
        if(this.operator == 1)
            operatorAsString = "and";
        else if(this.operator == 2)
            operatorAsString = "or";

        LogicExpression copy = new LogicExpression(operatorAsString, copyExpression1, copyExpression2);

        return copy;
    }

    @Override
    public String toString() {
        String operatorAsString = "";
        if(this.operator == 1)
            operatorAsString = "and";
        else if(this.operator == 2)
            operatorAsString = "or";

        return this.expression1.toString() + " " + operatorAsString + " " + this.expression2.toString();
    }
}
