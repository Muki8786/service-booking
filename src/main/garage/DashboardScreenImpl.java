package main.garage;

import main.Utils;
import sdk.garage.Employee;
import sdk.garage.Garage;
import sdk.interfaces.garage.DashboardScreen;

import java.util.InputMismatchException;
import java.util.List;

import static main.Utils.SCANNER;

public class DashboardScreenImpl implements DashboardScreen {
    @Override
    public int getDashboardChoice(String content) {
        int choice = 0;
        displayContent(content);
        try {
            choice = SCANNER.nextInt();
        }
        catch (InputMismatchException inputMismatchException)
        {
            SCANNER.nextLine();
            choice = 0;
        }
        return choice;
    }

    @Override
    public int getManageGarageProfileChoice(String content) {
        int choice = 0;
        displayContent(content);
        try {
            choice = SCANNER.nextInt();
        }
        catch (InputMismatchException inputMismatchException)
        {
            SCANNER.nextLine();
            choice = 0;
        }
        return choice;

    }

    @Override
    public void displayContent(String content) {
        Utils.printIOToConsole(System.lineSeparator()+content);
    }

    @Override
    public int getManageEmployeesChoice(String content) {
        int choice = 0;
        displayContent(content);
        try {
            choice = SCANNER.nextInt();
        }
        catch (InputMismatchException inputMismatchException)
        {
            SCANNER.nextLine();
            choice = 0;
        }
        return choice;
    }

    public int getRatingOrFeedbackChoice(String content)
    {
        int choice = 0;
        displayContent(content);
        try {
            choice = SCANNER.nextInt();
        }
        catch (InputMismatchException inputMismatchException)
        {
            SCANNER.nextLine();
            choice = 0;
        }
        return choice;
    }

    @Override
    public int getEmployeeChoice(List<Employee> employeeList) {
        int choice = 0;
        while (true) {
            displayEmployeeChoices(employeeList);
            Utils.printIOToConsole(System.lineSeparator() +"Enter your Choice : ");
            try {
                choice = SCANNER.nextInt();
                if(choice>=0 && choice <= employeeList.size())
                    break;
                else {
                    Utils.printErrToConsole(System.lineSeparator()+"Invalid choice.Try again.");
                }
            } catch (InputMismatchException inputMismatchException) {
                Utils.printErrToConsole("Your input is mismatched");
                SCANNER.nextLine();
            }
        }
        return choice;
    }

    void displayEmployeeChoices(List<Employee> employeeList)
    {
        Utils.printIOToConsole(System.lineSeparator()+"Press 0 to exit booking");
        int count = 1;
        Utils.printIOToConsole("\nChoose the Employee");
        for(Employee employee: employeeList)
        {
            Utils.printIOToConsole(System.lineSeparator()+"Press "+ count + " for "+ employee.getName()+"\t"+employee.getEmail());
            count++;
        }
    }

    public void printContent(String content)
    {
        Utils.printIOToConsole("\n"+content);
    }

    public void printErrorContent(String content)
    {
        Utils.printErrToConsole("\n"+content);
    }
}
