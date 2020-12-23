package Repository;
import Model.ADT.*;
import Model.exceptions.ADTException;
import Model.statement.StatementInterface;
import Model.value.StringValue;
import Model.value.ValueInterface;
import Repository.RepositoryInterface;
import Model.ProgramState;

import java.io.*;
import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.List;

public class InMemoryRepository implements RepositoryInterface{
    private List<ProgramState> programStates;
    private String logFilePath;

    public InMemoryRepository(ProgramState firstProgramState, String filePath)
    {
        this.programStates = new ArrayList<ProgramState>();
        //this.firstProgramState = firstProgramState; // used for writing the stack, tables and out to the file
        this.logFilePath = filePath;
        this.addProgram(firstProgramState);
    }

    @Override
    public void addProgram(ProgramState programState) {
        this.programStates.add(programState);
    }


    @Override
    public void logProgramStateExec(ProgramState programState) throws RuntimeException, IOException {
        PrintWriter logFile = null;
        try{
            logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));

            StackInterface<StatementInterface> executionStack = programState.getStack();
            DictionaryInterface<String, ValueInterface> symbolTable = programState.getSymbolTable();
            ListInterface<ValueInterface> out = programState.getOut();
            DictionaryInterface<StringValue, BufferedReader> fileTable = programState.getFileTable();
            HeapInterface<Integer, ValueInterface> heap = programState.getHeap();

            logFile.println("Id:");
            logFile.println(programState.getID());
            logFile.println("ExeStack:");
            logFile.println(executionStack.toStringFileFormat());
            logFile.println("SymTable:");
            logFile.println(symbolTable.toStringFileFormat());
            logFile.println("Out:");
            logFile.println(out.toStringFileFormat());
            logFile.println("FileTable:");
            logFile.println(fileTable.toStringFileFormat());
            logFile.println("Heap:");
            logFile.println(heap.toStringFileFormat());
            logFile.println("\n");
        }
        catch (IOException exception)
        {
            throw new RuntimeException("the log file does not exist");
        }
        finally {
            if(logFile != null)
                logFile.close();
        }
    }

    @Override
    public void clearLogFile() throws Exception {
        try {
            PrintWriter writer = new PrintWriter(this.logFilePath);
            writer.print("");
            writer.close();
        }
        catch (FileNotFoundException exception)
        {
            throw new Exception("the log file does not exist");
        }
    }

    @Override
    public List<ProgramState> getProgramStateList() {
        return this.programStates;
    }

    @Override
    public void setProgramStateList(List<ProgramState> newProgramStateList) {
        this.programStates = newProgramStateList;
    }
}
