package RestAssuredTests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Dummy {
    public static void main(String[] args) throws IOException {

        String str = new String(Files.readAllBytes(Paths.get("C:\\Users\\Harsha\\myOwnWorkSpace\\ParallelDataDrivenJenkinsCucumberOct\\body.json")));
        System.out.println(str);
    }
}
