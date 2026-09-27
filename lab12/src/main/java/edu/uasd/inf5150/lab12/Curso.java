package edu.uasd.inf5150.lab12;

public class Curso{
	
	private final String codigo;
	private final String nombre;
	private final Profesor profesorCursando;


    public Curso(String nombre, String codigo, Profesor profesorCursando) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del Curso no puede estar vacio ni ser nulo");
        }
        if (codigo == null || codigo.isBlank()) {

            throw new IllegalArgumentException("La especialidad no puede estar vacia ni ser nula");
        }
        if (profesorCursando == null) {

            throw new IllegalArgumentException("El curso necesita tener un profesor asignado");
        }
        
        this.nombre = nombre;
        this.codigo = codigo;
        this.profesorCursando= profesorCursando;
    }

    public String getNombre() {
        return nombre;
    }
    
    public String getCodigo() {
        return codigo;
    }

    public Profesor getProfesor() {
        return profesorCursando;
    }

  
    @Override
    public String toString() {
    	
    		return String.format("Codigo Curso: %s, Nombre: %s, Profesor: %s", codigo, nombre, profesorCursando);
    }
}

