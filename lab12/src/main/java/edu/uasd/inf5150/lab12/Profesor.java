package edu.uasd.inf5150.lab12;

public class Profesor {
	
	private final String nombre;
	private final String especialidad;

    public Profesor(String nombre, String especialidad) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del profesor no puede estar vacio ni ser nulo");
        }
        if (especialidad == null || especialidad.isBlank()) {
            throw new IllegalArgumentException("La especialidad no puede estar vacia ni ser nula");
        }
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

  
    @Override
    public String toString() {
    	
    		return String.format("Nombre: %s, Especialidad: %s", nombre, especialidad);
    }
}
