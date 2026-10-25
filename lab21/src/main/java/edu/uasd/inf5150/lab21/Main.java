package edu.uasd.inf5150.lab21;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

class Main {

    public static void main(String[] args) {

        //Aqui indicamos el archivo a leer, ubicado en la raiz del proyecto
        mostrarArchivo(new File("datos.txt"), System.out);
    }

    //Esta funcion muestra el contenido del archivo linea por linea en la salida que le pasemos
    //recibimos el PrintStream por parametro para poder probarla con JUnit
    static void mostrarArchivo(File archivo, PrintStream salida) {
        try {
            List<String> lineas = LectorArchivo.leerLineas(archivo);

            for (String linea : lineas) {
                salida.println(linea);
            }
        } catch (FileNotFoundException e) {
            //primero capturamos FileNotFoundException porque es la mas especifica
            salida.println("El archivo no fue encontrado");
        } catch (IOException e) {
            //aqui caen los demas errores de lectura
            salida.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}