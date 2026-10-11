package edu.uasd.inf5150.lab17;

import java.io.Serializable;

//Clase que representa un estudiante con nombre, matricula y promedio
//implementa Serializable para que podamos guardarla en un archivo binario
public class Estudiante implements Serializable {

    //Identificador de version de la clase, necesario para leer lo que se guardo
    private static final long serialVersionUID = 1L;

    private final String nombre;
    private final String matricula;
    private final double promedio;

    //Declare el constructor para aceptar nombre, matricula y promedio
    public Estudiante(String nombre, String matricula, double promedio) {
        //si el nombre o la matricula estan vacios, o el promedio esta fuera de 0 a 100, tira una excepcion
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio ni ser nulo");
        }
        if (matricula == null || matricula.isBlank()) {
            throw new IllegalArgumentException("La matricula no puede estar vacia ni ser nula");
        }
        if (Double.isNaN(promedio) || promedio < 0 || promedio > 100) {
            throw new IllegalArgumentException("El promedio debe estar entre 0 y 100");
        }

        this.nombre = nombre.trim();
        this.matricula = matricula.trim();
        this.promedio = promedio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMatricula() {
        return matricula;
    }

    public double getPromedio() {
        return promedio;
    }

    //Esta funcion nos da la informacion del estudiante en un texto
    @Override
    public String toString() {
        return "Estudiante{nombre=" + nombre + ", matricula=" + matricula + ", promedio=" + promedio + "}";
    }
}