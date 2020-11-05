package Model.expression;
import Model.ADT.DictionaryInterface;
import Model.exceptions.ExpressionEvaluationException;
import Model.expression.ExpressionInterface;
import Model.type.IntType;
import Model.value.ValueInterface;
import Model.value.IntValue;

public class ArithmeticExpression implements ExpressionInterface {
    ExpressionInterface expression1;
    ExpressionInterface expression2;
    int operator;

    public ArithmeticExpression(String operatorAsString, ExpressionInterface expression1, ExpressionInterface expression2)
    {
        this.expression1 = expression1;
        this.expression2 = expression2;

        switch (operatorAsString) {
            case "+" -> this.operator = 1;
            case "-" -> this.operator = 2;
            case "*" -> this.operator = 3;
            case "/" -> this.operator = 4;
        }
    }

    public void setExpression1(ExpressionInterface expression1) {
        this.expression1 = expression1;
    }

    public void setExpression2(ExpressionInterface expression2) {
        this.expression2 = expression2;
    }

    public ExpressionInterface getExpression1() {
        return expression1;
    }

    public ExpressionInterface getExpression2() {
        return expression2;
    }

    public ValueInterface evaluate(DictionaryInterface<String, ValueInterface> table) throws Exception {
        ValueInterface value1, value2;

        value1 = expression1.evaluate(table);

        if (value1.getType().equals(new IntType())) {
            value2 = expression2.evaluate(table);
            if (value2.getType().equals(new IntType())) {
                IntValue int1 = (IntValue) value1;
                IntValue int2 = (IntValue) value2;

                int operand1, operand2;
                operand1 = int1.getValue();
                operand2 = int2.getValue();

                if (operator == 1)
                    return new IntValue(operand1 + operand2);
                if (operator == 2)
                    return new IntValue(operand1 - operand2);
                if (operator == 3)
                    return new IntValue(operand1 * operand2);
                if (operator == 4)
                    if (operand2 == 0)
                        throw new ExpressionEvaluationException("division by zero");
                    else
                        return new IntValue(operand1 / operand2);
            } else
                throw new ExpressionEvaluationException("second operand is not an integer");
        } else
            throw new ExpressionEvaluationException("first operand is not an integer");

        return value1;
    }

    @Override
    public ExpressionInterface deepCopy() {
        ExpressionInterface copyExpression1 = this.expression1.deepCopy();
        ExpressionInterface copyExpression2 = this.expression2.deepCopy();
        String operatorAsString = "";

        if(this.operator == 1)
            operatorAsString = "+";
        else if(this.operator == 2)
            operatorAsString = "-";
        else if(this.operator == 3)
            operatorAsString = "*";
        else if(this.operator == 4)
            operatorAsString = "/";

        ArithmeticExpression copy = new ArithmeticExpression(operatorAsString, copyExpression1, copyExpression2);

        return copy;
    }

    @Override
    public String toString() {
        String operatorAsString = "";

        if(this.operator == 1)
            operatorAsString = "+";
        else if(this.operator == 2)
            operatorAsString = "-";
        else if(this.operator == 3)
            operatorAsString = "*";
        else if(this.operator == 4)
            operatorAsString = "/";

        return this.expression1.toString() + " " + operatorAsString + " " + this.expression2.toString();
    }
}