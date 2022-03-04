package pageObject;

import base.Util.Util;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.Point;
import org.testng.Assert;

import java.util.List;

public class ProductPage extends Util {
    @AndroidFindBy(accessibility = "PRODUCT_DETAIL_SCREEN")
    MobileElement ProductDetailScreen;
    @AndroidFindBy(accessibility = "cart-test-id")
    MobileElement cartIcon;
    @AndroidFindBy(accessibility = "ADD_TO_CART_BUTTON")
    MobileElement btnAddToCart;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"PRODUCT_DETAIL_SCREEN\"]//android.widget.HorizontalScrollView//android.widget.ImageView")
    MobileElement productPicture;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"PRODUCT_DETAIL_SCREEN\"]//android.widget.HorizontalScrollView/following-sibling::android.view.ViewGroup[1]//android.widget.TextView")
    MobileElement productType;
    String addCartPopup = "ADD_TO_CART_SUCCESS_MODAL";
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"ADD_TO_CART_SUCCESS_MODAL\"]/android.widget.TextView")
    List<MobileElement> addCartInfor;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"PRODUCT_DETAIL_SCREEN\"]/android.view.ViewGroup[1]/android.view.ViewGroup/descendant::android.widget.TextView[2]")
    MobileElement quantityInCart;
//
//
//    @AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.TextView")
//    MobileElement btnAddToCard;

    public ProductPage isOnProductPage() {
        waitForElement(ProductDetailScreen);
        return this;
    }

    public AddCartPage clickAddToCard() {
        clickOnElement(btnAddToCart);
        return new AddCartPage();
    }

    public ProductPage swipeToLeft() {
        waitForElement(productPicture);
        Point anchorPoint = productPicture.getCenter();
        double anchorPercentage = (double) (anchorPoint.y) / screenHeight;
        try {
            swipeHorizontal(0.8D, 0.2D, anchorPercentage, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }
    public String getType(){
        return getTextElement(productType);
    }
    public ProductPage verifyAddCartSuccess(String productName, String type, String quantity){
        waitForElement(MobileBy.AccessibilityId(addCartPopup));
        Assert.assertEquals(getTextElement(addCartInfor.get(0)),"Cart updated");
        Assert.assertEquals(getTextElement(addCartInfor.get(1)),productName);
        Assert.assertEquals(getTextElement(addCartInfor.get(2)),type);
        Assert.assertEquals(getTextElement(addCartInfor.get(3)).replaceAll("× ",""),quantity);
        return this;
    }
    public ProductPage verifyQuantityInCart(String quantity){
        waitForElementToDisAppear(MobileBy.AccessibilityId(addCartPopup));
        Assert.assertEquals(getTextElement(quantityInCart),quantity);
        return this;
    }
}
