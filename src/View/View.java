package View;

import Controller.Controller;
import Model.ADT.*;
import Model.ProgramState;
import Model.statement.StatementInterface;
import Model.value.StringValue;
import Model.value.ValueInterface;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Scanner;

public class View {
    private Controller controller;
    private ArrayList<StatementInterface> listStatementInterfaces;
    Scanner inputScanner = new Scanner(System.in);

    public View(Controller controller, ArrayList<StatementInterface> listStatementInterfaces)
    {
        this.controller = controller;
        this.listStatementInterfaces = listStatementInterfaces;
    }

    private void printMenu()
    {
        String message = "0. Exit\n";
        message += "1. Input a program\n";
        message += "2. Complete execution of a program\n";

        System.out.println(message);
    }

    private void printAvailablePrograms()
    {
        String message = "";
        for(int i = 0; i < this.listStatementInterfaces.size(); i++)
            message += String.valueOf(i + 1) + " " + this.listStatementInterfaces.get(i).toString() + "\n";

        System.out.println(message);
    }

    private void inputProgram()
    {
        this.printAvailablePrograms();
        System.out.println("Enter the program you want:");
        int option = inputScanner.nextInt();
        inputScanner.nextLine();

        if (option < 1 || option > this.listStatementInterfaces.size())
            System.out.println("Wrong option");
        else
        {
            StatementInterface chosenStatementInterface= this.listStatementInterfaces.get(option - 1);
            StackInterface<StatementInterface> executionStack = new ADTStack<StatementInterface>();
            DictionaryInterface<String, ValueInterface> symbolTable = new ADTDictionary<String, ValueInterface>();
            ListInterface<ValueInterface> out = new ADTList<ValueInterface>();
            DictionaryInterface<StringValue, BufferedReader> fileTable = new ADTDictionary<StringValue, BufferedReader>();
            HeapInterface<Integer, ValueInterface> heap = new ADTHeap<Integer, ValueInterface>();
            ProgramState chosenProgramState = new ProgramState(executionStack, symbolTable, out,
                    chosenStatementInterface, fileTable, heap);
            this.controller.addProgram(chosenProgramState);
        }
    }

    private void completeExecutionOfCurrentProgram() throws Exception
    {
        this.controller.allSteps();
    }

    public void run()
    {
        while(true)
        {
            try {
                this.printMenu();
                System.out.println("Enter option:");
                int option = inputScanner.nextInt();
                inputScanner.nextLine();

                if (option == 0)
                    return;
                else if (option == 1)
                    inputProgram();
                else if (option == 2)
                    completeExecutionOfCurrentProgram();
                else
                    System.out.println("Wrong option");
                //System.out.println('\n');
            }
            catch (Exception exception)
            {
                System.out.println("Exception: " + exception.getMessage());
            }
        }
    }
}
