package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.suites.Acceptance;
import br.com.restassuredapitesting.suites.E2e;
import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import br.com.restassuredapitesting.tests.booking.requests.PostBookingRequest;
import br.com.restassuredapitesting.utils.Utils;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PostBookingTest extends BaseTest {

    PostBookingRequest postBookingRequest = new PostBookingRequest();

    @Test
    @Category(Acceptance.class)
    @DisplayName("Criar uma nova reserva")
    public void criarReservaValida() {
        postBookingRequest
                .criarReserva(Utils.validPayloadBooking("Elcio", "Dalosto"))
                .then().log().all()
                .assertThat()
                .statusCode(200);

    }


    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Validar retorno 500 quando o payload da reserva estiver inválido")
    public void criarReservaInvalida(){
        /*
        * Retorna o Código 500 - Internal Server Error
        * */
        postBookingRequest
                .criarReserva(Utils.invalidPayloadBooking())
                .then().log().all()
                .assertThat()
                .statusCode(500);
    }



    @Test
    @Category(E2e.class)
    @DisplayName("Criar uma reserva enviando mais parâmetros no payload da reserva")
    public void criarReservaComMaisParametros(){
        /*
        * Retorna código 200 - Ok
        * mas poderia retornar 201 - Created
        * */
        postBookingRequest
                .criarReserva(
                Utils
                        .parameterizedPayloadBooking(
                        "Elcio",
                        "Dalosto",
                        300.00,
                        true,
                        "Dinner",
                        "2018-01-01",
                        "2019-01-01"
                        )
                )
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    @Category(E2e.class)
    @DisplayName("Validar retorno 418 quando o header Accept for invalido")
    public void validarRetornoAcceptInvalido(){
        postBookingRequest
                .criarReservaAcceptInvalido(Utils.validPayloadBooking("Elcio", "Dalosto"))
                .then().log().all()
                .assertThat()
                .statusCode(418);
    }


    @Test
    @Category(E2e.class)
    @DisplayName("Validar a criação de mais de um livro(reserva) em sequencia")
    public void criarVariasReservasEmSequencia() {

        for(int i=0; i< 3; i++) {
            postBookingRequest
                    .criarReserva(Utils.validPayloadBooking("Elcio"+i, "Dalosto"))
                    .then().log().all()
                    .assertThat()
                    .statusCode(200);
        }
    }
}
