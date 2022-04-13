package sdk.interfaces;

public interface SignUpScreen {
    String getName();
    boolean isOnlyLettersSpaces(String s);
    String getEmail();
    String getMobile();
    boolean isMobileNumber(String mobileNumber);
    String getAddress();
    String getUserName();
    void printUserNameAlreadyExists();
    String getPassword();
    boolean isValidPassword(String password);
    int getAddVehicleConsent();
    void display0ToExit();
}
