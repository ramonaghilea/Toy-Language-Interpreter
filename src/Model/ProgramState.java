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
        this.originalProgram = originalProgram.deepCopy();
        this.executionStack.push(originalProgram);
    }

    protected StatementInterface deepCopy(StatementInterface originalProgram) {
        return originalProgram;
    }

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

    // setters
    public void setStack(StackInterface<StatementInterface> stack)  { this.executionStack = stack; }
    public void setSymbolTable(DictionaryInterface<String, ValueInterface> table) { this.symbolTable = table; }
    public void setOut(ListInterface<ValueInterface> out) { this.out = out; }
    public void setOriginalProgram(StatementInterface originalProgram) { this.originalProgram = originalProgram; }
    public void setFileTable(DictionaryInterface<StringValue, BufferedReader> fileTable) { this.fileTable = fileTable; }
    public void setHeap(HeapInterface<Integer, ValueInterface> heap) { this.heap = heap; }

    public void addFile(String fileName)
    {
        this.fileTable.add(new StringValue(fileName), null);
    }

    @Override
    public String toString() {
        String message = "";
        message += "Execution stack: " + this.executionStack.toString() + "\n";
        message += "Symbol table: " + this.symbolTable.toString() + "\n";
        message += "Out: " + this.out.toString() + "\n";
        message += "FileTable: " + this.fileTable.toString() + "\n";
        message += "Heap: " + this.heap.toString() + "\n";
        return message;
    }
}
