package sdk.operations;

import sdk.garageOperations.ManageGarageNotifications;
import sdk.interfaces.NotificationsScreen;
import sdk.interfaces.SignUpScreen;
import sdk.interfaces.customer.*;
import sdk.interfaces.garage.ManageRequestsScreen;
import sdk.modules.Book;
import sdk.modules.Notification;
import sdk.modules.Request;
import sdk.modules.Service;
import sdk.customer.Customer;
import sdk.garage.Garage;
import sdk.modules.review.Feedback;

import java.util.List;

public class Home {

    private final Customer customer ;
    private final HomeMenuScreen homeMenuScreen;
    private final BookingWindow bookingWindow;
    private final List<Service> serviceList;
    private final List<Garage> garageList;
    private final ServicesScreen servicesScreen;
    private final MyBookingsScreen myBookingsScreen;
    private final ManageVehicle manageVehicle;
    private final NotificationsScreen notificationsScreen;
    private final PostServicesScreen postServicesScreen;
    private final ManageRequestsScreen manageRequestsScreen;
    private final DashboardScreen dashboardScreen;
    private final SignUpScreen signUpScreen;
    private final Services services;


    public Home(Customer customer, HomeMenuScreen homeMenuScreen, BookingWindow bookingWindow, List<Service> serviceList,
                List<Garage> garageList, ServicesScreen servicesScreen, MyBookingsScreen myBookingsScreen, ManageVehicle manageVehicle, NotificationsScreen notificationsScreen, PostServicesScreen postServicesScreen, ManageRequestsScreen manageRequestsScreen, DashboardScreen dashboardScreen, SignUpScreen signUpScreen) {
        this.customer = customer;
        this.homeMenuScreen = homeMenuScreen;
        this.bookingWindow = bookingWindow;
        this.serviceList = serviceList;
        this.garageList = garageList;
        this.servicesScreen = servicesScreen;
        this.myBookingsScreen = myBookingsScreen;
        this.manageVehicle = manageVehicle;
        this.notificationsScreen = notificationsScreen;
        this.postServicesScreen = postServicesScreen;
        this.manageRequestsScreen = manageRequestsScreen;
        this.dashboardScreen = dashboardScreen;
        this.signUpScreen = signUpScreen;
        services = new Services(servicesScreen);
    }

    private void rateCompletedService()
    {
        if(customer.getCompletedBookingList().size()>0)
        {
            int choice = postServicesScreen.getRateUsChoice(System.lineSeparator()+"Press 1 to Rate the service " +System.lineSeparator()+"Press any other key to skip!");
            if(choice == 1)
            {
                Book book ;
                if(customer.getCompletedBookingList().size() == 1)
                {
                    book = customer.getCompletedBookingList().get(0);
                }
                else
                {
                    printMyBookings();
                    if(customer.getBookingsList().size()==0)
                    {
                        return;
                    }
                    int bookingChoice =0;
                    while (true)
                    {
                        bookingChoice = myBookingsScreen.getBookingChoice();
                        if(bookingChoice == 0)
                        {
                            return;
                        }
                        else if(getBooking(bookingChoice) != null)
                        {
                            break;
                        }
                        else
                        {
                            myBookingsScreen.printInvalidInput();
                        }
                    }

                    book = getBooking(bookingChoice);
                }

                //postServicesScreen.displayBooking(book.toString());


                if(book.isRedo() && book.isRedoCompleted())
                {
                    postServicesScreen.displayBooking(book.toString());
                    int ratingStars = postServicesScreen.getTheStars(System.lineSeparator()+"Choose the stars."+System.lineSeparator()+
                            "1 Star\t2 Stars\t3 Stars\t4 Stars\t5 Stars\nPress any other key to exit rating");
                    if(ratingStars>=1 && ratingStars<=5) {
                        int previousRating = book.getRating();
                        book.setRating(ratingStars);
                        book.getGarage().getRating().changeOldRating(previousRating,ratingStars);
                        createNotification(book.getGarage(),"New Rating","Your garage has received a "+ratingStars+" Rating");
                    }
                    if(postServicesScreen.getFeedbackChoice(System.lineSeparator()+"Press 1 to give review for the service " +System.lineSeparator()+"Press any other key to skip!") == 1)
                    {
                        String feedback = postServicesScreen.getDescription(System.lineSeparator()+"Enter the Feedback : \n");
                        Feedback feedback1 = new Feedback(book.getCustomer().getName(),feedback);
                        book.getGarage().addFeedbackInGarage(feedback1);
                        createNotification(book.getGarage(), "New review/Feedback","Your garage has received a new feedback");
                    }
                    //get the rating and feedback and update the rating
                    book.getCustomer().removeBookingFromCompletedList(book);
                    book.getCustomer().addBookingToPastServices(book);
                    book.setRatedAfterRedo(true);
                    return;
                }
                else if(book.isRedo())
                {
                    return;
                }

                postServicesScreen.displayBooking(book.toString());
                int ratingStars = postServicesScreen.getTheStars(System.lineSeparator()+"Choose the stars."+System.lineSeparator()+
                        "1 Star\t2 Stars\t3 Stars\t4 Stars\t5 Stars\nPress any other key to exit rating");
                if(ratingStars>=1 && ratingStars<=5) {
                    book.getGarage().addRating(ratingStars);
                    book.setAlreadyRated(true);
                    createNotification(book.getGarage(),"New Rating","Your garage has received a "+ratingStars+" Rating");
                }
                if(postServicesScreen.getFeedbackChoice(System.lineSeparator()+"Press 1 to give review for the service " +System.lineSeparator()+"Press any other key to skip!") == 1)
                {
                    String feedback = postServicesScreen.getDescription(System.lineSeparator()+"Enter the Feedback : ");
                    Feedback feedback1 = new Feedback(book.getCustomer().getName(),feedback);
                    book.getGarage().addFeedbackInGarage(feedback1);
                    createNotification(book.getGarage(), "New review/Feedback","Your garage has received a new feedback");
                }

                if(ratingStars<=2)
                {
                    book.setRating(ratingStars);
                    postServicesScreen.displayContent("We are extremely sorry for the bad experience");
                    book.setRedo(true);
                    Request garageRequest = new Request("Redo Booking request. Offer customer a Re-Service?",customer, book.getId(), book.getGarage(),book,false);
                    garageRequest.setRedo(true);
                    ManageRequests manageRequests = new ManageRequests();
                    manageRequests.addRequestInGarage(garageRequest);
                    book.setAlreadyRated(true);
                    book.setRedoStarted(true);
                }
                book.getCustomer().removeBookingFromCompletedList(book);
                book.getCustomer().addBookingToPastServices(book);
            }

        }
    }

