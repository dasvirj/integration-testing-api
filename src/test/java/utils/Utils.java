package utils;

import fixtures.RequestInformations;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class Utils {
    public static RequestSpecification getRequestSpecAPI() {
        return new RequestSpecBuilder()
                .setBaseUri(RequestInformations.url)
                .build();
    }
}
