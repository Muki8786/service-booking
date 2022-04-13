package main.garage;

import main.Utils;
import sdk.interfaces.garage.ManageBillsScreen;

import java.util.InputMismatchException;

import static main.Utils.SCANNER;

public class ManageBillsScreenImpl implements ManageBillsScreen {
    @Override
    public int getDisplayingChoice(String content) {
        int choice =0;

            Utils.printIOToConsole(System.lineSeparator()+content);
            try
            {
                choice = SCANNER.nextInt();
            } catch (InputMismatchException inputMismatchException)
            {
                choice = 0;
                SCANNER.nextLine();
            }
        return choice;
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
