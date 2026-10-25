package edu.uasd.inf5150.lab22;

//Excepcion personalizada que se lanza cuando se intenta retirar mas dinero del que hay en la cuenta
//al heredar de Exception es una excepcion verificada, por eso Java obliga a capturarla o declararla
public class FondosInsuficientesException extends Exception {

    //variables que guardan el saldo disponible y la cantidad que se intento retirar
    private final double saldoDisponible;
    private final double cantidadSolicitada;

    //Declare el constructor para aceptar el saldo y la cantidad y armar un mensaje explicativo
    public FondosInsuficientesException(double saldoDisponible, double cantidadSolicitada) {
        super(String.format("Fondos insuficientes: saldo disponible %.2f, cantidad solicitada %.2f",
                saldoDisponible, cantidadSolicitada));
        this.saldoDisponible = saldoDisponible;
        this.cantidadSolicitada = cantidadSolicitada;
    }

    public double getSaldoDisponible() {
        return saldoDisponible;
    }

    public double getCantidadSolicitada() {
        return cantidadSolicitada;
    }
}