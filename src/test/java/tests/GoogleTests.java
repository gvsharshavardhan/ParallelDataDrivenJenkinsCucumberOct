package tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class GoogleTests {

    @Test(testName = "ABC Test", priority = 4, invocationCount = 3)
    @Parameters({"browser", "url"})
    public void aTest001(String browser, String url) {
        System.out.println(browser);
        System.out.println(url);
        System.out.println("tc1");
    }

    @Test(testName = "XYZ Test", priority = 3)
    public void cTest002() {
        System.out.println("tc2");
    }

    @Test(priority = 0, timeOut = 2000L)
    public void eTest003() {
        System.out.println("tc3");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(priority = 45)
    public void dTest004() {
        System.out.println("tc4");
    }

    @Test(priority = -57, expectedExceptions = {ArithmeticException.class}, dependsOnMethods = "dTest004")
    public void bTest005() {
        System.out.println("tc5");
        int a = 20;
        int b = 0;
        System.out.println(a / b);
    }

    @Test
    public void testAssertions() {

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals("abc", "abc");
//        softAssert.assertEquals("xyz", "abc");
//        softAssert.assertEquals("lmn", "opq");

        softAssert.assertAll();
    }

    @Test(dataProvider = "dataPump", dataProviderClass = DataSupplier.class)
    public void login(Object[] credentials) {

        System.out.println("username: " + credentials[0]);
        System.out.println("password: " + credentials[1]);

    }


}
