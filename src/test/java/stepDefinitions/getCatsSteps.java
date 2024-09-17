package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;

import static io.restassured.internal.common.assertion.AssertParameter.notNull;
import static org.hamcrest.Matchers.matchesPattern;
import static org.hamcrest.Matchers.notNullValue;

public class getCatsSteps {
    private static final String url = "https://api.funtranslations.com/translate/";
    private static String phraseToTranslate;
    private static Response response;

    private RequestSpecification getRequestSpecAPI(){
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .log(LogDetail.ALL)
                .build();
    }
    @Given("uma frase")
    public void phraseToTranslate() {
       phraseToTranslate = "I am Daenerys Stormborn of the House Targaryen, of the blood of Old Valyria. Valyrian is my mother tongue.";
    }
    @When("envio a frase para a API de traducao Valyriana")
    public void sentPhraseToTranslate(){
       response = RestAssured
                .given()
               .spec(getRequestSpecAPI())
                .queryParam("text", phraseToTranslate)
                .when()
               .log()
               .all()
               .post("valyrian.json");
        System.out.println("Respomseeeeeeeeeeeeee"+response.statusCode());
    }
    @Then("o status code da API deve retornar 200")
    public void validateStatusCode(){
        response.then().statusCode(HttpStatus.SC_OK);
    }
    @Then("a frase deve ser retornada traduzida para Valiryan")
    public void validateTranslate(){
        response
                .then()
                .body("contents.translated", notNullValue())
                .body("contents.translation", matchesPattern("valyrian"));

    }
}