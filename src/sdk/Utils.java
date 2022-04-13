package sdk;

import sdk.modules.BookingStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Utils {
    private final HashMap< BookingStatus, List<BookingStatus>> bookingStatusMap;

    public Utils()
    {
        bookingStatusMap = new HashMap<>();
        bookingStatusMap.put(BookingStatus.INITIATED,getList(BookingStatus.INITIATED));
        bookingStatusMap.put(BookingStatus.ACCEPTED,getList(BookingStatus.ACCEPTED));
        bookingStatusMap.put(BookingStatus.PICKED_UP,getList(BookingStatus.PICKED_UP));
        bookingStatusMap.put(BookingStatus.REACHED_GARAGE,getList(BookingStatus.REACHED_GARAGE));
        bookingStatusMap.put(BookingStatus.UNDER_SERVICE,getList(BookingStatus.UNDER_SERVICE));
        bookingStatusMap.put(BookingStatus.SERVICE_COMPLETED,getList(BookingStatus.SERVICE_COMPLETED));
        bookingStatusMap.put(BookingStatus.BILL_GENERATED,getList(BookingStatus.BILL_GENERATED));
        bookingStatusMap.put(BookingStatus.PAYMENT_RECEIVED,getList(BookingStatus.PAYMENT_RECEIVED));
        bookingStatusMap.put(BookingStatus.LEFT_GARAGE,getList(BookingStatus.LEFT_GARAGE));
        bookingStatusMap.put(BookingStatus.REACHED_DESTINATION,getList(BookingStatus.REACHED_DESTINATION));
        bookingStatusMap.put(BookingStatus.DROPPED,getList(BookingStatus.DROPPED));
        bookingStatusMap.put(BookingStatus.COMPLETED,getList(BookingStatus.COMPLETED));
        bookingStatusMap.put(BookingStatus.CANCELLED,getList(BookingStatus.CANCELLED));
        bookingStatusMap.put(BookingStatus.REJECTED,getList(BookingStatus.REJECTED));
    }

    public HashMap<BookingStatus,List<BookingStatus>> getBookingStatusMap() {
        return bookingStatusMap;
    }

    public List<BookingStatus> getList(BookingStatus bookingStatus)
    {
        List<BookingStatus> bookingStatusList = new ArrayList<>();
        switch (bookingStatus)
        {
            case INITIATED: {
                bookingStatusList.add(BookingStatus.ACCEPTED);
            }
            break;
            case ACCEPTED:
            {
                bookingStatusList.add(BookingStatus.PICKED_UP);
                bookingStatusList.add(BookingStatus.REACHED_GARAGE);
            }
            break;
            case PICKED_UP:{
                bookingStatusList.add(BookingStatus.REACHED_GARAGE);
            }
            break;
            case REACHED_GARAGE:{
                bookingStatusList.add(BookingStatus.UNDER_SERVICE);
            }
            break;
            case UNDER_SERVICE:{
                bookingStatusList.add(BookingStatus.SERVICE_COMPLETED);
            }
            break;
            case SERVICE_COMPLETED:{
                //bookingStatusList.add(BookingStatus.UNDER_SERVICE);
                bookingStatusList.add(BookingStatus.BILL_GENERATED);
                //bookingStatusList.add(BookingStatus.LEFT_GARAGE);
                bookingStatusList.add(BookingStatus.COMPLETED);
            }
            break;
            case BILL_GENERATED:{
                bookingStatusList.add(BookingStatus.PAYMENT_RECEIVED);
            }
            break;
            case PAYMENT_RECEIVED:{
                bookingStatusList.add(BookingStatus.LEFT_GARAGE);
                bookingStatusList.add(BookingStatus.COMPLETED);
            }
            break;
            case LEFT_GARAGE:{
                bookingStatusList.add(BookingStatus.REACHED_DESTINATION);
                bookingStatusList.add(BookingStatus.COMPLETED);
            }
            break;
            case REACHED_DESTINATION:{
                bookingStatusList.add(BookingStatus.DROPPED);
            }
            break;
            case DROPPED:{
                bookingStatusList.add(BookingStatus.COMPLETED);
            }
            break;

        }
        return bookingStatusList;
    }
}
