package sdk.personalData;

public class Address {
    private  String doorNo;
    private  String streetName;
    private  String locality;
    private  String district;
    private  String state;
    private  String country;
    private  String pin;

    public Address(String doorNo, String streetName, String locality, String district, String state, String country,
                   String pin)
    {
        this.doorNo = doorNo;
        this.streetName = streetName;
        this.locality = locality;
        this.district = district;
        this.state = state;
        this.country= country;
        this.pin = pin;
    }


    public String getDoorNo() {
        return doorNo;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getLocality() {
        return locality;
    }

    public String getDistrict() {
        return district;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getPin() {
        return pin;
    }

    public void setDoorNo(String doorNo) {
        this.doorNo = doorNo;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    @Override
    public String toString() {
        return "Address" +
                "doorNo='" + doorNo +
                ", streetName='" + streetName +
                ", locality='" + locality +
                ", district='" + district +
                ", state='" + state +
                ", country='" + country  +
                ", pin='" + pin ;
    }
}
