package edu.uasd.inf5150.lab17;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputFilter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

//Clase que tiene solo la logica de escribir y leer un Estudiante en un archivo binario
public class AlmacenEstudiante {

    //Filtro que solo deja leer la clase Estudiante y las clases basicas de java.lang
    //asi evitamos que el archivo cargue otros objetos inesperados
    private static final String FILTRO = "edu.uasd.inf5150.lab17.Estudiante;java.lang.*;!*";

    //Esta funcion guarda un estudiante en un archivo binario usando ObjectOutputStream
    //si el archivo ya existe lo sobrescribe
    public void guardar(Path ruta, Estudiante estudiante) throws IOException {
        if (ruta == null) {
            throw new IllegalArgumentException("La ruta del archivo no puede ser nula");
        }
        if (estudiante == null) {
            throw new IllegalArgumentException("El estudiante no puede ser nulo");
        }

        //el try con recursos cierra los flujos automaticamente
        try (OutputStream salida = Files.newOutputStream(ruta);
             ObjectOutputStream objetos = new ObjectOutputStream(salida)) {
            objetos.writeObject(estudiante);
        }
    }

    //Esta funcion lee un estudiante desde un archivo binario usando ObjectInputStream
    //tira IOException si el archivo no existe o no contiene un Estudiante valido
    public Estudiante leer(Path ruta) throws IOException {
        if (ruta == null) {
            throw new IllegalArgumentException("La ruta del archivo no puede ser nula");
        }

        try (InputStream entrada = Files.newInputStream(ruta);
             ObjectInputStream objetos = new ObjectInputStream(entrada)) {

            objetos.setObjectInputFilter(ObjectInputFilter.Config.createFilter(FILTRO));

            Object leido = objetos.readObject();
            if (!(leido instanceof Estudiante)) {
                throw new IOException("El archivo no contiene un Estudiante");
            }
            return (Estudiante) leido;
        } catch (ClassNotFoundException e) {
            throw new IOException("El archivo contiene una clase desconocida: " + e.getMessage(), e);
        }
    }
}