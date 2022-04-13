package sdk.operations;

import sdk.customer.Customer;
import sdk.interfaces.garage.ManageRequestsScreen;
import sdk.modules.Book;
import sdk.modules.Request;

import java.util.List;

public class CustomerRequests {
    private final Customer customer;
    private final ManageRequestsScreen manageRequestsScreen;

    public CustomerRequests(Customer customer, ManageRequestsScreen manageRequestsScreen) {
        this.customer = customer;
        this.manageRequestsScreen = manageRequestsScreen;
    }

    void manageRequests()
    {
        List<Request> requestList = customer.getRequestList();
        if(requestList.size() == 0) {
            manageRequestsScreen.displayRequests(System.lineSeparator() + "Oops, No requests at the moment");
            return;
        }
        else
        {
            manageRequestsScreen.displayRequests(requestList.toString());
        }

        int requestChoice =0;
        while (true)
        {
            requestChoice = manageRequestsScreen.getRequestChoice();
            if(requestChoice == 0)
            {
                return;
            }
            else if(getRequest(requestChoice) != null)
            {
                break;
            }
            else
            {
                manageRequestsScreen.displayRequests(System.lineSeparator()+"Invalid choice");
            }
        }

        Request request = getRequest(requestChoice);
        if(request == null) return;
        manageRequestsScreen.displayRequests(System.lineSeparator()+ request.toString());

        int acceptChoice = 0;
        while (true) {
            acceptChoice = manageRequestsScreen.getAcceptChoice();
            if(acceptChoice == 0)
            {
                return;
            }
            else if(acceptChoice == 1)
            {
                // accept
                // Update the status in the booking of customer
                // add this service in the vehicle list in garage
                // Assign the employee to this service

                //accept
                //

                ManageRequests manageRequests = new ManageRequests();
                manageRequests.acceptCustomerRequest(request);
                manageRequestsScreen.displayRequests("Request accepted");
                manageRequests.addRequestInCustomerOldList(request);
                manageRequests.removeRequestInCustomer(request);

                break;
            }
            else if(acceptChoice == 2)
            {
                //reject

                ManageRequests manageRequests = new ManageRequests();
                manageRequests.rejectCustomerRequest(request);
                request.setAccepted(-1);
                manageRequestsScreen.displayRequests("Request rejected");
                manageRequests.addRequestInCustomerOldList(request);
                manageRequests.removeRequestInCustomer(request);
                break;
            }
            else
            {
                manageRequestsScreen.displayRequests(System.lineSeparator()+"Invalid choice");
            }
        }
    }


    private Request getRequest(int requestChoice)
    {
        for(Request request : customer.getRequestList())
        {
            if(request.getId() == requestChoice)
            {
                return request;
            }
        }
        return null;
    }

    private Book getBooking(Request request)
    {
        return request.getCustomer().getBooking(request.getBookingId());
    }

}
