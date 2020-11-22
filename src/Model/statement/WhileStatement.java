package Model.statement;

import Model.ADT.DictionaryInterface;
import Model.ADT.HeapInterface;
import Model.ADT.StackInterface;
import Model.ProgramState;
import Model.exceptions.StatementException;
import Model.expression.ExpressionInterface;
import Model.type.BoolType;
import Model.value.BoolValue;
import Model.value.ValueInterface;

public class WhileStatement implements StatementInterface{
    private ExpressionInterface expression;

    public WhileStatement(ExpressionInterface expression) { this.expression = expression; }

    @Override
    public ProgramState execute(ProgramState programState) throws Exception {
        StackInterface<StatementInterface> stack = programState.getStack();
        DictionaryInterface<String, ValueInterface> symbolTable = programState.getSymbolTable();
        HeapInterface<Integer, ValueInterface> heap = programState.getHeap();

        // evaluate the expression and check if the value of the expression is a bool value
        ValueInterface valueExpression = this.expression.evaluate(symbolTable, heap);
        if(valueExpression.getType().equals(new BoolType()))
        {
            // cast the value from ValueInterface to BoolValue
            BoolValue boolValue = (BoolValue) valueExpression;
            if(!boolValue.getValue())
            {
                // the condition is false, the while statement is finished, the next statement will not be executed
                stack.pop();
            }
            else
            {
                // the condition is true, the while statement is not finished, the next statement will be executed,
                // the while statement and the next statement need to stay on the stack, therefore they will pe pushed back
                StatementInterface nextStatement = stack.pop(); // pop the next statement in order to make a copy
                stack.push(nextStatement);
                stack.push(this);
                stack.push(nextStatement.deepCopy());
            }
        }
        else throw new StatementException("condition expression is not a boolean");

        return programState;
    }

    @Override
    public StatementInterface deepCopy() {
        return null;
    }

    @Override
    public String toString() {
        return "while (" + this.expression.toString() + ")";
    }
}
