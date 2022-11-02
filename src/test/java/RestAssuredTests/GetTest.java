package RestAssuredTests;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetTest {

    List<Object> isbns;
    String username;
    String password = "Password@1234";

    String userID;

    String token;

    public String getRandomUserName() {
        return new Faker().name().fullName();
    }


    public void getAllBooks() {
        Response response = RestAssured.get("https://demoqa.com/BookStore/v1/Books");
        ResponseBody body = response.body();
        isbns = body.jsonPath().getList("books.isbn");
        for (Object obj : isbns) {
            System.out.println(obj);
            System.out.println("_________________________________________________________________");
        }
//        String book = body.jsonPath().getString("books[0].isbn");
//        System.out.println("Book 0 isbn number: " + book);
    }

    public void generateToken() {
        Map<String, String> credentialsBody = new HashMap<>();
        credentialsBody.put("userName", username);
        credentialsBody.put("password", password);

        Response response = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(credentialsBody)
                .post("https://demoqa.com/Account/v1/GenerateToken");
        token = response.jsonPath().getString("token");
        response.prettyPrint();
    }

    @Test(priority = 5)
    public void createUser() throws IOException {
        username = getRandomUserName();
        RequestSpecification rs = given();
        String requestBody = new String(Files.readAllBytes(Paths.get("C:\\Users\\Harsha\\myOwnWorkSpace\\ParallelDataDrivenJenkinsCucumberOct\\body.json")));
        rs.accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .body(requestBody.replace("$$", username));
        Response response = rs.post("https://demoqa.com/Account/v1/User");
        response.prettyPrint();
        userID = response.jsonPath().getString("userID");

        Assert.assertEquals(response.jsonPath().getString("username"), username);
        Assert.assertEquals(response.statusCode(), 201);

        getAllBooks();
        generateToken();
    }


    @Test(priority = 9)
    public void assignBooksToTheUser() throws IOException {
        String booksBody = new String(Files.readAllBytes(Paths.get("C:\\Users\\Harsha\\myOwnWorkSpace\\ParallelDataDrivenJenkinsCucumberOct\\Books.json")));
        String body = booksBody.replace("$$", userID).replace("@@", isbns.get(0).toString());

        Response response = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .auth().basic(username, password).auth().oauth2(token)
                .log().all()
                .body(body)
                .when()
                .post("https://demoqa.com/BookStore/v1/Books");

        response.prettyPrint();

        getUserDetails();
    }

    @Test(priority = 10)
    public void deleteBooks() {
        Map<String, String> querParams = new HashMap<>();
        querParams.put("UserId", userID);

        Response response = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .queryParams(querParams)
                .auth().basic(username, password).auth().oauth2(token)
                .delete("https://demoqa.com/BookStore/v1/Books");

        Assert.assertEquals(response.statusCode(), 204);
        response.prettyPrint();

        getUserDetails();
    }


    public void getUserDetails() {
        Map<String, String> querParams = new HashMap<>();
        querParams.put("UserId", userID);
        Response response = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .queryParams(querParams)
                .auth().basic(username, password).auth().oauth2(token)
                .get("https://demoqa.com/BookStore/v1/User/");
        Assert.assertEquals(response.statusCode(), 200);
        response.body().prettyPrint();
    }
}