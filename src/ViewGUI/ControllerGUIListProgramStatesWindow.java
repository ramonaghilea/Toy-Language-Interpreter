package ViewGUI;

import Controller.Controller;
import Model.ADT.*;
import Model.ProgramState;
import Model.exceptions.ExpressionEvaluationException;
import Model.expression.*;
import Model.statement.*;
import Model.type.BoolType;
import Model.type.IntType;
import Model.type.ReferenceType;
import Model.type.TypeInterface;
import Model.value.BoolValue;
import Model.value.IntValue;
import Model.value.StringValue;
import Model.value.ValueInterface;
import Repository.InMemoryRepository;
import Repository.RepositoryInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;

import java.io.BufferedReader;
import java.util.ArrayList;

public class ControllerGUIListProgramStatesWindow {
    private ArrayList<StatementInterface> listProgramStates;
    private ControllerGUIMainWindow mainWindowController;

    @FXML
    private ListView<StatementInterface> programListView;

    @FXML
    private Button selectProgramButton;

    public void setMainWindowController(ControllerGUIMainWindow mainWindowController) { this.mainWindowController = mainWindowController; }

    public void populateProgramsListView() throws ExpressionEvaluationException {
        // int v; v=2; Print(v)
        StatementInterface example1 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))));

//        ADTDictionary<String, TypeInterface> typeEnv1 = new ADTDictionary<String, TypeInterface>();
//        example1.typeCheck(typeEnv1);
//        StackInterface<StatementInterface> executionStack1 = new ADTStack<StatementInterface>();
//        DictionaryInterface<String, ValueInterface> symbolTable1 = new ADTDictionary<String, ValueInterface>();
//        ListInterface<ValueInterface> out1 = new ADTList<ValueInterface>();
//        DictionaryInterface<StringValue, BufferedReader> fileTable1 = new ADTDictionary<StringValue, BufferedReader>();
//        HeapInterface<Integer, ValueInterface> heap1 = new ADTHeap<Integer, ValueInterface>();
//        ProgramState programState1 = new ProgramState(executionStack1, symbolTable1, out1, example1, fileTable1, heap1);
//        RepositoryInterface repository1 = new InMemoryRepository(programState1, "log1.txt");
//        Controller controller1 = new Controller(repository1);

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

//        ADTDictionary<String, TypeInterface> typeEnv2 = new ADTDictionary<String, TypeInterface>();
//        example2.typeCheck(typeEnv2);
//        StackInterface<StatementInterface> executionStack2 = new ADTStack<StatementInterface>();
//        DictionaryInterface<String, ValueInterface> symbolTable2 = new ADTDictionary<String, ValueInterface>();
//        ListInterface<ValueInterface> out2 = new ADTList<ValueInterface>();
//        DictionaryInterface<StringValue, BufferedReader> fileTable2 = new ADTDictionary<StringValue, BufferedReader>();
//        HeapInterface<Integer, ValueInterface> heap2 = new ADTHeap<Integer, ValueInterface>();
//        ProgramState programState2 = new ProgramState(executionStack2, symbolTable2, out2, example2, fileTable2, heap2);
//        RepositoryInterface repository2 = new InMemoryRepository(programState2, "log2.txt");
//        Controller controller2 = new Controller(repository2);

        // bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)
        StatementInterface example3 = new CompoundStatement(new VariableDeclarationStatement("a",new BoolType()),
                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                        new CompoundStatement(new AssignmentStatement("a",
                                new ValueExpression(new BoolValue(true))),
                                new CompoundStatement(new IfStatement(new VariableExpression("a"),
                                        new AssignmentStatement("v",new ValueExpression(new IntValue(2))),
                                        new AssignmentStatement("v", new ValueExpression(new IntValue(3)))),
                                        new PrintStatement(new VariableExpression("v"))))));

//        ADTDictionary<String, TypeInterface> typeEnv3 = new ADTDictionary<String, TypeInterface>();
//        example3.typeCheck(typeEnv3);
//        StackInterface<StatementInterface> executionStack3 = new ADTStack<StatementInterface>();
//        DictionaryInterface<String, ValueInterface> symbolTable3 = new ADTDictionary<String, ValueInterface>();
//        ListInterface<ValueInterface> out3 = new ADTList<ValueInterface>();
//        DictionaryInterface<StringValue, BufferedReader> fileTable3 = new ADTDictionary<StringValue, BufferedReader>();
//        HeapInterface<Integer, ValueInterface> heap3 = new ADTHeap<Integer, ValueInterface>();
//        ProgramState programState3 = new ProgramState(executionStack3, symbolTable3, out3, example3, fileTable3, heap3);
//        RepositoryInterface repository3 = new InMemoryRepository(programState3, "log3.txt");
//        Controller controller3 = new Controller(repository3);

        // int v; v=2; Print(v)
        // Ref int v;new(v,20);Ref Ref int a; new(a,v);print(v);print(a)
        StatementInterface example4 = new CompoundStatement(new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                new CompoundStatement(new NewStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                                new PrintStatement(new VariableExpression("a")))))));

