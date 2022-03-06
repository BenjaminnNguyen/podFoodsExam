package base.WebUtil;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class AbstractWebTest {
    private static final String workingDir = System.getProperty("user.dir");
    private static ThreadLocal<WebDriver> driver = new ThreadLocal();

    public static WebDriver getDriver() {
        return (WebDriver)driver.get();
    }

    private static void setDriver(WebDriver webDriver) {
        driver.set(webDriver);
    }

    public static void openMultiBrowser(String browserName) {
        WebDriver tmpDriver = null;
        (new File(System.getProperty("user.dir") + "/downloadFile")).mkdirs();
        System.setProperty("Web.downloadPath", (new File(System.getProperty("user.dir") + "/downloadFile")).getPath());
        if (browserName.equalsIgnoreCase("hfirefox")) {
            WebDriverManager.firefoxdriver().setup();
            System.setProperty("webdriver.firefox.marionette", "true");
            System.setProperty("webdriver.firefox.logfile", workingDir + "\\FirefoxLog.txt");
            tmpDriver = new FirefoxDriver();
        } else {
            ChromeOptions option;
            HashMap chromePrefs;
            if (browserName.equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
                option = new ChromeOptions();
                option.addArguments(new String[]{"--incognito"});
                option.addArguments(new String[]{"--disable-extensions"});
                option.addArguments(new String[]{"--disable-infobars"});
                chromePrefs = new HashMap();
                chromePrefs.put("download.default_directory", System.getProperty("Web.downloadPath"));
                option.setExperimentalOption("prefs", chromePrefs);
                tmpDriver = new ChromeDriver(option);
            } else if (browserName.equalsIgnoreCase("hchrome")) {
                WebDriverManager.chromedriver().setup();
                option = new ChromeOptions();
                option.addArguments(new String[]{"headless"});
                option.addArguments(new String[]{"--incognito"});
                option.addArguments(new String[]{"--disable-extensions"});
                option.addArguments(new String[]{"--disable-infobars"});
                option.addArguments(new String[]{"window-size=1920,1080"});
                chromePrefs = new HashMap();
                chromePrefs.put("download.default_directory", System.getProperty("Web.downloadPath"));
                option.setExperimentalOption("prefs", chromePrefs);
                tmpDriver = new ChromeDriver(option);
            } else if (browserName.equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                System.setProperty("webdriver.firefox.marionette", "true");
                System.setProperty("webdriver.firefox.logfile", workingDir + "\\FirefoxLog.txt");
                tmpDriver = new FirefoxDriver();
            } else if (browserName.equalsIgnoreCase("safari")) {
                tmpDriver = new SafariDriver();
            }
        }
        setDriver((WebDriver)tmpDriver);
        getDriver().manage().timeouts().implicitlyWait((long)30l, TimeUnit.SECONDS);
        getDriver().manage().window().maximize();
    }

    public static void closeBrowserAndDriver(WebDriver driver) {
        try {
            driver.manage().deleteAllCookies();
            driver.quit();
        } catch (Exception var2) {
            System.out.println(var2.getMessage());
        }

    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(ITestResult result) {
       openMultiBrowser("chrome");
    }
    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) {
        closeBrowserAndDriver(getDriver());
    }

}
