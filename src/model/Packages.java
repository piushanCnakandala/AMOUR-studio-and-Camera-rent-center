package model;

public class Packages {
    private String packageId;
    private String packageType;
    private String packageDeatils;
    private double price;
    private String packageCategoryId;

    public Packages() {
    }

    public Packages(String packageId, String packageType, String packageDeatils, double price, String packageCategoryId) {
        this.setPackageId(packageId);
        this.setPackageType(packageType);
        this.setPackageDeatils(packageDeatils);
        this.setPrice(price);
        this.setPackageCategoryId(packageCategoryId);
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public String getPackageDeatils() {
        return packageDeatils;
    }

    public void setPackageDeatils(String packageDeatils) {
        this.packageDeatils = packageDeatils;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPackageCategoryId() {
        return packageCategoryId;
    }

    public void setPackageCategoryId(String packageCategoryId) {
        this.packageCategoryId = packageCategoryId;
    }

    @Override
    public String toString() {
        return "Packages{" +
                "packageId='" + packageId + '\'' +
                ", packageType='" + packageType + '\'' +
                ", packageDeatils='" + packageDeatils + '\'' +
                ", price=" + price +
                ", packageCategoryId='" + packageCategoryId + '\'' +
                '}';
    }
}
