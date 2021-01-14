package ViewGUI;

import Controller.Controller;
import Model.ADT.*;
import Model.ProgramState;
import Model.exceptions.ExpressionEvaluationException;
import Model.statement.StatementInterface;
import Model.type.TypeInterface;
import Model.value.StringValue;
import Model.value.ValueInterface;
import Repository.InMemoryRepository;
import Repository.RepositoryInterface;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;


public class ControllerGUIMainWindow implements Initializable {
    private Controller controllerProgramState;
    private ProgramState currentProgramState; // the selected programState from listProgramStatesListView
    ExecutorService executor;

    @FXML
    private TextField numberProgramStatesTextField;

    @FXML
    private TableView<HeapValuesPair> heapTableTableView;

    @FXML
    private TableColumn<HeapValuesPair, Integer> addressColumnHeapTable;

    @FXML
    private TableColumn<HeapValuesPair, ValueInterface> valueColumnHeapTable;

    @FXML
    private ListView<Integer> listProgramStatesListView;

    @FXML
    private Button runOneStepButton;

    @FXML
    private ListView<ValueInterface> outListView;

    @FXML
    private ListView<StringValue> fileTableListView;

    @FXML
    private TableView<SymbolTableValuesPair> symbolTableTableView;

    @FXML
    private TableColumn<SymbolTableValuesPair, String> variableNameColumnSymbolTable;

    @FXML
    private TableColumn<SymbolTableValuesPair, ValueInterface> valueColumnSymbolTable;

    @FXML
    private ListView<StatementInterface> executionStackListView;

    @FXML
    private Label numberProgramStatesLabel;


    public void setProgramStateController(Controller controller)
    {
        this.controllerProgramState = controller;
        // set the currentProgramState to be the first in the list of program states
        this.setFirstProgramState();
        // populate the fields
        this.updateProgramStateDetails();
//        this.populateNumberProgramStatesTextField();
//        this.populateListProgramStatesIds();
    }

    public void setFirstProgramState()
    {
        ProgramState programState = this.controllerProgramState.getProgramStateList().get(0);
        if(programState != null)
            this.currentProgramState = programState;
    }

    public void setCurrentProgramState()
    {
        // take the selected id from the listProgramStatesListView
        int indexProgram = this.listProgramStatesListView.getSelectionModel().getSelectedIndex();

        if(indexProgram >= 0) {
            // if a program was selected
            Integer Id = this.listProgramStatesListView.getItems().get(indexProgram);
            // get the program state with the specific Id from the repository
            ProgramState programState = this.controllerProgramState.getProgramStateById(Id);
            if(programState != null)
                this.currentProgramState = programState;
        }
        else
        {
            // no program was selected
            Alert error = new Alert(Alert.AlertType.ERROR, "No program was selected.", ButtonType.OK);
            error.showAndWait();
        }
    }

    private void populateNumberProgramStatesTextField()
    {
        int numberProgramStates = this.controllerProgramState.getNumberProgramStates();
        this.numberProgramStatesTextField.setText(String.valueOf(numberProgramStates));
    }

    private void populateListProgramStatesIds()
    {
        this.listProgramStatesListView.setItems(FXCollections.observableArrayList(this.controllerProgramState.getProgramStatesIds()));
    }

