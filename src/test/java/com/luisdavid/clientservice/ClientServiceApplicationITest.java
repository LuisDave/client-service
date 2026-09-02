package com.luisdavid.clientservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Tag("integration")
@DisplayName("Prueba de integración del contexto de Client Service")
class ClientServiceApplicationITest {

    @Test
    @DisplayName("Debe iniciar el contexto de Spring con MySQL configurado")
    void shouldLoadApplicationContext() {
    }

}
