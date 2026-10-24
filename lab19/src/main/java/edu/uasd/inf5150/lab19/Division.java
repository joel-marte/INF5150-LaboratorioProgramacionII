package edu.uasd.inf5150.lab19;

//Clase que tiene solo la operacion de division, sin leer ni imprimir por consola
public class Division {

    //Esta funcion divide dos numeros enteros
    //si el divisor es cero, Java tira una ArithmeticException por si solo
    public static int dividir(int dividendo, int divisor) {
        return dividendo / divisor;
    }
}