package edu.uasd.inf5150.lab13;

//Clase abstracta que representa un Empleado generico del hospital
public abstract class Empleado {
    //variables que guardan el nombre y salario del empleado
    private final String nombre;
    private final double salarioBase;

    //Declare el constructor para aceptar el nombre y el salario base del empleado
    public Empleado(String nombre, double salarioBase) {
        //si el nombre esta vacio o es nulo tira una excepcion
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del empleado no puede estar vacio ni ser nulo");
        }
        //si el salario base es negativo tira una excepcion
        if (salarioBase < 0) {
            throw new IllegalArgumentException("El salario base no puede ser negativo");
        }

        //asignamos el nombre y el salario base del empleado
        this.nombre = nombre;
        this.salarioBase = salarioBase;
    }

    //Funciones getters
    public String getNombre() {
        return nombre;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    //Declare el metodo abstracto que cada subclase (Medico, Enfermero, Administrativo) tiene 
    //que implementar con su propio comportamiento
    public abstract String trabajar();

    //Funcion toString() devuelve los datos string del Empleado para mostrar por consola
    @Override
    public String toString() {
        return String.format("[Nombre: %s], [Salario: %.2f], [Actividad: %s]", nombre, salarioBase, trabajar());
    }
}