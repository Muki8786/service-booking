package sdk.garage;

import sdk.modules.*;
import sdk.modules.review.Feedback;
import sdk.modules.review.Rating;
import sdk.vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class Garage {
    private final int id;
    private static int count = 0;
    private String name;
    private String address;
    private String userName;
    private String password;
    private String owner;
    private String email;
    private String mobile;
    private String workHours;
    private String upi;
    private GarageStatus garageStatus;
    private List<Vehicle> vehiclesList;
    private List<Service> services;
    private List<Employee> employeeList;
    private List<Employee> availableEmployeeList;
    private List<Employee> employeesAtWork;
    private List<Request> requestList;
    private final List<Request> oldRequestList;
    private List<Book> currentBookings;
    private List<Book> completedBookings;
    private List<Notification> notificationList;
    private List<Bill> currentBillsList;
    private final List<Bill> paidBillsList;
    private final List<Feedback> feedbackList;
    private Rating rating;
    private static int employeeChoosingCount = 0;



    public Garage(String name, String address, String userName, String password, String owner, String email, String mobile, String workHours, String upi) {
        id = ++count;
        this.name = name;
        this.address = address;
        this.userName = userName;
        this.password = password;
        this.owner = owner;
        this.workHours = workHours;
        this.upi = upi;
        garageStatus = GarageStatus.OPEN;
        services = new ArrayList<>();
        employeeList = new ArrayList<>();
        currentBookings = new ArrayList<>();
        completedBookings = new ArrayList<>();
        notificationList = new ArrayList<>();
        currentBillsList = new ArrayList<>();
        paidBillsList = new ArrayList<>();
        feedbackList = new ArrayList<>();
        employeeList.add(new Employee("Ramesh","ramesh@abc.com","9876543212","Coimbatore"));
        employeeList.add(new Employee("Suresh","suresh@abc.com","9876543213","Coimbatore"));
        employeeList.add(new Employee("Dinesh","ramesh@abc.com","9876543212","Coimbatore"));
        employeeList.add(new Employee("Prabhu","ramesh@abc.com","9876543212","Coimbatore"));
        employeeList.add(new Employee("Sam","ramesh@abc.com","9876543212","Coimbatore"));
        employeeList.add(new Employee("Vignesh","ramesh@abc.com","9876543212","Coimbatore"));
        employeeList.add(new Employee("Tilak","ramesh@abc.com","9876543212","Coimbatore"));
        employeeList.add(new Employee("Irfan","ramesh@abc.com","9876543212","Coimbatore"));

        availableEmployeeList = new ArrayList<>();
        availableEmployeeList = getEmployeeList();
        employeesAtWork = new ArrayList<>();
        requestList= new ArrayList<>();
        oldRequestList = new ArrayList<>();
        vehiclesList = new ArrayList<>();
        rating = new Rating();
    }

    public String getName()
    {
        return this.name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWorkHours() {
        return workHours;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setWorkHours(String workHours) {
        this.workHours = workHours;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public void addRating(int noOfStars)
    {
        rating.addRating(noOfStars);
        rating.sendRatingNotificationToGarage(noOfStars);
    }

    public String getUpi() {
        return upi;
    }

    public void setUpi(String upi) {
        this.upi = upi;
    }

    public GarageStatus getGarageStatus() {
        return garageStatus;
    }

    public void setGarageStatus(GarageStatus garageStatus) {
        this.garageStatus = garageStatus;
    }

    public String getOwner() {
        return owner;
    }

    public void setVehiclesList(List<Vehicle> vehiclesList) {
        this.vehiclesList = vehiclesList;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public void setAvailableEmployeeList(List<Employee> availableEmployeeList) {
        this.availableEmployeeList = availableEmployeeList;
    }

    public void setRequestList(List<Request> requestList) {
        this.requestList = requestList;
    }

    public List<Employee> getEmployeeList() {
        List<Employee> employees = new ArrayList<>();
        for(Employee employee : employeeList)
        {
            employees.add(employee);
        }
        return employees;
    }

    public List<Employee> getAvailableEmployeeList()
    {
        return availableEmployeeList;
    }

    public void addEmployee(Employee employee)
    {
        if(!employeeList.contains(employee))
        {
            employeeList.add(employee);
            availableEmployeeList.add(employee);
        }

    }

    public boolean removeEmployee(Employee employee)
    {
        if(availableEmployeeList.contains(employee)) {
            employeeList.remove(employee);
            availableEmployeeList.remove(employee);
            return true;
        }
        else
        {
            return false;

        }
    }

    public void addAvailableEmployee(Employee employee)
    {
        availableEmployeeList.add(employee);
    }

    public void removeAvailableEmployee(Employee employee)
    {
        availableEmployeeList.remove(employee);
    }

    public List<Request> getRequestList()
    {
        return requestList;
    }

    public void addRequest(Request request)
    {
        requestList.add(request);
    }

    public void removeRequest(Request request)
    {
        requestList.remove(request);
    }

    public void addRequestInOldList(Request request)
    {
        oldRequestList.add(request);
    }

    public void removeRequestInOldList(Request request)
    {
        oldRequestList.remove(request);
    }

    public List<Book> getCurrentBookings() {
        return currentBookings;
    }

    public void addCurrentBooking(Book book)
    {
        currentBookings.add(book);
    }

    public void deleteCurrentBooking(Book book)
    {
        currentBookings.remove(book);
    }

    public void addCompletedBooking(Book book) {
        completedBookings.add(book);
    }

    public void removeCompletedBooking(Book book)
    {
        completedBookings.remove(book);
    }

    public void setCurrentBookings(List<Book> currentBookings) {
        this.currentBookings = currentBookings;
    }

    public List<Book> getCompletedBookings() {
        return completedBookings;
    }

    public void setCompletedBookings(List<Book> completedBookings) {
        this.completedBookings = completedBookings;
    }

    public void addBookingInList(Book book)
    {
        currentBookings.add(book);
    }

    public void removeBookingFromList(Book book)
    {
        currentBookings.remove(book);
    }

    public void assignEmployeeWithBooking(Book book)
    {
        Employee employee = getEmployee();
        book.setEmployee(employee);

        // employee.assignService (Change status, remove from available emps list , add the book in the list in emp,
        employee.assignService(book);
        removeAvailableEmployee(employee);
        addEmployeeAtWork(employee);
    }

    public void freeEmployee(Book book)
    {
        book.getEmployee().assignService(null);
        removeEmployeeAtWork(book.getEmployee());
        addAvailableEmployee(book.getEmployee());
    }

    public List<Employee> getEmployeesAtWork() {
        return employeesAtWork;
    }

    public void setEmployeesAtWork(List<Employee> employeesAtWork) {
        this.employeesAtWork = employeesAtWork;
    }

    public void addEmployeeAtWork(Employee employee)
    {
        employeesAtWork.add(employee);
    }

    public void removeEmployeeAtWork(Employee employee)
    {
        employeesAtWork.remove(employee);
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

    public List<Bill> getCurrentBillsList() {
        return currentBillsList;
    }

    public void setCurrentBillsList(List<Bill> currentBillsList) {
        this.currentBillsList = currentBillsList;
    }

    public void addBill(Bill bill)
    {
        currentBillsList.add(bill);
    }

    public void removeBill(Bill bill)
    {
        currentBillsList.remove(bill);
    }

    public Bill getBill(int billId)
    {
        for(Bill bill : currentBillsList)
        {
            if(bill.billId == billId)
            {
                return bill;
            }
        }
        return null;
    }

    public List<Bill> getPaidBillsList() {
        return paidBillsList;
    }

    public List<Feedback> getFeedbackList()
    {
        return feedbackList;
    }

    public void addFeedbackInGarage(Feedback feedback)
    {
        feedbackList.add(feedback);
    }

    public void removeFeedbackInGarage(Feedback feedback)
    {
        feedbackList.remove(feedback);
    }

    public void addPaidBill(Bill bill)
    {
        paidBillsList.add(bill);
    }

    public void removePaidBill(Bill bill)
    {
        paidBillsList.remove(bill);
    }

    public void removeRequestFromRequestList(Book book)
    {
        Request requestToBeRemoved = null;
        for(Request request : requestList)
        {
            if(book.getId() == request.getBookingId())
            {
                requestToBeRemoved = request;
                //requestList.remove(request);
            }
        }
        if(requestToBeRemoved!=null)
        {
            requestList.remove(requestToBeRemoved);
        }
    }

    public Employee getEmployee()
    {
        if(!availableEmployeeList.isEmpty())
        {
            return availableEmployeeList.get(0);
        }
        else
        {
            if(employeeChoosingCount >= employeeList.size())
            {
                employeeChoosingCount = 0;
            }
            return employeeList.get(employeeChoosingCount++);
        }
    }
    // Assign the booking to an available employee

    public List<Employee> getEmployeesAtWorkList()
    {
        return employeesAtWork;
    }

    public List<Request> getOldRequestList() {
        return oldRequestList;
    }

    @Override
    public String toString() {
        return
                "Name : " + name +
                "\t\t Rating : "+ rating.getAverageRating();
    }
}
