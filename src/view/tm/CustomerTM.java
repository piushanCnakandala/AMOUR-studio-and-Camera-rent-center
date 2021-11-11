package view.tm;

public class CustomerTM {
    private String customerId;
    private String customerName;
    private String customerAddress;
    private String customerNic;
    private String customerTPNumber;

    public CustomerTM() {
    }

    public CustomerTM(String customerId, String customerName, String customerAddress, String customerNic, String customerTPNumber) {
        this.setCustomerId(customerId);
        this.setCustomerName(customerName);
        this.setCustomerAddress(customerAddress);
        this.setCustomerNic(customerNic);
        this.setCustomerTPNumber(customerTPNumber);
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerNic() {
        return customerNic;
    }

    public void setCustomerNic(String customerNic) {
        this.customerNic = customerNic;
    }

    public String getCustomerTPNumber() {
        return customerTPNumber;
    }

    public void setCustomerTPNumber(String customerTPNumber) {
        this.customerTPNumber = customerTPNumber;
    }

    @Override
    public String toString() {
        return "CustomerTM{" +
                "customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", customerNic='" + customerNic + '\'' +
                ", customerTPNumber='" + customerTPNumber + '\'' +
                '}';
    }
}
