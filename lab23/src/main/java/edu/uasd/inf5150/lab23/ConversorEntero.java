package edu.uasd.inf5150.lab23;

//Clase que tiene solo la conversion de texto a entero, sin leer ni imprimir por consola
public class ConversorEntero {

    //Esta funcion convierte un texto a entero usando Integer.parseInt()
    //quita los espacios al inicio y al final antes de convertir
    //si el texto no es un entero valido, Integer.parseInt() tira una NumberFormatException
    //y la clase que la use es la encargada de capturarla
    public static int convertir(String texto) {
        //si el texto es nulo, parseInt tambien tira una NumberFormatException
        if (texto == null) {
            return Integer.parseInt(null);
        }
        return Integer.parseInt(texto.trim());
    }
}