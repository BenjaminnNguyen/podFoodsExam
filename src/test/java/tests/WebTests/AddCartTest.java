package tests.WebTests;

import base.WebUtil.AbstractWebTest;
import base.WebUtil.BasePage;
import org.testng.annotations.Test;
import pageObject.WebPageObject.CartPage;
import pageObject.WebPageObject.HomePage;

import static base.Constant.Constants.*;

public class AddCartTest extends AbstractWebTest {

    @Test(description = "Add cart test")
    public void AddCartTest() throws Exception{
        HomePage homePage = new HomePage().openHomePage()
                .signIn(EMAIL_WEB,PASSWORD,true)
                .closePopup()
                .search(PRODUCT)
                .clickAddCart()
                .waitAddCartPopup()
                .inputQuantity("1")
                .clickAddToCard()
                .waitAddCartSuccess()
                .closePopupAddCart()
                .hoverToCart()
                .verifyProductInCart();
        CartPage cartPage = homePage.goToCartPage()
                .verifySippingAddress()
                .verifyProductInfo()
                .verifymessage();
    }
}
