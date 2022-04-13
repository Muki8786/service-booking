package sdk.interfaces;

public interface LoginScreen {
    String getUserName();

    String getPassword();

    void display0ToExit();

    void printErrorContent(String content);
}
