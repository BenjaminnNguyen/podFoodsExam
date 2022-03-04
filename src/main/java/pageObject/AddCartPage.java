package pageObject;

import base.Util.Util;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AddCartPage extends Util {

    @AndroidFindBy(accessibility = "QUANTITY")
    MobileElement quantity;
    @AndroidFindBy(accessibility = "ADD_TO_CART_BUTTON")
    MobileElement btnAddToCart;

    public AddCartPage isOnAddCartPage(){
        waitForElement(quantity);
        return this;
    }
    public ProductPage clickAddToCart(){
       clickOnElement(btnAddToCart);
        return new ProductPage();
    }
    public String getQuantity(){
        return getTextElement(quantity);
    }
}
