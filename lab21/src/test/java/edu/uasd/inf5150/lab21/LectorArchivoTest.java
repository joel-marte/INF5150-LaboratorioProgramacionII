package edu.uasd.inf5150.lab21;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LectorArchivoTest {

    //Carpeta temporal que JUnit crea y borra automaticamente para cada prueba
    @TempDir
    Path carpeta;

    //Esta prueba verifica que se lean las lineas en orden, incluyendo las vacias,
    //y que un archivo vacio devuelva una lista vacia
    @Test
    @DisplayName("leerLineas() devuelve las lineas en orden, incluyendo las vacias, y una lista vacia con un archivo vacio")
    void leerLineasDevuelveLasLineasEnOrden() throws IOException {
        File archivo = Files.writeString(carpeta.resolve("datos.txt"), "Primera linea\n\nTercera linea").toFile();
        File vacio = Files.writeString(carpeta.resolve("vacio.txt"), "").toFile();

        assertEquals(List.of("Primera linea", "", "Tercera linea"), LectorArchivo.leerLineas(archivo));
        assertTrue(LectorArchivo.leerLineas(vacio).isEmpty());
    }
 
    //Esta prueba verifica que un archivo que no existe tire FileNotFoundException
    @Test
    @DisplayName("leerLineas() tira FileNotFoundException cuando el archivo no existe")
    void archivoInexistenteTiraFileNotFoundException() {
        File inexistente = carpeta.resolve("no-existe.txt").toFile();

        assertThrows(FileNotFoundException.class, () -> LectorArchivo.leerLineas(inexistente));
    }

    //Esta prueba verifica que un error de lectura distinto tire IOException
    //usamos un Reader que falla a proposito para simular el error
    @Test
    @DisplayName("leerLineas() tira IOException cuando falla la lectura y no es FileNotFoundException")
    void errorDeLecturaTiraIOException() {
        Reader lectorQueFalla = new Reader() {
            @Override
            public int read(char[] buffer, int inicio, int cantidad) throws IOException {
                throw new IOException("Fallo simulado de lectura");
            }

            @Override
            public void close() {
            }
        };

        IOException excepcion = assertThrows(IOException.class, () -> LectorArchivo.leerLineas(lectorQueFalla));

        assertFalse(excepcion instanceof FileNotFoundException);
    }
}