package Model.type;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntTypeTest {

    @Test
    void testEquals() {
        IntType int1 = new IntType();
        IntType int2 = new IntType();
        assertTrue(int1.equals(int2));
    }

    @Test
    void testToString() {
        IntType int1 = new IntType();
        assertEquals(int1.toString(), "int");
    }
}