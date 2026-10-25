package edu.uasd.inf5150;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class PersonaTest {

    //Esta prueba verifica que el constructor cree una Persona valida con su nombre y edad
    @Test
    @DisplayName("Persona() crea una persona valida y getNombre() y getEdad() devuelven sus datos")
    void constructorCreaPersonaValida() throws EdadInvalidaException {
        Persona persona = new Persona("Ana Fernandez", 25);

        assertEquals("Ana Fernandez", persona.getNombre());
        assertEquals(25, persona.getEdad());
    }

    //Esta prueba verifica los valores limite: 0 y 120 deben ser edades validas
    @ParameterizedTest
    @CsvSource({"0", "1", "119", "120"})
    @DisplayName("Persona() acepta las edades limite 0 y 120 sin tirar excepcion")
    void constructorAceptaEdadesLimite(int edad) {
        assertDoesNotThrow(() -> new Persona("Ana Fernandez", edad));
    }

    //Esta prueba verifica que una edad menor que 0 o mayor que 120 tire EdadInvalidaException
    //y que la excepcion guarde la edad invalida y de un mensaje explicativo
    @ParameterizedTest
    @CsvSource({"-1", "-50", "121", "200"})
    @DisplayName("Persona() tira EdadInvalidaException con una edad menor que 0 o mayor que 120")
    void constructorTiraExcepcionConEdadInvalida(int edad) {
        EdadInvalidaException excepcion = assertThrows(EdadInvalidaException.class,
                () -> new Persona("Ana Fernandez", edad));

        assertEquals(edad, excepcion.getEdad());
        assertTrue(excepcion.getMessage().contains(String.valueOf(edad)));
    }

    //Esta prueba verifica que un nombre nulo o vacio tire una excepcion
    @Test
    @DisplayName("Persona() tira IllegalArgumentException si el nombre es nulo o esta vacio")
    void constructorTiraExcepcionConNombreInvalido() {
        assertThrows(IllegalArgumentException.class, () -> new Persona(null, 25));
        assertThrows(IllegalArgumentException.class, () -> new Persona("   ", 25));
    }
}