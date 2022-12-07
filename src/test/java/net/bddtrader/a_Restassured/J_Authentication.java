package net.bddtrader.a_Restassured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class J_Authentication {
    @Before
    public void setup(){
        RestAssured.baseURI="https://bddtrader.herokuapp.com";
    }

    @Test
    public void using_basic_auth(){
        Map<String, Object> mapObject = new HashMap<>();
        mapObject.put("email","nobunaga.oda@gmail.com");
        mapObject.put("firstName","Nobunaga");
        mapObject.put("lastName","Oda");

        given()
                .basePath("api/client")
                .auth().basic("user", "password")
                .contentType(ContentType.JSON)
                .body(mapObject)
        .when()
                .log().all()
                .post()
        .then()
                .log().body()
                .statusCode(200)
                .body("id",not(equalTo(0)))
                .body("email",equalTo("nobunaga.oda@gmail.com"))
                .body("firstName",equalTo("Nobunaga"))
                .body("lastName",equalTo("Oda"));
    }

    @Test
    public void using_digest_auth(){
        Map<String, Object> mapObject = new HashMap<>();
        mapObject.put("email","nobunaga.oda@gmail.com");
        mapObject.put("firstName","Nobunaga");
        mapObject.put("lastName","Oda");

        given()
                .basePath("api/client")
                .auth().digest("user", "password")
                .contentType(ContentType.JSON)
                .body(mapObject)
        .when()
                .log().all()
                .post()
        .then()
                .log().body()
                .statusCode(200)
                .body("id",not(equalTo(0)))
                .body("email",equalTo("nobunaga.oda@gmail.com"))
                .body("firstName",equalTo("Nobunaga"))
                .body("lastName",equalTo("Oda"));
    }
}
