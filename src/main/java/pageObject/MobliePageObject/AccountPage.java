package pageObject.MobliePageObject;

import base.MobileUtil.MobileUtil;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AccountPage extends MobileUtil {
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
