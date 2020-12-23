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
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {
    private RepositoryInterface repository;
    private boolean displayFlag;
    ExecutorService executor;

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

//    public List<Integer> getAddressFromSymbolTable(Collection<ValueInterface> symbolTableValues)
//    {
//        return symbolTableValues.stream().filter(v->v instanceof ReferenceValue).map(v->{ReferenceValue v1 = (ReferenceValue)v; return v1.getAddress();}).collect(Collectors.toList());
//    }

    public List<Integer> getAddressFromSymbolTableAndHeap(Collection<ValueInterface> symbolTableValues, Collection<ValueInterface> heapValues)
    {
        // take also the addresses from the heap, in case of multi-reference variable
        return Stream.concat(
                symbolTableValues.stream().filter(v->v instanceof ReferenceValue).map(v->{ReferenceValue v1 = (ReferenceValue)v; return v1.getAddress();}),
                        heapValues.stream().filter(v -> v instanceof ReferenceValue).map(v -> {ReferenceValue v1 = (ReferenceValue)v; return v1.getAddress();}))
                        .collect(Collectors.toList());
    }
    public Map<Integer, ValueInterface> garbageCollector(List<Integer> symbolTableAddresses,
                                                               Map<Integer, ValueInterface> heap)
    {
        return heap.entrySet().stream().filter(e->symbolTableAddresses.contains(e.getKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public List<ProgramState> removeCompletedPrograms(List<ProgramState> inputProgramList)
    {
        return inputProgramList.stream().filter(p -> p.isNotCompleted()).collect(Collectors.toList());
    }

//    public void allSteps() throws Exception
//    {
//        ProgramState programState = this.repository.getCurrentProgram();
//        System.out.println(programState.toString());
//        // clear the log file
//        this.repository.clearLogFile();
//        // write the program state to file
//        this.repository.logProgramStateExec();
//
//        while(!programState.getStack().isEmpty())
//        {
//            oneStep(programState);
//            // write the program state to file
//            this.repository.logProgramStateExec();
//            // call the garbage collector
//            programState.getHeap().setContent(
//                    garbageCollector(
//                            getAddressFromSymbolTableAndHeap(
//                                    programState.getSymbolTable().getContent().values(),
//                                    programState.getHeap().getContent().values()),
//                            programState.getHeap().getContent()));
//
////            programState.getHeap().setContent(
////                    garbageCollector(
////                            getAddressFromSymbolTable(
////                                    programState.getSymbolTable().getContent().values()),
////                            programState.getHeap().getContent()));
//
//            this.repository.logProgramStateExec();
//            if(this.displayFlag)
//                System.out.println(programState.toString());
//        }
//    }

    // InterruptedException for invokeAll
    public void oneStepForAllPrograms(List<ProgramState> programList) throws InterruptedException {
        // before execution, print the programState list into the log file
        programList.forEach(program -> {
            //this.repository.logProgramStateExec(program);
            try {
                this.repository.logProgramStateExec(program);
            } catch (Exception exception) {
                throw new RuntimeException(exception.getMessage());
            }
        });

        // run concurrently one step for each of the existing program states

        // prepare the list of Callables
        List<Callable<ProgramState>> callList = programList.stream()
                .map( (ProgramState program) -> (Callable<ProgramState>)(() -> { return program.oneStep(); }) )
                .collect(Collectors.toList());

        // start the execution of the callables
        // return the list of the new threads
        List<ProgramState> newProgramList = executor.invokeAll(callList).stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (Exception exception){
                        throw new RuntimeException(exception.getMessage());
                    }
                })
                .filter(program -> program != null)
                .collect(Collectors.toList());
        // add the new threads to the list of existing threads
        programList.addAll(newProgramList);

        // after execution, print the program state list into the log file
        programList.forEach(program -> {
            try {
                this.repository.logProgramStateExec(program);
            } catch (Exception exception) {
                throw new RuntimeException(exception.getMessage());
            }
        });

        // save the current programs in the repository
        this.repository.setProgramStateList(programList);
    }

    public void allSteps() throws Exception {
        executor = Executors.newFixedThreadPool(2);
        // remove the completed programs
        // clear the log file
        this.repository.clearLogFile();
        List<ProgramState> programList = removeCompletedPrograms(repository.getProgramStateList());
        while(programList.size() > 0)
        {
            // call the garbage collector
            programList.forEach(program -> { program.getHeap().setContent(
                    garbageCollector(
                            getAddressFromSymbolTableAndHeap(
                                    program.getSymbolTable().getContent().values(),
                                    program.getHeap().getContent().values()),
                            program.getHeap().getContent())); });
            // execute one step for all programs
            oneStepForAllPrograms(programList);
            // remove the completed programs
            programList = removeCompletedPrograms(repository.getProgramStateList());
        }
        executor.shutdownNow();

        // update the repository
        repository.setProgramStateList(programList);
    }
}
