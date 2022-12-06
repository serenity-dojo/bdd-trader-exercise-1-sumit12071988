package net.bddtrader;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.bddtrader.pojo.POJOClass;
import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class H_PUTCall {
    @Before
    public void setupBaseURL(){
        RestAssured.baseURI="https://bddtrader.herokuapp.com";
    }

    @Test
    public void updateAResourceUsingPOJO() throws JsonProcessingException {
        // Create a resource
        // Given a client exists
        POJOClass pojoClassObj = setPOJOData("Pam","Beasley","pam.beasley@gmail.com");
        String jsonBody = getJSONPayloadFromPOJO(pojoClassObj);

        String id = given()
                .basePath("api/client")
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .when()
                .log().all()
                .post()
                .jsonPath().getString("id");

        System.out.println("**** id is: "+id);

        // Updating resource attributes
        pojoClassObj = setPOJOData("Pam","Beasley","pam@gmail.com");
        jsonBody = getJSONPayloadFromPOJO(pojoClassObj);

        given()
                .basePath("api/client/{clientId}")
                .pathParam("clientId",id)
                .contentType(ContentType.JSON)
                .body(jsonBody)
        .when()
                .log().all()
                .put()
        .then()
                .statusCode(200);

        // Verify the resource using ID
        given()
                .basePath("api/client/{clientId}")
                .pathParam("clientId",id)
        .when()
                .log().all()
                .get()
        .then()
                .body("email", equalTo("pam@gmail.com"));
    }

    /**
     * Advantage of using MAP using PUT :
     *      Just pass that K-V pair which needs to be updated.
     *      No need to pass data for other K-V pairs as we do with POJO classes
     */
    @Test
    public void updateAResourceUsingMap() throws JsonProcessingException {
        // Create a resource
        // Given a client exists
        POJOClass pojoClassObj = setPOJOData("Pam", "Beasley", "pam.beasley@gmail.com");
        String jsonBody = getJSONPayloadFromPOJO(pojoClassObj);

        String id = given()
                .basePath("api/client")
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .when()
                .log().all()
                .post()
                .jsonPath().getString("id");

        // Updating resource attributes
        Map<String, Object> jsonMapObj = new HashMap<>();
        jsonMapObj.put("email","pam@gmail.com");

        given()
                .basePath("api/client/{clientId}")
                .pathParam("clientId",id)
                .contentType(ContentType.JSON)
                .body(jsonMapObj)
        .when()
                .log().all()
                .put()
        .then()
                .log().body()
                .statusCode(200);

        // Verify the resource using ID
        given()
                .basePath("api/client/{clientId}")
                .pathParam("clientId",id)
                .when()
                .log().all()
                .get()
                .then()
                .log().body()
                .body("email", equalTo("pam@gmail.com"));
    }

    private static String getJSONPayloadFromPOJO(POJOClass pojoClassObj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(pojoClassObj);
        System.out.println("**** JSON Body: "+ jsonBody);
        return jsonBody;
    }
    @NotNull
    private static POJOClass setPOJOData(String firstName, String lastName, String email) {
        POJOClass pojoClassObj = new POJOClass();
        pojoClassObj.setFirstName(firstName);
        pojoClassObj.setLastName(lastName);
        pojoClassObj.setEmail(email);
        return pojoClassObj;
    }
}
