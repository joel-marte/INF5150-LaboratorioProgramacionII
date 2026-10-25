package edu.uasd.inf5150.lab21;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @TempDir
    Path carpeta;

    //Esta funcion auxiliar muestra el archivo y devuelve todo el texto que se imprimio
    private String ejecutar(File archivo) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        PrintStream salida = new PrintStream(bytes, true, StandardCharsets.UTF_8);

        Main.mostrarArchivo(archivo, salida);

        return bytes.toString(StandardCharsets.UTF_8);
    }

    //Esta prueba verifica que se muestre cada linea del archivo, una por linea de consola
    @Test
    @DisplayName("mostrarArchivo() muestra el contenido del archivo linea por linea")
    void mostrarArchivoImprimeCadaLinea() throws IOException {
        File archivo = Files.writeString(carpeta.resolve("datos.txt"), "Hola\nMundo").toFile();

        String esperado = "Hola" + System.lineSeparator() + "Mundo" + System.lineSeparator();

        assertEquals(esperado, ejecutar(archivo));
    }

    //Esta prueba verifica que un archivo inexistente muestre el mensaje del mandato
    @Test
    @DisplayName("mostrarArchivo() captura FileNotFoundException y muestra El archivo no fue encontrado")
    void archivoInexistenteMuestraMensaje() {
        String texto = ejecutar(carpeta.resolve("no-existe.txt").toFile());

        assertTrue(texto.contains("El archivo no fue encontrado"));
    }
}