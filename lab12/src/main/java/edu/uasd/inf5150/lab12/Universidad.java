package edu.uasd.inf5150.lab12;

import java.util.ArrayList;
import java.util.List;

public class Universidad{
	
	private final String nombre;
	private final List<Curso> cursosActivos;


    public Universidad(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre de la universidad no puede estar vacio ni ser nula");
        }

        
        this.nombre = nombre;
		this.cursosActivos= new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }
    
    public void agregarCurso(Curso curso) {
        if(curso==null)
        {
            throw new IllegalArgumentException("El curso no puede estar vacio ni ser nulo");
        }
        
        for(Curso existente:cursosActivos)
        {
        		if (existente.getCodigo().equalsIgnoreCase(curso.getCodigo())) {
        			throw new IllegalArgumentException(
                        "Ya tenemos un curso creado con este codigo: " + curso.getCodigo());
        		}
        }
        
    }

    public List<Curso> obtenerCursos() {
        return new ArrayList<>(cursosActivos);
    }
    
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

