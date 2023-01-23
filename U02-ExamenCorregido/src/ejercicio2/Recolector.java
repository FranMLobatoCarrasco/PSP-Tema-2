package ejercicio2;

import java.util.Random;

public class Recolector implements Runnable {

    Colecta c;

    public Recolector(Colecta c) {
        this.c = c;
    }

    @Override
    public void run() {
        Random r = new Random();
        int cantidad;
        while (true) {
            try {
                Thread.sleep(r.nextLong(10, 200));
                cantidad = r.nextInt(4, 25);
                System.out.println(Thread.currentThread().getName() + " quiere recolectar " + cantidad);
                synchronized (c) {
                    while (cantidad + c.getCantidadRecaudada() > 2000) {
                        c.wait();
                    }
                    c.añadeDinero(cantidad);
                    System.out.println(Thread.currentThread().getName() + " ha recolectado " + cantidad);
                    System.out.println("Ahora mismo hay recolectado " + c.getCantidadRecaudada());
                    c.notifyAll();
                }
            } catch (InterruptedException e) {
                System.out.println("Ha habido un problema con la espera");
                e.printStackTrace();
            }
        }
    }
}
