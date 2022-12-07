package net.bddtrader.a_Restassured;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class A_GET {

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
                log().all().
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
                log().all().
                get().
        then().
                log().all().
                body("companyName", Matchers.equalTo("Apple, Inc.")).
                body("sector", Matchers.equalTo("Electronic Technology"));
    }


}
