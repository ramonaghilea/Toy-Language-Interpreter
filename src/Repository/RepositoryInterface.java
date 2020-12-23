package Repository;
import Model.ADT.ADTList;
import Model.ProgramState;
import Model.exceptions.ADTException;

import java.io.IOException;
import java.util.List;

public interface RepositoryInterface {

    void addProgram(ProgramState programState);
    void logProgramStateExec(ProgramState programState) throws Exception;
    void clearLogFile() throws Exception;

    List<ProgramState> getProgramStateList();
    void setProgramStateList(List<ProgramState> newProgramStateList);
}
