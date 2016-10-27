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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
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
//        File appDir = new File(System.getProperty("user.dir"), "/data/app/com.karhoo.app-1/base.apk");
//
//        File app = new File(appDir, "TestApp.app");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "0926d678021fcf62");
        capabilities.setCapability("platformVersion", "6.0.1");
        capabilities.setCapability("newCommandTimeout", "600");

        capabilities.setCapability("appPackage", "com.karhoo.app");
        capabilities.setCapability("appActivity", "com.karhoo.app.presentation.splash.SplashActivity");

        //tell Appium where the location of the app is
//        capabilities.setCapability("app", app.getAbsolutePath());

        //create a RemoteWebDriver, the default port for Appium is 4723
        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.MINUTES);
    }

    @Test
    public void example() throws Exception {

        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        System.out.println("test");
        if (false) {
            driver.findElement(By.id("com.karhoo.app:id/on_boarding_next")).click();
            driver.findElement(By.id("com.karhoo.app:id/on_boarding_next")).click();
            driver.findElement(By.id("com.karhoo.app:id/on_boarding_next")).click();
        }

        driver.findElement(By.id("com.karhoo.app:id/subtitle_pickup")).click();
        driver.findElement(By.id("com.karhoo.app:id/search_container")).click();
        driver.findElement(By.id("com.karhoo.app:id/search_container")).sendKeys("Big Ben London ");
        Thread.sleep(5000);
        driver.findElement(By.id("com.karhoo.app:id/tv_place_name")).click();
        driver.findElement(By.id("com.karhoo.app:id/tv_dropoff")).click();
        driver.findElement(By.id("com.karhoo.app:id/search_container")).click();
        driver.findElement(By.id("com.karhoo.app:id/search_container")).sendKeys("Southwark Station London ");
        Thread.sleep(4000);
        driver.findElement(By.id("com.karhoo.app:id/tv_place_name")).click();
        Thread.sleep(8000);

        List<WebElement> list = driver.findElements(By.id("com.karhoo.app:id/tv_provider"));
        List<WebElement> list1 = driver.findElements(By.id("com.karhoo.app:id/tv_fare"));

        for (int i = 0; i < list.size(); i++) {
            map.put(list.get(i).getText(), list1.get(i).getText());
        }

        System.out.println(map);
    }
}
