package base.model.PageObject;

public class AddCartInfor {
    public AddCartInfor(String message, String productName, String productType, String quantity){
        this.message = message;
        this.productName = productName;
        this.productType=productType;
        this.quantity=quantity;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    String message, productName, productType, quantity;

}
