package main.garage;

import main.Utils;
import sdk.interfaces.garage.CurrentServicesScreen;
import sdk.modules.Book;
import sdk.modules.BookingStatus;
import sdk.modules.Service;

import java.util.InputMismatchException;
import java.util.List;

import static main.Utils.SCANNER;

public class CurrentServicesScreenImpl implements CurrentServicesScreen {
    @Override
    public void displayCurrentServices(List<Book> currentBookingsList) {
        Utils.printIOToConsole(System.lineSeparator()+"Bookings");
        if(currentBookingsList.size() ==0)
        {
            Utils.printErrToConsole(System.lineSeparator()+"Sorry,No current Bookings");
        }
        for(Book book : currentBookingsList)
        {
            Utils.printIOToConsole(System.lineSeparator()+ book);
        }

    }

    @Override
    public int getBookNowChoice() { // Later, for booking through the services option

        int choice=0 ;

        Utils.printIOToConsole(System.lineSeparator()+"Enter the Booking ID to choose that booking ");
        Utils.printIOToConsole((System.lineSeparator()+"Enter any other key to go back to home"));
        Utils.printIOToConsole(System.lineSeparator()+"Enter your choice : ");

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

    @Override
    public int getChangeBookingStatusChoice(String content) {
        int choice = 0;
        Utils.printIOToConsole(content);
        try {
            choice = SCANNER.nextInt();
        }catch (InputMismatchException inputMismatchException)
        {
            choice = 0;
            SCANNER.nextLine();
        }
        return choice;
    }

    public void displaySameStatus()
    {
        Utils.printErrToConsole(System.lineSeparator()+"The booking status is same");
    }

    public void displayStatusChanged()
    {
        Utils.printIOToConsole(System.lineSeparator()+"Status was changed");
    }


    @Override
    public int getBookingStatusChoice(List<BookingStatus> bookingStatusList ) {
        int choice = 0;
        while (true) {
            displayBookingStatusChoices(bookingStatusList);
            Utils.printIOToConsole(System.lineSeparator() +"Enter your Choice : ");
            try {
                choice = SCANNER.nextInt();
                if(choice>=0 && choice <= bookingStatusList.size())
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

    public void displayBookingStatusChoices(List<BookingStatus> bookingStatusList)
    {
        Utils.printIOToConsole(System.lineSeparator()+"Press 0 to exit booking");
        int count = 1;
        for( BookingStatus bookingStatus : bookingStatusList)
        {
            Utils.printIOToConsole(System.lineSeparator()+"Press "+ count + " for "+ bookingStatus);
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