    private void populateExecutionStack()
    {

        ArrayList<StatementInterface> executionStack =  currentProgramState.getStack().getContent();

        this.executionStackListView.setItems(FXCollections.observableArrayList(executionStack));

    }
    private void populateSymbolTable()
    {
        //DictionaryInterface<String, ValueInterface> symbolTable = currentProgramState.getSymbolTable();
        //this.symbolTableTableView.setItems(FXCollections.observableArrayList(new ArrayList<>(currentProgramState.getSymbolTable().getContent())));
        ObservableList<SymbolTableValuesPair> data = FXCollections.observableArrayList();
        Map<String, ValueInterface> symbolTable = this.currentProgramState.getSymbolTable().getContent();
        for(Map.Entry<String, ValueInterface> elem : symbolTable.entrySet())
            data.add(new SymbolTableValuesPair(elem.getKey(), elem.getValue()));

        this.symbolTableTableView.setItems(data);
    }
    private void populateOut()
    {
        this.outListView.setItems(FXCollections.observableArrayList(currentProgramState.getOut().getContent()));
    }
    private void populateHeap()
    {
        ObservableList<HeapValuesPair> data = FXCollections.observableArrayList();
        Map<Integer, ValueInterface> heap = this.currentProgramState.getHeap().getContent();
        for(Map.Entry<Integer, ValueInterface> elem : heap.entrySet())
            data.add(new HeapValuesPair(elem.getKey(), elem.getValue()));

        this.heapTableTableView.setItems(data);
    }
    private void populateFileTable()
    {
        this.fileTableListView.setItems(FXCollections.observableArrayList(currentProgramState.getFileTable().getKeysContent()));
    }

    public void updateProgramStateDetails()
    {
        this.populateNumberProgramStatesTextField();
        this.populateListProgramStatesIds();
        this.populateExecutionStack();
        this.populateSymbolTable();
        this.populateOut();
        this.populateHeap();
        this.populateFileTable();
    }

    public void executeOneStepMainWindow() throws Exception {
        executor = Executors.newFixedThreadPool(2);
        this.controllerProgramState.setExecutor(executor);
        // remove the completed programs
        // clear the log file
        this.controllerProgramState.clearLogFileRepository();
        List<ProgramState> programList = this.controllerProgramState.removeCompletedPrograms(this.controllerProgramState.getProgramStateList());
        if(programList.size() > 0)
        {
            // call the garbage collector
            programList.forEach(program -> { program.getHeap().setContent(
                    this.controllerProgramState.garbageCollector(
                            this.controllerProgramState.getAddressFromSymbolTableAndHeap(
                                    program.getSymbolTable().getContent().values(),
                                    program.getHeap().getContent().values()),
                            program.getHeap().getContent())); });

            // execute one step for all programs
            this.controllerProgramState.oneStepForAllPrograms(programList);

            // remove the completed programs
            programList = this.controllerProgramState.removeCompletedPrograms(this.controllerProgramState.getProgramStateList());
        }
        else
        {
            Alert error = new Alert(Alert.AlertType.INFORMATION, "The programs have ended.", ButtonType.OK);
            error.showAndWait();
        }
        executor.shutdownNow();

        // update the repository
        this.controllerProgramState.setProgramStateList(programList);


        // update the interface
        this.updateProgramStateDetails();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.addressColumnHeapTable.setCellValueFactory(new PropertyValueFactory<>("address"));
        this.valueColumnHeapTable.setCellValueFactory(new PropertyValueFactory<>("value"));

        this.variableNameColumnSymbolTable.setCellValueFactory(new PropertyValueFactory<>("variableName"));
        this.valueColumnSymbolTable.setCellValueFactory(new PropertyValueFactory<>("value"));

        this.runOneStepButton.setOnAction(
                actionEvent -> {
                    if(this.controllerProgramState == null)
                    {
                        Alert error = new Alert(Alert.AlertType.ERROR, "No program was selected.", ButtonType.OK);
                        error.showAndWait();
                    }
                    else {
                        try {

                            this.executeOneStepMainWindow();

                        } catch (Exception exception) {
                            Alert error = new Alert(Alert.AlertType.ERROR, exception.getMessage(), ButtonType.OK);
                            error.showAndWait();

                            System.out.println(exception.getMessage());
                        }
                    }
                }
        );

        // action for selection in listProgramStatesListView
        this.listProgramStatesListView.setOnMouseClicked(
                actionEvent -> {
                    this.setCurrentProgramState();

                    // update the symbolTable, executionStack
                    this.populateSymbolTable();
                    this.populateExecutionStack();
                }
        );
    }
}
