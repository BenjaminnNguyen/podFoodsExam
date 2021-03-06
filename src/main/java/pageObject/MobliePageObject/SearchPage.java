package pageObject.MobliePageObject;

import base.MobileUtil.MobileUtil;
import io.appium.java_client.MobileBy;

public class SearchPage extends MobileUtil {

    String product = "new UiSelector().descriptionContains(\"PRODUCT_LIST_\").childSelector(new UiSelector().text(\"%s\"))";
    public SearchPage isOnSearchPage(){
        return this;
    }
    public ProductPage clickToProduct(String name){
        clickOnElementLocatedBy(MobileBy.AndroidUIAutomator(String.format(product,name)));
        return new ProductPage();
    }
}
