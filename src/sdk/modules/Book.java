package sdk.modules;

import sdk.customer.Customer;
import sdk.garage.Employee;
import sdk.garage.Garage;
import sdk.modules.review.Feedback;
import sdk.vehicles.Vehicle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Book {
    private final Customer customer;
    private final int id;
    private static int count = 0;
    private Vehicle vehicle;
    private Service service;
    private List<Service> services;
    private List<String> serviceNames;
    private Garage garage;
    private Employee employee;
    private Date date;
    private boolean pickUp;
    private boolean drop;
    private BookingStatus bookingStatus;
    private Bill bill;
    private boolean isBillPaid;
    private Feedback feedback;
    private boolean isRedo;
    private int rating;
    private boolean isAlreadyRated;
    private boolean isRatedAfterRedo;
    private boolean isRedoStarted;
    private boolean isRedoCompleted;
    private String pickUpAddress;
    private String dropAddress;

    public Book(Customer customer, Vehicle vehicle, List<Service> services, Garage garage, Date date, boolean pickUp, boolean drop,String pickUpAddress,String dropAddress) {
        this.customer = customer;
        this.vehicle = vehicle;
        count++;
        this.id = count;
        this.services = services;
        this.garage = garage;
        employee = null;
        this.date = date;
        this.pickUp = pickUp;
        this.drop = drop;
        bookingStatus = BookingStatus.INITIATED;
        bill = null;
        feedback = null;
        isBillPaid = false;
        isRedo = false;
        isAlreadyRated = false;
        isRatedAfterRedo = false;
        isRedoStarted = false;
        isRedoCompleted = false;
        serviceNames = new ArrayList<>();
        for(Service service : services)
        {
            serviceNames.add(service.getName());
            serviceNames.add("\n");
        }
        if(pickUpAddress==null)
        {
            this.pickUpAddress = "-";
        }
        else {
            this.pickUpAddress = pickUpAddress;
        }
        if(dropAddress == null)
        {
            this.dropAddress = "-";
        }
        else {
            this.dropAddress = dropAddress;
        }
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public void setGarage(Garage garage) {
        this.garage = garage;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPickUp(boolean pickUp) {
        this.pickUp = pickUp;
    }

    public void setDrop(boolean drop) {
        this.drop = drop;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }


    public int getId() {
        return id;
    }

    public List<Service> getServices() {
        return services;
    }

    public void addServiceToList(Service service)
    {
        if(!services.contains(service))
        services.add(service);
    }

    public void removeServiceFromList(Service service)
    {
        services.remove(service);
    }

    public Garage getGarage() {
        return garage;
    }

    public Date getDate() {
        return date;
    }

    public boolean isPickUp() {
        return pickUp;
    }

    public boolean isDrop() {
        return drop;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Bill getBill()
    {
        return bill;
    }

    public void setBill(Bill bill)
    {
        this.bill = bill;
    }

    public void setBillPaid(boolean isBillPaid)
    {
        this.isBillPaid = isBillPaid;
    }

    public boolean getBillPaid()
    {
        return isBillPaid;
    }

    public boolean isRedo() {
        return isRedo;
    }

    public void setRedo(boolean redo) {
        isRedo = redo;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    public boolean isAlreadyRated() {
        return isAlreadyRated;
    }

    public void setAlreadyRated(boolean alreadyRated) {
        isAlreadyRated = alreadyRated;
    }

    public boolean isRatedAfterRedo() {
        return isRatedAfterRedo;
    }

    public void setRatedAfterRedo(boolean ratedAfterRedo) {
        isRatedAfterRedo = ratedAfterRedo;
    }

    public boolean isRedoStarted() {
        return isRedoStarted;
    }

    public void setRedoStarted(boolean redoCompleted) {
        isRedoStarted = redoCompleted;
    }

    public boolean isRedoCompleted() {
        return isRedoCompleted;
    }

    public void setRedoCompleted(boolean redoCompleted) {
        isRedoCompleted = redoCompleted;
    }

    public String getPickUpAddress() {
        return pickUpAddress;
    }

    public void setPickUpAddress(String pickUpAddress) {
        this.pickUpAddress = pickUpAddress;
    }

    public String getDropAddress() {
        return dropAddress;
    }

    public void setDropAddress(String dropAddress) {
        this.dropAddress = dropAddress;
    }

    @Override
    public String toString() {

        String pickup ;
        String drop;
        if(isPickUp())
        {
            pickup = "Yes";
        }else pickup = "No";

        if(isDrop())
        {
            drop = "yes";
        }else drop = "No";

        if(bookingStatus == BookingStatus.COMPLETED)
        {
            return "Booking "+
                    "\nBooking Id : " + id +
                    "\nVehicle : "+ vehicle.getModelName()+"\t"+vehicle.getVehicleNumber()+
                    "\nService(s) : " + serviceNames +
                    "\nGarage : " + garage +
                    "\tEmployee : "+ employee +
                    "\nDate : " + date +
                    "\nPickUp : " + pickup +
                    "\tDrop : " + drop +
                    "\nPickUp address : " + pickUpAddress +
                    "\tDrop address : " + dropAddress +
                    "\nAmount paid : Rs." + bill.getTotal() ;
        }
        return "Booking "+
                "\nBooking Id : " + id +
                "\nVehicle : "+ vehicle.getModelName()+"\t"+vehicle.getVehicleNumber()+
                "\nService(s) : " + serviceNames +
                "\nGarage : " + garage +
                "\tEmployee : "+ employee +
                "\nDate : " + date +
                "\nPickUp : " + pickup +
                "\tDrop : " + drop +
                "\nPickUp address : " + pickUpAddress +
                "\tDrop address : " + dropAddress +
                "\nStatus : " + bookingStatus ;
    }
}
