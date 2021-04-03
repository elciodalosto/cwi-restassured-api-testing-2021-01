package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.suites.Acceptance;
import br.com.restassuredapitesting.suites.E2e;
import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.PutBookingRequest;
import br.com.restassuredapitesting.utils.Utils;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

public class PutBookingTest extends BaseTest {

    PutBookingRequest putBookingRequest = new PutBookingRequest();
    GetBookingRequest getBookingRequest = new GetBookingRequest();

    @Test
    @Severity(SeverityLevel.NORMAL) // severidade
    @Category(Acceptance.class)
    @DisplayName("Alterar uma reserva utilizando token") // nome do nosso Cenário
    public void validarAlterarUmaReservaUtilizandoToken() throws Exception {
        int primeiroId = getBookingRequest
                .listAllBookings()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        putBookingRequest
                .alterarUmaReservaComToken(
                        primeiroId,
                        Utils.validPayloadBooking("João", "Silva"))
                .then()
                .statusCode(200)
                .time(lessThan(2L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL) // severidade
    @Category(E2e.class)
    @DisplayName("Tentar alterar uma reserva quando o token não for enviado")
    public void validarAlterarUmaReservaSemToken() throws Exception {
       /*
       * Retorna o código 400 - Bad Request
       *
       *  */
        int primeiroId = getBookingRequest
                .listAllBookings()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        putBookingRequest
                .alterarUmaReservaSemToken(primeiroId)
                .then()
                .statusCode(200)
                .time(lessThan(2L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }


    @Test
    @Severity(SeverityLevel.NORMAL) // severidade
    @Category(E2e.class)
    @DisplayName("Tentar alterar uma reserva quando o token enviado for inválido") // nome do nosso Cenário
    public void validarAlterarUmaReservaUtilizandoTokenInvalido() throws Exception {
        /*
        * Retorna Erro de Código 500 - Internal Server Error
        * */
        int primeiroId = getBookingRequest
                .listAllBookings()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        putBookingRequest
                .alterarUmaReservaComToken(
                        primeiroId,
                        Utils.invalidPayloadBooking())
                .then()
                .statusCode(200)
                .time(lessThan(2L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }


    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Tentar alterar uma reserva que não existe")
    public void validarAlterarUmaReservaQueNaoExiste() throws Exception {
        /*
        * passado o ID: 999 como teste
        * Gera um erro 405 - Method Not Allowed (Método não permitido)
        *
        * */
        putBookingRequest
                .alterarUmaReservaComToken(
                        999,
                        Utils.validPayloadBooking("Joana", "Silva"))
                .then().log().all()
                .statusCode(200)
                .time(lessThan(2L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }


    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Alterar uma reserva usando o Basic auth")
    public void validarAlterarUmaReservaComBasicAuth() throws Exception {
        putBookingRequest.alterarUmaReservaComBasicAuth(15,
                Utils.validPayloadBooking("Jimy","Carter" ))
                .then()
                .log().all()
                .statusCode(200)
                .time(lessThan(2L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));

    }

}
