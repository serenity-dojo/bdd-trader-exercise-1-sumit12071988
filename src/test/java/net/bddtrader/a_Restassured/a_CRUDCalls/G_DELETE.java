package net.bddtrader.a_Restassured.a_CRUDCalls;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.bddtrader.pojo.asClass.A_POJOWithoutConstructor;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class G_DELETE {
    @Before
    public void setupBaseURL(){
        RestAssured.baseURI="https://bddtrader.herokuapp.com";
    }

    @Test
    public void deleteAResource() throws JsonProcessingException {
        // Given a client exists
        A_POJOWithoutConstructor APojoWithoutConstructorObj = new A_POJOWithoutConstructor();
        APojoWithoutConstructorObj.setFirstName("Pam");
        APojoWithoutConstructorObj.setLastName("Beasley");
        APojoWithoutConstructorObj.setEmail("pam.beasley@gmail.com");

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(APojoWithoutConstructorObj);
        System.out.println("**** JSON Body: "+ jsonBody);

        String id = given()
                            .basePath("api/client")
                            .contentType(ContentType.JSON)
                            .body(jsonBody)
                    .when()
                            .log().all()
                            .post()
                            .jsonPath().getString("id");

        // When I delete the client
        given()
                .basePath("api/client/{clientId}")
                .pathParam("clientId",id)
        .when()
                .log().all()
                .delete()
        .then()
                .log().all();


        // Then the client should no longer exist
        given()
                .basePath("api/client/{clientId}")
                .pathParam("clientId",id)
        .when()
                .log().all()
                .get()
        .then()
                .log().body()
                .statusCode(404);


    }
}
