package br.com.restassuredapitesting.tests.auth.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PutAuthRequest {

    @Step("Buscar o Basic Auth")
    public Response basicAuth(int id){
        return given()
                .auth()
                .basic("admin", "password123")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorisation", returnBasicAuth(id))
                .when()
                .put("booking/"+id);
    }

    @Step("Retornar o Basic Auth")
    public String returnBasicAuth(int id){
        return "Basic "+basicAuth(15)
                .then().log().all()
                .statusCode(200)
                .extract()
                .path("booking/"+id);
    }
}
