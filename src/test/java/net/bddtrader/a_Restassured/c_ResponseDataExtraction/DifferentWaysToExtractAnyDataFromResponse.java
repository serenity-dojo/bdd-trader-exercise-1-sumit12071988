package net.bddtrader.a_Restassured.c_ResponseDataExtraction;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.bddtrader.pojo.asClass.ConventionalWay.A_POJOWithoutConstructor;
import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class DifferentWaysToExtractAnyDataFromResponse {

    String jsonBody;

    @Before
    public void setupBaseURL() throws JsonProcessingException {
        RestAssured.baseURI="https://bddtrader.herokuapp.com";
        A_POJOWithoutConstructor pojoObj = setPOJOData("Pam","Beasley","pam.beasley@gmail.com");
        jsonBody = convertPOJOToJSON(pojoObj);
    }

    @Test
    public void fetchDataFromResponse_Way1() {
        String id = given()
                            .basePath("api/client")
                            .contentType(ContentType.JSON)
                            .body(jsonBody)
                    .when()
                            .log().all()
                            .post()
                            .jsonPath().getString("id");    // .jsonPath().getString() only works straight after
                                                                 //     get(), post(), put() and delete() calls

        System.out.println("**** id fetched from JSON is : "+id);
    }

    @Test
    public void fetchDataFromResponse_Way2() {
        String responseId = given()
                                    .basePath("api/client")
                                    .contentType(ContentType.JSON)
                                    .body(jsonBody)
                            .when()
                                    .log().all()
                                    .post()
                            .then()
                                    .log().body()
                                    .extract().jsonPath().getString("id");

        System.out.println("**** id fetched from JSON is : "+responseId);
    }

    @Test
    public void fetchDataFromResponse_Way3() {
        int responseId = given()
                                .basePath("api/client")
                                .contentType(ContentType.JSON)
                                .body(jsonBody)
                        .when()
                                .post()
                        .then()
                                .log().all()
                                .extract().path("id");

        System.out.println("**** id fetched from JSON is : "+responseId);
    }

    @Test
    public void fetchDataFromResponse_Way4() {
        Response response = given()
                                    .basePath("api/client")
                                    .contentType(ContentType.JSON)
                                    .body(jsonBody)
                             .when()
                                    .log().all()
                                    .post()
                             .then()
                                    .log().body()
                                    .extract().response();

        int responseId = response.path("id");

        System.out.println("**** id fetched from JSON is : "+responseId);
    }



    @Test
    public void fetchDataFromResponse_Way5() {
        String response = given()
                                .basePath("api/client")
                                .contentType(ContentType.JSON)
                                .body(jsonBody)
                          .when()
                                .log().all()
                                .post()
                          .then()
                                .log().body()
                                .extract().response().asString();

        JsonPath jsonPath = new JsonPath(response);
        int responseId = jsonPath.getInt("id");

        System.out.println("**** id fetched from JSON is : "+responseId);
    }





    // ------------- SUPPORTING SUB METHODS ----------------------------------------------------------------------------

    /**
     * Sub Method
     * @param APojoWithoutConstructorObj Object of POJO Class
     * @return String which represents JSON Payload
     */
    private static String convertPOJOToJSON(A_POJOWithoutConstructor APojoWithoutConstructorObj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(APojoWithoutConstructorObj);
        System.out.println("**** JSON Body ******"+ '\n' + jsonBody);
        return jsonBody;
    }

    /**
     * Sub Method
     * @param firstName attribute1
     * @param lastName attribute2
     * @param email attribute3
     * @return Pojo class object
     */
    @NotNull
    private static A_POJOWithoutConstructor setPOJOData(String firstName, String lastName, String email) {
        A_POJOWithoutConstructor APojoWithoutConstructorObj = new A_POJOWithoutConstructor();
        APojoWithoutConstructorObj.setFirstName(firstName);
        APojoWithoutConstructorObj.setLastName(lastName);
        APojoWithoutConstructorObj.setEmail(email);
        return APojoWithoutConstructorObj;
    }
}
