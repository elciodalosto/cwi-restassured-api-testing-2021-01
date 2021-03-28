package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import br.com.restassuredapitesting.utils.Utils;
import org.junit.Test;

public class DeleteBookingTest extends BaseTest {

    DeleteBookingTest deleteBookingTest = new DeleteBookingTest();
    PostBookingTest postBookingTest = new PostBookingTest();

/*    @Test
    public void excluirReserva(){
        int idReservaCriada = postBookingTest
                .criarReserva(Utils.validaPayloadBooking())
                .then().log().all()
                .statusCode(200)
                .extract()
                .path("bookingid");

        deleteBookingTest.excluirReserva(5)
            .then().log().all()
            .assertThat()
            .statusCode(200);

    }*/
}
