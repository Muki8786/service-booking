package sdk.customer;

import sdk.modules.Book;
import sdk.modules.Notification;
import sdk.modules.Request;
import sdk.personalData.Person;
import sdk.vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class Customer extends Person {

    private final int customerId;
    private  String userName;
    private  String password;
    private  String status;
    private List<Vehicle> vehicleList;
    private List<Book> bookingsList;
    private List<Book> completedBookingList;
    private List<Book> pastBookingsList;
    private List<Book> reServiceBookingList;
    private List<Notification> notificationList;
    private List<Request> requestList;
    private List<Request> oldRequestList;

    public Customer(String name, String email, String mobile, String address, int customerId, String userName,
                    String password, String status) {
        super(name, email, mobile, address);
        this.customerId = customerId;
        this.userName = userName;
        this.password = password;
        this.status = status;
        this.notificationList = new ArrayList<>();
        vehicleList = new ArrayList<>();
        bookingsList = new ArrayList<>();
        completedBookingList = new ArrayList<>();
        pastBookingsList = new ArrayList<>();
        reServiceBookingList = new ArrayList<>();
        requestList = new ArrayList<>();
        oldRequestList = new ArrayList<>();
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getStatus() {
        return status;
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void addVehicle(Vehicle vehicle)
    {
        if(!vehicleList.contains(vehicle))
        vehicleList.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle)
    {
        vehicleList.remove(vehicle);
    }

    public List<Book> getBookingsList() {
        return bookingsList;
    }

    public Book getBooking(int bookingId)
    {
        for(Book book : bookingsList)
        {
            if(book.getId() == bookingId)
            {
                return book;
            }
        }
        return null;
    }

    public Book getBookingFromCompletedList(int bookingId)
    {
        for(Book book : completedBookingList)
        {
            if(book.getId() == bookingId)
            {
                return book;
            }
        }
        return null;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Notification> getNotificationList() {
        return notificationList;
    }

    public void clearNotification()
    {
        notificationList = new ArrayList<>();
    }

    public void addNotification(Notification notification)
    {
        notificationList.add(notification);
    }

    public void addBookingToBookingList(Book book)
    {
        bookingsList.add(book);
    }

    public void removeBookingFromBookingList(Book book)
    {
        bookingsList.remove(book);
    }

    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    public void setBookingsList(List<Book> bookingsList) {
        this.bookingsList = bookingsList;
    }

    public List<Book> getCompletedBookingList() {
        return completedBookingList;
    }

    public void setCompletedBookingList(List<Book> completedBookingList) {
        this.completedBookingList = completedBookingList;
    }

    public List<Book> getPastBookingsList() {
        return pastBookingsList;
    }

    public void setPastBookingsList(List<Book> pastBookingsList) {
        this.pastBookingsList = pastBookingsList;
    }

    public void addBookingToPastServices(Book book)
    {
        pastBookingsList.add(book);
    }

    public void removeBookingFromPastServices(Book book)
    {
        pastBookingsList.remove(book);
    }

    public void addBookingToCompletedList(Book book)
    {
        completedBookingList.add(book);
    }

    public void removeBookingFromCompletedList(Book book)
    {
        completedBookingList.remove(book);
    }

    public List<Request> getRequestList() {
        return requestList;
    }

    public void setRequestList(List<Request> requestList) {
        this.requestList = requestList;
    }

    public void addRequestInList(Request request)
    {
        requestList.add(request);
    }

    public void removeRequestFromList(Request request)
    {
        requestList.remove(request);
    }

    public List<Request> getOldRequestList() {
        return oldRequestList;
    }

    public void setOldRequestList(List<Request> oldRequestList) {
        this.oldRequestList = oldRequestList;
    }
    public void addRequestInOldList(Request request)
    {
        oldRequestList.add(request);
    }

    public void removeRequestInOldList(Request request)
    {
        oldRequestList.remove(request);
    }

    @Override
    public String toString() {
        return "Name : "+getName()+"\nMobile : "+getMobile();
    }
}
