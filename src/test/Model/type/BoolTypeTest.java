package Model.type;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoolTypeTest {

    @Test
    void testEquals() {
        BoolType bool1 = new BoolType();
        IntType int1 = new IntType();
        assertFalse(bool1.equals(int1));
    }

    @Test
    void testToString() {
        BoolType bool = new BoolType();
        assertEquals(bool.toString(), "bool");
    }
}