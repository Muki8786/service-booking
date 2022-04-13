package sdk.interfaces;

public interface StartupScreen {
    int getLoginChoice(String content);
    void printNoUserFound();
    void printSignUpSuccess(boolean success);
    void printInvalidChoice();
    void printErrContent(String content);
    void printContent(String content);
}
