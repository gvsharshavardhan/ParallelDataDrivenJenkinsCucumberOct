package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import util.DriverManager;

import java.time.Duration;

public class BaseTest {


    @BeforeMethod
    public void setup() {
        DriverManager.init();
        DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterMethod
    public void teardown() {
        DriverManager.getDriver().quit();
    }

}
