package Model.statement;

import Controller.Controller;
import Model.ADT.*;
import Model.ProgramState;
import Model.exceptions.ExpressionEvaluationException;
import Model.exceptions.StatementException;
import Model.expression.ArithmeticExpression;
import Model.expression.ExpressionInterface;
import Model.expression.LogicExpression;
import Model.expression.ValueExpression;
import Model.type.BoolType;
import Model.value.BoolValue;
import Model.value.IntValue;
import Model.value.ValueInterface;
import Repository.InMemoryRepository;
import Repository.RepositoryInterface;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class AssignmentStatementTest {

    @Test
    void testToString() throws Exception {
//        StackInterface<StatementInterface> executionStack = new ADTStack<StatementInterface>();
//        DictionaryInterface<String, ValueInterface> symbolTable = new ADTDictionary<String, ValueInterface>();
//        ListInterface<ValueInterface> out = new ADTList<ValueInterface>();
//
//        VariableDeclarationStatement var = new VariableDeclarationStatement("ok", new BoolType());
//        ProgramState programState = new ProgramState(executionStack, symbolTable, out, var);
//        RepositoryInterface repository = new InMemoryRepository();
//        Controller controller = new Controller(repository);
//        controller.addProgram(programState);
//        controller.allSteps();

        String id = "ok";
        ExpressionInterface ex = new LogicExpression("and", new ValueExpression(new BoolValue(true)),
                new ValueExpression(new BoolValue(false)));
        AssignmentStatement assignmentStatement = new AssignmentStatement(id, ex);

        String string = "ok=true and false";
        assertEquals(string, assignmentStatement.toString());
    }

    @Test
    void execute() throws Exception {
        StackInterface<StatementInterface> executionStack = new ADTStack<StatementInterface>();
        DictionaryInterface<String, ValueInterface> symbolTable = new ADTDictionary<String, ValueInterface>();
        ListInterface<ValueInterface> out = new ADTList<ValueInterface>();

        StatementInterface origProgram = new CompoundStatement(new VariableDeclarationStatement(
                "ok", new BoolType()), new AssignmentStatement("ok", new LogicExpression(
                        "and", new ValueExpression(new BoolValue(true)),
                        new ValueExpression(new BoolValue(false)))));

        ProgramState programState = new ProgramState(executionStack, symbolTable, out, origProgram);
        RepositoryInterface repository = new InMemoryRepository();
        Controller controller = new Controller(repository);
        controller.addProgram(programState);
        controller.allSteps();

//        String id = "ok";
//        ExpressionInterface ex = new LogicExpression("and", new ValueExpression(new BoolValue(true)),
//                new ValueExpression(new BoolValue(false)));
//        AssignmentStatement assignmentStatement = new AssignmentStatement(id, ex);
//        assignmentStatement.execute(programState);

        BoolValue okValue = (BoolValue) symbolTable.lookUp("ok");
        assertEquals(okValue.getValue(), new BoolValue(false).getValue());
    }

    @Test
    public void executeException()
    {
        assertThrows(StatementException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                StackInterface<StatementInterface> executionStack = new ADTStack<StatementInterface>();
                DictionaryInterface<String, ValueInterface> symbolTable = new ADTDictionary<String, ValueInterface>();
                ListInterface<ValueInterface> out = new ADTList<ValueInterface>();

                StatementInterface origProgram = new CompoundStatement(new VariableDeclarationStatement(
                        "notOk", new BoolType()), new AssignmentStatement("ok", new LogicExpression(
                        "and", new ValueExpression(new BoolValue(true)),
                        new ValueExpression(new BoolValue(false)))));

                ProgramState programState = new ProgramState(executionStack, symbolTable, out, origProgram);
                RepositoryInterface repository = new InMemoryRepository();
                Controller controller = new Controller(repository);
                controller.addProgram(programState);
                controller.allSteps();
            }
        });
    }
}