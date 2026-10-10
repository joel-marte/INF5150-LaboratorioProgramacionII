package edu.uasd.inf5150.lab13;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class HospitalTest {

	 //Esta prueba verifica que el sistema cree correctamente un Medico, un
    //Enfermero y un Administrativo, cada uno con su atributo propio ademas
    //del nombre y el salario base que heredan de Empleado
    @Test
    @DisplayName("El sistema crea correctamente cada tipo de empleado con sus datos propios")
    void sistemaCreaTipoEmpleadoDatos() {
        Medico medico = new Medico("Ana Fernandez", 85000.0, "Cardiologia");
        Enfermero enfermero = new Enfermero("Luis Martinez", 45000.0, "Nocturno");
        Administrativo administrativo = new Administrativo("Carla Diaz", 40000.0, "Recursos Humanos");

        assertEquals("Ana Fernandez", medico.getNombre());
        assertEquals("Cardiologia", medico.getEspecialidad());

        assertEquals("Luis Martinez", enfermero.getNombre());
        assertEquals("Nocturno", enfermero.getTurno());

        assertEquals("Carla Diaz", administrativo.getNombre());
        assertEquals("Recursos Humanos", administrativo.getDepartamento());
    }

    //Esta prueba verifica que ninguna subclase puede crear un empleado
    //con nombre vacio, salario negativo, o su atributo propio vacio,
    //ya que esas validaciones son una parte base de la seguridad del sistema
    @Test
    @DisplayName("Las subclases Medico(), Enfermero() y Administrativo() no aceptan datos invalidos")
    void sistemaRechazaEmpleadosConDatosInvalidos() {
        //nombre vacio o nulo (validacion heredada de Empleado)
        assertThrows(IllegalArgumentException.class, () -> new Medico("", 85000.0, "Cardiologia"));

        //salario negativo (validacion heredada de Empleado)
        assertThrows(IllegalArgumentException.class, () -> new Enfermero("Luis Martinez", -500.0, "Nocturno"));

        //atributo propio vacio (validacion especifica de la subclase)
        assertThrows(IllegalArgumentException.class, () -> new Administrativo("Carla Diaz", 40000.0, ""));
    }

    //Esta prueba verifica que el sistema tire una excepcion cuando un nombre
    //tenga espacios en blanco, usando la funcion isBlank() 
    @Test
    @DisplayName("El sistema tira una excepcion cuando un nombre contenga espacios en blanco")
    void sistemaRechazaNombreConEspacios() {
        assertThrows(IllegalArgumentException.class,
                () -> new Medico("   ", 85000.0, "Cardiologia"));
    }

    //Esta prueba verifica que el sistema acepte un salario base de exactamente
    //0, ya que la validacion solo rechaza valores negativos
    @Test
    @DisplayName("El sistema prueba que acepta un salario base de cero como valor limite valido")
    void sistemaAceptaSalarioBaseDeCero() {
        Enfermero enfermero = new Enfermero("Luis Martinez", 0.0, "Nocturno");

        assertEquals(0.0, enfermero.getSalarioBase());
    }

    //Esta prueba verifica que el mensaje de la excepcion tenga informacion
    //util para el usuario, y que no se tire cualquier excepcion generica
    @Test
    @DisplayName("El mensaje de la excepcion debe decir por que se rechazo el empleado")
    void mensajeExcepcionExplicaMotivoRechazo() {
        Exception excepcion = assertThrows(IllegalArgumentException.class,
                () -> new Administrativo("Carla Diaz", -200.0, "Recursos Humanos"));

        assertFalse(excepcion.getMessage().isBlank());
    }

    //Esta prueba verifica que el toString() heredado de Empleado combine
    //el nombre, el salario y la actividad (trabajar()) de cualquier subclase
    @Test
    @DisplayName("El toString() del empleado combina nombre, salario y actividad")
    void toStringCombinaNombreSalarioYActividad() {
        Empleado administrativo = new Administrativo("Carla Diaz", 40000.0, "Recursos Humanos");

        String texto = administrativo.toString();

        assertTrue(texto.contains("Carla Diaz"));
        assertTrue(texto.contains("Recursos Humanos"));
    }
}