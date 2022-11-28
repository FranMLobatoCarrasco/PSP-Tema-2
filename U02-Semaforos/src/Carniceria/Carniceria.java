package Carniceria;

import java.util.concurrent.Semaphore;

public class Carniceria implements Runnable {

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            Thread hilo = new Thread(new Carniceria()); // Lanzamos el hilo
            hilo.setName(String.valueOf(i)); // Seteamos el nombre del hilo
            hilo.start(); // Lo iniciamos
        }
    }

    public static Semaphore semaforo = new Semaphore(4); // Permitimos como maximo 4 ejecuciones a la vez

    private boolean atendido = false;

    public Carniceria() {
        super();
    }

    @Override
    public void run() {
        while (!atendido) {
            if (semaforo.availablePermits() > 0 && !atendido) { // Comprobamos si hay hueco, y si está atendido el proceso
                try {
                    semaforo.acquire(); // Si hay alguien libre, entra
                    System.out.println("El cliente " + Thread.currentThread().getName() + " pidiendo en la carniceria"); // Mensaje de que está siendo atendido
                    Thread.sleep((long) (Math.random() * 10000)); // Esperamos un tiempo aleatorio entre 1 y 10 segundos
                    System.out.println("El " + Thread.currentThread().getName() + " ha terminado en la carniceria"); // Mensaje de que ha terminado
                    semaforo.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                atendido = true;
            }
        }
    }
}
