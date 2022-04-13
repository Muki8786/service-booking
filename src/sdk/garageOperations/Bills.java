package sdk.garageOperations;


import sdk.garage.Garage;
import sdk.interfaces.garage.ManageBillsScreen;
import sdk.modules.Bill;
import sdk.modules.Book;

public class Bills {
    private final Garage garage;
    private final ManageBillsScreen manageBillsScreen;

    public Bills(ManageBillsScreen manageBillsScreen,Garage garage) {
        this.manageBillsScreen = manageBillsScreen;
        this.garage = garage;
    }


    void manageBills()
    {
        int choice =0;
        while (true) {
            choice = getDisplayChoice();
            switch (choice) {
                case 0:
                    return;
                case 1:
                    displayCurrentBills();
                    break;
                case 2:
                    displayPaidBills();
                    break;
                default: {
                }
            }
        }
    }

    Bill generateBill(Book book)
    {
        Bill bill = new Bill(book);
        book.setBill(bill);
        addBillsInGarage(bill);
        return bill;

    }

    private void addBillsInGarage(Bill bill)
    {
        garage.addBill(bill);
    }

    private void displayCurrentBills()
    {
        if(garage.getCurrentBillsList().isEmpty())
        {
            manageBillsScreen.printErrorContent(System.lineSeparator()+"Oops!, No current bills at the moment");
        }
       else {
            manageBillsScreen.printContent(System.lineSeparator() + garage.getCurrentBillsList().toString() + System.lineSeparator());
        }
    }

    private void displayPaidBills()
    {
        if(garage.getPaidBillsList().isEmpty())
        {
            manageBillsScreen.printErrorContent(System.lineSeparator()+"Oops!, No Paid bills at the moment");
        }
        else {
            manageBillsScreen.printContent(System.lineSeparator() + garage.getPaidBillsList().toString() + System.lineSeparator());
        }
    }

    private int getDisplayChoice()
    {
        return manageBillsScreen.getDisplayingChoice(System.lineSeparator() + "Press 1 for displaying Current Bills." + System.lineSeparator() + "Press 2 for displaying Paid Bills" +System.lineSeparator()+"Press any other key to go back"+ System.lineSeparator() + "Enter your Choice : ");

    }
}
