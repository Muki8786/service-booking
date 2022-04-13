package main;

import main.customer.*;
import main.garage.CurrentServicesScreenImpl;
import main.garage.ManageBillsScreenImpl;
import main.garage.ManageRequestsScreenImpl;
import sdk.App;
import sdk.interfaces.*;
import sdk.interfaces.customer.*;
import sdk.interfaces.garage.CurrentServicesScreen;
import sdk.interfaces.garage.ManageBillsScreen;
import sdk.interfaces.garage.ManageRequestsScreen;

public class MainFactory {

    public static App createApp()
    {
        return new App(createStartUpScreen(),createLoginScreen(),createSignUpScreen(),createHomeMenuScreen()
                ,createBookingWindow(),createServicesScreen(),createManageVehicleWindow(),createMyBookingsScreen(),createGarageHomeMenuScreen(), createManageRequestsScreen()
        ,createNotificationsScreen(),createCurrentServicesScreen(),createManageBillsScreen(),createPostServiceScreen(),createDashBoardScreen(),createGarageDashboardScreen());
    }

    public static SignUpScreen createSignUpScreen()
    {
        return new SignUpScreenImpl();
    }

    public static StartupScreen createStartUpScreen()
    {
        return new StartupScreenImpl();
    }

    public static LoginScreen createLoginScreen()
    {
        return new LoginScreenImpl();
    }

    public static HomeMenuScreen createHomeMenuScreen()
    {
        return new HomeMenuScreenImpl();
    }

    public static BookingWindow createBookingWindow(){return new BookingWindowImpl();}

    public static ServicesScreen createServicesScreen()
    {
        return new ServicesScreenImpl();
    }

    public static ManageVehicleWindow createManageVehicleWindow()
    {
        return new ManageVehicleWindowImpl();
    }

    public static MyBookingsScreen createMyBookingsScreen()
    {
        return new MyBookingsScreenImpl();
    }

    public static sdk.interfaces.garage.HomeMenuScreen createGarageHomeMenuScreen()
    {
        return new main.garage.HomeMenuScreenImpl();
    }

    public static ManageRequestsScreen createManageRequestsScreen()
    {
        return new ManageRequestsScreenImpl();
    }

    public static NotificationsScreen createNotificationsScreen()
    {
        return new NotificationsScreenImpl();
    }

    public static CurrentServicesScreen createCurrentServicesScreen()
    {
        return new CurrentServicesScreenImpl();
    }

    public static ManageBillsScreen createManageBillsScreen()
    {
        return new ManageBillsScreenImpl();
    }

    public static PostServicesScreen createPostServiceScreen()
    {
        return new PostServiceScreenImpl();
    }

    public static DashboardScreen createDashBoardScreen()
    {
        return new DashboardScreenImpl();
    }

    public static sdk.interfaces.garage.DashboardScreen createGarageDashboardScreen()
    {
        return new main.garage.DashboardScreenImpl();
    }
}
