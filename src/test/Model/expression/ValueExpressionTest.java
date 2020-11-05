package Model.expression;

import Model.ADT.ADTDictionary;
import Model.ADT.DictionaryInterface;
import Model.value.IntValue;
import Model.value.ValueInterface;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValueExpressionTest {

    @Test
    void evaluate() throws Exception {
        DictionaryInterface<String, ValueInterface> table = new ADTDictionary<String, ValueInterface>();
        ValueExpression valExpr = new ValueExpression(new IntValue(15));
        IntValue intValue = (IntValue) valExpr.evaluate(table);
        assertEquals(intValue.getValue(), 15);
    }

    @Test
    void testToString() {
        ValueExpression valExpr = new ValueExpression(new IntValue(15));
        assertEquals("15", valExpr.toString());
    }
}