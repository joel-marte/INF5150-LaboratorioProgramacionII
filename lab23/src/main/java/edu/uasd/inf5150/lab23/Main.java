package edu.uasd.inf5150.lab23;

import java.io.PrintStream;
import java.util.Scanner;

class Main {

    //Constante con la palabra que termina el programa
    private static final String SALIR = "salir";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        //Con esta funcion comienza el ciclo que pide valores hasta recibir "salir"
        ejecutar(scanner, System.out);

        scanner.close();
    }

    //Esta funcion pide valores en un ciclo y los convierte, usando la entrada y salida que le pasemos
    //recibimos el Scanner y el PrintStream por parametro para poder probarla con JUnit
    static void ejecutar(Scanner entrada, PrintStream salida) {
        while (true) {
            salida.print("Ingrese un numero entero (escriba 'salir' para terminar programa): ");

            //si ya no hay mas lineas de entrada terminamos para evitar un error
            if (!entrada.hasNextLine()) {
                break;
            }

            String texto = entrada.nextLine();

            //si el usuario escribe "salir" sin importar mayu o minu se termina el programa
            if (texto.trim().equalsIgnoreCase(SALIR)) {
                salida.println("Programa terminado");
                break;
            }

            //en otro caso intentamos convertir el texto y capturamos la excepcion si no es numerico
            try {
                int numero = ConversorEntero.convertir(texto);
                salida.println("Numero convertido: " + numero);
            } catch (NumberFormatException e) {
                salida.println("El valor dado no es un numero valido");
            }
        }
    }
}