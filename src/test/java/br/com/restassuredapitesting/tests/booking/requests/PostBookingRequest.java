package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.utils.Utils;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostBookingRequest {

    @Step("Criar uma reserva")
    public Response criarReserva(JSONObject payload) {
        return given()
                .header("Content-Type", "application/json")
                .body(payload.toString())
                .when()
                .post("booking.firstname", equalTo("Teste"));
    }
}
