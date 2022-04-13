package sdk.interfaces.customer;

import sdk.modules.Service;

import java.util.List;

public interface MyBookingsScreen {
    void printMyBookings(String content);
    int getBookingChoice();
    void printInvalidInput();
    int editBookingChoice();
    void printCancelSuccess(boolean canCancel);
    int getEditOptionChoice();
    int getSingleEditOptionChoice();
    int getPayNowChoice();
    int getServiceChoice(List<Service> serviceList );
    int getEditServiceChoice(String content);
    void printErrorContent(String content);
    void printContent(String content);
}
