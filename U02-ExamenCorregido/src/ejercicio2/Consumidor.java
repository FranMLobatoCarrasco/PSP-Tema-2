package ejercicio2;

import java.util.Random;

public class Consumidor implements Runnable {

    Colecta c;

    public Consumidor(Colecta c) {
        super();
        this.c = c;
    }

    @Override
    public void run() {
        Random r = new Random();
        int cantidad;
        while (true) {
            try {
                Thread.sleep(r.nextLong(20, 300));
                cantidad = r.nextInt(10, 40);
                System.out.println(Thread.currentThread().getName() + " quiere recoger " + cantidad);
                synchronized (c) {
                    while (cantidad > c.getCantidadRecaudada()) {
                        c.wait();
                    }
                    c.recogerDinero(cantidad);
                    System.out.println(Thread.currentThread().getName() + " recoge " + cantidad);
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
