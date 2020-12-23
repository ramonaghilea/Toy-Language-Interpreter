package Model.statement;

import Model.ADT.*;
import Model.ProgramState;
import Model.exceptions.ExpressionEvaluationException;
import Model.type.TypeInterface;
import Model.value.StringValue;
import Model.value.ValueInterface;

import java.io.BufferedReader;

public class ForkStatement implements StatementInterface{
    private StatementInterface statement;

    public ForkStatement(StatementInterface statement) { this.statement = statement; }

    @Override
    public ProgramState execute(ProgramState programState) throws Exception {
        DictionaryInterface<String, ValueInterface> symbolTable = programState.getSymbolTable();

        // create the new program state
        // new execution stack, deep copy of the symbol table
        // out, file table and heap remain the same
        StackInterface<StatementInterface> newStack = new ADTStack<StatementInterface>();
        DictionaryInterface<String, ValueInterface> newSymbolTable = symbolTable.deepCopy();

        ProgramState newProgramState = new ProgramState(newStack, newSymbolTable, programState.getOut(), statement, programState.getFileTable(), programState.getHeap());
        return newProgramState;
    }

    @Override
    public StatementInterface deepCopy() {
        StatementInterface copyStatement = this.statement.deepCopy();
        ForkStatement copy = new ForkStatement(copyStatement);

        return copy;
    }

    @Override
    public ADTDictionary<String, TypeInterface> typeCheck(ADTDictionary<String, TypeInterface> typeEnv) throws ExpressionEvaluationException {
        return this.statement.typeCheck(typeEnv);
    }

    @Override
    public String toString() {
        return "fork( " + this.statement.toString() + ")";
    }
}
