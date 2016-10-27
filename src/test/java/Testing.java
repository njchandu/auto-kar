import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Step;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * Created by chandanjavaregowda on 21/10/16.
 */
@Listeners({TestListener.class})
public class Testing {
    public static WebDriver driver;

    @BeforeTest
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "0926d678021fcf62");
        capabilities.setCapability("platformVersion", "6.0.1");
        capabilities.setCapability("newCommandTimeout", "600");

        capabilities.setCapability("appPackage", "com.karhoo.app");
        capabilities.setCapability("appActivity", "com.karhoo.app.presentation.splash.SplashActivity");

        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void example() throws Exception {

        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        List<Double> priceList = new ArrayList<Double>();
        List<Double> etaList = new ArrayList<Double>();
        if (true) {
            driver.findElement(By.id("android:id/button1")).click();
        }
        if (false) {
            driver.findElement(By.id("com.karhoo.app:id/on_boarding_next")).click();
            driver.findElement(By.id("com.karhoo.app:id/on_boarding_next")).click();
            driver.findElement(By.id("com.karhoo.app:id/on_boarding_next")).click();
        }
        Thread.sleep(2000);
        selectPickup("Big Ben London ");
        Thread.sleep(5000);
        selectDropPoint("Southwark Station London ");
        Thread.sleep(15000);

//        List<WebElement> list = driver.findElements(By.id("com.karhoo.app:id/tv_provider"));
        List<WebElement> list1 = driver.findElements(By.id("com.karhoo.app:id/tv_fare"));
        for (WebElement el : list1) {
            priceList.add(Double.valueOf(el.getText().replace("£", "")));
        }
        isSorted(priceList);

        gotoEta();
        List<WebElement> list2 = driver.findElements(By.id("com.karhoo.app:id/tv_eta"));
        for (WebElement el : list2) {
            etaList.add(Double.valueOf(el.getText().replace(" mins", "")));
        }
        isSorted(etaList);
    }

    @Step("Go to ETA view.")
    private void gotoEta() {
        System.out.println("Go to ETA view.");
        driver.findElement(By.id("com.karhoo.app:id/eta_button")).click();
    }

    @Step("Verify if the list is sorted by price.")
    private boolean isSorted(List<Double> list) {
        System.out.println("Verify if the list is sorted by price.");
        System.out.println(list.toString());
        List tmp = new ArrayList(list);
        Collections.sort(tmp);

        boolean sorted = tmp.equals(list);
        return sorted;
    }

    @Step("Choose pickup point to {0}.")
    public void selectPickup(String pickupPoint) throws InterruptedException {
        System.out.println("Choose pickup point to " + pickupPoint);
        try {
            driver.findElement(By.id("com.karhoo.app:id/pickup_frame_container")).click();
        } catch (NoSuchElementException nse) {
            driver.findElement(By.id("co.karhoo.app:id/subtitle_pickup")).click();
        }
        driver.findElement(By.id("com.karhoo.app:id/search_container")).click();
        driver.findElement(By.id("com.karhoo.app:id/search_container")).sendKeys(pickupPoint);
        Thread.sleep(4000);
        driver.findElement(By.id("com.karhoo.app:id/tv_place_name")).click();
    }

    @Step("Choose drop point to {0}.")
    public void selectDropPoint(String dropPoint) throws InterruptedException {
        System.out.println("Choose pickup point to " + dropPoint);
        driver.findElement(By.id("com.karhoo.app:id/tv_dropoff")).click();
        driver.findElement(By.id("com.karhoo.app:id/search_container")).click();
        driver.findElement(By.id("com.karhoo.app:id/search_container")).sendKeys(dropPoint);
        Thread.sleep(4000);
        driver.findElement(By.id("com.karhoo.app:id/tv_place_name")).click();
    }
}
