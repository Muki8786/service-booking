package sdk.modules;

import sdk.customer.Customer;
import sdk.garage.Garage;

public class Request {
    private final int id;
    private static int count =1;
    private String name;
    private Book redoBook;
    private Customer customer;
    private int bookingId;
    private Garage garage;
    private int isAccepted;
    private boolean isRedo;
    private boolean isChangeGarageRequest;

    public Request(String name, Customer customer,int bookingId, Garage garage,Book redoBook,boolean isChangeGarageRequest) {
        id = count++;
        this.name = name;
        this.customer = customer;
        this.bookingId = bookingId;
        this.garage = garage;
        isAccepted = 0;
        isRedo = false;
        this.redoBook = redoBook;
        this.isChangeGarageRequest = isChangeGarageRequest;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public Garage getGarage() {
        return garage;
    }

    public void setGarage(Garage garage) {
        this.garage = garage;
    }

    public int isAccepted() {
        return isAccepted;
    }

    public void setAccepted(int accepted) {
        isAccepted = accepted;
    }

    public void acceptRequest()
    {
        isAccepted = 1;
    }

    public void rejectRequest()
    {
        isAccepted = -1;
    }

    public boolean isRedo() {
        return isRedo;
    }

    public void setRedo(boolean redo) {
        isRedo = redo;
    }

    public Book getRedoBook() {
        return redoBook;
    }

    public void setRedoBook(Book redoBook) {
        this.redoBook = redoBook;
    }

    public boolean isChangeGarageRequest() {
        return isChangeGarageRequest;
    }

    public void setChangeGarageRequest(boolean changeGarageRequest) {
        isChangeGarageRequest = changeGarageRequest;
    }

    @Override
    public String toString() {
        return "\n----------Request---------- " +
                "\nRequest Id : " + id +
                "\tname : " + name +
                "\nbookingId : " + bookingId +
                "\tcustomer : " + customer +
                "\ngarage " + garage ;
    }
}
