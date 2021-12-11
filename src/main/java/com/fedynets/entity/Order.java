package com.fedynets.entity;

import com.fedynets.constants.TourStatus;

import java.io.Serializable;

/**
 * Simple Order entity with getters and setters.
 * There is also a overrided method toString
 * @autor Yurii Fedynets
 */
public class Order implements Serializable {
    private int orderId;
    private int userId;
    private int tourId;
    private double price;
    private int stepDisCount;
    private int disCount;
    private TourStatus tourStatus;
    private String description;

    public Order() {
    }

    public Order(int orderId, int userId, int tourId, double price, int stepDisCount,
                 int disCount, TourStatus tourStatus, String description) {
        this.orderId = orderId;
        this.userId = userId;
        this.tourId = tourId;
        this.price = price;
        this.stepDisCount = stepDisCount;
        this.disCount = disCount;
        this.tourStatus = tourStatus;
        this.description = description;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStepDisCount() {
        return stepDisCount;
    }

    public void setStepDisCount(int stepDisCount) {
        this.stepDisCount = stepDisCount;
    }

    public int getDisCount() {
        return disCount;
    }

    public void setDisCount(int disCount) {
        this.disCount = disCount;
    }

    public TourStatus getTourStatus() {
        return tourStatus;
    }

    public void setTourStatus(TourStatus tourStatus) {
        this.tourStatus = tourStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Order[" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", tourId=" + tourId +
                ", price=" + price +
                ", stepDisCount=" + stepDisCount +
                ", disCount=" + disCount +
                ", tourStatus=" + tourStatus +
                ", description=" + description +
                "]";
    }

    public static class Builder {
        private Order order;

        public Builder() {
            order = new Order();
        }

        public Builder bSetOrderId(int orderId){
            order.orderId = orderId;
            return this;
        }

        public Builder bSetUserId(int userId){
            order.userId = userId;
            return this;
        }

        public Builder bSetTourId(int tourId){
            order.tourId = tourId;
            return this;
        }

        public Builder bSetPrice(double price){
            order.price = price;
            return this;
        }

        public Builder bSetStepDisCount(int stepDisCount){
            order.stepDisCount = stepDisCount;
            return this;
        }

        public Builder bSetDisCount(int disCount){
            order.disCount = disCount;
            return this;
        }

        public Builder bSetTourStatus(TourStatus tourStatus){
            order.tourStatus = tourStatus;
            return this;
        }

        public Builder bSetDescription(String description){
            order.description = description;
            return this;
        }

        public Order getResult(){
            return order;
        }
    }
}
