package main;

import sdk.interfaces.LoginScreen;

import static main.Utils.SCANNER;

public class LoginScreenImpl implements LoginScreen {

    public void display0ToExit()
    {
        Utils.printIOToConsole(System.lineSeparator()+"Enter 0[zero] to exit the process");
    }

    @Override
    public String getUserName() {
        String username;
        Utils.printIOToConsole(System.lineSeparator()+"Enter your username : ");
        username = SCANNER.next();
        username += SCANNER.nextLine();
        return username;
    }

    @Override
    public String getPassword() {
        String password;

        Utils.printIOToConsole(System.lineSeparator()+"Enter your Password :");
        password = SCANNER.next();
        password += SCANNER.nextLine();

        return password;
    }


    public void printErrorContent(String content)
    {
        Utils.printErrToConsole("\n"+content);
    }
}
