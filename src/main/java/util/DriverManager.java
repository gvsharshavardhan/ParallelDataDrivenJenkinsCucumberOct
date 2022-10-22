package util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {

    private static ThreadLocal<WebDriver> tl = new ThreadLocal<>();

    public static void init() {
        WebDriverManager.chromedriver().setup();
        tl.set(new ChromeDriver());
    }

    public static WebDriver getDriver() {
        return tl.get();
    }

}