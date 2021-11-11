package model;

import view.tm.RentItemOrderDeatilsTM;

import java.util.ArrayList;

public class OderItem {
    private String orderId;
    private String serialNumber;
    private String customerId;
    private String modelId;
    private String catogeryId;
    private double oneDyPrice;
    private Double totalPrice;
    private int totalDays;
    private ArrayList<RentItemOrderDeatilsTM> rentItemOrderDeatils;


    public OderItem(String orderId, String serialNumber, String customerId, String modelId, String catogeryId, double oneDyPrice, Double totalPrice, int totalDays, ArrayList<RentItemOrderDeatilsTM> rentItemOrderDeatils) {
        this.setOrderId(orderId);
        this.setSerialNumber(serialNumber);
        this.setCustomerId(customerId);
        this.setModelId(modelId);
        this.setCatogeryId(catogeryId);
        this.setOneDyPrice(oneDyPrice);
        this.setTotalPrice(totalPrice);
        this.setTotalDays(totalDays);
        this.setRentItemOrderDeatils(rentItemOrderDeatils);
    }


    public OderItem() {
    }

    public OderItem(String orderId, Double totalPrice, ArrayList<RentItemOrderDeatilsTM> rentItemOrderDeatils) {
        this.setOrderId(orderId);
        this.setTotalPrice(totalPrice);
        this.setRentItemOrderDeatils(rentItemOrderDeatils);
    }


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getCatogeryId() {
        return catogeryId;
    }

    public void setCatogeryId(String catogeryId) {
        this.catogeryId = catogeryId;
    }

    public double getOneDyPrice() {
        return oneDyPrice;
    }

    public void setOneDyPrice(double oneDyPrice) {
        this.oneDyPrice = oneDyPrice;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
    }

    public ArrayList<RentItemOrderDeatilsTM> getRentItemOrderDeatils() {
        return rentItemOrderDeatils;
    }

    public void setRentItemOrderDeatils(ArrayList<RentItemOrderDeatilsTM> rentItemOrderDeatils) {
        this.rentItemOrderDeatils = rentItemOrderDeatils;
    }

    @Override
    public String toString() {
        return "OderItem{" +
                "orderId='" + orderId + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", customerId='" + customerId + '\'' +
                ", modelId='" + modelId + '\'' +
                ", catogeryId='" + catogeryId + '\'' +
                ", oneDyPrice=" + oneDyPrice +
                ", totalPrice=" + totalPrice +
                ", totalDays=" + totalDays +
                ", rentItemOrderDeatils=" + rentItemOrderDeatils +
                '}';
    }
}
