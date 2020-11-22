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
import java.util.List;

public class InMemoryRepository implements RepositoryInterface{
    private ADTList<ProgramState> programStates;
    private String logFilePath;
    private ProgramState firstProgramState;

    public InMemoryRepository(ProgramState firstProgramState, String filePath)
    {
        this.programStates = new ADTList<ProgramState>();
        this.firstProgramState = firstProgramState; // used for writing the stack, tables and out to the file
        this.logFilePath = filePath;
        this.addProgram(firstProgramState);
    }

    @Override
    public void addProgram(ProgramState programState) {
        this.programStates.add(programState);
    }

    @Override
    public ProgramState getCurrentProgram() throws ADTException {
        return this.programStates.pop();
    }

    @Override
    public void logProgramStateExec() throws Exception {
        PrintWriter logFile = null;
        try{
            logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));

            StackInterface<StatementInterface> executionStack = firstProgramState.getStack();
            DictionaryInterface<String, ValueInterface> symbolTable = firstProgramState.getSymbolTable();
            ListInterface<ValueInterface> out = firstProgramState.getOut();
            DictionaryInterface<StringValue, BufferedReader> fileTable = firstProgramState.getFileTable();
            HeapInterface<Integer, ValueInterface> heap = firstProgramState.getHeap();

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
        catch (FileNotFoundException exception)
        {
            throw new Exception("the log file does not exist");
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
}
