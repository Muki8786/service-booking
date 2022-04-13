package main.customer;

import main.Utils;
import sdk.modules.Service;
import sdk.interfaces.customer.ServicesScreen;


import java.util.InputMismatchException;
import java.util.List;

import static main.Utils.SCANNER;

public class ServicesScreenImpl implements ServicesScreen { // Changes needed to be done
    @Override
    public void displayServices(List<Service> serviceList) {
        Utils.printIOToConsole(System.lineSeparator()+"Services");
        if(serviceList.size() ==0)
        {
            Utils.printErrToConsole(System.lineSeparator()+"Sorry,No services");
        }
        for(Service service : serviceList)
        {
            Utils.printIOToConsole(System.lineSeparator()+ service);
        }

    }

    @Override
    public int getBookNowChoice() { // Later, for booking through the services option

        int choice=0 ;

            Utils.printIOToConsole(System.lineSeparator()+"Enter the service ID to proceed with booking that service ");
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
}
