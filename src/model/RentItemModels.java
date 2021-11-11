package model;

public class RentItemModels {
    private String itemModelId;
    private String getItemModelName;

    public RentItemModels() {
    }

    public RentItemModels(String itemModelId, String getItemModelName) {
        this.setItemModelId(itemModelId);
        this.setGetItemModelName(getItemModelName);
    }

    public String getItemModelId() {
        return itemModelId;
    }

    public void setItemModelId(String itemModelId) {
        this.itemModelId = itemModelId;
    }

    public String getGetItemModelName() {
        return getItemModelName;
    }

    public void setGetItemModelName(String getItemModelName) {
        this.getItemModelName = getItemModelName;
    }

    @Override
    public String toString() {
        return "RentItemModel{" +
                "itemModelId='" + itemModelId + '\'' +
                ", getItemModelName='" + getItemModelName + '\'' +
                '}';
    }
}
