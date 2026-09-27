package edu.uasd.inf5150.lab12;

public class Curso{
	//variables del Curso
	private final String codigo;
	private final String nombre;
	private final Profesor profesorCursando;

	//El constructor acepta nombre, codigo y tipo Profesor
	//tira excepcion si esta los parametros estan vacion o son nulos
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
    //Funciones getters
    public String getNombre() {
        return nombre;
    }
    
    public String getCodigo() {
        return codigo;
    }

    public Profesor getProfesor() {
        return profesorCursando;
    }

    //Funcion toString() para devolver datos string del Curso para mostrar por consola
    @Override
    public String toString() {
    	
    		return String.format("Codigo Curso: %s, Nombre: %s, Profesor: %s", codigo, nombre, profesorCursando);
    }
}

