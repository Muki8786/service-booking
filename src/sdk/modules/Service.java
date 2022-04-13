package sdk.modules;

public class Service {

    private String name;
    //private int id;
    private float fare;
    private String duration;
    private String description;
    private boolean isTwoWheeler;
    private int countOfAvailed = 0;
    //Rating to be included

    public Service(String name,boolean isTwoWheeler, float fare, String duration, String description) {
        this.name = name;
        this.isTwoWheeler = isTwoWheeler;
        //this.id = id;
        this.fare = fare;
        this.duration = duration;
        this.description = description;
        countOfAvailed = 0;
    }

    public String getName() {
        return name;
    }

    /*public int getId() {
        return id;
    }

     */

    public float getFare() {
        return fare;
    }

    public String getDuration() {
        return duration;
    }

    public String getDescription() {
        return description;
    }

    public boolean isTwoWheeler() {
        return isTwoWheeler;
    }

    public void addCountOfAvailed()
    {
        countOfAvailed++;
    }

    /*public static int getCountOfAvailed() {
        return countOfAvailed;
    }

     */

    @Override
    public String toString() {
        return name +" Service \n" +
                //"Service ID -" + id + " \n"+
                "Fare : Rs." + fare +" \t"+
                "Duration : " + duration + " \n"+
                "Description : " + description + " \n"
                +"Number of customer(s) availed - " + countOfAvailed + " \n";
    }
}
