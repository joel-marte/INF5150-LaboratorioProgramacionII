package edu.uasd.inf5150.lab13;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class EmpleadoTest {

	//Esta prueba verifica que la lista de Empleado acepte las tres subclases
    //al mismo tiempo, sin importar que cada una tenga su propio atributo extra
    @Test
    @DisplayName("La lista de Empleado acepta Medico, Enfermero y Administrativo polimorficamente")
    void listaDeEmpleadosAceptaLasTresSubclases() {
        List<Empleado> empleados = new ArrayList<>();

        empleados.add(new Medico("Ana Fernandez", 85000.0, "Cardiologia"));
        empleados.add(new Enfermero("Luis Martinez", 45000.0, "Nocturno"));
        empleados.add(new Administrativo("Carla Diaz", 40000.0, "Recursos Humanos"));

        assertEquals(3, empleados.size());
        assertTrue(empleados.get(0) instanceof Medico);
        assertTrue(empleados.get(1) instanceof Enfermero);
        assertTrue(empleados.get(2) instanceof Administrativo);
    }

    //Esta es la prueba verifica el polimorfismo, recorriendo la lista con
    //la referencia Empleado (sin importar la subclase real de cada objeto), y
    //verificamos que trabajar() devuelva el comportamiento propio de cada uno
    @Test
    @DisplayName("trabajar() se comporta distinto en cada subclase al recorrer la lista de forma polimorfica")
    void trabajarComportaDistintoSegunSubclaseReal() {
        List<Empleado> empleados = new ArrayList<>();

        empleados.add(new Medico("Ana Fernandez", 85000.0, "Cardiologia"));
        empleados.add(new Enfermero("Luis Martinez", 45000.0, "Nocturno"));
        empleados.add(new Administrativo("Carla Diaz", 40000.0, "Recursos Humanos"));

        //recorremos la lista usando la referencia de Empleado, sin tener que pasear
        //a ninguna subclase, y vemos como cada metodo trabajar() responde diferente
        assertTrue(empleados.get(0).trabajar().contains("Cardiologia"));
        assertTrue(empleados.get(1).trabajar().contains("Nocturno"));
        assertTrue(empleados.get(2).trabajar().contains("Recursos Humanos"));
    }

    //Esta prueba verifica que dos empleados de subclases distintas nunca
    //nos devuelvan un mismo texto en trabajar(), lo que deja ver que cada subclase
    //tiene su propio comportamiento y no comparten logica
    @Test
    @DisplayName("Cada subclase devuelve un texto distinto en trabajar(), sin repetirse entre ellas")
    void cadaSubclaseDevuelveTextoDistintoTrabajar() {
        Empleado medico = new Medico("Ana Fernandez", 85000.0, "Cardiologia");
        Empleado enfermero = new Enfermero("Luis Martinez", 45000.0, "Nocturno");
        Empleado administrativo = new Administrativo("Carla Diaz", 40000.0, "Recursos Humanos");

        assertNotEquals(medico.trabajar(), enfermero.trabajar());
        assertNotEquals(enfermero.trabajar(), administrativo.trabajar());
        assertNotEquals(medico.trabajar(), administrativo.trabajar());
    }

    //Esta prueba verifica que aunque recorramos la lista con la referencia
    //generica de Empleado, cada objeto internamente sigue teniendo su subclase
    //real, y que se pueda hacer un parseo seguro para tener acceso sus atributos propio
    @Test
    @DisplayName("Cada Empleado en la lista guarda su tipo real y permite parsear a su subclase")
    void cadaEmpleadoMantieneSuTipoRealEnLaLista() {
        List<Empleado> empleados = new ArrayList<>();
        empleados.add(new Medico("Ana Fernandez", 85000.0, "Cardiologia"));

        Empleado primero = empleados.get(0);

        assertTrue(primero instanceof Medico);

        Medico medico = (Medico) primero;
        assertEquals("Cardiologia", medico.getEspecialidad());
    }

    //Esta prueba verifica que el recorrido polimorfico no se rompa ni cambie
    //de comportamiento sin importar en que orden se agreguen los empleados
    //a la lista, confirmando que cada trabajar() depende solo de su objeto real
    @Test
    @DisplayName("El orden en la lista no afecta el resultado de trabajar() de cada empleado")
    void ordenListaNoAfectaResultadoTrabajar() {
        List<Empleado> empleados = new ArrayList<>();

        empleados.add(new Administrativo("Carla Diaz", 40000.0, "Recursos Humanos"));
        empleados.add(new Medico("Ana Fernandez", 85000.0, "Cardiologia"));
        empleados.add(new Enfermero("Luis Martinez", 45000.0, "Nocturno"));

        assertTrue(empleados.get(0).trabajar().contains("Recursos Humanos"));
        assertTrue(empleados.get(1).trabajar().contains("Cardiologia"));
        assertTrue(empleados.get(2).trabajar().contains("Nocturno"));
    }}