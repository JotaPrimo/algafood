package com.algaworks.api.algafood.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;


@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@Sql(scripts = "/sql/restaurantes/restaurantes-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/restaurantes/restaurantes-truncate.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class CadastroCozinhaIntegrationTests {

    @Test
    void contextInitail() {
        Assertions.assertFalse(false);
    }

    @Test
    void loadData() {
        Assertions.assertEquals(false, false);
    }
}
