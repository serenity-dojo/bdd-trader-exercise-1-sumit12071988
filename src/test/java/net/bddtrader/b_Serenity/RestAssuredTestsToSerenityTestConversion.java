package net.bddtrader.b_Serenity;

import io.restassured.RestAssured;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.Ensure;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


import static net.serenitybdd.rest.SerenityRest.given;
import static org.hamcrest.Matchers.*;

@RunWith(SerenityRunner.class)  // Add this annotation to run in Rest Assured Tests and Serenity tests. At the end we can generate report using mvn serenity:aggregate
public class RestAssuredTestsToSerenityTestConversion {

    @Before
    public void prepare_rest_config() {
        RestAssured.baseURI = "https://bddtrader.herokuapp.com";
    }

    @Test
    public void singleAssertionSerenityTest() {
        given()
                .basePath("api/stock/{stockId}/company")
                .pathParam("stockId","aapl")
        .when()
                .log().all()
                .get()
        .then()
                .log().body();

        // Using Serenity Ensure for Assertions
        Ensure.that("The industry is correctly defined", response -> response.body("industry", equalToIgnoringCase("Telecommunications Equipment"))
        );
    }

    @Test
    public void multipleAssertionsSerenityTest() {
        given()
                .basePath("api/stock/aapl/company")
        .when()
                .get()
        .then()
                .log().all();

        Ensure.that("Company Name is Apple Inc.", response -> response.body("companyName", Matchers.equalTo("Apple, Inc.")))
              .andThat("Sector is Electronic Technology", response -> response.body("sector", Matchers.equalTo("Electronic Technology")));

    }
}