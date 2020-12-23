package Model.statement;
import Model.ADT.ADTDictionary;
import Model.ADT.DictionaryInterface;
import Model.ADT.StackInterface;
import Model.ProgramState;
import Model.exceptions.ExpressionEvaluationException;
import Model.exceptions.StatementException;
import Model.statement.StatementInterface;
import Model.type.IntType;
import Model.type.TypeInterface;
import Model.value.BoolValue;
import Model.value.IntValue;
import Model.value.ValueInterface;
import com.sun.jdi.Value;

public class VariableDeclarationStatement implements StatementInterface{
    String name;
    TypeInterface type;

    public VariableDeclarationStatement(String name, TypeInterface type)
    {
        this.name = name;
        this.type = type;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws StatementException {
        StackInterface<StatementInterface> stack = programState.getStack();
        DictionaryInterface<String, ValueInterface> symbolTable = programState.getSymbolTable();

        if (!symbolTable.isDefined(name)) {
            ValueInterface value = type.defaultValue();
            symbolTable.add(name, value);
        }
        else throw new StatementException("the variable " + name + " has already been declared");

        return null;
    }

    @Override
    public StatementInterface deepCopy() {
        String copyId = this.name;
        TypeInterface copyType = this.type.deepCopy();
        VariableDeclarationStatement copy = new VariableDeclarationStatement(copyId, copyType);

        return copy;
    }

    @Override
    public ADTDictionary<String, TypeInterface> typeCheck(ADTDictionary<String, TypeInterface> typeEnv) throws ExpressionEvaluationException {
        typeEnv.add(this.name, this.type);
        return typeEnv;
    }

    @Override
    public String toString() {
        return this.type.toString() + " " + this.name;
    }
}