//        ADTDictionary<String, TypeInterface> typeEnv4 = new ADTDictionary<String, TypeInterface>();
//        example4.typeCheck(typeEnv4);
//        StackInterface<StatementInterface> executionStack4 = new ADTStack<StatementInterface>();
//        DictionaryInterface<String, ValueInterface> symbolTable4 = new ADTDictionary<String, ValueInterface>();
//        ListInterface<ValueInterface> out4 = new ADTList<ValueInterface>();
//        DictionaryInterface<StringValue, BufferedReader> fileTable4 = new ADTDictionary<StringValue, BufferedReader>();
//        HeapInterface<Integer, ValueInterface> heap4 = new ADTHeap<Integer, ValueInterface>();
//        ProgramState programState4 = new ProgramState(executionStack4, symbolTable4, out4, example4, fileTable4, heap4);
//        RepositoryInterface repository4 = new InMemoryRepository(programState4, "log4.txt");
//        Controller controller4 = new Controller(repository4);
        //controller4.allSteps();

        // Ref int v;new(v,20);Ref Ref int a; new(a,v);print(rH(v));print(rH(rH(a))+5)
        StatementInterface example5 = new CompoundStatement(new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                new CompoundStatement(new NewStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(new PrintStatement(new readHeapExpression(new VariableExpression("v"))),
                                                new PrintStatement(new ArithmeticExpression("+",
                                                        new readHeapExpression(new readHeapExpression(new VariableExpression("a"))),
                                                        new ValueExpression(new IntValue(5)))))))));

//        ADTDictionary<String, TypeInterface> typeEnv5 = new ADTDictionary<String, TypeInterface>();
//        example5.typeCheck(typeEnv5);
//        StackInterface<StatementInterface> executionStack5 = new ADTStack<StatementInterface>();
//        DictionaryInterface<String, ValueInterface> symbolTable5 = new ADTDictionary<String, ValueInterface>();
//        ListInterface<ValueInterface> out5 = new ADTList<ValueInterface>();
//        DictionaryInterface<StringValue, BufferedReader> fileTable5 = new ADTDictionary<StringValue, BufferedReader>();
//        HeapInterface<Integer, ValueInterface> heap5 = new ADTHeap<Integer, ValueInterface>();
//        ProgramState programState5 = new ProgramState(executionStack5, symbolTable5, out5, example5, fileTable5, heap5);
//        RepositoryInterface repository5 = new InMemoryRepository(programState5, "log5.txt");
//        Controller controller5 = new Controller(repository5);
        //controller5.allSteps();

        // Ref int v;new(v,20);print(rH(v)); wH(v,30);print(rH(v)+5);
        StatementInterface example6 = new CompoundStatement(new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new PrintStatement(new readHeapExpression(new VariableExpression("v"))),
                                new CompoundStatement(new writeHeapStatement("v", new ValueExpression(new IntValue(30))),
                                        new PrintStatement(new ArithmeticExpression("+",
                                                new readHeapExpression(new VariableExpression("v")),
                                                new ValueExpression(new IntValue(5))))))));

//        ADTDictionary<String, TypeInterface> typeEnv6 = new ADTDictionary<String, TypeInterface>();
//        example6.typeCheck(typeEnv6);
//        StackInterface<StatementInterface> executionStack6 = new ADTStack<StatementInterface>();
//        DictionaryInterface<String, ValueInterface> symbolTable6 = new ADTDictionary<String, ValueInterface>();
//        ListInterface<ValueInterface> out6 = new ADTList<ValueInterface>();
//        DictionaryInterface<StringValue, BufferedReader> fileTable6 = new ADTDictionary<StringValue, BufferedReader>();
//        HeapInterface<Integer, ValueInterface> heap6 = new ADTHeap<Integer, ValueInterface>();
//        ProgramState programState6 = new ProgramState(executionStack6, symbolTable6, out6, example6, fileTable6, heap6);
//        RepositoryInterface repository6 = new InMemoryRepository(programState6, "log6.txt");
//        Controller controller6 = new Controller(repository6);
//        //controller6.allSteps();

        // Ref int v;new(v,20);Ref Ref int a; new(a,v); new(v,30);print(rH(rH(a)));
        StatementInterface example7 = new CompoundStatement(new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                new CompoundStatement(new NewStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(30))),
                                                new PrintStatement(new readHeapExpression(new readHeapExpression(new VariableExpression("a")))))))));

