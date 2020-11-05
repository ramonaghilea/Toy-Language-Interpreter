package Model.ADT;

import Model.exceptions.ADTException;
import Model.exceptions.ExpressionEvaluationException;
import Model.expression.ArithmeticExpression;
import Model.expression.ExpressionInterface;
import Model.expression.ValueExpression;
import Model.value.IntValue;
import Model.value.ValueInterface;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class ADTListTest {

    @Test
    void add() throws ADTException {
        ADTList<ValueInterface> list = new ADTList<ValueInterface>();
        list.add(new IntValue(10));
        IntValue popValue = (IntValue) list.pop();
        assertEquals(10, popValue.getValue());
    }

    @Test
    void pop() throws ADTException {
        ADTList<ValueInterface> list = new ADTList<ValueInterface>();
        list.add(new IntValue(10));
        list.add(new IntValue(19));
        IntValue popValue = (IntValue) list.pop();
        assertEquals(19, popValue.getValue());
    }
    @Test
    public void popException()
    {
        assertThrows(ADTException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                ADTList<ValueInterface> list = new ADTList<ValueInterface>();
                list.add(new IntValue(10));
                IntValue popValue1 = (IntValue) list.pop();
                IntValue popValue2 = (IntValue) list.pop();
            }
        });
    }

    @Test
    void testToString() {
        ADTList<ValueInterface> list = new ADTList<ValueInterface>();
        list.add(new IntValue(10));
        list.add(new IntValue(19));
        String asString = "{10,19}";
        assertEquals(asString, list.toString());
    }
}