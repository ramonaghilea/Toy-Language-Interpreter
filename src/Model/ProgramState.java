package Model;
import Model.ADT.*;

import Model.statement.CompoundStatement;
import Model.statement.StatementInterface;
import Model.value.ValueInterface;

import java.util.List;

public class ProgramState {
    StackInterface<StatementInterface> executionStack;
    DictionaryInterface<String, ValueInterface> symbolTable;
    ListInterface<ValueInterface> out;
    StatementInterface originalProgram;

//    public ProgramState(StackInterface<StatementInterface> executionStack, DictionaryInterface<String,
//            ValueInterface> symbolTable, ListInterface<ValueInterface> out)
//    {
//        this.executionStack = executionStack;
//        this.symbolTable = symbolTable;
//        this.out = out;
//    }

    public ProgramState(StackInterface<StatementInterface> executionStack, DictionaryInterface<String,
            ValueInterface> symbolTable, ListInterface<ValueInterface> out, StatementInterface originalProgram)
    {
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.out = out;
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

    // setters
    public void setStack(StackInterface<StatementInterface> stack)  { this.executionStack = stack; }
    public void setSymbolTable(DictionaryInterface<String, ValueInterface> table) { this.symbolTable = table; }
    public void setOut(ListInterface<ValueInterface> out) { this.out = out; }
    public void setOriginalProgram(StatementInterface originalProgram) { this.originalProgram = originalProgram; }

    @Override
    public String toString() {
        String message = "";
        message += "Execution stack: " + this.executionStack.toString() + "\n";
        message += "Symbol table: " + this.symbolTable.toString() + "\n";
        message += "Out: " + this.out.toString() + "\n";
        return message;
    }
}