//        ADTDictionary<String, TypeInterface> typeEnv7 = new ADTDictionary<String, TypeInterface>();
//        example7.typeCheck(typeEnv7);
//        StackInterface<StatementInterface> executionStack7 = new ADTStack<StatementInterface>();
//        DictionaryInterface<String, ValueInterface> symbolTable7 = new ADTDictionary<String, ValueInterface>();
//        ListInterface<ValueInterface> out7 = new ADTList<ValueInterface>();
//        DictionaryInterface<StringValue, BufferedReader> fileTable7 = new ADTDictionary<StringValue, BufferedReader>();
//        HeapInterface<Integer, ValueInterface> heap7 = new ADTHeap<Integer, ValueInterface>();
//        ProgramState programState7 = new ProgramState(executionStack7, symbolTable7, out7, example7, fileTable7, heap7);
//        RepositoryInterface repository7 = new InMemoryRepository(programState7, "log7.txt");
//        Controller controller7 = new Controller(repository7);
//        //controller7.allSteps();


        // int v; v=4; (while (v>0) print(v);v=v-1);print(v)
        StatementInterface example8 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(4))),
                        new CompoundStatement(new CompoundStatement(new WhileStatement(new RelationalExpression(">",
                                new VariableExpression("v"), new ValueExpression(new IntValue(0)))),
                                new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                        new AssignmentStatement("v", new ArithmeticExpression("-",
                                                new VariableExpression("v"), new ValueExpression(new IntValue(1)))))),
                                new PrintStatement(new VariableExpression("v")))));

//        ADTDictionary<String, TypeInterface> typeEnv8 = new ADTDictionary<String, TypeInterface>();
//        example8.typeCheck(typeEnv8);
//        StackInterface<StatementInterface> executionStack8 = new ADTStack<StatementInterface>();
//        DictionaryInterface<String, ValueInterface> symbolTable8 = new ADTDictionary<String, ValueInterface>();
//        ListInterface<ValueInterface> out8 = new ADTList<ValueInterface>();
//        DictionaryInterface<StringValue, BufferedReader> fileTable8 = new ADTDictionary<StringValue, BufferedReader>();
//        HeapInterface<Integer, ValueInterface> heap8 = new ADTHeap<Integer, ValueInterface>();
//        ProgramState programState8 = new ProgramState(executionStack8, symbolTable8, out8, example8, fileTable8, heap8);
//        RepositoryInterface repository8 = new InMemoryRepository(programState8, "log8.txt");
//        Controller controller8 = new Controller(repository8);
//        //controller8.allSteps();

        // int v; Ref int a; v=10;new(a,22);
        // fork(wH(a,30);v=32;print(v);print(rH(a)));
        // print(v);print(rH(a))
//        StatementInterface example9 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
//                new CompoundStatement(new VariableDeclarationStatement("a", new ReferenceType(new IntType())),
//                    new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(10))),
//                            new CompoundStatement(new NewStatement("a", new ValueExpression(new IntValue(22))),
//                                    new CompoundStatement(new ForkStatement(new CompoundStatement(new writeHeapStatement("a", new ValueExpression(new IntValue(30))),
//                                                                                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(32))),
//                                                                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")),
//                                                                                                new PrintStatement(new readHeapExpression(new VariableExpression("a"))))))),
//                                            new CompoundStatement(new PrintStatement(new VariableExpression("v")),
//                                                    new PrintStatement(new readHeapExpression(new VariableExpression("a")))))))));
        StatementInterface example9 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("a", new ReferenceType(new IntType())),
                        new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(10))),
                                new CompoundStatement(new NewStatement("a", new ValueExpression(new IntValue(22))),
                                        new CompoundStatement(new ForkStatement(new CompoundStatement(new writeHeapStatement("a", new ValueExpression(new IntValue(30))),
                                                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(32))),
                                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                                                new PrintStatement(new readHeapExpression(new VariableExpression("a"))))))),
                                                new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                                        new PrintStatement(new readHeapExpression(new VariableExpression("a")))))))));

