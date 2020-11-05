package Repository;
import Model.ADT.ADTList;
import Model.exceptions.ADTException;
import Repository.RepositoryInterface;
import Model.ProgramState;

import java.util.List;

public class InMemoryRepository implements RepositoryInterface{
    ADTList<ProgramState> programStates;

    public InMemoryRepository()
    {
        programStates = new ADTList<ProgramState>();
    }

    @Override
    public void addProgram(ProgramState programState) {
        this.programStates.add(programState);
    }

    @Override
    public ProgramState getCurrentProgram() throws ADTException {
        return this.programStates.pop();
    }
}
