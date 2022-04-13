package sdk.modules;

import java.util.ArrayList;
import java.util.List;

public class Bill {
    public final int billId;
    private final int bookingId;
    private static int count = 1;
    private final String customerName;
    private final String garageName;
    private final String upiId;
    private List<BillableService> services;
    private float total;
    private boolean isBillPaid;

    public Bill(Book book)
    {
        billId = count++;
        bookingId = book.getId();
        customerName = book.getCustomer().getName();
        garageName = book.getGarage().getName();
        upiId = book.getGarage().getUpi();
        services = new ArrayList<>();
        BillableService.makeCountOne();
        for(Service service : book.getServices()) {
            services.add(new BillableService(service));
            total += service.getFare();
        }
        isBillPaid = false;
    }

    public int getBillId() {
        return billId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public static int getCount() {
        return count;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getGarageName() {
        return garageName;
    }

    public String getUpiId() {
        return upiId;
    }

    public List<BillableService> getServices() {
        return services;
    }

    public float getTotal() {
        return total;
    }

    public boolean isBillPaid() {
        return isBillPaid;
    }

    public void setBillPaid(boolean billPaid) {
        isBillPaid = billPaid;
    }

    @Override
    public String toString() {
        return "                                       BILL"+System.lineSeparator()+
                "Bill id -> "+billId+System.lineSeparator()+
                "Customer Name -> "+customerName+"          "+"Booking Id -> "+bookingId+System.lineSeparator()+
                "Garage Name -> "+garageName+"          "+"UPI Id -> "+upiId+System.lineSeparator()+
                "S.no"+"        "+"Service Name"+"      "+"Fare"+System.lineSeparator()+
                services+System.lineSeparator()+
                "Total ->"+total+System.lineSeparator()+"Thank you";
    }
}
