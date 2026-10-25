package edu.uasd.inf5150.lab23;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    //Esta funcion de apoyo se encarga de ejecutar el ciclo con la entrada indicada y devuelve todo lo que se imprimio
    private String ejecutar(String entrada) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        PrintStream salida = new PrintStream(bytes, true, StandardCharsets.UTF_8);

        Main.ejecutar(new Scanner(entrada), salida);

        return bytes.toString(StandardCharsets.UTF_8);
    }

    //Esta prueba verifica que el programa siga pidiendo valores despues de un texto invalido,
    //muestre el mensaje del mandato y termine cuando se escribe "salir"
    @Test
    @DisplayName("ejecutar() sigue pidiendo valores tras un error, muestra el mensaje y termina con salir")
    void continuaDespuesDelErrorHastaSalir() {
        String texto = ejecutar("hola\n42\nsalir\n");

        assertTrue(texto.contains("El valor dado no es un numero valido"));
        assertTrue(texto.contains("Numero convertido: 42"));
        assertTrue(texto.contains("Programa terminado"));
    }

    //Esta prueba verifica que "salir" termine el programa de inmediato, sin importar mayusculas,
    //y que las lineas siguientes no se procesen
    @Test
    @DisplayName("ejecutar() termina de inmediato con SALIR sin procesar las lineas siguientes")
    void salirTerminaDeInmediato() {
        String texto = ejecutar("SALIR\n42\n");

        assertTrue(texto.contains("Programa terminado"));
        assertFalse(texto.contains("convertido"));
    }
}