package sdk;

import sdk.customer.Customer;
import sdk.garage.Garage;
import sdk.interfaces.*;
import sdk.interfaces.customer.*;
import sdk.interfaces.garage.CurrentServicesScreen;
import sdk.interfaces.garage.ManageBillsScreen;
import sdk.interfaces.garage.ManageRequestsScreen;
import sdk.modules.Service;
import sdk.operations.Home;
import sdk.operations.ManageVehicle;
import sdk.operations.Services;
import sdk.vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class App {

    private final List<Customer> customers;
    private final List<Service> serviceList;
    private final List<Garage> garageList;
    private static int customerCount = 1;
    private final StartupScreen startupScreen;
    private final LoginScreen loginScreen;
    private final SignUpScreen signUpScreen;
    private final HomeMenuScreen homeMenuScreen;
    private final BookingWindow bookingWindow;
    private final ServicesScreen servicesScreen;
    private final ManageVehicleWindow manageVehicleWindow;
    private final MyBookingsScreen myBookingsScreen;
    private ManageVehicle manageVehicle;
    private final sdk.interfaces.garage.HomeMenuScreen garageHomeMenuScreen;
    private final ManageRequestsScreen manageRequestsScreen;
    private final NotificationsScreen notificationsScreen;
    private final CurrentServicesScreen currentServicesScreen;
    private final ManageBillsScreen manageBillsScreen;
    private final PostServicesScreen postServicesScreen;
    private final DashboardScreen dashboardScreen;
    private final sdk.interfaces.garage.DashboardScreen garageDashboardScreen;

    public App(StartupScreen startupScreen, LoginScreen loginScreen, SignUpScreen signUpScreen, HomeMenuScreen homeMenuScreen,
               BookingWindow bookingWindow, ServicesScreen servicesScreen, ManageVehicleWindow manageVehicleWindow, MyBookingsScreen myBookingsScreen, sdk.interfaces.garage.HomeMenuScreen garageHomeMenuScreen, ManageRequestsScreen manageRequestsScreen, NotificationsScreen notificationsScreen, CurrentServicesScreen currentServicesScreen, ManageBillsScreen manageBillsScreen, PostServicesScreen postServicesScreen, DashboardScreen dashboardScreen, sdk.interfaces.garage.DashboardScreen garageDashboardScreen) {
        this.bookingWindow = bookingWindow;
        this.servicesScreen = servicesScreen;
        this.manageVehicleWindow = manageVehicleWindow;
        this.myBookingsScreen = myBookingsScreen;
        this.garageHomeMenuScreen = garageHomeMenuScreen;
        this.manageRequestsScreen = manageRequestsScreen;
        this.notificationsScreen = notificationsScreen;
        this.currentServicesScreen = currentServicesScreen;
        this.manageBillsScreen = manageBillsScreen;
        this.postServicesScreen = postServicesScreen;
        this.dashboardScreen = dashboardScreen;
        this.garageDashboardScreen = garageDashboardScreen;
        this.customers = new ArrayList<>();
        Services services = new Services(servicesScreen);
        this.serviceList = services.getAllServiceList();
        this.garageList = new ArrayList<>();
        createDummyGarages();//Need to work on it
        this.startupScreen = startupScreen;
        this.loginScreen = loginScreen;
        this.signUpScreen = signUpScreen;
        this.homeMenuScreen = homeMenuScreen;
        manageVehicle = new ManageVehicle(manageVehicleWindow);

    }

    private boolean signUp() // Need to add exit option while signing up
    {
        signUpScreen.display0ToExit();
        String name = signUpScreen.getName();
        if(name.equals("0"))
        {
            return false;
        }
        String email = signUpScreen.getEmail();
        if(email.equals("0"))
        {
            return false;
        }
        String mobile = signUpScreen.getMobile();
        if(mobile.equals("0"))
        {
            return false;
        }
        String address = signUpScreen.getAddress();
        if(address.equals("0"))
        {
            return false;
        }
        String userName;
        while (true) {
            userName = signUpScreen.getUserName();
            if(userName.equals("0"))
            {
                return false;
            }
            if (doesUserNameExists(userName)) {
                signUpScreen.printUserNameAlreadyExists();
            }
            else
            {
                break;
            }
        }
        String password = signUpScreen.getPassword();
        if(password.equals("0"))
        {
            return false;
        }

        int customerId = customerCount++;
        Customer customer = new Customer(name,email,mobile,address,customerId,userName,password,"Active");
        customers.add(customer);
        if(signUpScreen.getAddVehicleConsent() == 1) {
            Vehicle vehicle = manageVehicle.getNewVehicle();
            if(vehicle!=null)
            {
                customer.addVehicle(vehicle);
            }
        }

        return true;
    }

     private Customer signIn()
    {
        loginScreen.display0ToExit();
        String userName = loginScreen.getUserName();
        if(userName.equals("0"))
            return null;
        String password = null;
        while (true)
        {
            password = loginScreen.getPassword();
            if(password.equals("0"))
            {
                return null;
            }
            if(isValidPassword(password))
            {
                break;
            }else {
                loginScreen.printErrorContent("Invalid password");
            }
        }
        return getCustomer(userName,password);
    }

    private Garage garageSignIn()
    {
        loginScreen.display0ToExit();
        String username = loginScreen.getUserName();
        if(username.equals("0"))
            return null;
        String password = null;
        while (true)
        {
            password = loginScreen.getPassword();
            if(password.equals("0"))
            {
                return null;
            }
            if(isValidPassword(password))
            {
                break;
            }else {
                loginScreen.printErrorContent("Invalid password");
            }
        }

        return getGarage(username,password);
    }

    public boolean isValidPassword(String password)
    {
        boolean isValid = true;
        if (password.length() > 20 || password.length() < 8)
        {
            loginScreen.printErrorContent("Password must be less than 20 and more than 8 characters in length.");
            isValid = false;
        }
        String upperCaseChars = "(.*[A-Z].*)";
        if (!password.matches(upperCaseChars ))
        {
            loginScreen.printErrorContent("Password must have at least one uppercase character");
            isValid = false;
        }
        String lowerCaseChars = "(.*[a-z].*)";
        if (!password.matches(lowerCaseChars ))
        {
            loginScreen.printErrorContent("Password must have at least one lowercase character");
            isValid = false;
        }
        String numbers = "(.*[0-9].*)";
        if (!password.matches(numbers ))
        {
            loginScreen.printErrorContent("Password must have at least one number");
            isValid = false;
        }
        String specialChars = "(.*[@,#,$,%].*$)";
        if (!password.matches(specialChars ))
        {
            loginScreen.printErrorContent("Password must have at least one special character among @#$%");
            isValid = false;
        }
        return isValid;
    }

    private Garage getGarage(String username , String password)
    {
        for(Garage garage : garageList)
        {
            if(garage.getUserName().equals(username) && garage.getPassword().equals(password))
            {
                return garage;
            }
        }
        return null ;
    }

     private Customer getCustomer(String userName, String password)
    {
        for(Customer customer : customers)
        {
            if(customer.getUserName().equals(userName) && customer.getPassword().equals(password))
            {
                return customer;
            }
        }
        return null;
    }

    private boolean doesUserNameExists(String userName)
    {
        for(Customer customer : customers)
        {
            if(customer.getUserName().equals(userName))
            {
                return true;
            }
        }
        return false;
    }


    public void startApp()
    {
        int choice=0;
        while (true)
        {
            choice = startupScreen.getLoginChoice(System.lineSeparator() + "Press 1 to Sign up "+System.lineSeparator() + "Press 2 to Login"+System.lineSeparator()+ "Press 3 for Garage login"+System.lineSeparator() +"Enter your Choice : ");
            if(choice>=1 && choice<=3)
                break;
            else {
                startupScreen.printErrContent("\nInvalid choice");
            }
        }

        switch (choice)
        {
            case 1 :
                startupScreen.printSignUpSuccess(signUp());
                     break;
            case 2 : Customer customer = signIn();
            if(customer != null)
            {
                new Home(customer, homeMenuScreen,bookingWindow,serviceList,garageList, servicesScreen , myBookingsScreen,manageVehicle, notificationsScreen,postServicesScreen, manageRequestsScreen, dashboardScreen, signUpScreen).homeMenu();
            }
            else{
                startupScreen.printNoUserFound();
            }
            break;
            case 3 : Garage garage = garageSignIn();
            if(garage != null)
            {
                new sdk.garageOperations.Home(garage,garageHomeMenuScreen, serviceList, manageRequestsScreen, notificationsScreen,currentServicesScreen,manageBillsScreen,garageDashboardScreen, signUpScreen).homeMenu();
            }
            else
            {
                startupScreen.printNoUserFound();
            }
            break;
            default:
                startupScreen.printInvalidChoice();
        }
        startApp();

    }



    private void createDummyGarages()
    {
        garageList.add(new Garage("Mukesh auto care","Coimbatore","mukesh","cSNM8786@",
                "Mukesh","balajimukesh8786@gmail.com","9876543210","9:00 to 20:00","mukesh@xyz"));

        garageList.add(new Garage("ABC auto care","Coimbatore","xyz","aBCD1234@",
                "ABC","balajimukesh8786@gmail.com","8765432109","10:00 to 21:00","xyz@abc"));

    }
}
