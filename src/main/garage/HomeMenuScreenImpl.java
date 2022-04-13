package main.garage;

import main.Utils;
import sdk.interfaces.garage.HomeMenuScreen;

import java.util.InputMismatchException;

import static main.Utils.SCANNER;

public class HomeMenuScreenImpl implements HomeMenuScreen {
    @Override
    public int getHomeChoice(int notificationCount,int requestCount) {
        int choice = 0;
        while (true) {
            displayHomeChoices(notificationCount,requestCount);
            Utils.printIOToConsole(System.lineSeparator() +"Enter your Choice : ");
            try {
                choice = SCANNER.nextInt();
                if(choice>=0 && choice <= 5)
                    break;
                else {
                    Utils.printErrToConsole(System.lineSeparator()+"Invalid choice");
                }
            } catch (InputMismatchException inputMismatchException) {
                Utils.printErrToConsole("Your input is mismatched");
                SCANNER.nextLine();
            }
        }
        return choice;
    }

    public void displayHomeChoices(int notificationCount,int requestCount)
    {
        Utils.printIOToConsole(System.lineSeparator()+"Press 0 to exit");
        Utils.printIOToConsole(System.lineSeparator() + "Press 1 to Accept/Reject Requests_->"+requestCount);
        Utils.printIOToConsole(System.lineSeparator() + "Press 2 to view Notifications_->"+notificationCount);
        Utils.printIOToConsole(System.lineSeparator() + "Press 3 to open Dashboard");
        Utils.printIOToConsole(System.lineSeparator() + "Press 4 to view current services");
        Utils.printIOToConsole(System.lineSeparator() + "Press 5 to manage bills");
    }

    public void displayWelcomeMessage()
    {
        Utils.printIOToConsole(System.lineSeparator() + "Welcome");
    }

    public int getExitConsent()
    {
        Utils.printErrToConsole(System.lineSeparator()+"Are you sure you want to exit?");
        Utils.printIOToConsole(System.lineSeparator()+"Press 1 to continue using the app");
        Utils.printIOToConsole(System.lineSeparator()+"Press any other key to exit");
        Utils.printIOToConsole(System.lineSeparator()+"Enter Your choice : ");

        int choice =0;

        try {
            choice = SCANNER.nextInt();
        } catch (InputMismatchException inputMismatchException) {
            //displayThankYou();
            choice =0;
            SCANNER.nextLine();
        }
        return choice;
    }

    public void displayThankYou()
    {
        Utils.printIOToConsole("Thank you");
    }
}
