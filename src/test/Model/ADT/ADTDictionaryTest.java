package Model.ADT;

import Model.value.IntValue;
import Model.value.ValueInterface;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ADTDictionaryTest {

    @Test
    void add() {
        ADTDictionary<String, ValueInterface> dict = new ADTDictionary<String, ValueInterface>();
        dict.add("a", new IntValue(10));
        IntValue value = (IntValue) dict.lookUp("a");
        assertEquals(value.getValue(), 10);
    }

    @Test
    void update() {
        ADTDictionary<String, ValueInterface> dict = new ADTDictionary<String, ValueInterface>();
        dict.add("a", new IntValue(10));
        dict.update("a", new IntValue(35));
        IntValue value = (IntValue) dict.lookUp("a");
        assertEquals(value.getValue(), 35);
    }

    @Test
    void lookUp() {
        ADTDictionary<String, ValueInterface> dict = new ADTDictionary<String, ValueInterface>();
        dict.add("a", new IntValue(20));
        IntValue value = (IntValue) dict.lookUp("a");
        assertEquals(value.getValue(), 20);
    }

    @Test
    void isDefined() {
        ADTDictionary<String, ValueInterface> dict = new ADTDictionary<String, ValueInterface>();
        dict.add("a", new IntValue(20));
        assertFalse(dict.isDefined("b"));
    }

    @Test
    void testToString() {
        ADTDictionary<String, ValueInterface> dict = new ADTDictionary<String, ValueInterface>();
        dict.add("a", new IntValue(20));
        String asString = "{a->20}";
        assertEquals(asString, dict.toString());
    }
}