package sdk.operations;

import sdk.modules.Service;
import sdk.interfaces.customer.ServicesScreen;

import java.util.ArrayList;
import java.util.List;

public class Services {
    private final List<Service> serviceList ;
    private final List<Service> bikeServiceList;
    private final List<Service> carServiceList;
    private final ServicesScreen servicesScreen;

    public Services(ServicesScreen servicesScreen) {
        serviceList = new ArrayList<>();
        createServices();
        bikeServiceList = new ArrayList<>();
        carServiceList = new ArrayList<>();
        createBikeAndCarServices();
        this.servicesScreen = servicesScreen;
    }

    public void createServices()
    {
        serviceList.add(new Service("Wash ",true,150,"1 hour","Vehicles are sprayed with our magic " +
                "Foam and cleaned by our crew "));
        serviceList.add(new Service("Wash ",false,200,"1 hour","Vehicles are sprayed with our magic " +
                "Foam and cleaned by our crew "));
        serviceList.add(new Service("Oil change ",false,500,"2 hours","Old engine oil is replaced with " +
                "our partner(Motul)'s special engine oil for a smooth driving experience"));
        serviceList.add(new Service("General ",false,1000,"3-6 hours","Our crew checks for" +
                " the parts which needs to be altered or replaced with our trusted branded spare parts"));
        serviceList.add(new Service("General ",true,800,"3 hours","Our crew checks for" +
                " the parts which needs to be altered or replaced with our trusted branded spare parts"));
        serviceList.add(new Service("Oil change ",true,500,"2 hours","Old engine oil is replaced with " +
                "our partner(Motul)'s special engine oil for a smooth driving experience"));
        serviceList.add(new Service("Repair damaged parts",true,500,"2 hours","Damaged parts are replaced"));
        serviceList.add(new Service("Repair damaged parts",false,500,"2 hours","Damaged parts are replaced"));
    }

    private void createBikeAndCarServices()
    {
        for(Service service: serviceList)
        {
            if(service.isTwoWheeler())
            {
                bikeServiceList.add(service);
            }
            else {
                carServiceList.add(service);
            }
        }
    }

    public List<Service> getBikeServiceList()
    {
        return bikeServiceList;
    }

    public List<Service> getCarServiceList() {
        return carServiceList;
    }

    public  List<Service> getAllServiceList()
    {
            return serviceList;
    }

     void displayServices()
    {
        servicesScreen.displayServices(getAllServiceList());
    }

     /*Service askBookAService() {
        int choice = servicesScreen.getBookNowChoice();
        if (choice > 0 && choice <= serviceList.size()) {
            for (Service service : serviceList) {
                if(service.getId() == choice)
                {
                    return service;
                }
            }
        }
        return null;
    }

      */
}
