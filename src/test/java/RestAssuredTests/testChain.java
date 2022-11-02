package RestAssuredTests;

import org.testng.annotations.Test;

public class testChain {

    @Test
    public void apple() {
        System.out.println("apple");
    }
    @Test(dependsOnMethods = {"apple"})
    public void orange() {
        System.out.println("orange");
    }

    @Test(dependsOnMethods = {"orange","apple"})
    public void mango() {
        System.out.println("mango");
    }
}