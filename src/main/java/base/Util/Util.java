package base.Util;

import io.appium.java_client.*;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Util {
    public static AppiumDriver<MobileElement> driver;
    public static DesiredCapabilities caps;
    public int screenHeight;
    public int screenWidth;

    public Util(){
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10L)),this);
        this.screenHeight = this.driver.manage().window().getSize().getHeight();
        this.screenWidth = this.driver.manage().window().getSize().getWidth();
    }
    public static AppiumDriver<MobileElement> getDriver(){
        return driver;
    }

    public static void Android_LaunchApp(){
        try {
            caps = new DesiredCapabilities();
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "ANDROID");
            caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11");
//            caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_3a_API_30_x86");
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Redmi K40");
//            caps.setCapability(MobileCapabilityType.UDID, "emulator-5554");
            caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "60");
            caps.setCapability("appPackage", "co.podfoods.heypod");
//            caps.setCapability(MobileCapabilityType.APP, "co.podfoods.heypod");
            caps.setCapability("appActivity", "co.podfoods.heypod.MainActivity");

            URL url = new URL("http://127.0.0.1:4723/wd/hub");
            driver = new AppiumDriver<MobileElement>(url, caps);
//            driver = new AndroidDriver<MobileElement>(url, caps);
//            driver = new IOSDriver<MobileElement>(url, caps);

        }catch (Exception ecp){
            System.out.println("Cause is: "+ ecp.getCause());
            System.out.println("Message is: "+ ecp.getMessage());
        }
    }

    public static MobileElement waitForElement(MobileElement el) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 30L);
            wait.until(ExpectedConditions.visibilityOf(el));
            return el;
        } catch (TimeoutException var2) {
            throw new AssertionError(var2.getMessage());
        }


    }
    public static void click(By byEl){
        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(byEl)).click();
    }

    public MobileElement clickOnElement(MobileElement element) {
        MobileElement el = waitForElement(element);
        try {
            el.click();
        } catch (StaleElementReferenceException var4) {
            el = waitForElement(element);
            el.click();
        }
        return el;
    }
    public WebElement clickOnElementLocatedBy(By locator) {
        WebElement el = waitForElementToAppear(locator);

        try {
            el.click();
        } catch (StaleElementReferenceException var4) {
            el = waitForElementToAppear(locator);
            el.click();
        }

        return el;
    }
    public static void sendKeys(By byEl, String text){
        waitForElement(byEl);
        driver.findElement(byEl).sendKeys(text);
    }

    public MobileElement sendKeys(MobileElement element, String value) {
        MobileElement el = waitForElement(element);
        try {
            el.clear();
            el.setValue(value);
        } catch (StaleElementReferenceException var5) {
            el = waitForElement(element);
            el.click();
        }
        return el;
    }

    public static void waitForElement(By byEl){
        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(byEl));
    }
    public static void waitForElementNotPresent(By byEl){
        new WebDriverWait(driver, 20).until(ExpectedConditions.invisibilityOfElementLocated(byEl));
    }
    public Util goBack() {
        wait(1000);
        driver.navigate().back();
        return this;
    }
    public static boolean isElementDisplayed(MobileElement element) {
        boolean elementDisplayed = false;

        try {
            if (element.isDisplayed()) {
                elementDisplayed = true;
            }

            return elementDisplayed;
        } catch (Exception var3) {
            return false;
        }
    }
    public static void wait(int millis) {
        long start = (new Date()).getTime();

        try {
            Thread.sleep((long)millis);
        } catch (InterruptedException var6) {
            long end = (new Date()).getTime();

            do {
                end = (new Date()).getTime();
            } while(start + (long)millis > end);
        }
    }
    public static MobileElement waitForElementIsNotVisible(MobileElement el) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 5L);
            wait.until(ExpectedConditions.invisibilityOf(el));
            return el;
        } catch (TimeoutException var2) {
            throw new AssertionError(var2.getMessage());
        }
    }
    public boolean isTextDisplayed(String expectedText) {
        boolean isDisplayed = true;
        boolean isIos = driver instanceof IOSDriver<?>;

        try {
            By by = isIos ? MobileBy.iOSClassChain("**/*[`label CONTAINS[cd] \"" + expectedText + "\"`]") : MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"" + expectedText + "\")");
            waitForElement(by);
        } catch (NoSuchElementException var4) {
            isDisplayed = false;
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        if (!isDisplayed) {
            throw new AssertionError("Element with text: " + expectedText + " is not found on the screen.");
        } else {
            return isDisplayed;
        }
    }

    public boolean isTextNotDisplayed(String expectedText) {
        boolean isNotDisplayed = true;
        boolean isIos = driver instanceof IOSDriver<?>;
        try {
            By by = isIos ? MobileBy.iOSClassChain("**/*[`label CONTAINS[cd] \"" + expectedText + "\"`]") : MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"" + expectedText + "\")");
            waitForElementNotPresent(by);
        } catch (NoSuchElementException var4) {
            isNotDisplayed = false;
        } catch (Exception var5) {
            var5.printStackTrace();
        }
        if (!isNotDisplayed) {
            throw new AssertionError("Element with text: " + expectedText + " is found on the screen.");
        } else {
            return isNotDisplayed;
        }
    }
    public String getTextElement(MobileElement element) {
        String fieldValue;
        if (caps.getPlatform().toString().equals("ANDROID")) {
            fieldValue = element.getText();
        } else {
            fieldValue = element.getAttribute("value");
        }

        return fieldValue;
    }
    public void actionDoneFromKeyboard(){
        Map<String,String> map = new HashMap(); map.put("action", "done");
        driver.executeScript("mobile:performEditorAction", map);
    }
    public static WebElement waitForElementToAppear(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 45L);
            return (WebElement)wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (TimeoutException var2) {
            throw new AssertionError(var2.getMessage());
        }
    }
    public void swipeHorizontal(double startPercentage, double finalPercentage, double anchorPercentage, int duration) throws Exception {
        Dimension size = this.driver.manage().window().getSize();
        int anchor = (int)((double)size.height * anchorPercentage);
        int startPoint = (int)((double)size.width * startPercentage);
        int endPoint = (int)((double)size.width * finalPercentage);
        (new TouchAction(this.driver)).press(PointOption.point(startPoint, anchor)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds((long)duration))).moveTo(PointOption.point(endPoint, anchor)).release().perform();
    }
    public static void waitForElementToDisAppear(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 15L);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
}
