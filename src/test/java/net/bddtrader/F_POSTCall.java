package net.bddtrader;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.bddtrader.clients.Client;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static net.serenitybdd.rest.RestRequests.given;
import static org.hamcrest.Matchers.*;

public class F_POSTCall {

    @Before
    public void setupBaseURL(){
        RestAssured.baseURI="https://bddtrader.herokuapp.com/";
    }

    @Test
    public void post_with_JSONBody_as_String(){
        String jsonBody= """
                            {
                                "email": "scott_atkins@gmail.com",
                                "firstName": "Scott",
                                "lastName": "Atkins"
                            }
                        """;

        given()
                .basePath("/api/client")
                .contentType(ContentType.JSON)
                .accept(ContentType.ANY)
                .body(jsonBody)
        .when()
                .log().all()
                .post()
        .then()
                .statusCode(200)
                .body("id",not(equalTo(0)))
                .body("email",equalTo("scott_atkins@gmail.com"))
                .body("firstName",equalTo("Scott"))
                .body("lastName",equalTo("Atkins"));
    }

    @Test
    public void post_with_JSONBody_as_File(){
        File fileObject = new File("src/test/resources/client.json");

        given()
                .basePath("/api/client")
                .contentType(ContentType.JSON)
                .accept(ContentType.ANY)
                .body(fileObject)
        .when()
                .log().all()
                .post()
        .then()
                .log().all()
                .statusCode(200)
                .body("id",not(equalTo(0)))
                .body("email",equalTo("scott_atkins@gmail.com"))
                .body("firstName",equalTo("Scott"))
                .body("lastName",equalTo("Atkins"));
    }

@Test
    public void post_with_JSONBody_as_POJO(){

    Client jsonClassObject = Client.withFirstName("Sumit")
                                .andLastName("Saha")
                                .andEmail("sumit.saha@gmail.com");

    given()
            .basePath("/api/client")
            .contentType(ContentType.JSON)
            .accept(ContentType.ANY)
            .body(jsonClassObject)
    .when()
            .post()
    .then()
            .log().all()
            .statusCode(200)
            .body("id",not(equalTo(0)))
            .body("email",equalTo("sumit.saha@gmail.com"))
            .body("firstName",equalTo("Sumit"))
            .body("lastName",equalTo("Saha"));
    }

    @Test
    public void post_with_JSONBody_as_Map(){
        Map<String,Object> jsonMapObject = new HashMap<>();
        jsonMapObject.put("firstName","Sumit");
        jsonMapObject.put("lastName","Saha");
        jsonMapObject.put("email","sumit.saha@gmail.com");
        jsonMapObject.put("address","Texas");   // Creating a Key which is not required in JSON Body using maps will not affect the outcome
                                                //  This KEY in this case will be ignored and only required keys will be passed by Rest Assured

        given()
                .basePath("/api/client")
                .contentType(ContentType.JSON)
                .accept(ContentType.ANY)
                .body(jsonMapObject)
        .when()
                .log().all()
                .post()
        .then()
                .statusCode(200)
                .body("id",not(equalTo(0)))
                .body("email",equalTo("sumit.saha@gmail.com"))
                .body("firstName",equalTo("Sumit"))
                .body("lastName",equalTo("Saha"));
    }

}
