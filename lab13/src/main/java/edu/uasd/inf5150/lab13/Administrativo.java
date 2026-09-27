package edu.uasd.inf5150.lab13;

//Clase Administrativo que hereda de Empleado
public class Administrativo extends Empleado {
    //variable que guarda el departamento donde trabaja el administrativo
    private final String departamento;

    //Declare el constructor para aceptar el nombre, salario base y departamento del administrativo
    public Administrativo(String nombre, double salarioBase, String departamento) {
        //llamamos al constructor de la clase padre Empleado para que valide su nombre y salario
        super(nombre, salarioBase);

        //si el departamento esta vacio o es nulo tira una excepcion
        if (departamento == null || departamento.isBlank()) {
            throw new IllegalArgumentException("El departamento del administrativo no puede estar vacio ni ser nulo");
        }

        //asignamos el departamento del administrativo
        this.departamento = departamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    //Modificamos el metodo trabajar() con el comportamiento de un administrativo
    @Override
    public String trabajar() {
        return "Creando reportes o gestionando tareas administrativas en el departamento de " + departamento;
    }
}