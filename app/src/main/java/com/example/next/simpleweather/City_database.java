package com.example.next.simpleweather;

/**
 * Created by Student on 4/22/2017.
 */

public class City_database {

    int id;
    String city;

    public City_database(int id,String city) {
        this.id=id;
        this.city = city;
    }
public City_database(City_database another){
    this.id=another.getId();
    this.city=another.getCity();
}
    public void setId(int id) {
        this.id = id;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }
    }
