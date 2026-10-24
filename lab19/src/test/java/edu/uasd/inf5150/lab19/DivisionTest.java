package edu.uasd.inf5150.lab19;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class DivisionTest {

    //Esta prueba se encarga de verifica el comportamiento principal: la division entera trunca los decimales
    //y funciona con numeros negativos y con un dividendo cero
    @ParameterizedTest
    @CsvSource({
            "10, 2, 5",
            "7, 2, 3",
            "-7, 2, -3",
            "7, -2, -3",
            "0, 5, 0"
    })
    @DisplayName("dividir() divide enteros truncando los decimales, con negativos y dividendo cero")
    void dividirEnterosCorrectamente(int dividendo, int divisor, int esperado) {
        assertEquals(esperado, Division.dividir(dividendo, divisor));
    }

    //Esta prueba verifica que dividir entre cero tire una ArithmeticException,
    //incluyendo el caso de 0 entre 0
    @ParameterizedTest
    @CsvSource({"10", "-5", "0"})
    @DisplayName("dividir() tira una ArithmeticException cuando el divisor es cero")
    void dividirEntreCeroTiraExcepcion(int dividendo) {
        assertThrows(ArithmeticException.class, () -> Division.dividir(dividendo, 0));
    }
}