package view.tm;

public class OrderPackageTM {
    private String packageId;
    private String packageCatogeryId;
    private String photographerId;
    private double packagePrice;
    private String customerId;

    public OrderPackageTM(String packageId, String packageCatogeryId, String photographerId, double packagePrice, String customerId) {
        this.setPackageId(packageId);
        this.setPackageCatogeryId(packageCatogeryId);
        this.setPhotographerId(photographerId);
        this.setPackagePrice(packagePrice);
        this.setCustomerId(customerId);
    }

    public OrderPackageTM() {
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

    @Override
    public String toString() {
        return "OrderPackageTM{" +
                "packageId='" + packageId + '\'' +
                ", packageCatogeryId='" + packageCatogeryId + '\'' +
                ", photographerId='" + photographerId + '\'' +
                ", packagePrice=" + packagePrice +
                ", customerId='" + customerId + '\'' +
                '}';
    }
}
