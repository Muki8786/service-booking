package sdk.interfaces.garage;

import sdk.modules.Book;
import sdk.modules.BookingStatus;


import java.util.List;

public interface CurrentServicesScreen {
    void displayCurrentServices(List<Book> currentBookingsList);
    int getBookNowChoice();
    int getChangeBookingStatusChoice(String content);
    void displaySameStatus();
    void displayStatusChanged();
    int getBookingStatusChoice(List<BookingStatus> bookingStatusList );
    void displayBookingStatusChoices(List<BookingStatus> bookingStatusList);
    void printErrorContent(String content);
    void printContent(String content);
}
