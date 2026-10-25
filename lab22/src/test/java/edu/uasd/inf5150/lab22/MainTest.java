package edu.uasd.inf5150.lab22;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    //Esta prueba verifica que al retirar mas que el saldo se muestre el mensaje del mandato
    @Test
    @DisplayName("intentarRetiro() captura FondosInsuficientesException y muestra el mensaje de error")
    void fondosInsuficientesMuestraMensajeDelMandato() {
        CuentaBancaria cuenta = new CuentaBancaria("001-234567-8", 100.0);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        PrintStream salida = new PrintStream(bytes, true, StandardCharsets.UTF_8);

        Main.intentarRetiro(cuenta, 500.0, salida);

        String texto = bytes.toString(StandardCharsets.UTF_8);
        assertTrue(texto.contains("Error: fondos insuficientes. No se puede hacer el retiro."));
        assertEquals(100.0, cuenta.getSaldo(), 0.001);
    }
}