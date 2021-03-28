package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import br.com.restassuredapitesting.tests.booking.requests.PostBookingRequest;
import org.junit.Test;

public class PostBookingTest extends BaseTest {

    PostBookingRequest postBookingRequest = new PostBookingRequest();

/*    @Test
    public void criarReserva(){
        postBookingRequest.criarReserva()
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }*/

}
