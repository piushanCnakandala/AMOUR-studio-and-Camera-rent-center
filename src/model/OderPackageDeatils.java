package model;

public class OderPackageDeatils {
    private String orderId;
    private String packageId;
    private double packagePrice;
    private String packageCatogeryId;
    private String customerId;
    private String orderDate;
    private String photographerId;

    public OderPackageDeatils() {
    }

    public OderPackageDeatils(String orderId, String packageId, double packagePrice, String packageCatogeryId, String customerId, String orderDate, String photographerId) {
        this.setOrderId(orderId);
        this.setPackageId(packageId);
        this.setPackagePrice(packagePrice);
        this.setPackageCatogeryId(packageCatogeryId);
        this.setCustomerId(customerId);
        this.setOrderDate(orderDate);
        this.setPhotographerId(photographerId);
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

    public double getPackagePrice() {
        return packagePrice;
    }

    public void setPackagePrice(double packagePrice) {
        this.packagePrice = packagePrice;
    }

    public String getPackageCatogeryId() {
        return packageCatogeryId;
    }

    public void setPackageCatogeryId(String packageCatogeryId) {
        this.packageCatogeryId = packageCatogeryId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getPhotographerId() {
        return photographerId;
    }

    public void setPhotographerId(String photographerId) {
        this.photographerId = photographerId;
    }

    @Override
    public String toString() {
        return "OderPackageDeatils{" +
                "orderId='" + orderId + '\'' +
                ", packageId='" + packageId + '\'' +
                ", packagePrice=" + packagePrice +
                ", packageCatogeryId='" + packageCatogeryId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", photographerId='" + photographerId + '\'' +
                '}';
    }
}
