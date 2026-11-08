package edu.uasd.inf5150.lab26;

import java.util.function.IntConsumer;

public class ContadorPausable implements Runnable {

    private static final int HITO = 10;
    private static final int LIMITE = 20;

    // cada numero en main lo imprime y en el test lo guarda
    private final IntConsumer alContar;

    // banderas que protege el synchronized, las dos se usan como condiciones del wait
    private boolean hitoAlcanzado = false;
    private boolean pausaSolicitada = false;
    private boolean pausado = false;

    public ContadorPausable(IntConsumer alContar) {
        this.alContar = alContar;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= LIMITE; i++) {
                alContar.accept(i);

                if (i == HITO) {
                    avisarHito();
                }
                // en cada vuelta se revisa si se pidieron pausa
                puntoDePausa();
            }
        } catch (InterruptedException e) {
            // si interrumpen, deja la marca y termina
            Thread.currentThread().interrupt();
        }
    }

    // el hilo avisa a main que llego a 10 y despues espera a que main quiera pausar
    private synchronized void avisarHito() throws InterruptedException {
        hitoAlcanzado = true;
        notifyAll(); // despierta a main
        while (!pausaSolicitada) {
            wait(); // espera la decision de main (while: por si despierta sin motivo)
        }
    }

    // aqui el hilo se queda esperando mientras haya una pausa pedida
    private synchronized void puntoDePausa() throws InterruptedException {
        while (pausaSolicitada) {
            pausado = true;
            notifyAll(); // avisa a main que ya estoy pausado
            wait();
        }
        pausado = false;
    }

    // main espera hasta que el hilo llegue a 10
    public synchronized void esperarHito() throws InterruptedException {
        while (!hitoAlcanzado) {
            wait();
        }
    }

    // main pide la pausa, el hilo la hace cuando llegue a su punto de pausa
    public synchronized void pausar() {
        pausaSolicitada = true;
        notifyAll(); // por si el hilo esta esperando en avisarHito
    }

    // main espera a que el hilo confirme que ya esta pausado
    public synchronized void esperarPausado() throws InterruptedException {
        while (!pausado) {
            wait();
        }
    }

    // main activa el hilo
    public synchronized void reanudar() {
        pausaSolicitada = false;
        notifyAll(); // despierta al hilo pausado
    }
}