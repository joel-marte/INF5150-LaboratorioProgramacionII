package edu.uasd.inf5150.lab19;

import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        //Aqui pedimos los dos numeros enteros por consola
        int dividendo = leerEntero(scanner, "Ingrese el dividendo (numero entero): ");
        int divisor = leerEntero(scanner, "Ingrese el divisor (numero entero): ");

        //Luego hacemos la division y mostramos el resultado por consola
        hacerDivision(dividendo, divisor, System.out);

        scanner.close();
    }

    //Esta funcion hace la division y muestra los mensajes en la salida que le pasemos
    //recibimos el PrintStream por parametro para poder probarla con JUnit
    static void hacerDivision(int dividendo, int divisor, PrintStream salida) {
        try {
            int resultado = Division.dividir(dividendo, divisor);
            salida.println("Resultado: " + resultado);
        } catch (ArithmeticException e) {
            //si el divisor es cero se captura la excepcion y se muestra este mensaje
            salida.println("No se puede dividir entre cero");
        } finally {
            //el bloque finally se ejecuta siempre, haya o no excepcion
            salida.println("Fin del programa");
        }
    }

    //Esta funcion le pedimos al numero usuario hasta que el usuario escriba un numero entero valido
    private static int leerEntero(Scanner scanner, String impresion) {
        while (true) {
            System.out.print(impresion);

            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error: debe ingresar un numero entero");
                //descartamos el texto   para no quedar en un ciclo infinito
                scanner.next();
            }
        }
    }
}