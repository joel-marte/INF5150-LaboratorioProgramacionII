package edu.uasd.inf5150.lab19;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    //Esta funcion ejecuta la division y nos devuelve todo el texto que se imprimio por consola
    private String ejecutar(int dividendo, int divisor) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        PrintStream salida = new PrintStream(bytes, true, StandardCharsets.UTF_8);

        Main.hacerDivision(dividendo, divisor, salida);

        return bytes.toString(StandardCharsets.UTF_8);
    }

    //Esta prueba verifica que una division valida muestre el resultado
    //y que el bloque finally muestre igual "Fin del programa"
    @Test
    @DisplayName("realizarDivision() muestra el resultado y Fin del programa con una division valida")
    void divisionValidaMuestraResultadoYFinally() {
        String texto = ejecutar(10, 2);

        assertTrue(texto.contains("Resultado: 5"));
        assertFalse(texto.contains("No se puede dividir entre cero"));
        assertTrue(texto.contains("Fin del programa"));
    }

    //Esta prueba verifica que dividir entre cero se capture la excepcion,
    //muestre el mensaje del mandato y que el finally se ejecute de todas formas
    @Test
    @DisplayName("realizarDivision() captura ArithmeticException, muestra el mensaje y ejecuta finally")
    void divisionEntreCeroMuestraMensajeYFinally() {
        String texto = ejecutar(10, 0);

        assertTrue(texto.contains("No se puede dividir entre cero"));
        assertFalse(texto.contains("Resultado:"));
        assertTrue(texto.contains("Fin del programa"));
    }
}