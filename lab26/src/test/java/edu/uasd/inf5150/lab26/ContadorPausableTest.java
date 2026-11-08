package edu.uasd.inf5150.lab26;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContadorPausableTest {

    @Test
    void debePausarEnDiezSinAvanzar() throws InterruptedException {
        List<Integer> contados = Collections.synchronizedList(new ArrayList<>());
        ContadorPausable contador = new ContadorPausable(contados::add);
        Thread hilo = new Thread(contador);

        hilo.start();
        contador.esperarHito();
        contador.pausar();
        contador.esperarPausado();

        // el hilo esta parado, asi que solo deben existir del 1 al 10
        assertEquals(10, contados.size());
        assertEquals(10, contados.get(contados.size() - 1));
        assertTrue(hilo.isAlive());

        // limpieza: comienza para que el hilo termine y no se quede esperando
        contador.reanudar();
        hilo.join(5000);
    }

    @Test
    void debeTerminarDelUnoAlVeinteTrasReanudar() throws InterruptedException {
        List<Integer> contados = Collections.synchronizedList(new ArrayList<>());
        ContadorPausable contador = new ContadorPausable(contados::add);
        Thread hilo = new Thread(contador);

        hilo.start();
        contador.esperarHito();
        contador.pausar();
        contador.esperarPausado();

        // sin Thread.sleep: se activa de inmediato en vez de esperar dos segundos
        contador.reanudar();
        hilo.join(5000);

        List<Integer> esperado = IntStream.rangeClosed(1, 20).boxed().toList();
        assertFalse(hilo.isAlive());
        assertEquals(esperado, contados);
    }
}