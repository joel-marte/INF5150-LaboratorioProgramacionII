package edu.uasd.inf5150.lab11;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Creamos los objetos de estudiante con sus datos pasados al constructor
        Estudiante estudiante1 = new Estudiante("Joel Marte", 21, 85.5);
        Estudiante estudiante2 = new Estudiante("Ana Perez", 19, 62.0);
        Estudiante estudiante3 = new Estudiante("Carlos Ggmez", 23, 70.0);
        
        //Este un arreglo tipo estudiante para tenerlos en memeoria
        Estudiante[] estudiantes = { estudiante1, estudiante2, estudiante3 };

        System.out.println("***Reporte de Estudiantes**");
        //usando el for each se impremen todos los datos de los estudiantes que tenemos en el arreglo
        for (Estudiante estudiante : estudiantes) {
            System.out.println(estudiante.mostrarInformacion());
        }
        
	}

}
