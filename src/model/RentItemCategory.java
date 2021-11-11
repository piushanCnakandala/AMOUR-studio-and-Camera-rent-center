package model;

public class RentItemCategory {
    private String itemCategoryId;
    private String itemCategoryName;

    public RentItemCategory() {
    }

    public RentItemCategory(String itemCategoryId, String itemCategoryName) {
        this.setItemCategoryId(itemCategoryId);
        this.setItemCategoryName(itemCategoryName);
    }

    public String getItemCategoryId() {
        return itemCategoryId;
    }

    public void setItemCategoryId(String itemCategoryId) {
        this.itemCategoryId = itemCategoryId;
    }

    public String getItemCategoryName() {
        return itemCategoryName;
    }

    public void setItemCategoryName(String itemCategoryName) {
        this.itemCategoryName = itemCategoryName;
    }

    @Override
    public String toString() {
        return "RentItemCategory{" +
                "itemCategoryId='" + itemCategoryId + '\'' +
                ", itemCategoryName='" + itemCategoryName + '\'' +
                '}';
    }
}
