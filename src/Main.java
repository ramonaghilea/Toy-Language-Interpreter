import Controller.Controller;
import Model.ADT.*;
import Model.ProgramState;
import Model.expression.ArithmeticExpression;
import Model.expression.ValueExpression;
import Model.expression.VariableExpression;
import Model.statement.*;
import Model.type.BoolType;
import Model.type.IntType;
import Model.value.BoolValue;
import Model.value.IntValue;
import Model.value.ValueInterface;
import Repository.InMemoryRepository;
import Repository.RepositoryInterface;
import View.View;

import javax.swing.plaf.nimbus.State;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {
//        StatementInterface originalProgram = new IfStatement(new IntValue(10),
//                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(5))),
//                        new PrintStatement(new ArithmeticExpression('/',
//                                new VariableExpression("v"), new IntValue(3)))),
//                new PrintStatement(new IntValue(100)));

        StackInterface<StatementInterface> executionStack = new ADTStack<StatementInterface>();
        DictionaryInterface<String, ValueInterface> symbolTable = new ADTDictionary<String, ValueInterface>();
        ListInterface<ValueInterface> out = new ADTList<ValueInterface>();

        // int v; v=2; Print(v)
        StatementInterface example1 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))));

        // int a; int b; a=2+3*5; b=a+1; Print(b)
        StatementInterface example2 = new CompoundStatement(new VariableDeclarationStatement("a", new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("b", new IntType()),
                        new CompoundStatement(new AssignmentStatement("a",
                                new ArithmeticExpression("+", new ValueExpression(new IntValue(2)),
                                        new ArithmeticExpression("*", new ValueExpression(new IntValue(3)),
                                                new ValueExpression(new IntValue(5))))),
                                new CompoundStatement(new AssignmentStatement("b",
                                        new ArithmeticExpression("+", new VariableExpression("a"),
                                                new ValueExpression(new IntValue(1)))),
                                        new PrintStatement(new VariableExpression("b"))))));

        // bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)
        StatementInterface example3 = new CompoundStatement(new VariableDeclarationStatement("a",new BoolType()),
                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                        new CompoundStatement(new AssignmentStatement("a",
                                new ValueExpression(new BoolValue(true))),
                                new CompoundStatement(new IfStatement(new VariableExpression("a"),
                                        new AssignmentStatement("v",new ValueExpression(new IntValue(2))),
                                        new AssignmentStatement("v", new ValueExpression(new IntValue(3)))),
                                        new PrintStatement(new VariableExpression("v"))))));

        // int v; v=2 / 0; Print(v)
//        StatementInterface example4 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
//                new CompoundStatement(new AssignmentStatement("v", new ArithmeticExpression("/",
//                        new ValueExpression(new IntValue(2)), new ValueExpression(new IntValue(0))), new CompoundStatement(
//                        new PrintStatement(new VariableExpression("v"))))));

        ArrayList<StatementInterface> statementInterfaces = new ArrayList<StatementInterface>();
        statementInterfaces.add(example1);
        statementInterfaces.add(example2);
        statementInterfaces.add(example3);

        RepositoryInterface repository = new InMemoryRepository();
        Controller controller = new Controller(repository);
        View view = new View(controller, statementInterfaces);
        view.run();

        //ProgramState programState = new ProgramState(executionStack, symbolTable, out, example1);

//        RepositoryInterface repository = new InMemoryRepository();
//        Controller controller = new Controller(repository);
//        controller.addProgram(programState);
//        controller.allSteps();


//        controller.oneStep(programState);
//        System.out.println(programState.toString());
//        controller.oneStep(programState);
//        System.out.println(programState.toString());
//        controller.oneStep(programState);
//        System.out.println(programState.toString());
//        controller.oneStep(programState);
//        System.out.println(programState.toString());
//        controller.oneStep(programState);
//        System.out.println(programState.toString());
//        controller.oneStep(programState);
//        System.out.println(programState.toString());
    }
}
