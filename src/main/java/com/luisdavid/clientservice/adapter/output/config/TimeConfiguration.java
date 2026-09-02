package com.luisdavid.clientservice.adapter.output.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

/**
 * Expone las dependencias de tiempo para que los casos de uso sean deterministas en pruebas.
 */
@Configuration
public class TimeConfiguration {

    /**
     * Usa UTC para que las fechas creadas por el servicio no dependan de la zona horaria del servidor.
     *
     * @return reloj del sistema configurado en UTC.
     */
    @Bean
    Clock utcClock() {
        return Clock.systemUTC();
    }
}
