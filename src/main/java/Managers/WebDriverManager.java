package Managers;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.ElementScrollBehavior;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class WebDriverManager {

    public static  WebDriverManager instance=null;
    public static  WebDriver driver;
    private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
    private static final String IE_DRIVER_PROPERTY = "webdriver.ie.driver";
    private static final String chromeDriverPath = System.getProperty("user.dir")+"\\drivers\\ChromeDriver.exe";
    private static final String IEDriverPath = System.getProperty("user.dir")+"\\drivers\\IEDriverServer.exe";
    public static Properties properties;

    public static WebDriverManager getInstance(){
        if(instance==null){
            instance = new WebDriverManager();
        }
        return instance;
    }

    public static WebDriver getDriver(String driverType) throws Exception{
        if(driver == null) {
            driver = createDriver(driverType);
        }
        return driver;
    }

    @SuppressWarnings("deprecation")
    public static WebDriver createDriver(String driverType) throws Exception{
        switch (driverType) {
            case "FIREFOX" : driver = new FirefoxDriver();
                break;
            case "CHROME" :
                ChromeOptions option = new ChromeOptions();
                option.addArguments("--disable-notifications");
                option.addArguments("disable-infobars");
                option.addArguments("start-maximized");
                option.addArguments("--incognito");
                option.addArguments("--test-type");
                DesiredCapabilities chrome = DesiredCapabilities.chrome();
                chrome.setJavascriptEnabled(true);
                chrome.setCapability(ChromeOptions.CAPABILITY, option);
                System.setProperty(CHROME_DRIVER_PROPERTY, chromeDriverPath);
                driver = new ChromeDriver(option);
                break;
            case "IE" :
                DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
                capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                capabilities.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");
                capabilities.setCapability(InternetExplorerDriver.ELEMENT_SCROLL_BEHAVIOR, ElementScrollBehavior.BOTTOM);
                capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
                capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
                capabilities.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR, UnexpectedAlertBehaviour.IGNORE);
                capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS,true);
                capabilities.setCapability("ignoreProtectedModeSettings", true);
                capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING,true);
                capabilities.setJavascriptEnabled(true);
                System.setProperty(IE_DRIVER_PROPERTY, IEDriverPath);
                driver = new InternetExplorerDriver(capabilities);
                break;
        }
        properties = ConfigFileReader.readConfigFileContents();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Long.parseLong(properties.getProperty("ImplictWait")), TimeUnit.SECONDS);
        return driver;
    }

    public static void closeDriver() throws Exception{
        try{
            driver.close();
            driver.quit();
            driver = null;
        }catch(Exception e) {

        }
    }
}
