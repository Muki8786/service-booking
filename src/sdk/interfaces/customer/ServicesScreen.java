package sdk.interfaces.customer;

import sdk.modules.Service;

import java.util.List;

public interface ServicesScreen {
    void displayServices(List<Service> serviceList);
    int getBookNowChoice();
}
