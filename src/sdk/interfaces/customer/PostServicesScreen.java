package sdk.interfaces.customer;

public interface PostServicesScreen {
    int getRateUsChoice(String content);

    int getTheStars(String content);
    String getDescription(String content);
    void displayBooking(String content);
    int getFeedbackChoice(String content);
    void displayContent(String content);
}
