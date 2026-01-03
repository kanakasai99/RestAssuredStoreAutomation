package pojo;

public class Address {
    private String city;
    private String street;
    private int number;
    private String zipcode;
    private Geolocation geolocation;

    public Address(String city,String street, int number, String zipcode,Geolocation geolocation){
        this.city=city;
        this.street=street;
        this.number=number;
        this.zipcode=zipcode;
        this.geolocation=geolocation;
    }

    public Address(){

    }

    public String getCity(){
        return city;
    }
    public void setCity(String city){
        this.city=city;
    }
    public String getStreet(){
        return street;
    }
    public void setStreet(){
        this.street=street;
    }
    public int getNumber(){
        return number;
    }
    public void setNumber(int number){
        this.number=number;
    }
    public String getZipcode(){
        return zipcode;
    }
    public void setZipcode(String zipcode){
        this.zipcode=zipcode;
    }
    public Geolocation getGeolocation(){
        return geolocation;
    }
    public void setGeolocation(Geolocation geolocation){
        this.geolocation=geolocation;
    }
}
