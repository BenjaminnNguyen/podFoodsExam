package pageObject.MobliePageObject;

import base.MobileUtil.MobileUtil;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.testng.Assert;

public class LoginPage extends MobileUtil {

    @AndroidFindBy(accessibility = "EMAIL_INPUT")
    MobileElement email;
    @AndroidFindBy(accessibility = "PASSWORD_INPUT")
    MobileElement password;
    @AndroidFindBy(accessibility = "LOGIN_BUTTON")
    MobileElement loginBtn;

    public LoginPage isOnLoginPage(){
        Assert.assertTrue(isElementDisplayed(email));
        Assert.assertTrue(isElementDisplayed(password));
        Assert.assertTrue(isElementDisplayed(loginBtn));
        return this;
    }

    public HomePage login(String email, String pass){
        sendKeys(this.email,email);
        sendKeys(this.password,pass);
        clickOnElement(loginBtn);
        return new HomePage();
    }

}
