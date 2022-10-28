package api.petstore;

import com.jayway.restassured.RestAssured;
import org.testng.annotations.BeforeTest;

public abstract class BaseRequest {

    @BeforeTest
    public void setup(){
        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "/v2";
    }
}
