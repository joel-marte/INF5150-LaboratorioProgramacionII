package edu.uasd.inf5150.lab14;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class CalculadoraFechas {

    //Constantes con los textos de resultado al comparar las dos fechas
    public static final String DESPUES = "DESPUES";
    public static final String ANTES = "ANTES";
    public static final String IGUAL = "IGUAL";

    //Esta funcion convierte un texto con formato AAAA-MM-DD a LocalDate
    //si el texto es nulo, esta vacio o no es una fecha real tira una excepcion
    public static LocalDate convertirFecha(String texto) {
        if (texto == null || texto.isBlank()) {
            throw new IllegalArgumentException("Debe poner una fecha valida y no puede estar vacia ni ser nula");
        }

        try {
            //Aqui hacemos el parse a localdate por defecto el formato AAAA-MM-DD
            return LocalDate.parse(texto.trim());
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Es una fecha invalida, ponga la fecha en el formato AAAA-MM-DD: " + texto);
        }
    }

    //Esta funcion se usa para calcular cuantos dias existen entre dos fechas
    //tener encuenta que el resultado puede ser negativo si la segunda fecha es anterior a la primera
    public static long calcularDias(LocalDate inicio, LocalDate fin) {
        validarFechas(inicio, fin);
        return ChronoUnit.DAYS.between(inicio, fin);
    }

    //Esta funcion puede calcular los años, meses y dias que han pasado usando Period
    public static Period calcularPeriodo(LocalDate inicio, LocalDate fin) {
    	//validadomos la fechas 
        validarFechas(inicio, fin);
        return Period.between(inicio, fin);
    }

    //Esta funcion nos deja saber si la segunda fecha es posterior
    public static String compararFechas(LocalDate inicio, LocalDate fin) {
        validarFechas(inicio, fin);

        if (fin.isAfter(inicio)) {
            return DESPUES;
        }
        if (fin.isBefore(inicio)) {
            return ANTES;
        }
        return IGUAL;
    }

    //Esta funcion nos ayuda a no repetir la validacion de fechas nulas
    private static void validarFechas(LocalDate inicio, LocalDate fin) {
        if (inicio == null || fin == null) {
            throw new IllegalArgumentException("Las fechas no pueden estar nulas");
        }
    }
}