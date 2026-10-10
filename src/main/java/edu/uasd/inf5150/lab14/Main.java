package edu.uasd.inf5150.lab14;

import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        //Aqui pedimos las dos fechas por consola y las convertimos a LocalDate
        LocalDate fecha1 = leerFecha(entrada, "Escriba la primera fecha en el formato (AAAA-MM-DD): ");
        LocalDate fecha2 = leerFecha(entrada, "Escriba la segunda fecha en el formato(AAAA-MM-DD): ");

        //Luego usamos la calculadora para obtener los resultados
        long dias = CalculadoraFechas.calcularDias(fecha1, fecha2);
        Period periodo = CalculadoraFechas.calcularPeriodo(fecha1, fecha2);
        String comparacion = CalculadoraFechas.compararFechas(fecha1, fecha2);

        //Con esto mostramos la informacion organizada por consola
        System.out.println();
        System.out.println("Dias pasados entre las fechas: " + dias);
        System.out.println("Años: " + periodo.getYears() + ", Meses: " + periodo.getMonths()
                + ", Dias: " + periodo.getDays());
        System.out.println("La segunda fecha es " + comparacion + " a la primera");

       entrada.close();
    }

    //Esta funcion pide los datos de entrada hasta que el usuario ponga una fecha valida
    //usando tamien un try catch para captar los errores
    private static LocalDate leerFecha(Scanner scanner, String datos) {
        while (true) {
            System.out.print(datos);
            String entrada = scanner.nextLine();

            try {
                return CalculadoraFechas.convertirFecha(entrada);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}