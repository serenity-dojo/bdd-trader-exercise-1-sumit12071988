package net.bddtrader;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C_UsingPathParameters {

    @Before
    public void background() {
        RestAssured.baseURI = "https://bddtrader.herokuapp.com";    // Adding the contents which will be static all the time like the baseURI
    }

    @Test
    public void basic_RESTAssured_GET_Call_Way1() {
        RestAssured.get("/api/stock/{stockId}/company","aapl")  // Replacing dynamic part of base path with Path Parameter/ placeholder and passing the value afterwards
                .then()
                .log().all()
                .body("companyName", Matchers.equalTo("Apple, Inc."))
                .body("sector", Matchers.equalTo("Electronic Technology"));
    }

    @Test
    public void basic_RESTAssured_GET_Call_Way2(){
        given().
                pathParam("stockId","aapl").    // Creating path parameter with a value for replacement
        when().
                get("/api/stock/{stockId}/company"). // Replacing dynamic part of base path with Path Parameter/ placeholder
        then().
                log().all().
                body("companyName", Matchers.equalTo("Apple, Inc.")).
                body("sector", Matchers.equalTo("Electronic Technology"));
    }

    @Test
    public void basic_RESTAssured_GET_Call_Way3(){
        given().
                basePath("api/stock/{stockId}/company").    // Replacing dynamic part of base path with Path Parameter/ placeholder
                pathParam("stockId","aapl").    // Passing the value for Path Parameter for replacement
        when().
                get().
        then().
                log().all().
                body("companyName", Matchers.equalTo("Apple, Inc.")).
                body("sector", Matchers.equalTo("Electronic Technology"));
    }


}
