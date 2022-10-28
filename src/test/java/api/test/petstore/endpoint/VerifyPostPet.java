package api.test.petstore.endpoint;

import api.petstore.BaseRequest;
import api.petstore.CommonResponseVerification;
import api.petstore.helpers.Endpoints;
import api.petstore.helpers.StatusCode;
import com.jayway.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static com.jayway.restassured.RestAssured.given;

public class VerifyPostPet extends BaseRequest {

    private final Endpoints endpoint = new Endpoints();

    private final ArrayList<String> PhotoUrls = new ArrayList<>(){{
        add("fakePhotoUrl");
    }};

    HashMap validPayload = new HashMap<>(){{
        put("name","AndAliTest");
        put("photoUrls", PhotoUrls);
    }};

    private Response validResponse, invalidResponse;

    @BeforeClass
    public void postPet(){
        validResponse = given()
                .contentType("application/json")
                .body(validPayload)
                .when()
                .post(endpoint.getPetEndpoint());

        invalidResponse = given()
                .contentType("application/json")
                .body("")
                .when()
                .post(endpoint.getPetEndpoint());
    }

    @Test
    public void verifyCorrectPostStatusCode(){
        CommonResponseVerification.verifyStatusCode(validResponse, StatusCode.OK);
    }

    @Test
    public void verifyIncorrectPostStatusCode(){
        CommonResponseVerification.verifyStatusCode(invalidResponse, StatusCode.NOT_ALLOWED);
    }
}
