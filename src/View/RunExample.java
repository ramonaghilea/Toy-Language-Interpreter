package View;

import Controller.Controller;

public class RunExample extends Command{
    private Controller controller;

    public RunExample(String key, String description, Controller controller) {
        super(key, description);
        this.controller = controller;
    }

    @Override
    public void execute() throws Exception {
        try{
            controller.allSteps();
        }
        catch (Exception exception)
        {
            System.out.println("Exception: " + exception.getMessage());
        }
    }
}
