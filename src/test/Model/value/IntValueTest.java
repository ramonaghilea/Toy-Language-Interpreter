package Model.value;

import Model.type.IntType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntValueTest {

    @Test
    void getValue() {
        IntValue intValue = new IntValue(30);
        assertEquals(intValue.getValue(), 30);
    }

    @Test
    void testToString() {
        IntValue intValue = new IntValue(30);
        assertEquals(intValue.toString(), "30");
    }

    @Test
    void getType() {
        IntValue intValue = new IntValue(30);
        assertEquals(intValue.getType(), new IntType());
    }
}