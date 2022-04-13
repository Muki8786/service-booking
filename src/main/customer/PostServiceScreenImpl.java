package main.customer;

import main.Utils;
import sdk.interfaces.customer.PostServicesScreen;

import java.util.InputMismatchException;

import static main.Utils.SCANNER;

public class PostServiceScreenImpl implements PostServicesScreen {
    @Override
    public int getRateUsChoice(String content) {
        int choice = 0;
        Utils.printIOToConsole(content);
        try
        {
            choice = SCANNER.nextInt();
        }catch (InputMismatchException inputMismatchException)
        {
            SCANNER.nextLine();
            choice = 0;
        }
        return choice;
    }

    @Override
    public int getTheStars(String content) {
        int choice = 0;
        Utils.printIOToConsole(content);
        try
        {
            choice = SCANNER.nextInt();
        }catch (InputMismatchException inputMismatchException)
        {
            SCANNER.nextLine();
            choice = 0;
        }
        return choice;
    }

    public int getFeedbackChoice(String content)
    {
        int choice = 0;
        Utils.printIOToConsole(content);
        try
        {
            choice = SCANNER.nextInt();
        }catch (InputMismatchException inputMismatchException)
        {
            SCANNER.nextLine();
            choice = 0;
        }
        return choice;
    }

    @Override
    public String getDescription(String content) {
        Utils.printIOToConsole(content);
        SCANNER.nextLine();
        String feedback = SCANNER.nextLine();
        return feedback;
    }

    public void displayBooking(String content)
    {
        Utils.printIOToConsole(System.lineSeparator()+content);
    }

    public void displayContent(String content)
    {
        Utils.printIOToConsole(System.lineSeparator()+content);
    }

}
