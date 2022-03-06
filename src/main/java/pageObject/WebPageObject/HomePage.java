package pageObject.WebPageObject;

import base.WebUtil.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

import static base.Constant.Constants.URL_WEB;
import static base.Constant.ProductInfo.*;

public class HomePage extends BasePage {
    @FindBy(xpath="//div[@class='actions']/a[text()='Sign in']")
    private WebElement signinBtn;
    @FindBy(css="input[name='email']")
    private WebElement emailInput;
    @FindBy(css="input[name='password']")
    private WebElement passInput;
    @FindBy(xpath="//input[@value='buyer']")
    private WebElement buyerRadio;
    @FindBy(xpath="//button[text()='Sign In']")
    private WebElement signinBtnOnForm;
    @FindBy(xpath="//div[@class='banner-content']//img")
    private WebElement closePopup;
    @FindBy(xpath="//div[@class='search-input']//input")
    private WebElement searchInput;
    @FindBy(xpath="//div[@class='search-input']//button")
    private WebElement searchBtn;
    @FindBy(xpath="//div[@class='products-grid']")
    private WebElement productGrid;
    @FindBy(xpath="//div[@class='product-card']//span[@data-tip]")
    private WebElement addCartIcon;
    @FindBy(id="product-quick-view-modal")
    private WebElement popUpAddCart;
    @FindBy(xpath="//*[@id='ProductQuickViewForm']//input")
    private List<WebElement> quantityInput;
    @FindBy(xpath="//button[text()='Add to cart']")
    private WebElement addToCartBtn;
    @FindBy(css="p[class='message']")
    private WebElement addCartMessage;
    @FindBy(xpath="//div[@class='rb-notification']//span[@class='x']")
    private WebElement closeMessage;
    @FindBy(xpath="//div[@class='cart']")
    private WebElement cartIcon;
    @FindBy(xpath="//div[@class='cart']//div[@class='info']/div")
    private List<WebElement> productInfo;
    @FindBy(xpath="//div[@class='description']")
    private WebElement productDesc;
    @FindBy(xpath="//div[@class='total']")
    private WebElement productTotal;

    String orderValue = "//div[@class='report']//td[text()='%s']/following-sibling::td";

    public HomePage openHomePage(){
        this.driver.get(URL_WEB);
        return new HomePage();
    }
    public HomePage signIn(String emai, String pass, boolean isBuyer){
        clickToElement(signinBtn);
        waitForPageLoaded();
        sendKeyToElement(emailInput, emai);
        sendKeyToElement(passInput, pass);
        if(isBuyer){
            clickToElement(buyerRadio);
        }
        clickToElement(signinBtnOnForm);
        waitForPageLoaded();
        return this;
    }

    public HomePage closePopup(){
        clickToElement(waitForElementVisible(closePopup));
        waitForPageLoaded();
        return this;
    }

    public HomePage search(String value) throws Exception {
        waitForElementVisible(searchInput);
        sendKeyToElement(searchInput, value);
        clickToElement(searchBtn);
        Thread.sleep(3000);
        return this;
    }
    public HomePage clickAddCart(){
        clickToElement(addCartIcon);
        return this;
    }
    public HomePage waitAddCartPopup(){
        waitForElementVisible(popUpAddCart);
        return this;
    }
    public HomePage inputQuantity(String quantity){
        sendKeyToElement(quantityInput.get(0), quantity);
        return this;
    }
    public HomePage clickAddToCard(){
        clickToElement(addToCartBtn);
        return this;
    }
    public HomePage waitAddCartSuccess(){
        waitForElementVisible(addCartMessage);
        return this;
    }
    public HomePage closePopupAddCart(){
        clickToElement(closeMessage);
        return this;
    }
    public HomePage hoverToCart(){
        hoverMouseToElement(cartIcon);
        return this;
    }
    public HomePage verifyProductInCart(){
        Assert.assertEquals(getTextElement(productInfo.get(0)),BRAND, "Brand incorrect");
        Assert.assertEquals(getTextElement(productInfo.get(1)),NAME, "Name product incorrect");
        Assert.assertEquals(getTextElement(productInfo.get(2)),TYPE, "Type product incorrect");
        Assert.assertEquals(getTextElement(productDesc),"$10.00×1");
        Assert.assertEquals(getTextElement(orderValue,"Order Value"),CASE_PRICE, "Order Value product incorrect");
        Assert.assertEquals(getTextElement(orderValue,"Small Order Surcharge"),ORDER_SURCHARGE, "Small Order Surcharge product incorrect");
        Assert.assertEquals(getTextElement(orderValue,"Items Subtotal"),CASE_PRICE, "Items Subtotal product incorrect");
        Assert.assertEquals(getTextElement(orderValue,"Logistics Surcharge"),LOGICTICS_SURCHARGE, "Logistics Surcharge product incorrect");
        Assert.assertEquals(getTextElement(orderValue,"Total"),TOTAL, "Total incorrect");
        return this;
    }
    public CartPage goToCartPage(){
        clickToElement(cartIcon);
        return new CartPage();
    }
}
