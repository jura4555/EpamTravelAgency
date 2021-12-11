package com.fedynets.entity;

import com.fedynets.constants.HotelType;
import com.fedynets.constants.TourType;

import java.io.Serializable;
import java.util.Date;
import java.time.LocalDate;

/**
 * Simple Tour entity with getters and setters.
 * There is also a overrided method toString
 * @autor Yurii Fedynets
 */
public class Tour implements Serializable {
    private int tourId;
    private String name;
    private double price;
    private LocalDate dateDaparture;
    private LocalDate dateArrival;
    private int maxDisCount;
    private int placeCount;
    private int hotelId;
    private TourType tourType;
    private boolean isDurning;

    public Tour() {
    }

    public Tour(int tourId, String name, double price, LocalDate dateDaparture, LocalDate dateArrival,
                int maxDisCount, int placeCount, int hotelId, TourType tourType, boolean isDurning) {
        this.tourId = tourId;
        this.name = name;
        this.price = price;
        this.dateDaparture = dateDaparture;
        this.dateArrival = dateArrival;
        this.maxDisCount = maxDisCount;
        this.placeCount = placeCount;
        this.hotelId = hotelId;
        this.tourType = tourType;
        this.isDurning = isDurning;
    }

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getDateDaparture() {
        return dateDaparture;
    }

    public void setDateDaparture(LocalDate dateDaparture) {
        this.dateDaparture = dateDaparture;
    }

    public LocalDate getDateArrival() {
        return dateArrival;
    }

    public void setDateArrival(LocalDate dateArrival) {
        this.dateArrival = dateArrival;
    }

    public int getMaxDisCount() {
        return maxDisCount;
    }

    public void setMaxDisCount(int maxDisCount) {
        this.maxDisCount = maxDisCount;
    }

    public int getPlaceCount() {
        return placeCount;
    }

    public void setPlaceCount(int placeCount) {
        this.placeCount = placeCount;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public TourType getTourType() {
        return tourType;
    }

    public void setTourType(TourType tourType) {
        this.tourType = tourType;
    }

    public boolean isDurning() {
        return isDurning;
    }

    public void setDurning(boolean durning) {
        isDurning = durning;
    }

    @Override
    public String toString() {
        return "Tour[" +
                "tourId=" + tourId +
                ", name='" + name +
                ", price=" + price +
                ", dateDaparture=" + dateDaparture +
                ", dateArrival=" + dateArrival +
                ", maxDisCount=" + maxDisCount +
                ", placeCount=" + placeCount +
                ", hotelId=" + hotelId +
                ", hotelType=" + tourType +
                ", isDurning=" + isDurning +
                "]";
    }

    public static class Builder{
        private Tour tour;

        public Builder(){
            tour = new Tour();
        }

        public Builder bSetTourId(int tourId){
            tour.tourId = tourId;
            return this;
        }

        public Builder bSetName(String name){
            tour.name = name;
            return this;
        }

        public Builder bSetPrice(double price){
            tour.price = price;
            return this;
        }

        public Builder bSetDateDaparture(LocalDate dateDaparture){
            tour.dateDaparture = dateDaparture;
            return this;
        }

        public Builder bSetDateArrival(LocalDate dateArrival){
            tour.dateArrival = dateArrival;
            return this;
        }

        public Builder bSetMaxDisCount(int maxDisCount){
            tour.maxDisCount = maxDisCount;
            return this;
        }

        public Builder bSetPlaceCount(int placeCount){
            tour.placeCount = placeCount;
            return this;
        }

        public Builder bSetHotelId(int hotelId){
            tour.hotelId = hotelId;
            return this;
        }

        public Builder bSetTourType(TourType tourType){
            tour.tourType = tourType;
            return this;
        }

        public Builder bSetDurning(boolean durning){
            tour.isDurning = durning;
            return this;
        }

        public Tour getResult(){
            return tour;
        }
    }

}
