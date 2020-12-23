package Model;
import Model.ADT.*;

import Model.statement.CompoundStatement;
import Model.statement.StatementInterface;
import Model.value.ReferenceValue;
import Model.value.StringValue;
import Model.value.ValueInterface;

import java.io.BufferedReader;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProgramState {
    StackInterface<StatementInterface> executionStack;
    DictionaryInterface<String, ValueInterface> symbolTable;
    ListInterface<ValueInterface> out;
    StatementInterface originalProgram;
    DictionaryInterface<StringValue, BufferedReader> fileTable;
    HeapInterface<Integer, ValueInterface> heap;
    private int id;
    private static int nextId = 0; // common for all instances

//    public ProgramState(StackInterface<StatementInterface> executionStack, DictionaryInterface<String,
//            ValueInterface> symbolTable, ListInterface<ValueInterface> out)
//    {
//        this.executionStack = executionStack;
//        this.symbolTable = symbolTable;
//        this.out = out;
//    }

    public ProgramState(StackInterface<StatementInterface> executionStack, DictionaryInterface<String,
            ValueInterface> symbolTable, ListInterface<ValueInterface> out, StatementInterface originalProgram,
                        DictionaryInterface<StringValue, BufferedReader> fileTable,
                        HeapInterface<Integer, ValueInterface> heap)
    {
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.out = out;
        this.fileTable = fileTable;
        this.heap = heap;
        this.originalProgram = originalProgram;
        this.executionStack.push(originalProgram);
        this.id = generateNewId();
    }

    public static synchronized int generateNewId()
    {
        // generates a differend id for every instance of ProgramState
        int newId = nextId;
        nextId += 1;
        return newId;
    }

//    protected StatementInterface deepCopy(StatementInterface originalProgram) {
//        return originalProgram;
//    }

    // getters
    public StackInterface<StatementInterface> getStack()
    {
        return this.executionStack;
    }
    public DictionaryInterface<String, ValueInterface> getSymbolTable() {
        return this.symbolTable;
    }
    public ListInterface<ValueInterface> getOut() { return this.out; }
    public StatementInterface getOriginalProgram() { return this.originalProgram; }
    public DictionaryInterface<StringValue, BufferedReader> getFileTable() { return this.fileTable; }
    public HeapInterface<Integer, ValueInterface> getHeap() { return this.heap; }
    public int getID() { return this.id; };

    // setters
    public void setStack(StackInterface<StatementInterface> stack)  { this.executionStack = stack; }
    public void setSymbolTable(DictionaryInterface<String, ValueInterface> table) { this.symbolTable = table; }
    public void setOut(ListInterface<ValueInterface> out) { this.out = out; }
    public void setOriginalProgram(StatementInterface originalProgram) { this.originalProgram = originalProgram; }
    public void setFileTable(DictionaryInterface<StringValue, BufferedReader> fileTable) { this.fileTable = fileTable; }
    public void setHeap(HeapInterface<Integer, ValueInterface> heap) { this.heap = heap; }
    public void setID(int id) { this.id = id; }

    public void addFile(String fileName)
    {
        this.fileTable.add(new StringValue(fileName), null);
    }

    public boolean isNotCompleted()
    {
        return !this.executionStack.isEmpty();
    }

    public ProgramState oneStep() throws Exception
    {
        if(this.executionStack.isEmpty())
            throw new Exception("program state stack is empty");
        StatementInterface currentStatement = executionStack.pop();

        return currentStatement.execute(this);
    }

    @Override
    public String toString() {
        String message = "";
        message += "Id: " + this.id + "\n";
        message += "Execution stack: " + this.executionStack.toString() + "\n";
        message += "Symbol table: " + this.symbolTable.toString() + "\n";
        message += "Out: " + this.out.toString() + "\n";
        message += "FileTable: " + this.fileTable.toString() + "\n";
        message += "Heap: " + this.heap.toString() + "\n";
        return message;
    }
}
