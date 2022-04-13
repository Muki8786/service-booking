package sdk.interfaces.garage;

public interface HomeMenuScreen {
    int getHomeChoice(int notificationCount,int requestCount);
    void displayHomeChoices(int notificationCount,int requestCount);
    void displayWelcomeMessage();
    int getExitConsent();
    void displayThankYou();
}
