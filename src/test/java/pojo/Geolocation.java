package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Geolocation {
    private  String lat;
    @JsonProperty("long")
    private String lng;

public Geolocation(String lat, String lng ){
    this.lat=lat;
    this.lng=lng;
}
public Geolocation(){

}
public String getLat(){
    return lat;
}
public void setLat(String lat){
    this.lat=lat;
}

public String getLng(){
    return lng;
}
public void setLng(String lng){
    this.lng=lng;
}
}
