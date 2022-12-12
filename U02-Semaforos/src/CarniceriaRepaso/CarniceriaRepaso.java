package CarniceriaRepaso;

import java.util.concurrent.Semaphore;

public class CarniceriaRepaso implements Runnable {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread hilo = new Thread(new CarniceriaRepaso());
            hilo.setName("cliente " + (i + 1));
            hilo.start();
        }
    }

    private static final Semaphore empleados = new Semaphore(4);
    private boolean estaAtendido = false;

    @Override
    public void run() {
        while (!estaAtendido) {
            if (empleados.availablePermits() > 0 && !estaAtendido) {
                try {
                    empleados.acquire();
                    System.out.println("Esta siendo atendido el " + Thread.currentThread().getName());
                    Thread.sleep((long) (Math.random() * 10000));
                    System.out.println("Ya ha sido atendido el cliente " + Thread.currentThread().getName());
                    empleados.release();
                } catch (InterruptedException e) {
                    System.err.println("Error. " + e.getMessage());
                    System.exit(1);
                }
                estaAtendido = true;
            }
        }
    }
}
