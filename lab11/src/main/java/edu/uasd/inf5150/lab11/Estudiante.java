package edu.uasd.inf5150.lab11;


public class Estudiante {
	
	//Utilize varibles static o constantes para la comparacion de las calif de los estudiantes
	//Pero tambien aplicque encapsulacion para que sus datos no se puedan modificar desde afuera
	//Esto nos permite tener un codigo mas legible al no tener que declarar las variables de nuevo
    private static final double PROMEDIO_MINIMO_VALIDO = 0.0;
    private static final double PROMEDIO_MAXIMO_VALIDO = 100.0;
    private static final double NOTA_MINIMA_APROBADA = 70.0;
    
    //Declaramos los atributos como final tambien para tener una buena encapsulacion
    //Debido a que sus datos no se pueden modificar desde afuera de la clases
    private final String nombre;
    private final int edad;
    private final double promedio;

    //Declare el constructor para aceptar los datos del estudiante
    public Estudiante(String nombre, int edad, double promedio) {
    	//agregue un if para que este lance una excepcion cuando nombre se null o este vacia
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede esta vacio y ser nulo.");
        }
        
    	//otro if para que este lance una excepcion cuando la eda sea negativa
        if (edad <= 0) {
            throw new IllegalArgumentException("La edad tiene que ser un numero positivo.");
        }
    	//otro if para que este lance una excepcion cuando el promedio sea mayor o menor 
        //a los promedios minimo o maximo que declare anteriormente
        if (promedio < PROMEDIO_MINIMO_VALIDO || promedio > PROMEDIO_MAXIMO_VALIDO) {
            throw new IllegalArgumentException(
                    "El promedio tiene que estar entre " + PROMEDIO_MINIMO_VALIDO + " y " + PROMEDIO_MAXIMO_VALIDO);
        }

        this.nombre = nombre;
        this.edad = edad;
        this.promedio = promedio;
    }

    //Estas funciones son los gettes los cuales nos permiten leer los 
    //datos del estudiante sin modificarlo y de forma individual
    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public double getPromedio() {
        return promedio;
    }

    //esta funcion nos deja saber si el estudiante aprobo con mayor o igual a 70
    public boolean aprueba() {
        return promedio >= NOTA_MINIMA_APROBADA;
    }

 
    //En esta le devolvemos al usuario toda la informacion del estudiante
    //si aprobo y reprobo mas sus datos bien organizados y legibles
    public String mostrarInformacion() {
        String estado = aprueba() ? "Aprobado" : "Reprobado";
        return String.format(
                "Estudiante: %s | Edad: %d | Promedio: %.2f | Estado: %s",
                nombre, edad, promedio, estado);
    }

    //por ultimo tenemos una funcion toString()
    //en el caso de que el usuario quiera imprimir el objeto de estudiante directamente
    //se le presente mostrarInformacion()
    @Override
    public String toString() {
        return mostrarInformacion();
    }
}