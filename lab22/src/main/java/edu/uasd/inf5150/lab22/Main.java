package edu.uasd.inf5150.lab22;

import java.io.PrintStream;

class Main {

    public static void main(String[] args) {

        CuentaBancaria cuenta = new CuentaBancaria("001-234567-8", 1000.0);

        //Aqui probamos un retiro valido, uno que supera el saldo y uno con cantidad invalida
        intentarRetiro(cuenta, 250.0, System.out);
        intentarRetiro(cuenta, 2000.0, System.out);
        intentarRetiro(cuenta, -50.0, System.out);

        System.out.println(cuenta);
    }

    //Esta funcion intenta el retiro y captura las excepciones en la salida que le pasemos
    //recibimos el PrintStream por parametro para poder probarla con JUnit
    static void intentarRetiro(CuentaBancaria cuenta, double cantidad, PrintStream salida) {
        try {
            cuenta.retirar(cantidad);
            salida.println(String.format("Retiro aprobado de %.2f. Saldo actual: %.2f", cantidad, cuenta.getSaldo()));
        } catch (FondosInsuficientesException e) {
            salida.println("Error: fondos insuficientes. No se puede hacer el retiro.");
        } catch (IllegalArgumentException e) {
            //aqui cae la cantidad invalida (cero, negativa o NaN)
            salida.println("Error: " + e.getMessage());
        }
    }
}