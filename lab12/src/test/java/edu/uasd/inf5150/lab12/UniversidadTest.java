package edu.uasd.inf5150.lab12;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class UniversidadTest {

    private Universidad universidad;
    private Profesor profesor;

    //@BeforeEach lo ejecutamos ANTES de cada prueba individual,
    // para de esta forma tener estado limpio y no repetir codigo.
    @BeforeEach
    void setUp() {
        universidad = new Universidad("UASD");
        profesor = new Profesor("Ana Torres", "Ciberseguridad");
    }
	//Esta prueba se encarga de crear y validar un objeto Universidad
    @Test
    @DisplayName("Crea una universidad funcional y valida")
    void creaUniversidadValida() {
        assertEquals("UASD", universidad.getNombre());
        assertTrue(universidad.obtenerCursos().isEmpty());
    }
	//Esta prueba se encarga de crear y validar un objeto Curso
    @Test
    @DisplayName("agregarCurso() agrega un curso que sea valido")
    void agregarCursoValido() {
        Curso curso = new Curso("INF-5150", "Lenguaje de Programación II", profesor);
        universidad.agregarCurso(curso);

        assertEquals(1, universidad.obtenerCursos().size());
        assertEquals("INF-5150", universidad.obtenerCursos().get(0).getCodigo());
    }
    
	//Esta prueba tira una excepcion cuando hay codigo ya creado
    @Test
    @DisplayName("agregarCurso() tira una excepción si el codigo ya esta creado")
    void agregarCursoConCodigoDuplicado() {
        Curso curso1 = new Curso("INF-5150", "Lenguaje de Programación II", profesor);
        Curso curso2 = new Curso("INF-5150", "Otro nombre", profesor);

        universidad.agregarCurso(curso1);

        assertThrows(IllegalArgumentException.class,
                () -> universidad.agregarCurso(curso2));

        // Aqui verificamos que el curso duplicado no se agrego
        assertEquals(1, universidad.obtenerCursos().size());
    }

	//Esta prueba tira una excepcio si detecta cursos duplicados con o sin mayusculas/minusculas
    @Test
    @DisplayName("agregarCurso() detecta duplicados con mayusculas o minusculas")
    void agregarCursoCodigoDuplicadoMayusculasMiniusculas() {
        Curso curso1 = new Curso("inf-5150", "Curso 1", profesor);
        Curso curso2 = new Curso("INF-5150", "Curso 2", profesor);

        universidad.agregarCurso(curso1);

        assertThrows(IllegalArgumentException.class,
                () -> universidad.agregarCurso(curso2));
    }
    
	//Esta prueba tira una excepcion si detecta que el curso es null
    @Test
    @DisplayName("agregarCurso() tira una excepcion si el curso es nulo")
    void agregarCursoNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> universidad.agregarCurso(null));
    }

	//Esta prueba verifica que obtenerCurso() funciona correctamente siempre que sus codigos son diferentes
    @Test
    @DisplayName("obtenerCursos() permite agregar varios cursos con codigos diferentes")
    void agregarVariosCursosDistintos() {
        universidad.agregarCurso(new Curso("INF-5150", "Curso A", profesor));
        universidad.agregarCurso(new Curso("INF-4020", "Curso B", profesor));
        universidad.agregarCurso(new Curso("INF-3010", "Curso C", profesor));

        assertEquals(3, universidad.obtenerCursos().size());
    }

	//Esta prueba verifica la execepcion del constructor de Universidad cuando nombre esta vacio o es nulo
    @Test
    @DisplayName("Constructor de Universidad tira excepcion si el nombre esta vacio o es nulo")
    void universidadNombreInvalido() {
        assertThrows(IllegalArgumentException.class, () -> new Universidad(null));
        assertThrows(IllegalArgumentException.class, () -> new Universidad("   "));
    }
    
	//Esta prueba verifica que obtenerCurso() funciona correctamente siempre que sus codigos son diferentes
    @Test
    @DisplayName("Constructor de Curso tira una excepcion si el profesor esta vacio o es nulo")
    void cursoProfesorInvalido() {
        assertThrows(IllegalArgumentException.class,
                () -> new Curso("INF-5150", "Curso A", null));
    }
    
	//Esta prueba verifica que obtenerCurso() funciona correctamente siempre que sus codigos son diferentes
    @Test
    @DisplayName("Constructor de Profesor tira una excepción si su especialidad esta vacia o es nula")
    void profesorEspecialidadInvalida() {
        assertThrows(IllegalArgumentException.class,
                () -> new Profesor("Juan", null));
        assertThrows(IllegalArgumentException.class,
                () -> new Profesor("Juan", "   "));
    }
}