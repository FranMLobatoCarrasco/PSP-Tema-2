package ejercicio1;

import java.util.concurrent.Semaphore;

public class Barberia implements Runnable {

    static Semaphore barberos = new Semaphore(2);
    static Semaphore sillas = new Semaphore(4);

    public static void main(String[] args) {
        Barberia b = new Barberia();
        for (int i = 1; i <= 10; i++) {
            Thread hilo = new Thread(b);
            System.out.println(hilo.getState());
            hilo.setName("Cliente " + i);
            hilo.start();
            try {
                Thread.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                System.out.println("Se ha producido un error en la espera.");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        if (sillas.availablePermits() > 0) {
            try {
                System.out.println(Thread.currentThread().getName() + " llega a la barbería.");
                sillas.acquire();
                System.out.println(Thread.currentThread().getName() + " se ha sentado.");
                barberos.acquire();
                System.out.println(Thread.currentThread().getState());
                System.out.println(Thread.currentThread().getName() + " está siendo atendido.");
                // Tiempo de pelado
                Thread.sleep((long) (Math.random() * 5000));
                System.out.println(Thread.currentThread().getState());
                System.out.println(Thread.currentThread().getName() + " ha terminado de pelarse.");
                barberos.release();
                System.out.println(Thread.currentThread().getName() + " se levanta de la silla.");
                sillas.release();
            } catch (InterruptedException e) {
                System.out.println("Se ha producido una excepción durante la espera.");
                e.printStackTrace();
            }
        } else {
            System.out.println("No hay sillas libres y se va " + Thread.currentThread().getName());
            Thread.currentThread().interrupt();
            System.out.println(Thread.currentThread().getState());
        }
    }
}
