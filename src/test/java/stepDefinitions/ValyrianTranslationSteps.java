package stepDefinitions;

import fixtures.RequestInformations;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import utils.Utils;

import static io.restassured.internal.common.assertion.AssertParameter.notNull;
import static org.hamcrest.Matchers.matchesPattern;
import static org.hamcrest.Matchers.notNullValue;

public class ValyrianTranslationSteps {
//    private static final String url = "https://api.funtranslations.com/translate/";
//    private static String initializePhrase;
//    private static Response response;
//
//    private RequestSpecification getRequestSpecAPI() {
//        return new RequestSpecBuilder()
//                .setBaseUri(url)
//                .build();
//    }

    @Given("uma frase")
    public void phraseToTranslate() {
        RequestInformations.initializePhrase =
                "I am Daenerys Stormborn of the House Targaryen, of the blood of Old Valyria. Valyrian is my mother tongue.";
    }

    @When("envio a frase para a API de traducao Valyriana")
    public void sentPhraseToTranslate() {
        RequestInformations.response = RestAssured
                .given()
                .spec(Utils.getRequestSpecAPI())
                .queryParam("text", RequestInformations.initializePhrase)
                .when()
                .post("valyrian.json");
        RequestInformations.response.prettyPrint();
    }

    @Then("o status code da API deve retornar 200")
    public void validateStatusCode() {
        RequestInformations.response.then().statusCode(HttpStatus.SC_OK);
    }

    @Then("a frase deve ser retornada traduzida para Valiryan")
    public void validateTranslate() {
        RequestInformations.response
                .then()
                .body("contents.translated", notNullValue())
                .body("contents.translation", matchesPattern("valyrian"));

    }
}