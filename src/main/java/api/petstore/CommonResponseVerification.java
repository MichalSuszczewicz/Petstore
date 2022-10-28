package api.petstore;

import api.petstore.helpers.StatusCode;
import org.testng.Assert;
import com.jayway.restassured.response.Response;

public class CommonResponseVerification {

    /**
     * Verify that response status code is the same as expected
     *
     * @param response   response received
     * @param statusCode expected status code (e.g. 200, 400)
     */
    public static void verifyStatusCode(Response response, StatusCode statusCode) {
        Assert.assertEquals(response.getStatusCode(), Integer.parseInt(statusCode.getStatusCode()), "Actual status code is not correct");
    }
}
