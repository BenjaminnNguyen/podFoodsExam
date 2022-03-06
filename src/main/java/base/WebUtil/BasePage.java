package base.WebUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class BasePage {
    WebElement element;
    List<WebElement> elements;
    WebDriverWait wait;
    Actions action;
    public WebDriver driver = AbstractWebTest.getDriver();

    public BasePage(){
//        driver = _driver;
        PageFactory.initElements(this.driver, this);
    }

    public void clickToElement(WebElement element) {
        wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }
    public void sendKeyToElement( WebElement element, String text) {
        wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(text);
    }
    public void clear(WebElement element) {
        element.clear();
    }

    public void hoverMouseToElement(WebElement element) {
        this.action = new Actions(this.driver);
        this.action.moveToElement(element).perform();
    }
    public void waitForPageLoaded() {
        ExpectedCondition expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor)driver).executeScript("return document.readyState", new Object[0]).toString().equals("complete");
            }
        };

        try {
            Thread.sleep(1000L);
            WebDriverWait wait = new WebDriverWait(this.driver, 30L);
            wait.until(expectation);
        } catch (Throwable var3) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }
    public WebElement waitForElementVisible(WebElement element) {
        this.wait = new WebDriverWait(this.driver, 30L);

        try {
            return (WebElement)this.wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception var5) {
            return null;
        }
    }
    public String getTextElement(WebElement element) {
        element = this.waitForElementVisible(element);
        return element.getText();
    }
    public String getTextElement(String locator, String... values) {
        locator = String.format(locator, (Object[])values);
        this.element = this.waitForElementVisible(driver.findElement(By.xpath(locator)));
        return this.element.getText();
    }

}
