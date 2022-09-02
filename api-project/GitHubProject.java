package restAssuredProject ;
import static io.restassured.RestAssured.given;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
public class GitHubProject {
	
	// Declare request specification
    RequestSpecification requestSpec;
    int SSHKey;
    int id;
    @BeforeClass
    public void setUp() {
        // Create request specification
        requestSpec = new RequestSpecBuilder()
                // Set content type
                .setContentType(ContentType.JSON)
                // set Token
                .addHeader("Authorization", "token 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")
                // Set base URL
                .setBaseUri("https://api.github.com")
                // Build request specification
                .build();
    }
	
  @Test(priority=1)
  public void PostSSH () {
	// Create JSON request
      String reqBody = "{\"title\": \"TestAPIKey\", \"key\": \"ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQDcjWgN0T5wu82bgMO7ECBrFKdDIv7XgCOkmiawNrG5pn2yvVs7wwNVBduubgcj23LcRYVuMcWQra1NVZhje4aJPh8w6Yv0e6vvMHgdvKC7Kz/76ytX6AW69QTzoD9AnSPSW09J9+8jOC5CwQnppYTYRZeJGLgRKz8b/anq8r2Q08M6izLjLpBn3oH1kldObLrPGvSi5mPTxLMuGKBtUysg62lgf97PD31GKohg2wRP09xEGTkys6xo093DMTXvUlyEPNUiOzMnuU8Fy9Q79Rc7efC2l31eckiT97bzs4XCQyRDkljDLsP8/HjiMeKjxpO7UxhONjdkuSnL/D+0kO1z\"}";
      Response response =
    		  given().spec(requestSpec) // Set headers
              .body(reqBody) // Add request body
              .when().post("/user/keys"); // Send POST request
         // Print response
              System.out.println(response.getBody().asPrettyString());
            SSHKey = response.then().extract().path("id");
      // Assertion
      response.then().statusCode(201);
  }
  @Test(priority=2)
  public void getSSH () {
	// Create JSON request
      String reqBody = "{\"title\": \"TestAPIKey\", \"key\": \"ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQDcjWgN0T5wu82bgMO7ECBrFKdDIv7XgCOkmiawNrG5pn2yvVs7wwNVBduubgcj23LcRYVuMcWQra1NVZhje4aJPh8w6Yv0e6vvMHgdvKC7Kz/76ytX6AW69QTzoD9AnSPSW09J9+8jOC5CwQnppYTYRZeJGLgRKz8b/anq8r2Q08M6izLjLpBn3oH1kldObLrPGvSi5mPTxLMuGKBtUysg62lgf97PD31GKohg2wRP09xEGTkys6xo093DMTXvUlyEPNUiOzMnuU8Fy9Q79Rc7efC2l31eckiT97bzs4XCQyRDkljDLsP8/HjiMeKjxpO7UxhONjdkuSnL/D+0kO1z\"}";
      Response response =
    		  given().spec(requestSpec) // Set headers
              .body(reqBody) // Add request body
              .when().get("/user/keys"); // Send get request
         // Print response
              System.out.println(response.getBody().asPrettyString());
              response.then().log().body();
      // Assertion
      response.then().statusCode(200);
  }
  @Test(priority=3)
  public void DeleteSSH () {
	// Create JSON request
      String reqBody = "{\"title\": \"TestAPIKey\", \"key\": \"ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQDcjWgN0T5wu82bgMO7ECBrFKdDIv7XgCOkmiawNrG5pn2yvVs7wwNVBduubgcj23LcRYVuMcWQra1NVZhje4aJPh8w6Yv0e6vvMHgdvKC7Kz/76ytX6AW69QTzoD9AnSPSW09J9+8jOC5CwQnppYTYRZeJGLgRKz8b/anq8r2Q08M6izLjLpBn3oH1kldObLrPGvSi5mPTxLMuGKBtUysg62lgf97PD31GKohg2wRP09xEGTkys6xo093DMTXvUlyEPNUiOzMnuU8Fy9Q79Rc7efC2l31eckiT97bzs4XCQyRDkljDLsP8/HjiMeKjxpO7UxhONjdkuSnL/D+0kO1z\"}";
      Response response =
    		  given().spec(requestSpec) // Set headers
    		  .body(reqBody) // Add request body
              .pathParam("id", SSHKey) // Add path parameter
              .when().delete("/user/keys/{id}");; // Send delete request
         // Print response
              System.out.println(response.getBody().asPrettyString());
      // Assertion
      response.then().statusCode(204);
  }
}