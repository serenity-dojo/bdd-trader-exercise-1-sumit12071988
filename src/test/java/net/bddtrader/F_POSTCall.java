package net.bddtrader;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static net.serenitybdd.rest.RestRequests.given;
import static org.hamcrest.Matchers.*;

public class F_POSTCall {

    @Before
    public void setupBaseURL(){
        RestAssured.baseURI="https://bddtrader.herokuapp.com/";
    }

    @Test
    public void each_new_client_should_get_a_unique_id(){
        String jsonBody="{\n" +
                            "    \"email\": \"scott_atkins@gmail.com\",\n" +
                            "    \"firstName\": \"Scott\",\n" +
                            "    \"lastName\": \"Atkins\"\n" +
                            "}";

        given()
                .basePath("/api/client")
                .contentType(ContentType.JSON)
                .body(jsonBody)
        .when()
                .post()
        .then()
                .log().all()
                .statusCode(200)
                .body("id",not(equalTo(0)))
                .body("email",equalTo("scott_atkins@gmail.com"))
                .body("firstName",equalTo("Scott"))
                .body("lastName",equalTo("Atkins"));
    }
}
