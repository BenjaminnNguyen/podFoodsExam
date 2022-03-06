package pageObject.WebPageObject;

import base.WebUtil.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import static base.Constant.ProductInfo.*;

public class CartPage extends BasePage {
    @FindBy(xpath="//span[text()='Change']/following-sibling::div")
    private WebElement sippingAddress;

    @FindBy(css="div.message")
    private WebElement message;


    String productInfo = "//div[@class='meta']//*[@class='%s']";

    public CartPage verifySippingAddress(){
        Assert.assertEquals(getTextElement(sippingAddress),SHIPPING_INFO);
        return this;
    }
    public CartPage verifyProductInfo(){
        Assert.assertEquals(getTextElement(productInfo, "brand"),BRAND);
        Assert.assertEquals(getTextElement(productInfo, "name"),NAME);
        Assert.assertEquals(getTextElement(productInfo, "variant"),TYPE);
        Assert.assertEquals(getTextElement(productInfo, "variant unit-upc"),VARIANT);
        Assert.assertEquals(getTextElement(productInfo, "item-price"),PRICE);
        Assert.assertEquals(getTextElement(productInfo, "case-price"),CASE_PRICE);
        return this;
    }
    public CartPage verifymessage(){
        Assert.assertEquals(getTextElement(message),MESSAGE);
        return this;
    }

}
