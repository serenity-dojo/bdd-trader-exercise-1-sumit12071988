package net.bddtrader.a_Restassured;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class B_RefactoredGET {

    @Before
    public void background() {
        // Adding the contents which will be static all the time like the baseURI
        RestAssured.baseURI = "https://bddtrader.herokuapp.com";
    }

    @Test
    public void basic_RESTAssured_GET_Call_Way1() {
        RestAssured.get("api/stock/aapl/company")
                .then()
                    .log().all()
                    .body("companyName", Matchers.equalTo("Apple, Inc."))
                    .body("sector", Matchers.equalTo("Electronic Technology"));
    }

    @Test
    public void basic_RESTAssured_GET_Call_Way2(){
        given().
        when().
                log().all().
                get("api/stock/aapl/company").
        then().
                log().body().
                body("companyName", Matchers.equalTo("Apple, Inc.")).
                body("sector", Matchers.equalTo("Electronic Technology"));
    }

    @Test
    public void basic_RESTAssured_GET_Call_Way3(){
        given().
                basePath("api/stock/aapl/company").
        when().
                log().all().
                get().
        then().
                log().all().
                body("companyName", Matchers.equalTo("Apple, Inc.")).
                body("sector", Matchers.equalTo("Electronic Technology"));
    }


}
