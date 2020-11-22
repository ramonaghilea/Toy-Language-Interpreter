package Controller;

import Model.ADT.StackInterface;
import Model.ProgramState;
import Model.statement.StatementInterface;
import Model.type.ReferenceType;
import Model.value.ReferenceValue;
import Model.value.ValueInterface;
import Repository.RepositoryInterface;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {
    private RepositoryInterface repository;
    private boolean displayFlag;

    public Controller(RepositoryInterface repository)
    {
        this.repository = repository;
        this.displayFlag = true;
    }

    public void addProgram(ProgramState programState)
    {
        this.repository.addProgram(programState);
    }

    public void setDisplayFlag(boolean boolValue)
    {
        this.displayFlag = boolValue;
    }

    public Map<Integer, ValueInterface> unsafeGarbageCollector(List<Integer> symbolTableAddresses,
                                                               Map<Integer, ValueInterface> heap)
    {
        return heap.entrySet().stream().filter(e->symbolTableAddresses.contains(e.getKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public int getInnerAddress(ReferenceValue refValue)
    {
        int address = refValue.getAddress();
        while(refValue.getType() instanceof ReferenceType)
        {

        }
        return address;
    }

    public List<Integer> getAddressFromSymbolTable(Collection<ValueInterface> symbolTableValues, Collection<ValueInterface> heapValues)
    {
        return Stream.concat(
                symbolTableValues.stream().filter(v->v instanceof ReferenceValue).map(v->{ReferenceValue v1 = (ReferenceValue)v; return v1.getAddress();}),
                        heapValues.stream().filter(v -> v instanceof ReferenceValue).map(v -> {ReferenceValue v1 = (ReferenceValue)v; return v1.getAddress();}))
                        .collect(Collectors.toList());
    }

    public ProgramState oneStep(ProgramState programState) throws Exception
    {
        StackInterface<StatementInterface> executionStack = programState.getStack();
        if(executionStack.isEmpty())
            throw new Exception("program state stack is empty");
        StatementInterface currentStatement = executionStack.pop();

        return currentStatement.execute(programState);
    }

    public void allSteps() throws Exception
    {
        ProgramState programState = this.repository.getCurrentProgram();
        System.out.println(programState.toString());
        // clear the log file
        this.repository.clearLogFile();
        // write the program state to file
        this.repository.logProgramStateExec();

        while(!programState.getStack().isEmpty())
        {
            oneStep(programState);
            // write the program state to file
            this.repository.logProgramStateExec();
            // call the garbage collector
            programState.getHeap().setContent(
                    unsafeGarbageCollector(
                            getAddressFromSymbolTable(
                                    programState.getSymbolTable().getContent().values(),
                                    programState.getHeap().getContent().values()),
                            programState.getHeap().getContent()));

            this.repository.logProgramStateExec();
            if(this.displayFlag)
                System.out.println(programState.toString());
        }
    }
}
