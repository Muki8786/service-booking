package sdk.interfaces.customer;

public interface HomeMenuScreen {
    int getHomeChoice(String content);
    void displayContent(String content);
    void displayWelcomeMessage();
    int getExitConsent(String content);
    void displayThankYou();
    void printErrContent(String content);
}
