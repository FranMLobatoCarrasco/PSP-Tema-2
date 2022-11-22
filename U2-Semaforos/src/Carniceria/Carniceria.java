package Carniceria;

import java.util.concurrent.Semaphore;

public class Carniceria implements Runnable {

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            Thread hilo = new Thread(new Carniceria());
            hilo.setName(String.valueOf(i));
            hilo.start();
        }
    }


    public static Semaphore semaforo = new Semaphore(4);

    private boolean atendido = false;

    public Carniceria() {
        super();
    }

    public void carniceria() {
        try {
            semaforo.acquire();
            System.out.println("El cliente " + Thread.currentThread().getName() + " pidiendo en la carniceria");
            Thread.sleep((long) (Math.random() * 10000));
            System.out.println("El " + Thread.currentThread().getName() + " ha terminado en la carniceria");
            semaforo.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (!atendido) {
            if (semaforo.availablePermits() > 0 && !atendido) {
                carniceria();
                atendido = true;
            }
        }
    }
}
