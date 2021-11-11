package view.tm;

public class CartTM {
    private String orderId;
    private String serialNumber;
    private String customerId;
    private String modelId;
    private String catogeryId;
    private double oneDyPrice;
    private Double totalPrice;
    private int totalDays;
    private String rentDate;
    private String returnDate;

    public CartTM() {
    }

    public CartTM(String orderId, String serialNumber, Double totalPrice, String rentDate, String returnDate) {
        this.orderId = orderId;
        this.serialNumber = serialNumber;
        this.totalPrice = totalPrice;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
    }

    public CartTM(String orderId, String serialNumber, String customerId, String modelId, String catogeryId, double oneDyPrice, Double totalPrice, int totalDays) {
        this.setOrderId(orderId);
        this.setSerialNumber(serialNumber);
        this.setCustomerId(customerId);
        this.setModelId(modelId);
        this.setCatogeryId(catogeryId);
        this.setOneDyPrice(oneDyPrice);
        this.setTotalPrice(totalPrice);
        this.setTotalDays(totalDays);
    }

    public CartTM(String orderId, String serialNumber, String customerId, String modelId, String catogeryId, double oneDyPrice, Double totalPrice, int totalDays, String rentDate, String returnDate) {
        this.orderId = orderId;
        this.serialNumber = serialNumber;
        this.customerId = customerId;
        this.modelId = modelId;
        this.catogeryId = catogeryId;
        this.oneDyPrice = oneDyPrice;
        this.totalPrice = totalPrice;
        this.totalDays = totalDays;
        this.setRentDate(rentDate);
        this.setReturnDate(returnDate);
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

    public String getRentDate() {
        return rentDate;
    }

    public void setRentDate(String rentDate) {
        this.rentDate = rentDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "CartTM{" +
                "orderId='" + orderId + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", customerId='" + customerId + '\'' +
                ", modelId='" + modelId + '\'' +
                ", catogeryId='" + catogeryId + '\'' +
                ", oneDyPrice=" + oneDyPrice +
                ", totalPrice=" + totalPrice +
                ", totalDays=" + totalDays +
                ", rentDate='" + rentDate + '\'' +
                ", returnDate='" + returnDate + '\'' +
                '}';
    }
}
