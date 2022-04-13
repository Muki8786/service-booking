package sdk.garageOperations;

import sdk.garage.Garage;
import sdk.interfaces.garage.ManageRequestsScreen;
import sdk.modules.Book;
import sdk.modules.Request;
import sdk.operations.ManageRequests;

import java.util.List;

public class Requests {
    private final Garage garage;
    private final ManageRequestsScreen manageRequestsScreen;

    public Requests(Garage garage, ManageRequestsScreen manageRequestsScreen) {
        this.garage = garage;
        this.manageRequestsScreen = manageRequestsScreen;
    }

    void manageRequests()
    {
        List<Request> requestList = garage.getRequestList();
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

                ManageRequests manageRequests = new ManageRequests();
                manageRequests.acceptGarageRequest(request);
                if(!request.isRedo())
                {
                    garage.assignEmployeeWithBooking(getBooking(request));
                }
                else {
                    garage.assignEmployeeWithBooking(request.getRedoBook());
                }
                manageRequestsScreen.displayRequests("Request accepted");
                manageRequests.addRequestInGarageOldList(request);
                manageRequests.removeRequestInGarage(request);

                break;
            }
            else if(acceptChoice == 2)
            {
                //reject

                ManageRequests manageRequests = new ManageRequests();
                manageRequests.rejectGarageRequest(request);
                request.setAccepted(-1);
                manageRequestsScreen.displayRequests("Request rejected");
                manageRequests.addRequestInGarageOldList(request);
                manageRequests.removeRequestInGarage(request);
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
        for(Request request : garage.getRequestList())
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

    private Book getBookingFromCompletedList(Request request)
    {
        return request.getCustomer().getBookingFromCompletedList(request.getBookingId());
    }
}
