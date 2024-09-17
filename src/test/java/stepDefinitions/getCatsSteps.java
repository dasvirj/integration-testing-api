package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;

public class getCatsSteps {
    @Given("um acesso a API")
    public void acesso() {
        RestAssured
                .when()
                .get("https://meowfacts.herokuapp.com/");
    }
    @Then(" um fato é exibido")
    public void show(){
        RestAssured
                .when()
                .get("https://meowfacts.herokuapp.com/")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
}