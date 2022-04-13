package sdk.modules;

public class Notification {
    private final int id;
    private static int count =0;
    private final String header;
    private final String description;
    private final String type;

    public Notification(String header, String description, String type) {
        count++;
        this.id = count;
        this.header = header;
        this.description = description;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public static void setCountToZero()
    {
        count =0;
    }

    @Override
    public String toString() {
        return "\n----------NOTIFICATION----------" +"\n"+
                header +"\n"+
                description;
    }
}
