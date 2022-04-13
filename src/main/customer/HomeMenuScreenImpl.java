package main.customer;

import main.Utils;
import sdk.interfaces.customer.HomeMenuScreen;

import java.util.InputMismatchException;

import static main.Utils.SCANNER;

public class HomeMenuScreenImpl implements HomeMenuScreen {

    @Override
    public int getHomeChoice(String content) {
        int choice = 0;

            displayContent(content);
            try {
                choice = SCANNER.nextInt();
            } catch (InputMismatchException inputMismatchException) {
                Utils.printErrToConsole("Your input is mismatched");
                SCANNER.nextLine();
            }
        return choice;
    }

    public void displayContent(String content)
    {
        Utils.printIOToConsole(content);
    }

    public void displayWelcomeMessage()
    {
        Utils.printIOToConsole(System.lineSeparator() + "Welcome");
    }

    public int getExitConsent(String content)
    {
        displayContent(content);
        int choice =0;
            try {
                choice = SCANNER.nextInt();
            } catch (InputMismatchException inputMismatchException) {
                SCANNER.nextLine();
            }
        return choice;
    }

    public void displayThankYou()
    {
        Utils.printIOToConsole("Thank you");
    }

    public void printErrContent(String content)
    {
        Utils.printErrToConsole(System.lineSeparator()+content);
    }
}
