package br.com.restassuredapitesting.suites;

public interface E2e {
}
/*
* Suite E2e :
/DELETE
    Tentar excluir um reserva que não existe
    Tentar excluir uma reserva sem autorização
/GET
    Visualizar erro de servidor 500 quando enviar filtro mal formatado
/POST
    Validar retorno 500 quando o payload da reserva estiver inválido
    Validar a criação de mais de um livro em sequencia
    Criar uma reserva enviando mais parâmetros no payload da reserva
    Validar retorno 418 quando o header Accept for invalido
/PUT
    Tentar alterar uma reserva quando o token não for enviado
    Tentar alterar uma reserva quando o token enviado for inválido
    Tentar alterar uma reserva que não existe
*
* */
