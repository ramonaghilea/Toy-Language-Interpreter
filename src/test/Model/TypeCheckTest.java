package Model;

import Model.ADT.ADTDictionary;
import Model.ADT.ADTHeap;
import Model.exceptions.ExpressionEvaluationException;
import Model.expression.*;
import Model.statement.*;
import Model.type.BoolType;
import Model.type.IntType;
import Model.type.ReferenceType;
import Model.type.TypeInterface;
import Model.value.IntValue;
import Model.value.ValueInterface;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TypeCheckTest {

    @Test
    void statement9() throws ExpressionEvaluationException {
        assertThrows(ExpressionEvaluationException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                StatementInterface example9 = new CompoundStatement(new VariableDeclarationStatement("v", new BoolType()),
                        new CompoundStatement(new VariableDeclarationStatement("a", new ReferenceType(new IntType())),
                                // assignment error: v is of type bool
                                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(10))),
                                        new CompoundStatement(new NewStatement("a", new ValueExpression(new IntValue(22))),
                                                new CompoundStatement(new ForkStatement(new CompoundStatement(new writeHeapStatement("a", new ValueExpression(new IntValue(30))),
                                                        new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(32))),
                                                                new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                                                        new PrintStatement(new readHeapExpression(new VariableExpression("a"))))))),
                                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                                                new PrintStatement(new readHeapExpression(new VariableExpression("a")))))))));

                ADTDictionary<String, TypeInterface> typeEnv = new ADTDictionary<String, TypeInterface>();
                example9.typeCheck(typeEnv);;
            }
        });
    }
}
