package sdk.garage;

import sdk.modules.Book;
import sdk.personalData.Person;
import java.util.ArrayList;
import java.util.List;

public class Employee extends Person {
    private EmployeeStatus employeeStatus;
    private final List<Book> currentServices;
    private final List<Book> pastServices;

    public Employee(String name, String email, String mobile, String address) {
        super(name, email, mobile, address);
        employeeStatus = EmployeeStatus.AVAILABLE;
        currentServices = new ArrayList<>();
        pastServices = new ArrayList<>();
    }

    public EmployeeStatus getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(EmployeeStatus employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public List<Book> getCurrentServices() {
        return currentServices;
    }

    public void assignService(Book book)
    {
        currentServices.add(book);
        employeeStatus = EmployeeStatus.SERVICING;
    }

    public List<Book> getPastServices() {
        return pastServices;
    }

    @Override
    public String toString() {
        return " : " +  name + "\nMobile : " + mobile;
    }
}
