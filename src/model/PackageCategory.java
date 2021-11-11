package model;

public class PackageCategory {
    private String pcId;
    private String pcName;

    public PackageCategory() {
    }

    public PackageCategory(String pcId, String pcName) {
        this.setPcId(pcId);
        this.setPcName(pcName);
    }

    public String getPcId() {
        return pcId;
    }

    public void setPcId(String pcId) {
        this.pcId = pcId;
    }

    public String getPcName() {
        return pcName;
    }

    public void setPcName(String pcName) {
        this.pcName = pcName;
    }

    @Override
    public String toString() {
        return "PackageCategory{" +
                "pcId='" + pcId + '\'' +
                ", pcName='" + pcName + '\'' +
                '}';
    }
}
