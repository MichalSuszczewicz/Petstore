package api.test.petstore.endpoint;

import api.petstore.BaseRequest;
import api.petstore.CommonResponseVerification;
import api.petstore.helpers.Endpoints;
import api.petstore.helpers.PathSegments;
import api.petstore.helpers.StatusCode;
import api.petstore.models.PetResponse;
import com.jayway.restassured.response.Response;
import org.json.simple.JSONArray;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;

public class VerifyGetPet extends BaseRequest {

    private final Endpoints endpoint = new Endpoints();
    private final PetResponse petResponse = new PetResponse();
    private final Map<String, Object> expectedCategory = new LinkedHashMap<>(){{
        put("id",10);
        put("name", "string");
    }};
    private final ArrayList<String> expectedPhotoUrls = new ArrayList<>(){{
        add("string");
    }};
    private final JSONArray expectedTags = new JSONArray(){{
        add(expectedCategory);
    }};
    private final String existingPetId = "10";
    private final String fakePetId = "1";
    private final String invalidPetId = "invalidPetId";
    private final PetResponse expectedResponse = PetResponse.builder()
            .id(Integer.parseInt(existingPetId))
            .category(expectedCategory)
            .name("doggie")
            .photoUrls(expectedPhotoUrls)
            .tags(expectedTags)
            .status("string")
            .build();
    private Response response, responseNotFound, responseInvalid;

    @BeforeClass
    public void getPet(){
        response = given().when().get(endpoint.getPetEndpoint()+ PathSegments.pathWithEntityID(existingPetId));
        responseNotFound = given().when().get(endpoint.getPetEndpoint()+ PathSegments.pathWithEntityID(fakePetId));
        responseInvalid= given().when().get(endpoint.getPetEndpoint()+ PathSegments.pathWithEntityID(invalidPetId));
    }

    @Test
    public void validateCorrectResponse(){
        CommonResponseVerification.verifyStatusCode(response, StatusCode.OK);
        petResponse.verify(response, expectedResponse);
    }
    @Test
    public void validateNotFoundResponse(){
        CommonResponseVerification.verifyStatusCode(responseNotFound, StatusCode.NOT_FOUND);
    }
    @Test
    public void validateInvalidResponse(){
        CommonResponseVerification.verifyStatusCode(responseInvalid, StatusCode.BAD_REQUEST);
    }
}
