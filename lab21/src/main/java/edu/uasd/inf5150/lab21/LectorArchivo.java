package edu.uasd.inf5150.lab21;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

//Clase que tiene solo la lectura del archivo, sin imprimir nada por consola
public class LectorArchivo {

    //Esta funcion lee un archivo y devuelve sus lineas en una lista
    //si el archivo no existe, FileReader tira una FileNotFoundException
    public static List<String> leerLineas(File archivo) throws IOException {
        //si el archivo es nulo tira una excepcion
        if (archivo == null) {
            throw new IllegalArgumentException("El archivo no puede ser nulo");
        }

        //el try con recursos cierra el archivo automaticamente al terminar
        try (FileReader lector = new FileReader(archivo, StandardCharsets.UTF_8)) {
            return leerLineas(lector);
        }
    }

    //Esta funcion lee las lineas desde cualquier Reader
    //la separamos para poder probar los errores de lectura con un Reader que falle
    public static List<String> leerLineas(Reader lector) throws IOException {
        if (lector == null) {
            throw new IllegalArgumentException("El lector no puede ser nulo");
        }

        List<String> lineas = new ArrayList<>();
        BufferedReader buffer = new BufferedReader(lector);

        //leemos linea por linea hasta que readLine() devuelva null (fin del archivo)
        String linea;
        while ((linea = buffer.readLine()) != null) {
            lineas.add(linea);
        }
        return lineas;
    }
}