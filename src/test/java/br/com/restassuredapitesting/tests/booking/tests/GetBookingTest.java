package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.suites.E2e;
import br.com.restassuredapitesting.suites.Acceptance;
import br.com.restassuredapitesting.suites.Contract;
import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import br.com.restassuredapitesting.utils.Utils;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

public class GetBookingTest extends BaseTest {

    GetBookingRequest getBookingRequest = new GetBookingRequest();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category(Contract.class)
    @DisplayName("Garantir o contrato do retorno da lista de reservas")
    public void garantirContratoListaReserva() throws Exception {

        getBookingRequest
                .listAllBookings()
                .then().log().all()
                .statusCode(200)
                .assertThat()
                .body(
                matchesJsonSchema(
                        new File(
                            Utils.getContractsBasePath("booking","bookings")
                            )
                        )
                );
    }


    @Test
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Garantir o contrato do retorno de uma reserva específica")
    @Category(Contract.class)
    public void garantirContratoReservaEspecifica() throws Exception {

        getBookingRequest
                .listBookingById(2).then().log().all()
                .statusCode(200)
                .assertThat()
                .body(
                matchesJsonSchema(
                        new File(
                                Utils.getContractsBasePath("booking","booking_by_id")
                        )
                )
        );
    }


    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs das reservas")
    public void listarIdsDasReservas() throws Exception {

        getBookingRequest
                .listAllBookings().then()
                .statusCode(200).log().all()
                .time(lessThan(6L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs de reservas utilizando o filtro firstname")
    public void filtrarReservaPorPrimeiroNome() throws Exception {

        getBookingRequest
                .listBookingByFirstName("Sally").then()
                .statusCode(200).log().all()
                .time(lessThan(6L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs de reservas utilizando o filtro lastname")
    public void filtrarReservaPorSobrenome() throws Exception {

        getBookingRequest
                .listBookingByLastName("Brown").then()
                .statusCode(200).log().all()
                .time(lessThan(6L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs de reservas utilizando o filtro checkin")
    public void filtrarReservaPorCheckIn() throws Exception {

        getBookingRequest
                .listBookingByCheckIn("2018-01-01").then()
                .statusCode(200).log().all()
                .time(lessThan(6L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs de reservas utilizando o filtro checkout")
    public void filtrarReservaPorCheckOut() throws Exception {
    /*
    * Na documentação consta o seguinte texto:
    * "Return bookings that have a checkout date greater than
    * or equal to the set checkout date. Format must be CCYY-MM-DD."
    *
    * Mas ao invés de retornar as IDs das reservas com a data Igual E Superiores à data passada como argumento,
    *    ela está retornando reservas de data Igual e Inferior à data passada como argumento.
    * Acredito que seja um erro de documentação da API, porque a lógica do retorno me parece correta,
    * apenas o texto que não condiz com o que ele faz.
    *
    *  Buscando pela data 2015-01-01, ele busca tudo o que existe até esta data, e nada que venha depois.
    * */
        getBookingRequest
                .listBookingByCheckOut("2015-01-01").then()
                .statusCode(200).log().all()
                .time(lessThan(6L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs de reservas utilizando os filtros checkin e checkout")
    public void filtrarReservaPorCheckInAndCheckOut() throws Exception {
/*
* Retornando Código 200 - Ok
* */
        getBookingRequest
                .listBookingByCheckInAndCheckOut("2016-07-20", "2018-12-09")
                .then()
                .statusCode(200).log().all()
                .time(lessThan(6L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }


    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs de reservas utilizando os filtros name, checkin e checkout")
    /*
    * O sistema retorna código 200 - OK
    * */
    public void filtrarReservaPorNameCheckinAndCheckout(){

        getBookingRequest.listBookingsByNameCheckInAndCheckOut(
                "Jim",
                "2016-03-11",
                "2020-08-30"
        )
                .then()
                .statusCode(200).log().all()
                .time(lessThan(6L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Visualizar erro de servidor 500 quando enviar filtro mal formatado")
    public void filtrarReservaPorFiltroMalFormatado(){
        /*
        * Ao enviar a data em um formato desconhecido, pelo método de listar reservas por Checkin,
        * o servidor retorna o código 500 - Internal Server Error,
        * uma solicitação imprevista, com a qual ele não sabe lidar
        *  */

        getBookingRequest.listBookingByCheckIn("2014_10_05")
                .then()
                .statusCode(500).log().all()
                .time(lessThan(6L), TimeUnit.SECONDS);
//                .body("size()", greaterThan(0));
    }
}
