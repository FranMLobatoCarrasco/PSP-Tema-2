package CharcuteriaCarniceria;

import java.util.concurrent.Semaphore;

public class ColaCarniceria implements Runnable {

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            Thread hilo = new Thread(new ColaCarniceria());
            hilo.setName(String.valueOf(i));
            hilo.start();
        }
    }

    public static Semaphore semaforoCarniceria = new Semaphore(4);

    public static Semaphore semaforoCharcuteria = new Semaphore(2);
    private boolean esAtendidoCarniceria = false;

    private boolean esAtendidoCharcuteria = false;

    public ColaCarniceria() {
        super();
    }

    public void carniceria() {
        try {
            semaforoCarniceria.acquire();
            System.out.println("El cliente " + Thread.currentThread().getName() + " pidiendo en la carnicería");
            Thread.sleep((long) (Math.random() * 10000));
            System.out.println("El cliente " + Thread.currentThread().getName() + " ha terminado en la carnicería");
            semaforoCarniceria.release();
        } catch (InterruptedException e) {
            System.err.println("Error inesperado. " + e.getMessage());
        }
    }

    public void charcuteria() {
        try {
            semaforoCharcuteria.acquire();
            System.out.println("El cliente " + Thread.currentThread().getName() + " pidiendo en la charcutería");
            Thread.sleep((long) (Math.random() * 10000));
            System.out.println("El cliente " + Thread.currentThread().getName() + " ha terminado en la charcutería");
            semaforoCharcuteria.release();
        } catch (InterruptedException e) {
            System.err.println("Error inesperado. " + e.getMessage());
        }
    }

    @Override
    public void run() {
        while (!esAtendidoCarniceria || !esAtendidoCharcuteria) {
            if (semaforoCarniceria.availablePermits() > 0 && !esAtendidoCarniceria) {
                carniceria();
                esAtendidoCarniceria = true;
            }
            if (semaforoCharcuteria.availablePermits() > 0 && !esAtendidoCharcuteria) {
                charcuteria();
                esAtendidoCharcuteria = true;
            }
        }
    }
}
