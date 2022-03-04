package pageObject;

import base.Util.Util;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.testng.Assert;

public class HomePage extends Util {

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Account\")")
    MobileElement accountIcon;
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Login\")")
    MobileElement loginTxt;
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"NEW VERSION AVAILABLE\")")
    MobileElement newVersionPopup;
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"SKIP\")")
    MobileElement skipBtn;
    @AndroidFindBy(accessibility = "EMAIL_INPUT")
    MobileElement emailInput;
    @AndroidFindBy(accessibility = "PASSWORD_INPUT")
    MobileElement passwordInput;
    @AndroidFindBy(accessibility = "SEARCH_TOUCHABLE")
    MobileElement searchTxt;
    @AndroidFindBy(accessibility = "SEARCH_INPUT")
    MobileElement searchInput;
    @AndroidFindBy(uiAutomator = "new UiSelector(). description(\"LOGIN_BUTTON\").childSelector(new UiSelector().className(android.widget.TextView))")
    MobileElement accountName;
    public HomePage isOnHomePage(){
        Assert.assertTrue(isElementDisplayed(waitForElement(searchTxt)));
        return this;
    }
    public HomePage closePopup(){
        if(isElementDisplayed(newVersionPopup)){
            clickOnElement(skipBtn);
        }
        return this;
    }
    public AccountPage Login(){
        clickOnElement(accountIcon);
        return new AccountPage();
    }
    public HomePage verifyLoggedIn(String accountName){
        Assert.assertEquals(accountName, getTextElement(this.accountName).replaceAll("Hi, |,",""));
        return this;
    }
    public SearchPage search(String value){
        clickOnElement(searchTxt);
        sendKeys(searchInput,value);
        actionDoneFromKeyboard();
        return new SearchPage();
    }
}
