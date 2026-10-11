package edu.uasd.inf5150.lab17;

import java.io.IOException;
import java.nio.file.Path;

class Main {

    public static void main(String[] args) {

        //Aqui creamos un estudiante con datos para el programa
        Estudiante original = new Estudiante(" ", "2024-0001", 92.5);
        Path ruta = Path.of("estudiante.dat");

        AlmacenEstudiante almacen = new AlmacenEstudiante();

        try {
            //Primero guardamos el objeto en el archivo binario
            almacen.guardar(ruta, original);
            System.out.println("Estudiante guardado en: " + ruta.toAbsolutePath());

            //Luego lo leemos de nuevo y mostramos la informacion recuperada
            Estudiante recuperado = almacen.leer(ruta);

            System.out.println();
            System.out.println("Informacion recuperada:");
            System.out.println("Nombre: " + recuperado.getNombre());
            System.out.println("Matricula: " + recuperado.getMatricula());
            System.out.println("Promedio: " + recuperado.getPromedio());
        } catch (IOException e) {
            System.out.println("Error con el archivo: " + e.getMessage());
        }
    }
}