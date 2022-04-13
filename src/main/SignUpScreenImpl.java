package main;


import sdk.interfaces.SignUpScreen;
import sdk.vehicles.Vehicle;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.InputMismatchException;

import static main.Utils.SCANNER;


public class SignUpScreenImpl implements SignUpScreen{

    public void display0ToExit()
    {
        Utils.printIOToConsole(System.lineSeparator()+"Enter 0[zero] to exit the process");
    }

    @Override
    public String getName()
    {
        String name;
        while (true)
        {
            Utils.printIOToConsole(System.lineSeparator()+"Enter your Name ");
            name = SCANNER.next();
            name += SCANNER.nextLine();
            name = name.trim();
            if(name.equals("0"))
                return name;
            if(isOnlyLettersSpaces(name))
            {
                break;
            }
            else
            {
                Utils.printErrToConsole("Name should be letters");
            }
        }
        return name;
    }

    @Override
    public boolean isOnlyLettersSpaces(String s){
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if (Character.isLetter(ch) || ch == ' ') {
                continue;
            }
            return false;
        }
        return true;
    }

    @Override
    public String getEmail() {
        String emailAddress;
        while(true)
        {
            Utils.printIOToConsole(System.lineSeparator()+"Enter your email address");
            //Utils.printIOToConsole(System.lineSeparator()+"Enter 0 to exit the process");
            emailAddress = SCANNER.next();
            emailAddress += SCANNER.nextLine();
            if(emailAddress.equals("0"))
            {
                return emailAddress;
            }

            if(isValidEmailAddress(emailAddress))
            {
                break;
            }
            else
            {
                Utils.printErrToConsole("Invalid email address");
            }
        }
        return emailAddress;
    }


    public boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddress = new InternetAddress(email);
            emailAddress.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }




    @Override
    public String getMobile() {
        String mobileNumber;
        while (true)
        {
            Utils.printIOToConsole(System.lineSeparator()+"Enter your Mobile number ");
            //Utils.printIOToConsole(System.lineSeparator()+"Enter 0 to exit the process");
            mobileNumber = SCANNER.next();
            mobileNumber += SCANNER.nextLine();
            mobileNumber = mobileNumber.trim();
            if(mobileNumber.equals("0"))
            {
                return mobileNumber;
            }
            if(isMobileNumber(mobileNumber))
            {
                break;
            }
            else
            {
                Utils.printErrToConsole("Invalid input");
            }
        }
        return mobileNumber;
    }

    @Override
    public boolean isMobileNumber(String mobileNumber)
    {
        if(mobileNumber.length()!=10)
        {
            return false;
        }
        for(int i=0;i<mobileNumber.length();i++)
        {
            char ch = mobileNumber.charAt(i);
            if (Character.isDigit(ch)) {
                continue;
            }
            return false;
        }
        return true;
    }

    @Override
    public String getAddress() {
        String address;

            Utils.printIOToConsole(System.lineSeparator()+"Enter your address - Separated by commas. : ");
        //Utils.printIOToConsole(System.lineSeparator()+"Enter 0 to exit the process");
            address = SCANNER.next();
            address += SCANNER.nextLine();

        return address;
    }

    @Override
    public String getUserName() { // Need to validate with existing records
        String username;
        Utils.printIOToConsole(System.lineSeparator()+"Enter your username : ");
        //Utils.printIOToConsole(System.lineSeparator()+"Enter 0 to exit the process");
        username = SCANNER.next();
        username += SCANNER.nextLine();

        return username;
    }

    public void printUserNameAlreadyExists()
    {
        Utils.printErrToConsole(System.lineSeparator() + "User name is already taken ");
    }

    @Override
    public String getPassword() {
        String password;
        while(true)
        {
            Utils.printIOToConsole(System.lineSeparator()+"Enter your Password :");
            //Utils.printIOToConsole(System.lineSeparator()+"Enter 0 to exit the process");
            password = SCANNER.next();
            password += SCANNER.nextLine();
            if(password.equals("0"))
            {
                return password;
            }
            if(isValidPassword(password) )
            {
                break;
            }
            else
                {
                    Utils.printErrToConsole("Entered passwords doesn't match");
                }
            }
        return password;
    }

    @Override
    public boolean isValidPassword(String password)
    {
        boolean isValid = true;
        if (password.length() > 20 || password.length() < 8)
        {
            Utils.printErrToConsole("Password must be less than 20 and more than 8 characters in length.");
            isValid = false;
        }
        String upperCaseChars = "(.*[A-Z].*)";
        if (!password.matches(upperCaseChars ))
        {
            Utils.printErrToConsole("Password must have at least one uppercase character");
            isValid = false;
        }
        String lowerCaseChars = "(.*[a-z].*)";
        if (!password.matches(lowerCaseChars ))
        {
            Utils.printErrToConsole("Password must have at least one lowercase character");
            isValid = false;
        }
        String numbers = "(.*[0-9].*)";
        if (!password.matches(numbers ))
        {
            Utils.printErrToConsole("Password must have at least one number");
            isValid = false;
        }
        String specialChars = "(.*[@,#,$,%].*$)";
        if (!password.matches(specialChars ))
        {
            Utils.printErrToConsole("Password must have at least one special character among @#$%");
            isValid = false;
        }
        return isValid;
    }

    @Override
    public int getAddVehicleConsent() {
        int choice = 0;
        Utils.printIOToConsole(System.lineSeparator()+"Press 1 to add vehicle now ");
        Utils.printIOToConsole(System.lineSeparator()+"Press any other key to skip ");
        Utils.printIOToConsole(System.lineSeparator()+"Enter your choice : ");

        try
        {
            choice = SCANNER.nextInt();
        }
        catch (InputMismatchException inputMismatchException)
        {
            choice = 0; //Can be used to validate later
            SCANNER.nextLine();
        }
        return choice;
    }




}
