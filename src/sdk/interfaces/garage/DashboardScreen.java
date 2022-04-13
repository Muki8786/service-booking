package sdk.interfaces.garage;

import sdk.garage.Employee;

import java.util.List;

public interface DashboardScreen {
    int getDashboardChoice(String content);
    int getManageGarageProfileChoice(String content);
    void displayContent(String content);
    int getManageEmployeesChoice(String content);
    int getRatingOrFeedbackChoice(String content);
    int getEmployeeChoice(List<Employee> employeeList);
    void printErrorContent(String content);
    void printContent(String content);
}
