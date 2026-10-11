package edu.uasd.inf5150.lab17;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class AlmacenEstudianteTest {

    //Carpeta temporal que JUnit crea y borra automaticamente para cada prueba
    @TempDir
    Path carpeta;

    private final AlmacenEstudiante almacen = new AlmacenEstudiante();

    //Esta prueba verifica que Estudiante sea Serializable y que se recuperen los mismos datos
    @Test
    @DisplayName("guardar() y leer() recuperan un Estudiante con los mismos datos")
    void guardarYLeerRecuperaLosMismosDatos() throws IOException {
        Estudiante original = new Estudiante("Ana Perez", "2024-0001", 92.5);
        Path ruta = carpeta.resolve("estudiante.dat");

        almacen.guardar(ruta, original);
        Estudiante recuperado = almacen.leer(ruta);

        assertInstanceOf(Serializable.class, original);
        assertNotSame(original, recuperado);
        assertEquals("Ana Perez", recuperado.getNombre());
        assertEquals("2024-0001", recuperado.getMatricula());
        assertEquals(92.5, recuperado.getPromedio());
    }

    //Esta prueba verifica que se cree el archivo y que al guardar otra vez se sobrescriba
    @Test
    @DisplayName("guardar() crea el archivo binario y lo sobrescribe si ya existe")
    void guardarCreaYSobrescribeElArchivo() throws IOException {
        Path ruta = carpeta.resolve("estudiante.dat");

        almacen.guardar(ruta, new Estudiante("Ana Perez", "2024-0001", 92.5));
        assertTrue(Files.exists(ruta));
        assertTrue(Files.size(ruta) > 0);

        almacen.guardar(ruta, new Estudiante("Luis Gomez", "2024-0002", 80));
        assertEquals("Luis Gomez", almacen.leer(ruta).getNombre());
    }

    //Esta prueba verifica que un archivo inexistente o con texto normal tire una IOException
    @Test
    @DisplayName("leer() tira una IOException si el archivo no existe o no es un objeto serializado")
    void leerArchivoInvalidoTiraIOException() throws IOException {
        assertThrows(IOException.class, () -> almacen.leer(carpeta.resolve("no-existe.dat")));

        Path texto = Files.writeString(carpeta.resolve("texto.dat"), "esto no es un objeto serializado");
        assertThrows(IOException.class, () -> almacen.leer(texto));
    }

    //Esta prueba verifica las validaciones del estudiante y de los parametros de guardar()
    @ParameterizedTest
    @CsvSource({
            "'', 2024-0001, 90",
            "Ana, '', 90",
            "Ana, 2024-0001, -1",
            "Ana, 2024-0001, 100.5"
    })
    @DisplayName("Estudiante() y guardar() tiran una excepcion con datos invalidos")
    void datosInvalidosTiranExcepcion(String nombre, String matricula, double promedio) {
        assertThrows(IllegalArgumentException.class, () -> new Estudiante(nombre, matricula, promedio));

        Estudiante valido = new Estudiante("Ana", "2024-0001", 90);
        assertThrows(IllegalArgumentException.class, () -> almacen.guardar(null, valido));
        assertThrows(IllegalArgumentException.class, () -> almacen.guardar(carpeta.resolve("x.dat"), null));
    }
}