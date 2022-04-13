package main;

import sdk.interfaces.NotificationsScreen;

import java.util.InputMismatchException;

import static main.Utils.SCANNER;

public class NotificationsScreenImpl implements NotificationsScreen {
    @Override
    public void printNotifications(String notification) {
        Utils.printIOToConsole(System.lineSeparator()+notification);
    }

    @Override
    public int getManageNotificationsChoice(String content) {
        int choice = 0;
        while(true) {
            Utils.printIOToConsole(System.lineSeparator() + content);

            try {
                choice = SCANNER.nextInt();
                break;
            } catch (InputMismatchException inputMismatchException)
            {
                Utils.printErrToConsole(System.lineSeparator()+"Invalid input");
                SCANNER.nextLine();
            }
        }
        return choice;
    }
}
