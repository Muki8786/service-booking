package sdk.vehicles;

public class Vehicle {
    protected  String modelName;
    protected  String vehicleNumber;
    //protected  String modelType;
    protected  String color;
    protected  String manufacturerName;
    //protected  String description;
    protected boolean isTwoWheeler;

    /*public Vehicle(String modelName, String vehicleNumber, String modelType, String color, String manufacturerName, String description,
                   boolean isTwoWheeler)
    {
        this.modelName = modelName;
        this.vehicleNumber = vehicleNumber;
        this.modelType = modelType;
        this.color = color;
        this.manufacturerName = manufacturerName;
        this.description = description;
        this.isTwoWheeler = isTwoWheeler;
    }

     */

    public Vehicle(String modelName, String vehicleNumber, String color, String manufacturerName,boolean isTwoWheeler) {
        this.modelName = modelName;
        this.vehicleNumber = vehicleNumber;
        this.color = color;
        this.manufacturerName = manufacturerName;
        this.isTwoWheeler = isTwoWheeler;
    }

    public String getModelName() {
        return modelName;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

   /* public String getModelType() {
        return modelType;
    }

    */

    public String getManufacturerName() {
        return manufacturerName;
    }

    /*public String getDescription() {
        return description;
    }

     */

    public String getColor() {
        return color;
    }

    public boolean isTwoWheeler() {
        return isTwoWheeler;
    }

    @Override
    public String toString() {
        return "Vehicle" + "\n"+
                "Model Name : " + modelName + "\t"+
                "Vehicle Number - " + vehicleNumber +"\n"+
                "Color : " + color + "\t"+
                "Manufacturer Name : " + manufacturerName + "\n";
    }
}
