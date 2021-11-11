package view.tm;

public class ItemTm {
    private String serialNumber;
    private String type;
    private double price;
    private String categoryId;
    private String modelId;
    private String text;

    public ItemTm() {
    }

    public ItemTm(String serialNumber, String type, double price, String categoryId, String modelId, String text) {
        this.setSerialNumber(serialNumber);
        this.setType(type);
        this.setPrice(price);
        this.setCategoryId(categoryId);
        this.setModelId(modelId);
        this.setText(text);
    }

    public ItemTm(String serialNumber, String type, double price, String categoryId, String modelId) {
        this.serialNumber = serialNumber;
        this.type = type;
        this.price = price;
        this.categoryId = categoryId;
        this.modelId = modelId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "ItemTm{" +
                "serialNumber='" + serialNumber + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", categoryId='" + categoryId + '\'' +
                ", modelId='" + modelId + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
