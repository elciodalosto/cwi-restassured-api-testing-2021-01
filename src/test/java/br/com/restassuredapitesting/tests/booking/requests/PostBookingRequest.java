package br.com.restassuredapitesting.tests.booking.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;

public class PostBookingRequest {

    @Step("Criar uma reserva")
    public Response criarReserva(JSONObject payload) {
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(payload.toString())
                .when()
                .post("booking");
    }

    @Step("Criar uma reserva")
    public Response criarReservaAcceptInvalido(JSONObject payload) {
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "Um Texto Qualquer")
                .body(payload.toString())
                .when()
                .post("booking");
    }

}
