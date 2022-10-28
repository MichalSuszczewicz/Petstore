package api.petstore.models;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.response.Response;
import lombok.*;
import org.json.simple.JSONArray;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PetResponse {

    private long id;
    private Map<String,Object> category = new LinkedHashMap<>();
    private String name;
    private ArrayList<String> photoUrls = new ArrayList<>();
    private JSONArray tags = new JSONArray();
    private String status;

    @JsonAnySetter
    void setCategory(String key, Object value){
        category.put(key,value);
    }

    /**
     * Verify that the expected pet is equal to the one
     * from the response
     *
     * @param response                            getting response
     * @param expectedPet all expected pet properties
     */
    @SneakyThrows
    public void verify(Response response, PetResponse expectedPet) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonAsString = response.getBody().asString();
        PetResponse actualPet = mapper.readValue(jsonAsString, PetResponse.class);

        Assert.assertEquals(actualPet, expectedPet);
    }
}
