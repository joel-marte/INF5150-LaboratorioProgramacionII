package edu.uasd.inf5150.lab13;

//Clase Enfermero que hereda de Empleado
public class Enfermero extends Empleado {
    //variable que guarda el turno en el que trabaja el enfermero
    private final String turno;

    //Declare el constructor para aceptar el nombre, salario base y turno del enfermero
    public Enfermero(String nombre, double salarioBase, String turno) {
        //llamamos al constructor de la clase padre Empleado para que valide nombre y salario
        super(nombre, salarioBase);

        //si el turno esta vacio o es nulo tira una excepcion
        if (turno == null || turno.isBlank()) {
            throw new IllegalArgumentException("El turno del enfermero no puede estar vacio ni ser nulo");
        }

        //asignamos el turno del enfermero
        this.turno = turno;
    }

    public String getTurno() {
        return turno;
    }

    //Modificamos el metodo trabajar() con el comportamiento de un enfermero
    @Override
    public String trabajar() {
        return "Cuidando pacientes y administrando medicamentos en su correspondiente turno " + turno;
    }
}