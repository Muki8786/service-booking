package main.customer;

import main.Utils;
import sdk.interfaces.customer.MyBookingsScreen;
import sdk.modules.Service;

import java.util.InputMismatchException;
import java.util.List;

import static main.Utils.SCANNER;

public class MyBookingsScreenImpl implements MyBookingsScreen {
    @Override
    public void printMyBookings(String content) {
        Utils.printIOToConsole(System.lineSeparator()+content);
    }

    @Override
    public int getBookingChoice() {
        int choice = 0;
        while (true)
        {
            displayChooseBookingToManage();
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


    void displayChooseBookingToManage()
    {
        Utils.printIOToConsole(System.lineSeparator()+"Enter the booking ID to manage");
        Utils.printIOToConsole(System.lineSeparator()+"Press 0 to go back");
        Utils.printIOToConsole(System.lineSeparator()+"Enter your choice : ");
    }

    public void printInvalidInput()
    {
        Utils.printErrToConsole(System.lineSeparator()+"Invalid input.Try again!!!");
    }

    @Override
    public int editBookingChoice() {
        int choice = 0;
        while (true)
        {
            Utils.printIOToConsole(System.lineSeparator()+"Press 1 to cancel the booking");
            Utils.printIOToConsole(System.lineSeparator()+"Press 2 to edit the booking");
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

    @Override
    public void printCancelSuccess(boolean canCancel) {
        if(canCancel) {
            Utils.printIOToConsole(System.lineSeparator() + "Your booking has been cancelled");
        }else
        {
            Utils.printErrToConsole(System.lineSeparator()+"Your booking is processed . It cannot be cancelled");
        }
    }

    @Override
    public int getEditOptionChoice() {
        int choice = 0;
        while (true)
        {
            Utils.printIOToConsole(System.lineSeparator()+"Press 1 to Re-fill the booking form");
            Utils.printIOToConsole(System.lineSeparator()+"Press 2 to edit a single field");
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

    @Override
    public int getSingleEditOptionChoice() {
        int choice = 0;
        while (true)
        {
            Utils.printIOToConsole(System.lineSeparator()+"Press 1 to edit the vehicle");
            Utils.printIOToConsole(System.lineSeparator()+"Press 2 to edit the service");
            Utils.printIOToConsole(System.lineSeparator()+"Press 3 to edit the garage");
            Utils.printIOToConsole(System.lineSeparator()+"Press 4 to edit the date");
            Utils.printIOToConsole(System.lineSeparator()+"Press 5 to edit the pick up option");
            Utils.printIOToConsole(System.lineSeparator()+"Press 6 to edit the drop option");
            Utils.printIOToConsole(System.lineSeparator()+"Press 7 to edit the pickUp address");
            Utils.printIOToConsole(System.lineSeparator()+"Press 8 to edit the drop address");
            Utils.printIOToConsole(System.lineSeparator()+"Press any other key to go back");
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

    public int getPayNowChoice()
    {
        int choice = 0;
        Utils.printIOToConsole(System.lineSeparator()+"Press 1 to make payment");
        Utils.printIOToConsole(System.lineSeparator()+"Press any other key to pay later");
        Utils.printIOToConsole(System.lineSeparator()+"You have to make payment to continue the servicing further");
        Utils.printIOToConsole(System.lineSeparator()+"Enter your choice : ");
        try {
            choice = SCANNER.nextInt();
        } catch (InputMismatchException inputMismatchException)
        {
            choice = 0;
            SCANNER.nextLine();
        }
        return choice;
    }

    @Override
    public int getServiceChoice(List<Service> serviceList ) {
        int choice = 0;
        while (true) {
            displayServiceChoices(serviceList);
            Utils.printIOToConsole(System.lineSeparator() +"Enter your Choice : ");
            try {
                choice = SCANNER.nextInt();
                if(choice>=0 && choice <= serviceList.size())
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

    @Override
    public int getEditServiceChoice(String content) {
        Utils.printIOToConsole("\n"+content);
        int choice = 0;
        try
        {
            choice = SCANNER.nextInt();
        }
        catch (InputMismatchException inputMismatchException)
        {
            SCANNER.nextLine();
        }
        return choice;
    }

    void displayServiceChoices(List<Service> services)
    {
        Utils.printIOToConsole(System.lineSeparator()+"Press 0 to exit booking");
        int count = 1;
        for(Service service : services)
        {
            Utils.printIOToConsole(System.lineSeparator()+"Press "+ count + " for "+ service);
            count++;
        }
    }

    public void printErrorContent(String content)
    {
        Utils.printErrToConsole(content);
    }

    public void printContent(String content)
    {
        Utils.printIOToConsole(content);
    }
}
