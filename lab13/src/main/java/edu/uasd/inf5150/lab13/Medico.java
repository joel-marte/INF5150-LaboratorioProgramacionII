package edu.uasd.inf5150.lab13;

//Clase Medico que hereda de Empleado
public class Medico extends Empleado {
    //variable que guarda la especialidad del medico
    private final String especialidad;

    //Declare el constructor para aceptar el nombre, salario base y especialidad del medico
    public Medico(String nombre, double salarioBase, String especialidad) {
        //llamamos al constructor de la clase padre Empleado para que valide nombre y salario
        super(nombre, salarioBase);

        //si la especialidad esta vacia o es nula tira una excepcion
        if (especialidad == null || especialidad.isBlank()) {
            throw new IllegalArgumentException("La especialidad del medico no puede estar vacia ni ser nula");
        }

        //asignamos la especialidad del medico
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    //Modificamos el metodo trabajar() con el comportamiento de un doctor
    @Override
    public String trabajar() {
        return "Diagnosticando, revisando casos y atendiendo pacientes en " + especialidad;
    }
}