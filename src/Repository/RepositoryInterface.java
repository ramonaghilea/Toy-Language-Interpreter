package Repository;
import Model.ProgramState;
import Model.exceptions.ADTException;

public interface RepositoryInterface {

    void addProgram(ProgramState programState);
    ProgramState getCurrentProgram() throws ADTException;
}
