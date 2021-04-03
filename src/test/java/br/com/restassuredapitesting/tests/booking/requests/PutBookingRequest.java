package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.tests.auth.requests.PostAuthRequest;
import br.com.restassuredapitesting.tests.auth.requests.PutAuthRequest;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;

public class PutBookingRequest {

    PostAuthRequest postAuthRequest = new PostAuthRequest();
    PutAuthRequest putAuthRequest = new PutAuthRequest();
    @Step("Alterar uma Reserva COM Token")
    public Response alterarUmaReservaComToken(int id, JSONObject payload){
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", postAuthRequest.getToken())
                .when()
                .body(payload.toString())
                .put("booking/" + id);
    }

    @Step("Alterar uma Reserva SEM Token")
    public Response alterarUmaReservaSemToken(int id){
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", "Fake Payload com o Token")
                .when()
                .body("Token Falso")
                .put("booking/" + id);
    }

    @Step("Alterar uma Reserva com o Basic Auth")
    public Response alterarUmaReservaComBasicAuth(int id,JSONObject payload){
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorisation",putAuthRequest.basicAuth(2))
                .when()
                .body(payload.toString())
                .put("booking/" + id);
    }
}
