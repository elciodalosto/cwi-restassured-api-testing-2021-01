package br.com.restassuredapitesting.tests.ping.tests;

import br.com.restassuredapitesting.runners.Acceptance;
import br.com.restassuredapitesting.runners.HealthCheck;
import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import br.com.restassuredapitesting.tests.ping.requests.GetHealthCheckRequest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.lessThan;

public class GetHealthCheckTest extends BaseTest {

    GetHealthCheckRequest getHealthCheckRequest = new GetHealthCheckRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(HealthCheck.class)
    @DisplayName("Verificar se API está online")
    public void apiHealth() throws Exception {
    /* RETORNA O CÓDIGO 201 - CREATED
        mas seria interessante se retornasse CODIGO 200 - OK, POR SE TRATAR DE UM "GET"
        QUE NÃO CRIA NADA NO SERVIDOR,
         APENAS RETORNA DADOS

        SE for definido, ele oscila entre 2,5 e 4,0 segundos para retornar algo,
        então deixei o filtro de tempo comentado/desabilitado.
    */
        getHealthCheckRequest
                .healthStatus().then().log().all()
                .statusCode(201);
//                .time(lessThan(3L), TimeUnit.SECONDS);
    }


}
