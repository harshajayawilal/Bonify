package framework.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by FOX on 11/11/2018.
 */
public class TestFramework {

    private WebDriver webDriver;
    private static TestFramework seleniumUtilsInstance = null;

    String homeUrl = "https://my.bonify.de/login?";

    public TestFramework() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ "\\src\\main\\resources\\chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        seleniumUtilsInstance = this;
        webDriver.manage().window().maximize();
    }

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        webDriver.get(homeUrl);
    }

    @AfterMethod(alwaysRun = true)
    public void close() {
        System.out.println("End of test case");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        webDriver.close();
    }

    public static TestFramework getConnection() {
        if (seleniumUtilsInstance == null) {
            seleniumUtilsInstance = new TestFramework();
        }
        return seleniumUtilsInstance;
    } //Maintains a single web driver instance, disable when running on a distributed CI

    public WebDriver getWebDriver() {
        return webDriver;
    } //Get web driver

    public void sleep (int milliSeconds){
        try {
            Thread.sleep(milliSeconds);
        }
        catch (Exception e){
            System.out.printf(e.getMessage());
        }
    }

    public void failTest(String error) {
        fail(error);
    } //Fail test

}
