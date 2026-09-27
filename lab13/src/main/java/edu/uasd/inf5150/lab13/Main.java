package edu.uasd.inf5150.lab13;

import java.util.ArrayList;
import java.util.List;

class Main {

    public static void main(String[] args) {

        //Aqui creamos la lista de Empleado, que puede tener cualquier subclase
        //Medico, Enfermero o Administrativo usando el polimorfismo
        List<Empleado> empleados = new ArrayList<>();

        //Luego tenemos los ejemplos de cada tipo de empleado del hospital
        Medico medico1 = new Medico("Ana Fernandez", 85000.0, "Cardiologia");
        Enfermero enfermero1 = new Enfermero("Luis Martinez", 45000.0, "Nocturno");
        Administrativo administrativo1 = new Administrativo("Carla Diaz", 40000.0, "Recursos Humanos");

        //Despues agregamos estos empleados a la lista
        empleados.add(medico1);
        empleados.add(enfermero1);
        empleados.add(administrativo1);

        //Con este for recorremos la lista de forma polimorfica, sin saber
        //si es un Medico, Enfermero o Administrativo, y mostramos sus datos
        for (Empleado empleado : empleados) {
            System.out.println("Nombre: " + empleado.getNombre());
            System.out.println("Salario: " + empleado.getSalarioBase());
            System.out.println("Actividad: " + empleado.trabajar());
            System.out.println();
        }
    }
}