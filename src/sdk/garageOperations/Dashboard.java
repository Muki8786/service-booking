package sdk.garageOperations;

import sdk.customer.Customer;
import sdk.garage.Employee;
import sdk.garage.Garage;
import sdk.interfaces.SignUpScreen;
import sdk.interfaces.garage.DashboardScreen;
import sdk.modules.Book;
import sdk.modules.Notification;
import sdk.modules.Request;
import sdk.modules.review.Feedback;
import sdk.operations.ManageNotifications;

import java.util.ArrayList;
import java.util.List;

public class Dashboard {
    //profile -> Changes in the details
    //Available employees display
    // past services
    //Ratings and feedbacks reviews

    private Garage garage;
    private final DashboardScreen dashboardScreen;
    private final SignUpScreen signUpScreen;

    public Dashboard(Garage garage, DashboardScreen dashboardScreen, SignUpScreen signUpScreen)
    {
        this.garage = garage;
        this.dashboardScreen = dashboardScreen;
        this.signUpScreen = signUpScreen;
    }

    public void ManageDashboard()
    {
        while (true) {
            int choice = dashboardScreen.getDashboardChoice("\nPress 1 to view and manage profile.\nPress 2 to view and manage Employees\nPress 3 to view Ratings and Reviews\nPress 4 to view Completed services.\nPress 5 to view old requests.\nPress any other key to go back\nEnter your choice : ");

            switch (choice) {
                case 1:
                    manageProfile();
                    break;
                case 2:
                    manageEmployees();
                    break;
                case 3:
                    manageRatingsAndReviews();
                    break;
                case 4:
                    viewPastServices();
                    break;
                case 5 :
                    viewPastRequests();
                    break;
                default:{
                    return;
                }
            }
        }
    }

    private void manageProfile()
    {
        //String name,mobile,email, String address,String password, String owner, String workHours, String upi(notif the bill generated unpaid )

        int choice = dashboardScreen.getManageGarageProfileChoice("\nPress 1 to change your name.\nPress 2 to change your email.\nPress 3 to change your mobile number.\nPress 4 to change your address.\nPress 5 to change your password.\nPress any other key to go back.\nEnter your choice : ");
        String existingName = garage.getName();
        switch (choice)
        {
            case 1 :
                signUpScreen.display0ToExit();

                String name = signUpScreen.getName();
                if (name.equals("0")) {
                    break;
                }
                if (checkSimilarString(existingName, name)) {
                    dashboardScreen.displayContent("\nThe existing name is same as the new one.");
                    break;
                } else {
                    garage.setName(name);
                    dashboardScreen.printContent("\nName changed");
                    List<Customer> customersToReceiveNotification = new ArrayList<>();
                    for(Book book : garage.getCurrentBookings())
                    {
                        customersToReceiveNotification.add(book.getCustomer());
                    }
                    Notification notification = new Notification("Change in garage details","The name of the garage - "+existingName+" was changed to "+name,"Important");
                    for(Customer customer : customersToReceiveNotification) {
                        ManageNotifications manageNotifications = new ManageNotifications(customer);
                        manageNotifications.addNotification(notification);
                    }
                }
                break;
            case 2 :
                signUpScreen.display0ToExit();
                String existingEmail = garage.getEmail();
                String email = signUpScreen.getEmail();
                if(email.equals("0"))
                {
                    break;
                }
                if(checkSimilarString(email,existingEmail))
                {
                    dashboardScreen.displayContent("\nThe existing email id is same as the new one.");
                    break;
                }else {
                    garage.setEmail(email);
                    dashboardScreen.printContent("\nEmail changed");
                    List<Customer> customersToReceiveNotification = new ArrayList<>();
                    for(Book book : garage.getCurrentBookings())
                    {
                        customersToReceiveNotification.add(book.getCustomer());
                    }
                    Notification notification = new Notification("Change in garage details","The email of the garage - "+existingName+" was changed to "+email,"Important");
                    for(Customer customer : customersToReceiveNotification) {
                        ManageNotifications manageNotifications = new ManageNotifications(customer);
                        manageNotifications.addNotification(notification);
                    }
                }
                break;
            case 3:
                signUpScreen.display0ToExit();
                String existingMobile = garage.getMobile();
                String mobile = signUpScreen.getMobile();
                if(mobile.equals("0"))
                {
                    break;
                }
                if(checkSimilarString(mobile,existingMobile))
                {
                    dashboardScreen.displayContent("\nThe existing mobile number is same as the new one.");
                    break;
                }else {
                    garage.setMobile(mobile);
                    dashboardScreen.printContent("\nMobile number changed");
                    List<Customer> customersToReceiveNotification = new ArrayList<>();
                    for(Book book : garage.getCurrentBookings())
                    {
                        customersToReceiveNotification.add(book.getCustomer());
                    }
                    Notification notification = new Notification("Change in garage details","The mobile number of the garage - "+existingName+" was changed to "+mobile,"Important");
                    for(Customer customer : customersToReceiveNotification) {
                        ManageNotifications manageNotifications = new ManageNotifications(customer);
                        manageNotifications.addNotification(notification);
                    }
                }
                break;
            case 4 :
                signUpScreen.display0ToExit();
                String existingAddress = garage.getAddress();
                String address = signUpScreen.getAddress();
                if(address.equals("0"))
                {
                    break;
                }
                if(checkSimilarString(address,existingAddress))
                {
                    dashboardScreen.displayContent("\nThe existing address is same as the new one.");
                    break;
                }else {
                    garage.setAddress(address);
                    dashboardScreen.printContent("\nAddress changed");
                    List<Customer> customersToReceiveNotification = new ArrayList<>();
                    for(Book book : garage.getCurrentBookings())
                    {
                        customersToReceiveNotification.add(book.getCustomer());
                    }
                    Notification notification = new Notification("Change in garage details","The address of the garage - "+existingName+" was changed to "+address,"Important");
                    for(Customer customer : customersToReceiveNotification) {
                        ManageNotifications manageNotifications = new ManageNotifications(customer);
                        manageNotifications.addNotification(notification);
                    }
                }
                break;
            case 5:
                signUpScreen.display0ToExit();
                String existingPassword = garage.getPassword();
                String password = signUpScreen.getPassword();
                if(password.equals("0"))
                {
                    break;
                }
                dashboardScreen.displayContent("\nRe-enter the password.");
                String reEnterPassword = signUpScreen.getPassword();
                if(!password.equals(reEnterPassword))
                {
                    if(reEnterPassword.equals("0"))
                    {
                        break;
                    }
                    dashboardScreen.displayContent("\nThe passwords does not match.");
                    break;
                }
                if(checkSimilarString(password,existingPassword))
                {
                    dashboardScreen.displayContent("\nThe existing email id is same as the new one.");
                    break;
                }else {
                    garage.setPassword(password);
                    dashboardScreen.printContent("\nPassword changed");
                }
                break;
        }
    }

