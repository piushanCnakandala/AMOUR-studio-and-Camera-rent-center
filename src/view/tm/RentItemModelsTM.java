package view.tm;

public class RentItemModelsTM {
    private String itemModelId;
    private String itemModelName;

    public RentItemModelsTM() {
    }

    public RentItemModelsTM(String itemModelId, String itemModelName) {
        this.setItemModelId(itemModelId);
        this.setItemModelName(itemModelName);
    }

    public String getItemModelId() {
        return itemModelId;
    }

    public void setItemModelId(String itemModelId) {
        this.itemModelId = itemModelId;
    }

    public String getItemModelName() {
        return itemModelName;
    }

    public void setItemModelName(String itemModelName) {
        this.itemModelName = itemModelName;
    }

    @Override
    public String toString() {
        return "RentItemModelsTM{" +
                "itemModelId='" + itemModelId + '\'' +
                ", itemModelName='" + itemModelName + '\'' +
                '}';
    }
}
