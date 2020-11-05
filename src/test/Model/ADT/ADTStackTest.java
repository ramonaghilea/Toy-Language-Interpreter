package Model.ADT;

import Model.exceptions.ADTException;
import Model.value.IntValue;
import Model.value.ValueInterface;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ADTStackTest {

    @Test
    void pop() throws ADTException {
        ADTStack<ValueInterface> stack = new ADTStack<ValueInterface>();
        stack.push(new IntValue(10));
        IntValue popValue = (IntValue) stack.pop();
        assertEquals(10, popValue.getValue());
    }

    @Test
    void push() throws ADTException {
        ADTStack<ValueInterface> stack = new ADTStack<ValueInterface>();
        stack.push(new IntValue(10));
        stack.push(new IntValue(20));
        IntValue popValue = (IntValue) stack.pop();
        assertEquals(20, popValue.getValue());
    }

    @Test
    void isEmpty() {
        ADTStack<ValueInterface> stack = new ADTStack<ValueInterface>();
        assertTrue(stack.isEmpty());
    }
}