package Model.expression;

import Model.ADT.ADTDictionary;
import Model.ADT.DictionaryInterface;
import Model.ADT.HeapInterface;
import Model.exceptions.ExpressionEvaluationException;
import Model.type.BoolType;
import Model.type.IntType;
import Model.type.TypeInterface;
import Model.value.BoolValue;
import Model.value.IntValue;
import Model.value.ValueInterface;

import java.util.HashMap;

public class RelationalExpression implements ExpressionInterface{
    ExpressionInterface expression1;
    ExpressionInterface expression2;
    int operator;

    public RelationalExpression(String operatorAsString, ExpressionInterface expression1, ExpressionInterface expression2)
    {
        this.expression1 = expression1;
        this.expression2 = expression2;

        switch (operatorAsString) {
            case "<" -> this.operator = 1;
            case "<=" -> this.operator = 2;
            case "==" -> this.operator = 3;
            case "!=" -> this.operator = 4;
            case ">" -> this.operator = 5;
            case ">=" -> this.operator = 6;
        }
    }
    @Override
    public ValueInterface evaluate(DictionaryInterface<String, ValueInterface> table, HeapInterface<Integer, ValueInterface> heap) throws Exception {
        ValueInterface value1, value2;
        BoolValue result = null;

        value1 = expression1.evaluate(table, heap);
        if (value1.getType().equals(new IntType())) {
            value2 = expression2.evaluate(table, heap);
            if (value2.getType().equals(new IntType())) {
                // cast the values from ValueInterface to IntValue
                IntValue int1 = (IntValue) value1;
                IntValue int2 = (IntValue) value2;

                // take the int from IntValue
                int operand1, operand2;
                operand1 = int1.getValue();
                operand2 = int2.getValue();

                if (operator == 1)
                    result = new BoolValue(operand1 < operand2);
                if (operator == 2)
                    result = new BoolValue(operand1 <= operand2);
                if (operator == 3)
                    result = new BoolValue(operand1 == operand2);
                if (operator == 4)
                    result = new BoolValue(operand1 != operand2);
                if(operator == 5)
                    result = new BoolValue(operand1 > operand2);
                if(operator == 6)
                    result = new BoolValue(operand1 >= operand2);
            } else
                throw new ExpressionEvaluationException("second operand is not an integer");
        } else
            throw new ExpressionEvaluationException("first operand is not an integer");

        return  result;
    }

    @Override
    public ExpressionInterface deepCopy() {
        ExpressionInterface copyExpression1 = this.expression1.deepCopy();
        ExpressionInterface copyExpression2 = this.expression2.deepCopy();
        String operatorAsString = "";

        if(this.operator == 1)
            operatorAsString = "<";
        else if(this.operator == 2)
            operatorAsString = "<=";
        else if(this.operator == 3)
            operatorAsString = "==";
        else if(this.operator == 4)
            operatorAsString = "!=";
        else if(this.operator == 5)
            operatorAsString = ">";
        else if(this.operator == 6)
            operatorAsString = ">=";

        RelationalExpression copy = new RelationalExpression(operatorAsString, copyExpression1, copyExpression2);

        return copy;
    }

    @Override
    public TypeInterface typeCheck(ADTDictionary<String, TypeInterface> typeEnv) throws ExpressionEvaluationException {
        TypeInterface type1, type2;
        type1 = this.expression1.typeCheck(typeEnv);
        type2 = this.expression2.typeCheck(typeEnv);

        if(type1.equals(new IntType()))
            if(type2.equals(new IntType()))
                return new BoolType();
            else
                throw new ExpressionEvaluationException("second operand is not an integer");
        else
            throw new ExpressionEvaluationException("first operand is not an integer");
    }

    @Override
    public String toString() {
        String operatorAsString = "";

        if(this.operator == 1)
            operatorAsString = "<";
        else if(this.operator == 2)
            operatorAsString = "<=";
        else if(this.operator == 3)
            operatorAsString = "==";
        else if(this.operator == 4)
            operatorAsString = "!=";
        else if(this.operator == 5)
            operatorAsString = ">";
        else if(this.operator == 6)
            operatorAsString = ">=";

        return this.expression1.toString() + " " + operatorAsString + " " + this.expression2.toString();
    }
}
