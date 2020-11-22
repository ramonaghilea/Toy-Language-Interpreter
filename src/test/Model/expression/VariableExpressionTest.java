package Model.expression;

import Model.ADT.*;
import Model.exceptions.ADTException;
import Model.exceptions.ExpressionEvaluationException;
import Model.value.IntValue;
import Model.value.ValueInterface;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class VariableExpressionTest {

    @Test
    public void evaluateException()
    {
        assertThrows(ExpressionEvaluationException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                DictionaryInterface<String, ValueInterface> table = new ADTDictionary<String, ValueInterface>();
                HeapInterface<Integer, ValueInterface> heap = new ADTHeap<Integer, ValueInterface>();
                VariableExpression varExpr = new VariableExpression("var");
                varExpr.evaluate(table, heap);
            }
        });
    }

    @Test
    void testToString() {
        VariableExpression varExpr = new VariableExpression("var");
        assertEquals("var", varExpr.toString());
    }
}