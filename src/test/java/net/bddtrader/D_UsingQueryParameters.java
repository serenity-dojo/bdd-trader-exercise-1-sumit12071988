package net.bddtrader;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.Matchers.everyItem;

public class D_UsingQueryParameters {

    String queryParamValue = "AAPL";

    @Before
    public void background() {
        RestAssured.baseURI = "https://bddtrader.herokuapp.com";    // Adding the contents which will be static all the time like the baseURI
    }

    @Test
    public void basic_RESTAssured_GET_Call_Way1() {
        given().
                queryParam("symbols",queryParamValue).
        when().
                log().all().
                get("api/news").
        then().
                log().body().
                body("related",everyItem(containsStringIgnoringCase(queryParamValue)));
    }

    @Test
    public void basic_RESTAssured_GET_Call_Way2(){
        given().
                basePath("api/news").
                queryParam("symbols",queryParamValue).
        when().
                log().all().
                get().
        then().
                log().body().
                body("related",everyItem(containsStringIgnoringCase(queryParamValue)));
    }


}
