package br.com.restassuredapitesting.tests.booking.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetBookingRequest {

    @Step("Listar IDs das reservas")
    public Response listAllBookings(){
            return given()
                .header("Content-Type", "application/json")
                .when()
                .get("booking");
    }

    @Step("Listar uma reserva específica")
    public Response listBookingById(int id){
        return given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .when()
                .get("booking"+"/"+id);
    }

    @Step("Lista IDs de reservas utilizando o filtro firstname")
    public Response listBookingByFirstName(String firstName){
        return given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .when()
                .get("booking?firstname="+firstName);
    }
    @Step("Lista os IDs de reservas utilizando o filtro lastname")
    public Response listBookingByLastName(String lastName){
        return given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .when()
                .get("booking?lastname="+lastName);
    }

    @Step("Lista os IDs de reservas utilizando o filtro checkin")
    public Response listBookingByCheckIn(String checkin){
        return given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .when()
                .get("booking?checkin="+checkin);
    }

    @Step("Lista os IDs de reservas utilizando o filtro checkout")
    public Response listBookingByCheckOut(String checkout){
        return given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .when()
                .get("booking?checkout="+checkout);
    }

    @Step("Listar IDs de reservas utilizando o filtro checkin and checkout")
    public Response listBookingByCheckInAndCheckOut(String checkin, String checkout){
        return given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .when()
                .get("booking?"+"checkin="+checkin+"&"+"checkout="+checkout);
    }

    @Step("Listar IDs de reservas utilizando o filtro name, checkin and checkout date")
    public  Response listBookingsByNameCheckInAndCheckOut(String name, String checkin, String checkout){
        return given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .when()
                .get("booking?"+"name="+name+"&"+"checkin="+checkin+"&"+"checkout="+checkout);
    }

}
