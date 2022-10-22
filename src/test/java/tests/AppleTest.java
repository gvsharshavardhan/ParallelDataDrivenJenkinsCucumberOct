package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static util.DriverManager.getDriver;

public class AppleTest extends BaseTest {

    @Test
    public void test2() {
        getDriver().get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        getDriver().findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");
        getDriver().findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");
        getDriver().findElement(By.xpath("//button[contains(@class,'orangehrm-login-button')]")).click();
    }
}
