package edu.uasd.inf5150.lab22;

//Clase CuentaBancaria que guarda el numero de cuenta y el saldo, y permite retirar dinero
public class CuentaBancaria {

    //variable que guarda el numero de cuenta, no cambia despues de creada
    private final String numeroCuenta;
    //variable que guarda el saldo, no es final porque cambia con cada retiro
    private double saldo;

    //Declare el constructor para aceptar el numero de cuenta y el saldo inicial
    public CuentaBancaria(String numeroCuenta, double saldo) {
        //si el numero de cuenta es nulo o esta vacio tira una excepcion
        if (numeroCuenta == null || numeroCuenta.isBlank()) {
            throw new IllegalArgumentException("El numero de cuenta no puede estar vacio ni ser nulo");
        }
        //si el saldo inicial es negativo tira una excepcion
        if (saldo < 0) {
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo");
        }

        //asignamos el numero de cuenta y el saldo inicial
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
    }

    //Funciones getters
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    //Esta funcion permite retirar dinero de la cuenta
    //tira IllegalArgumentException si la cantidad no es positiva
    //y FondosInsuficientesException si la cantidad es mayor que el saldo
    public void retirar(double cantidad) throws FondosInsuficientesException {
        
        if (!(cantidad > 0)) {
            throw new IllegalArgumentException("La cantidad a retirar debe ser mayor que cero");
        }
        if (cantidad > saldo) {
            throw new FondosInsuficientesException(saldo, cantidad);
        }

        //si llegamos hasta aqui, el retiro es valido y descontamos la cantidad del saldo
        saldo -= cantidad;
    }

    //Funcion toString() para devolver los datos de la cuenta para mostrar por consola
    @Override
    public String toString() {
        return String.format("Cuenta: %s, Saldo: %.2f", numeroCuenta, saldo);
    }
}