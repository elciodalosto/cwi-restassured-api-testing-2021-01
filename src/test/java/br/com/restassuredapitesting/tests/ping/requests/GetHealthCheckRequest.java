package br.com.restassuredapitesting.tests.ping.requests;

import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetHealthCheckRequest extends BaseTest {

    @Step("Verificar a Saúde da API")
    public Response healthStatus() {

        return given()
                .get("/ping");

    }
}
