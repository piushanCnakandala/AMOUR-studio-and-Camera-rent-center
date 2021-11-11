package view.tm;

public class PackageCategoryTM {
    private String pcId;
    private String pcName;

    public PackageCategoryTM() {
    }

    public PackageCategoryTM(String pcId, String pcName) {
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
        return "PackageCategoryTM{" +
                "pcId='" + pcId + '\'' +
                ", pcName='" + pcName + '\'' +
                '}';
    }
}
