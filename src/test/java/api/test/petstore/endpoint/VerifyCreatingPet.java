package api.test.petstore.endpoint;

import api.petstore.BaseRequest;
import api.petstore.CommonResponseVerification;
import api.petstore.helpers.Endpoints;
import api.petstore.helpers.PathSegments;
import api.petstore.helpers.StatusCode;
import api.petstore.models.PetResponse;
import com.jayway.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static com.jayway.restassured.RestAssured.given;

public class VerifyCreatingPet extends BaseRequest {
    private final Endpoints endpoint = new Endpoints();
    private PetResponse petResponse = new PetResponse();

    private final ArrayList<String> PhotoUrls = new ArrayList<>(){{
        add("fakePhotoUrl");
    }};

    HashMap payload = new HashMap<>(){{
        put("name","e2eTestPet");
        put("photoUrls", PhotoUrls);
    }};

    private Response postResponse, getResponse;

    @BeforeClass
    public void postPet(){
        postResponse = given()
                .contentType("application/json")
                .body(payload)
                .when()
                .post(endpoint.getPetEndpoint())
                .then()
                .extract().response();

        petResponse = postResponse.getBody().as(PetResponse.class);

        getResponse = given()
                .when()
                .get(endpoint.getPetEndpoint()+ PathSegments.pathWithEntityID(String.valueOf(petResponse.getId())));

    }

    @Test
    public void verifyPetWasAdded(){
        CommonResponseVerification.verifyStatusCode(postResponse, StatusCode.OK);
        petResponse.verify(getResponse, petResponse);
    }
}
