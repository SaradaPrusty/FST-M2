package Activities;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import static org.hamcrest.CoreMatchers.equalTo;

public class Activity2 {
    // Set base URL
    final static String ROOT_URI = "https://petstore.swagger.io/v2/user";

    @Test(priority=1)
    public void addNewUserFromFile() throws IOException {
        // Import JSON file
        FileInputStream inputJSON = new FileInputStream("src/Activities/userInfo.json");
        // Read JSON file as String
        String reqBody = new String(inputJSON.readAllBytes());

        Response response = 
            given().contentType(ContentType.JSON) // Set headers
            .body(reqBody) // Pass request body from file
            .when().post(ROOT_URI); // Send POST request

        inputJSON.close();

        // Assertion
        response.then().body("code", equalTo(200));
        response.then().body("message", equalTo("5411"));
    }
    
    @Test(priority=2)
    public void getUserInfo() {
        // Import JSON file to write to
        File outputJSON = new File("src/Activities/userGETResponse.json");

        Response response = 
            given().contentType(ContentType.JSON) // Set headers
            .pathParam("username", "Sachis") // Pass request body from file
            .when().get(ROOT_URI + "/{username}"); // Send POST request
        
        // Get response body
        String resBody = response.getBody().asPrettyString();

        try {
            // Create JSON file
            outputJSON.createNewFile();
            // Write response body to external file
            FileWriter writer = new FileWriter(outputJSON.getPath());
            writer.write(resBody);
            writer.close();
        } catch (IOException excp) {
            excp.printStackTrace();
        }
        
        // Assertion
        response.then().body("id", equalTo(5411));
        response.then().body("username", equalTo("Sachis"));
        response.then().body("firstName", equalTo("Sachi"));
        response.then().body("lastName", equalTo("Tripathy"));
        response.then().body("email", equalTo("sachisma1@mail.com"));
        response.then().body("password", equalTo("password123"));
        response.then().body("phone", equalTo("9888812345"));
    }
    
    @Test(priority=3)
    public void deleteUser() throws IOException {
        Response response = 
            given().contentType(ContentType.JSON) // Set headers
            .pathParam("username", "Sachis") // Add path parameter
            .when().delete(ROOT_URI + "/{username}"); // Send POST request

        // Assertion
        response.then().body("code", equalTo(200));
        response.then().body("message", equalTo("Sachis"));
    }
}
