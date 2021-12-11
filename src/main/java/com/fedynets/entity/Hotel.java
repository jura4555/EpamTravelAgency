package com.fedynets.entity;

import com.fedynets.constants.HotelType;

import java.io.Serializable;

/**
 * Simple Hotel entity with getters and setters.
 * There is also a overrided method toString
 * @autor Yurii Fedynets
 */
public class Hotel implements Serializable {
    private int hotelId;
    private String name;
    private HotelType hotelType;

    public Hotel() {
    }

    public Hotel(int hotelId, String name, HotelType hotelType) {
        this.hotelId = hotelId;
        this.name = name;
        this.hotelType = hotelType;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HotelType getHotelType() {
        return hotelType;
    }

    public void setHotelType(HotelType hotelType) {
        this.hotelType = hotelType;
    }

    @Override
    public String toString() {
        return "Hotel[" +
                "hotelId=" + hotelId +
                ", name=" + name +
                ", hotelType=" + hotelType +
                "]";
    }

    public static class Builder{
        private Hotel hotel;
        public Builder(){
            hotel = new Hotel();
        }

        public Builder bSetHotelId(int hotelId){
            hotel.hotelId = hotelId;
            return this;
        }

        public Builder bSetName(String name) {
            hotel.name = name;
            return this;
        }

        public Builder bSetHotelType(HotelType hotelType){
            hotel.hotelType = hotelType;
            return this;
        }

        public Hotel getResults(){
            return hotel;
        }
    }

}
