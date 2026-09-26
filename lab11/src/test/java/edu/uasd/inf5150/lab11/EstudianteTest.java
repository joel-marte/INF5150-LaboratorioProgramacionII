package edu.uasd.inf5150.lab11;

//Se importaron las funciones y utilidades de Junit  (Jupiter)
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

//este import static nos deja usar assertEquals(), assertTrue() y entre mas
//directamente sin tener que escribir Assertions para cada funcion
import static org.junit.jupiter.api.Assertions.*;

class EstudianteTest {
	
	//Esta prueba se encarga de crear y validar un objeto estudiante
    @Test
    @DisplayName("Crea un estudiante correctamente")
    void creaEstudianteValido() {
    	
        Estudiante estudiante = new Estudiante("Laura Diaz", 20, 90.0);
        
        //assertEquals() se encarga de verificar los getters devuelvan el valor correcto
        assertEquals("Laura Diaz", estudiante.getNombre());
        assertEquals(20, estudiante.getEdad());
        assertEquals(90.0, estudiante.getPromedio());
    }
    //Esta prueba verifica que si el promedio del estudiante es mayor a 70
    @Test
    @DisplayName("aprueba() se vuelve true cuando el promedio es mayor a 70")
    void apruebaPromedioMayor70() {
        Estudiante estudiante = new Estudiante("Pedro Luna", 22, 85.0);
        //asserTrue() verifica y nos dice prueba fallida cuando el valor bool es false 
        assertTrue(estudiante.aprueba());
    }
    
    //Esta prueba verifica que cuando el promedio del estudiante es igual a 70
    @Test
    @DisplayName("aprueba() se vuelve true exactamente cuando el promedio del estudiante es 70")
    void apruebaPromedioExactamente70() {
        Estudiante estudiante = new Estudiante("Sofia Gomez", 18, 70.0);
        assertTrue(estudiante.aprueba());
    }
    
    //Esta prueba verifica que si el promedio del estudiante es menor a 70
    @Test
    @DisplayName("aprueba() se vuelve false cuando el promedio es menor a 70")
    void noapruebaPromedioMenor70() {
        Estudiante estudiante = new Estudiante("Mario Cruz", 19, 59.99);
        //asserFalse() verifica y nos dice prueba fallida cuando el valor bool es true
        assertFalse(estudiante.aprueba());
    }
    
    //Esta prueba verifica que si el promedio del estudiante es menor a 70
    @Test
    @DisplayName("mostrarInformacion() debe tener nombre, edad, promedio y estatus de nota")
    void mostrarInformacionDatosClave() {
    	
        Estudiante estudiante = new Estudiante("Elena Vargas", 21, 75.0);
        String info = estudiante.mostrarInformacion();
        
        //con la funcion contains(), buscamos y verificamos en el texto dado por mostrarInformacion()
        //que tiene todos los datos correctamente del estudiante
        assertTrue(info.contains("Elena Vargas"));
        assertTrue(info.contains("21"));
        assertTrue(info.contains("75"));
        assertTrue(info.contains("Aprobado"));
    }
    
    //Esta prueba verifica que si el estudiante reprobo
    @Test
    @DisplayName("mostrarInformacion() nos dice Reprobado cuando el promedio del estudiante es menor a 70")
    void mostrarInformacionIndicaReprobado() {
        Estudiante estudiante = new Estudiante("Ivan Suarez", 20, 55.0);
        assertTrue(estudiante.mostrarInformacion().contains("Reprobado"));
    }
    
    //Esta prueba verifica una excepcion cuando la edad del estudiante es 0
    @Test
    @DisplayName("Tira una excepcion cuando la edad es 0")
    void lanzaExcepcionEdadCero() {
        assertThrows(IllegalArgumentException.class,
                () -> new Estudiante("Juan", 0, 80.0));
    }
    
    //Esta prueba verifica una excepcion cuando la edad del estudiante es negativa
    @Test
    @DisplayName("Tira una excepcion cuando la edad es negativa")
    void tiraExcepcionEdadNegativa() {
        assertThrows(IllegalArgumentException.class,
                () -> new Estudiante("Jose", -15, 80.0));
    }
    
    //Esta prueba verifica una excepcion cuando el promedio del estudiante es negativa
    @Test
    @DisplayName("Tira una excepcion cuando el promedio es negativo")
    void tiraExcepcionPromedioNegativo() {
        assertThrows(IllegalArgumentException.class,
                () -> new Estudiante("Carla", 20, -5.0));
    }
    
    //Esta prueba verifica una excepcion cuando el promedio del estudiante es mayor a 100
    @Test
    @DisplayName("Tira una excepcion cuando el promedio es mayor a 100")
    void tiraExcepcionPromedioMayor100() {
        assertThrows(IllegalArgumentException.class,
                () -> new Estudiante("Pedrito", 20, 100.25));
    }
    
    //Esta prueba verifica una excepcion cuando el nombre del estudiante es nulo o esta vacio
    @Test
    @DisplayName("Tira una excepcion cuando el nombre es nulo o vacio")
    void tiraExcepcionNombreInvalido() {
        assertThrows(IllegalArgumentException.class,
                () -> new Estudiante(null, 20, 80.0));
        assertThrows(IllegalArgumentException.class,
                () -> new Estudiante("   ", 20, 80.0));
    }

    //Luego tenemos una prueba parametrizada que nos ayuda automatizar los metodos @Test
    //Usamos @ParameterizedTest + @CsvSource para ejecutar los mismo metodos pero con valores
    //de entrada y salida diferentes
    @ParameterizedTest(name = "promedio={0} -> aprueba={1}")
    @DisplayName("Prueba parametrizada para diferentes promedios y verificacion de aprobacion final")
    @CsvSource({
            "0.0, false",
            "67.6, false",
            "70.5, true",
            "85.0, true",
            "100.0, true"
    })
    void apruebaDiferentesPromedios(double promedio, boolean esperado) {
        Estudiante estudiante = new Estudiante("Estudiante Prueba", 20, promedio);
        assertEquals(esperado, estudiante.aprueba());
    }
}