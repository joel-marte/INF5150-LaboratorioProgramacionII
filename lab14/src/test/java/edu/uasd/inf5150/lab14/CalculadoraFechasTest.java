package edu.uasd.inf5150.lab14;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.time.Period;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraFechasTest {

    //En esta prueba se verifica que una fecha con formato AAAA-MM-DD se convierta a LocalDate
    //y que un texto invalido tire una excepcion
    @Test
    @DisplayName("convertirFecha() convierte AAAA-MM-DD a LocalDate y tambien nos tira una excepcion con fecha invalida")
    void convertirFechaValidaInvalida() {
        assertEquals(LocalDate.of(2024, 3, 20), CalculadoraFechas.convertirFecha("2024-03-20"));

        assertThrows(IllegalArgumentException.class, () -> CalculadoraFechas.convertirFecha("2024-02-30"));
        assertThrows(IllegalArgumentException.class, () -> CalculadoraFechas.convertirFecha("hola"));
        assertThrows(IllegalArgumentException.class, () -> CalculadoraFechas.convertirFecha(null));
    }

    //Esta prueba se encarga de hacer el calculo de dias con varios ejemplos, incluyendo año bisiesto
    //y una segunda fecha antes que nos da un resultado negativo
    @ParameterizedTest
    @CsvSource({
            "2024-01-15, 2024-03-20, 65",
            "2020-01-01, 2024-01-01, 1461",
            "2024-03-20, 2024-01-15, -65"
    })
    @DisplayName("calcularDias() calcula los dias entres dos fechas, y da negativo si la segunda es anterior a la primera")
    void calcularDiasEntreFechas(String f1, String f2, long esperado) {
        assertEquals(esperado, CalculadoraFechas.calcularDias(LocalDate.parse(f1), LocalDate.parse(f2)));
    }

    //Esta prueba verifica que Period nos de los años, meses y dias correctos
    @Test
    @DisplayName("calcularPeriodo() devuelve años, meses y dias con Period")
    void calcularPeriodoCorrecto() {
        Period periodo = CalculadoraFechas.calcularPeriodo(
                LocalDate.of(2020, 1, 15), LocalDate.of(2024, 3, 20));

        assertEquals(4, periodo.getYears());
        assertEquals(2, periodo.getMonths());
        assertEquals(5, periodo.getDays());
    }

    //Aqui verificamos que si la segunda fecha es despues, antes o igual
    @ParameterizedTest
    @CsvSource({
            "2024-01-01, 2024-06-01, DESPUES",
            "2024-06-01, 2024-01-01, ANTES",
            "2024-06-01, 2024-06-01, IGUAL"
    })
    @DisplayName("compararFechas() nos deja saber si la segunda fecha es futura(despues), pasada(antes) o igual")
    void compararFechasIndicaRelacion(String f1, String f2, String esperado) {
        assertEquals(esperado, CalculadoraFechas.compararFechas(LocalDate.parse(f1), LocalDate.parse(f2)));
    }
}