//        ADTDictionary<String, TypeInterface> typeEnv9 = new ADTDictionary<String, TypeInterface>();
//        example9.typeCheck(typeEnv9);
//        StackInterface<StatementInterface> executionStack9 = new ADTStack<StatementInterface>();
//        DictionaryInterface<String, ValueInterface> symbolTable9 = new ADTDictionary<String, ValueInterface>();
//        ListInterface<ValueInterface> out9 = new ADTList<ValueInterface>();
//        DictionaryInterface<StringValue, BufferedReader> fileTable9 = new ADTDictionary<StringValue, BufferedReader>();
//        HeapInterface<Integer, ValueInterface> heap9 = new ADTHeap<Integer, ValueInterface>();
//        ProgramState programState9 = new ProgramState(executionStack9, symbolTable9, out9, example9, fileTable9, heap9);
//        RepositoryInterface repository9 = new InMemoryRepository(programState9, "log9.txt");
//        Controller controller9 = new Controller(repository9);
        //controller9.allSteps();

        // add all the statements to the listProgramStates
//        this.listProgramStates.add(example1);
//        this.listProgramStates.add(example2);
//        this.listProgramStates.add(example3);
//        this.listProgramStates.add(example4);
//        this.listProgramStates.add(example5);
//        this.listProgramStates.add(example6);
//        this.listProgramStates.add(example7);
//        this.listProgramStates.add(example8);
//        this.listProgramStates.add(example9);

        ObservableList<StatementInterface> data = FXCollections.observableArrayList();
        data.add(example1);
        data.add(example2);
        data.add(example3);
        data.add(example4);
        data.add(example5);
        data.add(example6);
        data.add(example7);
        data.add(example8);
        data.add(example9);

        programListView.setItems(data);
    }


    @FXML
    public void initialize() {
        // populate the programs list view
        try {
            this.populateProgramsListView();
        } catch (ExpressionEvaluationException e) {
            e.printStackTrace();
        }

        //this.programListView.getSelectionModel().selectFirst();

        // set the action of the selectProgram button
        this.selectProgramButton.setOnAction(
                actionEvent -> {
                int indexStatement = this.programListView.getSelectionModel().getSelectedIndex();

                if(indexStatement >= 0) {
                    // if a program was selected
                    StatementInterface selectedStatement = this.programListView.getItems().get(indexStatement);

                    ADTDictionary<String, TypeInterface> typeEnv1 = new ADTDictionary<String, TypeInterface>();
                    try {
                        selectedStatement.typeCheck(typeEnv1);
                    } catch (ExpressionEvaluationException e) {
                        e.printStackTrace();
                        Alert error = new Alert(Alert.AlertType.ERROR, "The program is not correct", ButtonType.OK);
                        error.showAndWait();
                    }

                    StackInterface<StatementInterface> executionStack = new ADTStack<StatementInterface>();
                    DictionaryInterface<String, ValueInterface> symbolTable = new ADTDictionary<String, ValueInterface>();
                    ListInterface<ValueInterface> out = new ADTList<ValueInterface>();
                    DictionaryInterface<StringValue, BufferedReader> fileTable = new ADTDictionary<StringValue, BufferedReader>();
                    HeapInterface<Integer, ValueInterface> heap = new ADTHeap<Integer, ValueInterface>();
                    ProgramState programState = new ProgramState(executionStack, symbolTable, out, selectedStatement, fileTable, heap);
                    RepositoryInterface repository = new InMemoryRepository(programState, "log.txt");
                    Controller controller = new Controller(repository);

                    // set the program state controller of the main window
                    this.mainWindowController.setProgramStateController(controller);
                }
                else
                {
                    // no program was selected
                    Alert error = new Alert(Alert.AlertType.ERROR, "No program was selected.", ButtonType.OK);
                    error.showAndWait();
                }
                // main window set controller



//                Stage programStage = new Stage();
//                GridPane rootMainWindow = null;
//                Callback<Class<?>, Object> controllerFactory =
//                try {
//                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
//                    fxmlLoader.setControllerFactory();
//                    rootMainWindow = (GridPane) fxmlLoader.load(getClass().getResource("MainWindow.fxml"));
//                    Scene sceneMainWindow = new Scene(rootMainWindow, 400, 400);
//                    programStage.setTitle("Welcome main stage untold");
//                    programStage.setScene(sceneMainWindow);
//                    programStage.show();
//                } catch (IOException e) {
//                    e.printStackTrace();
            }
        );
    }
}
