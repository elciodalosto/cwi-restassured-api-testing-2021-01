package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.suites.Acceptance;
import br.com.restassuredapitesting.suites.E2e;
import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import br.com.restassuredapitesting.tests.booking.requests.DeleteBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.PostBookingRequest;
import br.com.restassuredapitesting.utils.Utils;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DeleteBookingTest extends BaseTest {

    DeleteBookingRequest deleteBookingRequest = new DeleteBookingRequest();
    PostBookingRequest postBookingRequest = new PostBookingRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Excluir um reserva com sucesso")
    public void excluirReserva(){

        // cria uma reserva para depois excluí-la sem afetar os dados já existentes no banco de dados
        int idReservaCriada = postBookingRequest
                .criarReserva(
                        Utils.validPayloadBooking("Joana", "Silva"))
                .then().log().all()
                .statusCode(200)
                .extract()
                .path("bookingid");

        // deleta a reserva criada anteriormente no teste, identificada pelo id atribuído à variável "IdReservaCriada"
        deleteBookingRequest
                .excluirReservaComToken(idReservaCriada)
            .then().log().all()
            .assertThat()
            .statusCode(201);

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Excluir um reserva que não existe")
    public void excluirReservaQueNaoExiste(){
/* ao tentar excluir uma ID que não existe, é retornado
o erro 405 - Method Not Allowed(método não permitido) e o teste falha.
Ao tentar excluir uma ID que existe, retorna o código 201 - Created e o teste passa.
*/
        deleteBookingRequest
                .excluirReservaComToken(54321)
            .then().log().all()
            .assertThat()
            .statusCode(201);

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Excluir um reserva Sem Autorização")
    public void excluirReservaSemAuth(){
// Ao tentar excluir sem token de autorização,
// retorna o código 403 - Forbidden(proibido) e o teste falha.
        deleteBookingRequest
                .excluirReservaSemToken(12)
            .then().log().all()
            .assertThat()
            .statusCode(201);

    }
}
