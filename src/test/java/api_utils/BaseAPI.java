package api_utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;

import java.util.Map;

public class BaseAPI {
    public static Response callPostAPI(String url, String jsonBody, Map<String, ?> headers) {
//        Serenity.recordReportData().withTitle("Post API").andContents(jsonBody);
        Response response = SerenityRest
                .given()
                .contentType("application/json")
                .headers(headers)
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .post(url)
                .then()
                .extract().response();
        return response;
    }
}
