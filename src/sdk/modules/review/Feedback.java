package sdk.modules.review;

public class Feedback {
    //private int sno;
    //private static int count = 1;
    private String description;
    private String name;


    public Feedback(String name,String description) {
        //sno = count++;
        this.name = name;
        this.description = description;
    }

    /*public static void resetCount()
    {
        count = 1;
    }

     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "\nFeedback : " +
                "\nName : " + name+
        "\nDescription : " + description;
    }
}