    private void viewPastRequests()
    {
        List<Request> pastRequests = garage.getOldRequestList();

        if(!pastRequests.isEmpty())
        {
            dashboardScreen.displayContent(pastRequests.toString());
        }
        else {
            dashboardScreen.displayContent("\nNo old requests");
        }
    }

    private void manageEmployees()
    {
        while (true) {
            int choice = dashboardScreen.getManageEmployeesChoice("\nPress 1 to view Available employees.\nPress 2 to view Employees at work.\nPress 3 to add employee.\nPress 4 to remove employee.\nPress 5 to view Employees List\nPress any other key to go back\nEnter your choice : ");
            switch (choice) {
                case 1:
                    dashboardScreen.displayContent(garage.getAvailableEmployeeList().toString());
                    break;
                case 2:
                    dashboardScreen.displayContent(garage.getEmployeesAtWorkList().toString());
                    break;
                case 3:
                    List<Employee> employeesList = garage.getEmployeeList();
                    Employee employee = getEmployee();
                    if (employee == null) {
                        break;
                    } else if (!employeesList.contains(employee)) {
                        garage.addEmployee(employee);
                        dashboardScreen.printContent("\nEmployee is added");
                    } else {
                        dashboardScreen.printErrorContent("\nEmployee already present in the list");
                    }
                    break;
                case 4:
                    int index = dashboardScreen.getEmployeeChoice(garage.getAvailableEmployeeList()) - 1;
                    if (index != -1) {
                        Employee employee1 = garage.getAvailableEmployeeList().get(index);
                        if(garage.removeEmployee(employee1))
                        {
                            dashboardScreen.printContent("\nEmployee is removed");
                        }else dashboardScreen.printErrorContent("\nThe employee is currently assigned to a service.You cannot remove the employee at the moment.");

                    }
                    break;
                case 5:
                    dashboardScreen.displayContent(garage.getEmployeeList().toString());
                    break;
                default:{
                    return;
                }
            }
        }
    }

    private void manageRatingsAndReviews()
    {
        while (true) {

            int choice = dashboardScreen.getRatingOrFeedbackChoice("\nPress 1 to view Ratings.\nPress 2 to view Feedbacks/Reviews\nPress any other key to go back\nEnter your choice : ");
            switch (choice) {
                case 1:
                    dashboardScreen.displayContent(garage.getRating().toString());
                    break;
                case 2:
                    List<Feedback> feedbackList = garage.getFeedbackList();
                    if (!feedbackList.isEmpty()) {
                        dashboardScreen.displayContent(feedbackList.toString());
                    } else {
                        dashboardScreen.displayContent("\nOops, no Feedbacks ");
                    }
                    break;
                default:{
                    return;
                }
            }
        }
    }

    private void viewPastServices()
    {
        List<Book> pastBookings = garage.getCompletedBookings();

        if(!pastBookings.isEmpty())
        {
            dashboardScreen.displayContent(pastBookings.toString());
        }
        else {
            dashboardScreen.displayContent("\nNo past services");
        }
    }

    private Employee getEmployee()
    {
        //String name, String email, String mobile, String address
        signUpScreen.display0ToExit();
        String name = signUpScreen.getName();
        if (name.equals("0")) {
            return null;
        }
        String email = signUpScreen.getEmail();
        if(email.equals("0"))
        {
            return null;
        }
        String mobile = signUpScreen.getMobile();
        if(mobile.equals("0"))
        {
            return null;
        }
        String address = signUpScreen.getAddress();
        if(address.equals("0"))
        {
            return null;
        }
        Employee employee = new Employee(name,email,mobile,address);
        return employee;
    }

    private boolean checkSimilarString(String string1,String string2)
    {
        return string1.equals(string2);
    }
}

