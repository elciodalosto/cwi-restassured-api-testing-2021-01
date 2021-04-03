package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.tests.auth.requests.PostAuthRequest;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DeleteBookingRequest {

    PostAuthRequest postAuthRequest = new PostAuthRequest();

    @Step("Excluir reserva pelo ID, com Token")
    public Response excluirReservaComToken(int id){
        return given()
                .header("Content-Type", "application/json")
                .header("Cookie", postAuthRequest.getToken())
                .delete("booking/" + id);
    }

    @Step("Excluir reserva pelo ID, sem Token")
    public  Response excluirReservaSemToken(int id){
        return given()
                .header("Content-Type", "application/json")
                .delete("booking/" + id);
    }




}
