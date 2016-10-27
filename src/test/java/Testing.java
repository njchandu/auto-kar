import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * Created by chandanjavaregowda on 21/10/16.
 */
public class Testing {
    WebDriver driver;

    @BeforeTest
    public void setUp() throws Exception {
        // set up appium against a local application
        File appDir = new File(System.getProperty("user.dir"), "/data/app/com.karhoo.app-1/base.apk");

        File app = new File(appDir, "TestApp.app");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "0926d678021fcf62");
        capabilities.setCapability("platformVersion", "6.0.1");
        capabilities.setCapability("appPackage", "com.karhoo.app");
        capabilities.setCapability("appActivity", "com.karhoo.app.presentation.splash.SplashActivity");

        //tell Appium where the location of the app is
        capabilities.setCapability("app", app.getAbsolutePath());

        //create a RemoteWebDriver, the default port for Appium is 4723
        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.MINUTES);
    }

    @Test
    public void example() throws Exception {
//        Thread.sleep(300000);
        System.out.println("test");
        driver.findElement(By.id("android:id/button1")).click();
    }
}
