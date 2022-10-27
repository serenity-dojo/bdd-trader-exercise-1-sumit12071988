package net.bddtrader;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class A_BasicGETCall {

    @Test
    public void basic_RESTAssured_GET_Call_Way1() {
            RestAssured.get("https://bddtrader.herokuapp.com/api/stock/aapl/company")
                    .then()
                    .log().all()
                    .body("companyName", Matchers.equalTo("Apple, Inc."))
                    .body("sector", Matchers.equalTo("Electronic Technology"));
    }

    @Test
    public void basic_RESTAssured_GET_Call_Way2(){
        given().
        when().
                get("https://bddtrader.herokuapp.com/api/stock/aapl/company").
        then().
                log().all().
                body("companyName", Matchers.equalTo("Apple, Inc.")).
                body("sector", Matchers.equalTo("Electronic Technology"));
    }

    @Test
    public void basic_RESTAssured_GET_Call_Way3(){
        given().
                baseUri("https://bddtrader.herokuapp.com").
                basePath("api/stock/aapl/company").
        when().
                get().
        then().
                log().all().
                body("companyName", Matchers.equalTo("Apple, Inc.")).
                body("sector", Matchers.equalTo("Electronic Technology"));
    }


}
