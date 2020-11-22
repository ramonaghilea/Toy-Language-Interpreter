package Repository;
import Model.ProgramState;
import Model.exceptions.ADTException;

import java.io.IOException;

public interface RepositoryInterface {

    void addProgram(ProgramState programState);
    ProgramState getCurrentProgram() throws ADTException;
    void logProgramStateExec() throws Exception;
    void clearLogFile() throws Exception;
}
