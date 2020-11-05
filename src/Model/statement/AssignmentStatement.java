package Model.statement;
import Model.ADT.DictionaryInterface;
import Model.ADT.StackInterface;
import Model.ProgramState;
import Model.exceptions.StatementException;
import Model.expression.ExpressionInterface;
import Model.statement.StatementInterface;
import Model.type.TypeInterface;
import Model.value.ValueInterface;

public class AssignmentStatement implements StatementInterface{
    String id;
    ExpressionInterface expression;

    public AssignmentStatement(String id, ExpressionInterface expression)
    {
        this.id = id;
        this.expression = expression;
    }

    @Override
    public String toString() {
        return id + "=" + expression.toString();
    }

    @Override
    public ProgramState execute(ProgramState programState) throws Exception
    {
        StackInterface<StatementInterface> stack = programState.getStack();
        DictionaryInterface<String, ValueInterface> symbolTable = programState.getSymbolTable();

        if (symbolTable.isDefined(id))
        {
            ValueInterface value = expression.evaluate(symbolTable);
            TypeInterface typeId = (symbolTable.lookUp(id)).getType();
            if(value.getType().equals(typeId))
                symbolTable.update(id, value);
            else
                throw new StatementException("declared type of variable " + id + " and type of the assigned expression do not match");
        }
        else throw new StatementException("the used variable " + id + " was not declared before");

        return programState;
    }

    @Override
    public StatementInterface deepCopy() {
        String copyId = this.id;
        ExpressionInterface copyExpression = this.expression.deepCopy();
        AssignmentStatement copy = new AssignmentStatement(copyId, copyExpression);

        return copy;
    }

}
