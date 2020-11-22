package View;

import java.util.HashMap;
import java.util.Scanner;

public class TextMenu {
    private HashMap<String, Command> commands;

    public TextMenu() { commands = new HashMap<String, Command>(); }

    public void addCommand(Command command)
    {
        this.commands.put(command.getKey(), command);
    }

    private void printMenu()
    {
        System.out.println("Available options: ");
        for(Command command : commands.values())
        {
            String line = String.format("%4s : %s", command.getKey(), command.getDescription());
            System.out.println(line);
        }
    }

    public void show() throws Exception {
        Scanner scanner = new Scanner(System.in);
        while(true)
        {
            // print the menu and read the option chosen
            printMenu();
            System.out.println("Enter the option: ");
            String key = scanner.nextLine();

            // look up the command mapped to the option and execute it if exists
            Command command = commands.get(key);
            if(command == null)
            {
                System.out.println("Invalid option");
                continue;
            }
            command.execute();
        }
    }
}
