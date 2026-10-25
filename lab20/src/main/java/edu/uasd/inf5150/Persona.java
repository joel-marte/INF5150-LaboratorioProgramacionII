package edu.uasd.inf5150;

public class Persona {

    //Constantes con el rango de edad permitido
    public static final int EDAD_MINIMA = 0;
    public static final int EDAD_MAXIMA = 120;

    //variables que guardan el nombre y la edad de la persona
    private final String nombre;
    private final int edad;

    //Declare el constructor para aceptar nombre y edad
    //tira EdadInvalidaException si la edad es menor que 0 o mayor que 120
    public Persona(String nombre, int edad) throws EdadInvalidaException {
        //si el nombre es nulo o esta vacio tira una excepcion
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre de la persona no puede estar vacio ni ser nulo");
        }
        //si la edad esta fuera del rango tira nuestra excepcion personalizada
        if (edad < EDAD_MINIMA || edad > EDAD_MAXIMA) {
            throw new EdadInvalidaException(edad);
        }

        //asignamos el nombre y la edad de la persona
        this.nombre = nombre;
        this.edad = edad;
    }

    //Funciones getters
    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    //Funcion toString() para devolver los datos de la Persona para mostrar por consola
    @Override
    public String toString() {
        return String.format("[Nombre: %s], [Edad: %d]", nombre, edad);
    }
}