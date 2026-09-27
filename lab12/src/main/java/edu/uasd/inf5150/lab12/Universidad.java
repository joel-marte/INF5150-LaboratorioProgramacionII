package edu.uasd.inf5150.lab12;

import java.util.ArrayList;
import java.util.List;

public class Universidad{
	//variable que guarda el nombre de la Universidad
	private final String nombre;
	//Un arryaList de la clase Curso
	private final List<Curso> cursosActivos;

    //Declare el constructor para aceptar el nombre de la universidad
    public Universidad(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre de la universidad no puede estar vacio ni ser nula");
        }

        //asignamos el nombre de la universidad y inicializamos el arraylist de Curso
        this.nombre = nombre;
		this.cursosActivos= new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }
    
    //Esta funcion permite agregar los Curso a nuestra clase Universidad
    public void agregarCurso(Curso curso) {
    	//si el curso es null tira una excepcion
        if(curso==null)
        {
            throw new IllegalArgumentException("El curso no puede estar vacio ni ser nulo");
        }
        //en este for recorremos el arraylist de Curso y su este no es un duplicado se guarda
        //de lo contrario tira una excepcion de que ese curso esta creado y muestra el codigo
        for(Curso existente:cursosActivos)
        {
        		if (existente.getCodigo().equalsIgnoreCase(curso.getCodigo())) {
        			throw new IllegalArgumentException(
                        "Ya tenemos un curso creado con este codigo: " + curso.getCodigo());
        		}
        }
        //agregamos el curso
        cursosActivos.add(curso);
        
    }
    //esta funcion nos devuelve todos los cursos activos
    public List<Curso> obtenerCursos() {
        return new ArrayList<>(cursosActivos);
    }
    
    //Por utlimo esta funcion muestra todos los curso creados por consola
    public void listarCursos() {
        System.out.println("***Universidad: " + nombre + "***");
        System.out.println("Total de cursos activos: " + cursosActivos.size());

        if (cursosActivos.isEmpty()) {
            System.out.println("No hay ningun curso creado");
            return;
        }

        for (Curso curso : cursosActivos) {
            System.out.println("- " + curso);
        }
    }

  
}

