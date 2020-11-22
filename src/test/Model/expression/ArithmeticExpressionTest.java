package Model.expression;

import Model.ADT.ADTDictionary;
import Model.ADT.ADTHeap;
import Model.exceptions.ExpressionEvaluationException;
import Model.value.IntValue;
import Model.value.ValueInterface;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class ArithmeticExpressionTest {

    @Test
    void evaluate() throws Exception {
        ExpressionInterface ex1 = new ValueExpression(new IntValue(2));
        ExpressionInterface ex2 = new ValueExpression(new IntValue(10));
        ArithmeticExpression arithmeticExpression = new ArithmeticExpression("*", ex1, ex2);
        IntValue result = new IntValue(20);
        IntValue expressionResult = (IntValue) arithmeticExpression.evaluate(new ADTDictionary<String, ValueInterface>(), new ADTHeap<Integer, ValueInterface>());
        assertEquals(result.getValue(), expressionResult.getValue());
    }
    @Test
    public void evaluateException()
    {
        assertThrows(ExpressionEvaluationException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                ExpressionInterface ex1 = new ValueExpression(new IntValue(2));
                ExpressionInterface ex2 = new ValueExpression(new IntValue(0));
                ArithmeticExpression arithmeticExpression = new ArithmeticExpression("/", ex1, ex2);
                arithmeticExpression.evaluate(new ADTDictionary<String, ValueInterface>(), new ADTHeap<Integer, ValueInterface>());
            }
        });
    }

    @Test
    void deepCopy() {
        ExpressionInterface ex1 = new ValueExpression(new IntValue(2));
        ExpressionInterface ex2 = new ValueExpression(new IntValue(10));
        ArithmeticExpression arithmeticExpression = new ArithmeticExpression("*", ex1, ex2);
        ArithmeticExpression arithmeticExpressionCopy = (ArithmeticExpression) arithmeticExpression.deepCopy();
        ExpressionInterface ex3 = new ValueExpression(new IntValue(5));
        arithmeticExpressionCopy.setExpression2(ex3);
        assertFalse(arithmeticExpression.getExpression2() == arithmeticExpressionCopy.getExpression2());
    }

    @Test
    void testToString() {
        ExpressionInterface ex1 = new ValueExpression(new IntValue(2));
        ExpressionInterface ex2 = new ValueExpression(new IntValue(10));
        ArithmeticExpression arithmeticExpression = new ArithmeticExpression("*", ex1, ex2);
        String string = "2 * 10";
        assertEquals(arithmeticExpression.toString(), string);
    }
}