    public void homeMenu()
    {
        homeMenuScreen.displayWelcomeMessage();

        rateCompletedService();

        String content = "\nPress 0 to exit"
        + "\nPress 1 to view services"
        +"\nPress 2 to Book a service"
        +"\nPress 3 to view My Bookings"
        +"\nPress 4 to view Notifications_->"+customer.getNotificationList().size()
        +"\nPress 5 to Accept/Reject Requests ->"+ customer.getRequestList().size()
        +"\nPress 6 to open Dashboard"
        +"\nEnter your Choice : ";
        int choice = 0;
        while (true) {
            choice = homeMenuScreen.getHomeChoice(content);
            if(choice>=0 && choice <= 6)
                break;
            else {
                homeMenuScreen.printErrContent(System.lineSeparator()+"Invalid choice");
            }
        }
        switch (choice)
        {
            case 1 :
                services.displayServices();

                break;
            case 2 : new Booking(customer,bookingWindow,serviceList, garageList, manageVehicle, services).bookService();
                break;
            case 3 : new MyBookings(customer,myBookingsScreen, bookingWindow, serviceList, garageList, manageVehicle,services).manageMyBookings();
                break;
            case 4 : new MyNotifications(customer,notificationsScreen).manageNotifications();
                break;
            case 5 : new CustomerRequests(customer,manageRequestsScreen).manageRequests();
                break;
            case 6 : new Dashboard(customer,dashboardScreen, signUpScreen, bookingWindow, manageVehicle).manageDashboard();
            break;
            default:
            {
                String content1 = "\nAre you sure you want to exit?"+
                "\nPress 1 to continue using the app"+
                "\nPress any other key to exit"+
                "\nEnter Your choice : ";
                int consentChoice = homeMenuScreen.getExitConsent(content1);
                if(consentChoice != 1)
                {
                    homeMenuScreen.displayThankYou();
                    return;
                }
            }
        }
        homeMenu();
    }

    void printMyBookings()
    {
        if(customer.getBookingsList().size() == 0)
        {
            myBookingsScreen.printMyBookings("OOPS , Nothing to see here");
        }
        else
        {
            for(Book booking : customer.getBookingsList())
            {
                myBookingsScreen.printMyBookings(booking.toString());
            }
        }
    }

    private Book getBooking(int bookingId)
    {
        for(Book book : customer.getBookingsList())
        {
            if(book.getId() == bookingId)
            {
                return book;
            }
        }
        return null;
    }

    private void createNotification(Garage garage,String header ,String content)
    {
        Notification notification = new Notification(header,content,"Important");
        ManageGarageNotifications manageGarageNotifications = new ManageGarageNotifications(garage);
        manageGarageNotifications.addNotification(notification);
    }
}
