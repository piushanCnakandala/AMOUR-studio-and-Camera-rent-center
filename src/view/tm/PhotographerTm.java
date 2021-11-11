package view.tm;

public class PhotographerTm {
    private String photographerId;
    private String photographerName;
    private String photographerAddress;
    private String photographerTpNumber;
    private double photographerSalary;

    public PhotographerTm() {
    }

    public PhotographerTm(String photographerId, String photographerName, String photographerAddress, String photographerTpNumber, double photographerSalary) {
        this.setPhotographerId(photographerId);
        this.setPhotographerName(photographerName);
        this.setPhotographerAddress(photographerAddress);
        this.setPhotographerTpNumber(photographerTpNumber);
        this.setPhotographerSalary(photographerSalary);
    }

    public String getPhotographerId() {
        return photographerId;
    }

    public void setPhotographerId(String photographerId) {
        this.photographerId = photographerId;
    }

    public String getPhotographerName() {
        return photographerName;
    }

    public void setPhotographerName(String photographerName) {
        this.photographerName = photographerName;
    }

    public String getPhotographerAddress() {
        return photographerAddress;
    }

    public void setPhotographerAddress(String photographerAddress) {
        this.photographerAddress = photographerAddress;
    }

    public String getPhotographerTpNumber() {
        return photographerTpNumber;
    }

    public void setPhotographerTpNumber(String photographerTpNumber) {
        this.photographerTpNumber = photographerTpNumber;
    }

    public double getPhotographerSalary() {
        return photographerSalary;
    }

    public void setPhotographerSalary(double photographerSalary) {
        this.photographerSalary = photographerSalary;
    }

    @Override
    public String toString() {
        return "PhotographerTm{" +
                "photographerId='" + photographerId + '\'' +
                ", photographerName='" + photographerName + '\'' +
                ", photographerAddress='" + photographerAddress + '\'' +
                ", photographerTpNumber='" + photographerTpNumber + '\'' +
                ", photographerSalary=" + photographerSalary +
                '}';
    }
}
