package tests;

import org.testng.annotations.DataProvider;

public class DataSupplier {

    //1. object[3][2] -> 1 dim: exe count, 2 dim: no of parameters

    /**
     * v1, v2,v3,v4
     * v10,v20,v30,v40
     */
    //2. Iterator<object[]>
    @DataProvider
    public Object[][] dataPump() {
        return new Object[][]{
                {"abc", "pass1"},
                {"xyz", "pass2"},
                {"lmn", "pass3"}
        };
    }

}