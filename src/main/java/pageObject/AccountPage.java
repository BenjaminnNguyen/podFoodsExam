package pageObject;

import base.Util.Util;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AccountPage extends Util {
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Login\")")
    MobileElement LoginBtn;

    public AccountPage isAccountPage(){
        isElementDisplayed(LoginBtn);
        return this;
    }
    public LoginPage clickLogin(){
        clickOnElement(LoginBtn);
        return new LoginPage();
    }
}
