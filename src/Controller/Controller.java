package Controller;

import Model.ADT.StackInterface;
import Model.ProgramState;
import Model.statement.StatementInterface;
import Repository.RepositoryInterface;

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

        while(!programState.getStack().isEmpty())
        {
            oneStep(programState);
            if(this.displayFlag)
                System.out.println(programState.toString());
        }
    }
}
