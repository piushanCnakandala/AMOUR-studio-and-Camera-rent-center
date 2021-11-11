package view.tm;

public class RentItemOrderDeatilsTM {
    private String orderId;
    private String serialNum;
    private double totalPrice;
    private String custId;
    private int totalDate;
    private String rentDate;
    private String returnDate;
    private String status;

    public RentItemOrderDeatilsTM() {
    }

    public RentItemOrderDeatilsTM(String orderId, String serialNum, double totalPrice, String custId, String rentDate, String returnDate) {
        this.orderId = orderId;
        this.serialNum = serialNum;
        this.totalPrice = totalPrice;
        this.custId = custId;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
    }

    public RentItemOrderDeatilsTM(String orderId, String serialNum, String custId, String rentDate, String returnDate) {
        this.orderId = orderId;
        this.serialNum = serialNum;
        this.custId = custId;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
    }

    public RentItemOrderDeatilsTM(String orderId, String serialNum, double totalPrice, String custId, int totalDate, String rentDate, String returnDate, String status) {
        this.setOrderId(orderId);
        this.setSerialNum(serialNum);
        this.setTotalPrice(totalPrice);
        this.setCustId(custId);
        this.setTotalDate(totalDate);
        this.setRentDate(rentDate);
        this.setReturnDate(returnDate);
        this.setStatus(status);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public int getTotalDate() {
        return totalDate;
    }

    public void setTotalDate(int totalDate) {
        this.totalDate = totalDate;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RentItemOrderDeatilsTM{" +
                "orderId='" + orderId + '\'' +
                ", serialNum='" + serialNum + '\'' +
                ", totalPrice=" + totalPrice +
                ", custId='" + custId + '\'' +
                ", totalDate=" + totalDate +
                ", rentDate='" + rentDate + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
