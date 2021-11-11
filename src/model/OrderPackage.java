package model;

import java.util.ArrayList;

public class OrderPackage {
    private String orderId;
    private String packageId;
    private String packageCatogeryId;
    private String photographerId;
    private double packagePrice;
    private  String customerId;
    private ArrayList<OderPackageDeatils> orderDeatiles;

    public OrderPackage() {
    }

    public OrderPackage(String orderId, String packageId, String packageCatogeryId, String photographerId, double packagePrice, String customerId, ArrayList<OderPackageDeatils> orderDeatiles) {
        this.setOrderId(orderId);
        this.setPackageId(packageId);
        this.setPackageCatogeryId(packageCatogeryId);
        this.setPhotographerId(photographerId);
        this.setPackagePrice(packagePrice);
        this.setCustomerId(customerId);
        this.setOrderDeatiles(orderDeatiles);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getPackageCatogeryId() {
        return packageCatogeryId;
    }

    public void setPackageCatogeryId(String packageCatogeryId) {
        this.packageCatogeryId = packageCatogeryId;
    }

    public String getPhotographerId() {
        return photographerId;
    }

    public void setPhotographerId(String photographerId) {
        this.photographerId = photographerId;
    }

    public double getPackagePrice() {
        return packagePrice;
    }

    public void setPackagePrice(double packagePrice) {
        this.packagePrice = packagePrice;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public ArrayList<OderPackageDeatils> getOrderDeatiles() {
        return orderDeatiles;
    }

    public void setOrderDeatiles(ArrayList<OderPackageDeatils> orderDeatiles) {
        this.orderDeatiles = orderDeatiles;
    }

    @Override
    public String toString() {
        return "OrderPackage{" +
                "orderId='" + orderId + '\'' +
                ", packageId='" + packageId + '\'' +
                ", packageCatogeryId='" + packageCatogeryId + '\'' +
                ", photographerId='" + photographerId + '\'' +
                ", packagePrice=" + packagePrice +
                ", customerId='" + customerId + '\'' +
                ", orderDeatiles=" + orderDeatiles +
                '}';
    }
}
