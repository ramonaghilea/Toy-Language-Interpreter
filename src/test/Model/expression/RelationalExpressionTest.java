package Model.expression;

import Model.ADT.ADTDictionary;
import Model.ADT.ADTHeap;
import Model.value.BoolValue;
import Model.value.IntValue;
import Model.value.ValueInterface;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RelationalExpressionTest {

    @Test
    void evaluate() throws Exception {
        ExpressionInterface ex1 = new ValueExpression(new IntValue(2));
        ExpressionInterface ex2 = new ValueExpression(new IntValue(10));
        RelationalExpression relationalExpression = new RelationalExpression("<=", ex1, ex2);
        BoolValue result = new BoolValue(true);
        BoolValue expressionResult = (BoolValue) relationalExpression.evaluate(new ADTDictionary<String, ValueInterface>(), new ADTHeap<Integer, ValueInterface>());
        assertEquals(result.getValue(), expressionResult.getValue());
    }

    @Test
    void testToString() {
        ExpressionInterface ex1 = new ValueExpression(new IntValue(2));
        ExpressionInterface ex2 = new ValueExpression(new IntValue(10));
        RelationalExpression relationalExpression= new RelationalExpression("<=", ex1, ex2);
        String string = "2 <= 10";
        assertEquals(relationalExpression.toString(), string);
    }
}