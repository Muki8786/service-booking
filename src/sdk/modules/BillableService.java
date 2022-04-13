package sdk.modules;

public class BillableService {
    int sno;
    static int count = 1;
    String serviceName;
    float fare;

    BillableService(Service service)
    {
        sno = count++;
        serviceName = service.getName();
        fare = service.getFare();
    }

    static void makeCountOne()
    {
        count = 1;
    }

    @Override
    public String toString() {
        return  sno +"        "+ serviceName +"       "+fare+System.lineSeparator();
    }
}


// Option to add the services in the bill again (Extending)
