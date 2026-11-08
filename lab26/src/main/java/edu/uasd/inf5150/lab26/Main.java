package edu.uasd.inf5150.lab26;

public class Main {

    public static void main(String[] args) {
        ContadorPausable contador = new ContadorPausable(
            n -> System.out.println("Contando: " + n)
        );
        Thread hilo = new Thread(contador, "contador");
        hilo.start();

        try {
            contador.esperarHito();      // espera el aviso del 10
            contador.pausar();           // pide la pausa
            contador.esperarPausado();   // confirma que el hilo se detuvo
            System.out.println("Hilo pausado, esperando 2 segundos...");

            Thread.sleep(2000);

            System.out.println("Reanudando hilo");
            contador.reanudar();

            hilo.join();                 // espera a que llegue a 20
            System.out.println("Conteo finalizado");
        } catch (InterruptedException e) {
            hilo.interrupt();
            Thread.currentThread().interrupt();
        }
    }
}