package sdk.garageOperations;

import sdk.garage.Garage;
import sdk.interfaces.SignUpScreen;
import sdk.interfaces.garage.*;
import sdk.modules.Service;
import sdk.interfaces.NotificationsScreen;

import java.util.List;

public class Home {
    private Garage garage;
    private final HomeMenuScreen homeMenuScreen;
    private final List<Service> serviceList;
    private final ManageRequestsScreen manageRequestsScreen;
    private final NotificationsScreen notificationsScreen;
    private final CurrentServicesScreen currentServicesScreen;
    private final ManageBillsScreen manageBillsScreen;
    private final DashboardScreen dashboardScreen;
    private final SignUpScreen signUpScreen;


    public Home(Garage garage, HomeMenuScreen homeMenuScreen, List<Service> serviceList,
                ManageRequestsScreen manageRequestsScreen, NotificationsScreen notificationsScreen,
                CurrentServicesScreen currentServicesScreen, ManageBillsScreen manageBillsScreen, DashboardScreen dashboardScreen, SignUpScreen signUpScreen) {
        this.garage = garage;
        this.homeMenuScreen = homeMenuScreen;
        this.serviceList = serviceList;
        this.manageRequestsScreen = manageRequestsScreen;
        this.notificationsScreen = notificationsScreen;
        this.currentServicesScreen = currentServicesScreen;
        this.manageBillsScreen = manageBillsScreen;
        this.dashboardScreen = dashboardScreen;
        this.signUpScreen = signUpScreen;
    }

    public void homeMenu()
    {
        homeMenuScreen.displayWelcomeMessage();
        int notificationCount = garage.getNotificationList().size();
        int requestCount = garage.getRequestList().size();
        int choice = homeMenuScreen.getHomeChoice(notificationCount,requestCount);
        switch (choice)
        {
            case 1 : new Requests(garage,manageRequestsScreen).manageRequests();
                break;
            case 2 : new MyNotifications(garage,notificationsScreen).manageNotifications();
                break;
            case 3 : new Dashboard(garage,dashboardScreen, signUpScreen).ManageDashboard();
                break;
            case 4 : new CurrentServices(garage,currentServicesScreen, manageBillsScreen).manageCurrentServices();
                break;
            case 5 : new Bills(manageBillsScreen,garage).manageBills();
                break;
            default:
            {
                int consentChoice = homeMenuScreen.getExitConsent();
                if(consentChoice != 1)
                {
                    homeMenuScreen.displayThankYou();
                    return;
                }
            }
        }
        homeMenu();
    }


}
