package main.garage;

import main.Utils;
import sdk.interfaces.garage.ManageRequestsScreen;

import java.util.InputMismatchException;

import static main.Utils.SCANNER;

public class ManageRequestsScreenImpl implements ManageRequestsScreen {
    @Override
    public void displayRequests(String content) {
        Utils.printIOToConsole(System.lineSeparator()+ content);
    }

    @Override
    public int getRequestChoice() {
        int choice = 0;
        while (true)
        {
            displayChooseRequestToManage();
            try {
                choice = SCANNER.nextInt();
                break;
            }
            catch (InputMismatchException inputMismatchException)
            {
                Utils.printErrToConsole(System.lineSeparator()+"The input should be a number");
                SCANNER.nextLine();
            }
        }
        return choice;
    }

    @Override
    public int getAcceptChoice() {
        int choice = 0;
        while (true)
        {
            Utils.printIOToConsole(System.lineSeparator()+"Press 1 to Accept the Request");
            Utils.printIOToConsole(System.lineSeparator()+"Press 2 to Reject the Request");
            Utils.printIOToConsole(System.lineSeparator()+"Press 0 to go back");
            Utils.printIOToConsole(System.lineSeparator()+"Enter your choice : ");

            try
            {
                choice = SCANNER.nextInt();
                break;
            }
            catch (InputMismatchException inputMismatchException)
            {
                Utils.printErrToConsole(System.lineSeparator()+"Your input should be a number");
                SCANNER.nextLine();
            }
        }
        return choice;
    }

    void displayChooseRequestToManage()
    {
        Utils.printIOToConsole(System.lineSeparator()+"Enter the Request ID to accept or reject a Request");
        Utils.printIOToConsole(System.lineSeparator()+"Press 0 to go back");
        Utils.printIOToConsole(System.lineSeparator()+"Enter your choice : ");
    }
}
