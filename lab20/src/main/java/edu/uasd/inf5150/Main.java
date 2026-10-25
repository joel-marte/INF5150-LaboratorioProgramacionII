package edu.uasd.inf5150;

class Main {

    public static void main(String[] args) {


        //Aqui probamos una persona con una edad valida
        crearPersona("Ana Fernandez", 25);

        //Luego probamos dos edades invalidas, una por debajo y otra por encima del rango
        crearPersona("Luis Martinez", -5);
        crearPersona("Carla Diaz", 150);
    }

    //Esta funcion intenta crear una Persona y captura la excepcion si la edad no es valida
    private static void crearPersona(String nombre, int edad) {
        try {
            Persona persona = new Persona(nombre, edad);
            System.out.println("Persona creada: " + persona);
        } catch (EdadInvalidaException e) {
            //mostramos un mensaje explicativo para el usuario
            System.out.println("No se pudo crear a " + nombre + ": " + e.getMessage());
        }
    }
}