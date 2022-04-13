package main;

import sdk.interfaces.StartupScreen;

import java.util.InputMismatchException;

import static main.Utils.SCANNER;

public class StartupScreenImpl implements StartupScreen {

    @Override
    public int getLoginChoice(String content) {
        int choice =0;
        printContent(content);
        try {
                choice = SCANNER.nextInt();
            } catch (InputMismatchException inputMismatchException) {
                Utils.printErrToConsole(System.lineSeparator()+ "Your input is mismatched");
                SCANNER.nextLine();
            }

        return choice;
    }

    public void printNoUserFound()
    {
        Utils.printErrToConsole(System.lineSeparator()+"Sorry , No user found");
    }

    public void printSignUpSuccess(boolean success)
    {
        if(success)
        {
            Utils.printIOToConsole(System.lineSeparator()+"Sign up successful");
        }
        else
        {
            Utils.printErrToConsole(System.lineSeparator()+ "Sign up failed");
        }
    }

    public void printInvalidChoice()
    {
        Utils.printErrToConsole(System.lineSeparator()+"Invalid choice");
    }

    public void printContent(String content)
    {
        Utils.printIOToConsole(System.lineSeparator()+content);
    }

    public void printErrContent(String content)
    {
        Utils.printErrToConsole(System.lineSeparator()+content);
    }
}
