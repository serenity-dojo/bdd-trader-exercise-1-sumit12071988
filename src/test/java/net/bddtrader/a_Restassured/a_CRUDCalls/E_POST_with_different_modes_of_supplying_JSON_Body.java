package net.bddtrader.a_Restassured.a_CRUDCalls;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.bddtrader.pojo.asClass.ConventionalWay.A_POJOWithoutConstructor;
import net.bddtrader.pojo.asClass.ConventionalWay.B_POJOWithConstructor;
import net.bddtrader.pojo.asClass.JFWay.Client;
import net.bddtrader.pojo.asClass.ConventionalWay.C_POJOUsingMethodChaining;
import net.bddtrader.pojo.asRecord.POJORecord;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static net.serenitybdd.rest.RestRequests.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class E_POST_with_different_modes_of_supplying_JSON_Body {

    @Before
    public void setupBaseURL(){
        RestAssured.baseURI="https://bddtrader.herokuapp.com";
    }

    //------------------------------------------------------------------------------------------------------------------
    @Test
    public void way1_passing_body_as_String(){
        String jsonBody= """
                            {
                                "email": "scott_atkins@gmail.com",
                                "firstName": "Scott",
                                "lastName": "Atkins"
                            }
                        """;

        given()
                .basePath("api/client")
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

    //------------------------------------------------------------------------------------------------------------------
    @Test
    public void way2_passing_body_as_File(){
        File fileObject = new File("src/test/resources/client.json");

        given()
                .basePath("api/client")
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

    //------------------------------------------------------------------------------------------------------------------
    @Test
    public void way3_passing_body_as_Map(){
        Map<String,Object> jsonMapObject = new HashMap<>();
        jsonMapObject.put("firstName","Sumit");
        jsonMapObject.put("lastName","Saha");
        jsonMapObject.put("email","sumit.saha@gmail.com");
        jsonMapObject.put("address","Texas");   // Creating a Key which is not required in JSON Body using maps will not affect the outcome
        //  This KEY in this case will be ignored and only required keys will be passed by Rest Assured

        given()
                .basePath("api/client")
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

    //------------------------------------------------------------------------------------------------------------------
    @Test
    public void way4_passing_body_as_POJO_Class_without_constructor() throws JsonProcessingException {
        // Creating an Object of POJO Class
        A_POJOWithoutConstructor pojoWithoutConstructorObj = new A_POJOWithoutConstructor();
        pojoWithoutConstructorObj.setFirstName("Sumit");
        pojoWithoutConstructorObj.setLastName("Saha");
        pojoWithoutConstructorObj.setEmail("sumit.saha@gmail.com");

        // Converting Java class object into JSON payload
        ObjectMapper objectMapper = new ObjectMapper();
        String clientJSONBody = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(pojoWithoutConstructorObj);

        System.out.println("**** JSON BODY formed using POJO class object: "+clientJSONBody);

        given()
                .basePath("api/client")
                .contentType(ContentType.JSON)
                .accept(ContentType.ANY)
                .body(clientJSONBody)
        .when()
                .log().all()
                .post()
        .then()
                .log().body()
                .statusCode(200)
                .body("id",not(equalTo(0)))
                .body("email",equalTo("sumit.saha@gmail.com"))
                .body("firstName",equalTo("Sumit"))
                .body("lastName",equalTo("Saha"));

    }

    //------------------------------------------------------------------------------------------------------------------
    @Test
    public void way5_passing_body_as_POJO_Class_with_constructor(){
        // Creating an Object of POJO Class
        B_POJOWithConstructor pojoWithConstructorObj = new B_POJOWithConstructor("Sumit","Saha","Sumit.saha@gmail.com");

        given()
                .basePath("api/client")
                .contentType(ContentType.JSON)
                .accept(ContentType.ANY)
                .body(pojoWithConstructorObj)
        .when()
                .post()
        .then()
                .log().all()
                .statusCode(200)
                .body("id",not(equalTo(0)))
                .body("email",equalTo("Sumit.saha@gmail.com"))
                .body("firstName",equalTo("Sumit"))
                .body("lastName",equalTo("Saha"));
    }

    //------------------------------------------------------------------------------------------------------------------
    @Test
    public void way6_passing_body_as_POJO_using_Method_Chaining(){
        // Creating an Object of POJO Class
        C_POJOUsingMethodChaining newClient = C_POJOUsingMethodChaining.getInstance().withFirstName("Sumit")
                                                                                        .andLastName("Saha")
                                                                                        .andEmail("sumit@gmail.com");
        given()
                .basePath("api/client")
                .contentType(ContentType.JSON)
                .accept(ContentType.ANY)
                .body(newClient)
        .when()
                .post()
        .then()
                .log().all()
                .statusCode(200)
                .body("id",not(equalTo(0)))
                .body("email",equalTo("sumit@gmail.com"))
                .body("firstName",equalTo("Sumit"))
                .body("lastName",equalTo("Saha"));
    }

    //------------------------------------------------------------------------------------------------------------------
    @Test
    public void way7_passing_body_as_POJO_Record(){
        // Creating an Object of POJO Record
        POJORecord recordObj = new POJORecord("Sumit","Saha","Sumit.saha@gmail.com");

        given()
                .basePath("api/client")
                .contentType(ContentType.JSON)
                .accept(ContentType.ANY)
                .body(recordObj)
        .when()
                .post()
        .then()
                .log().all()
                .statusCode(200)
                .body("id",not(equalTo(0)))
                .body("email",equalTo("Sumit.saha@gmail.com"))
                .body("firstName",equalTo("Sumit"))
                .body("lastName",equalTo("Saha"));
    }

    //------------------------------------------------------------------------------------------------------------------
    @Test
    public void way8_passing_body_as_POJO_JF_Way(){
        // Creating an Object of POJO Class
        Client client = Client.withFirstName("Sumit")
                                .andLastName("Saha")
                                .andEmail("sumit@gmail.com");

        given()
                .basePath("api/client")
                .contentType(ContentType.JSON)
                .accept(ContentType.ANY)
                .body(client)
        .when()
                .post()
        .then()
                .log().all()
                .statusCode(200)
                .body("id",not(equalTo(0)))
                .body("email",equalTo("sumit@gmail.com"))
                .body("firstName",equalTo("Sumit"))
                .body("lastName",equalTo("Saha"));
    }

}
