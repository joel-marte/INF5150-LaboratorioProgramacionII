package edu.uasd.inf5150.lab22;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class CuentaBancariaTest {

    //Esta prueba verifica un retiro valido: el saldo tiene que bajar exactamente la cantidad retirada
    @Test
    @DisplayName("retirar() descuenta la cantidad del saldo con un retiro valido")
    void retiroValidoDescuentaDelSaldo() throws FondosInsuficientesException {
        CuentaBancaria cuenta = new CuentaBancaria("001-234567-8", 1000.0);

        cuenta.retirar(250.0);

        assertEquals(750.0, cuenta.getSaldo(), 0.001);
    }

    //Esta prueba verifica el caso limite: se puede retirar exactamente todo el saldo 
    @Test
    @DisplayName("retirar() permite retirar exactamente todo el saldo y lo deja en cero")
    void retirarTodoElSaldoEsValido() throws FondosInsuficientesException {
        CuentaBancaria cuenta = new CuentaBancaria("001-234567-8", 500.0);

        cuenta.retirar(500.0);

        assertEquals(0.0, cuenta.getSaldo(), 0.001);
    }

    //Esta prueba verifica que una cantidad mayor que el saldo tire FondosInsuficientesException,
    //que la excepcion guarde los datos del intento y que el saldo no cambie
    @Test
    @DisplayName("retirar() tira FondosInsuficientesException si la cantidad es mayor que el saldo y no cambia el saldo")
    void retiroMayorQueElSaldoTiraExcepcion() {
        CuentaBancaria cuenta = new CuentaBancaria("001-234567-8", 1000.0);

        FondosInsuficientesException excepcion = assertThrows(FondosInsuficientesException.class,
                () -> cuenta.retirar(1000.01));

        assertEquals(1000.0, excepcion.getSaldoDisponible(), 0.001);
        assertEquals(1000.01, excepcion.getCantidadSolicitada(), 0.001);
        assertEquals(1000.0, cuenta.getSaldo(), 0.001);
    }

    //Esta prueba verifica que una cantidad cero o negativa tire IllegalArgumentException
    //y que el saldo no cambie
    @ParameterizedTest
    @CsvSource({"0", "-0.01", "-50"})
    @DisplayName("retirar() tira IllegalArgumentException si la cantidad es cero o negativa y no cambia el saldo")
    void cantidadInvalidaTiraExcepcion(double cantidad) {
        CuentaBancaria cuenta = new CuentaBancaria("001-234567-8", 1000.0);

        assertThrows(IllegalArgumentException.class, () -> cuenta.retirar(cantidad));

        assertEquals(1000.0, cuenta.getSaldo(), 0.001);
    }
}
