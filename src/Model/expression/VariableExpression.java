package Model.expression;
import Model.ADT.DictionaryInterface;
import Model.exceptions.ExpressionEvaluationException;
import Model.expression.ExpressionInterface;
import Model.value.ValueInterface;

public class VariableExpression implements ExpressionInterface{
    String id;

    public VariableExpression(String id)
    {
        this.id = id;
    }

    public ValueInterface evaluate(DictionaryInterface<String, ValueInterface> table) throws Exception
    {
        if(table.isDefined(id))
            return table.lookUp(id);
        else
            throw new ExpressionEvaluationException("variable " + id + " is not defined");
    }

    @Override
    public ExpressionInterface deepCopy() {
        String copyId = this.id;
        VariableExpression copy = new VariableExpression(copyId);

        return copy;
    }

    @Override
    public String toString() {
        return id;
    }
}
