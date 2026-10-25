package edu.uasd.inf5150.lab23;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ConversorEnteroTest {

    //Esta prueba verifica que los textos numericos validos se conviertan al entero correcto,
    //incluyendo negativos, signo mas, espacios alrededor y los limites de int
    @ParameterizedTest
    @CsvSource({
            "42, 42",
            "-17, -17",
            "+8, 8",
            "0, 0",
            "' 25 ', 25",
            "2147483647, 2147483647",
            "-2147483648, -2147483648"
    })
    @DisplayName("convertir() convierte con Integer.parseInt() un texto de numero a entero correcto")
    void convertirTextoNumericoValido(String texto, int esperado) {
        assertEquals(esperado, ConversorEntero.convertir(texto));
    }

    //Esta prueba verifica que los textos no numericos tiren NumberFormatException:
    //letras, decimales, mezclas, vacios, nulos, desbordamiento de int y separadores de miles
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"abc", "3.14", "12abc", "   ", "2147483648", "1,000"})
    @DisplayName("convertir() tira NumberFormatException cuando el texto no es un entero valido")
    void convertirTextoInvalidoTiraExcepcion(String texto) {
        assertThrows(NumberFormatException.class, () -> ConversorEntero.convertir(texto));
    }
}