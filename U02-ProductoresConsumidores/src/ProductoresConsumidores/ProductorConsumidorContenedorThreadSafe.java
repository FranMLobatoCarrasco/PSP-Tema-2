package ProductoresConsumidores;

import java.util.Random;

class Contenedor<T> {

    private T dato;

    public T get() throws InterruptedException {
        T result;
        synchronized (this) {
            while (!this.datoDisponible()) {
                this.wait();
            }
            result = this.dato;
            this.dato = null;
            this.notifyAll();
        }
        return result;
    }

    public void put(T valor) throws InterruptedException {
        synchronized (this) {
            while (this.datoDisponible()) {
                this.wait();
            }
            this.dato = valor;
            this.notifyAll();
        }
    }

    synchronized boolean datoDisponible() {
        return (this.dato != null);
    }
}

class HiloProductor implements Runnable {

    final Contenedor<Integer> cont;
    String miNombre;

    HiloProductor(Contenedor<Integer> cont, String miNombre) {
        this.cont = cont;
        this.miNombre = miNombre;
    }

    @Override
    public void run() {
        Random r = new Random();
        for (int i = 1; ; i++) {
            try {
                Thread.sleep(10 + r.nextInt(50 - 10 + 1));
                this.cont.put(i);
                long numSec = Secuencia.getNumSec();
                System.out.printf("%d-Hilo %s produce valor %s.\n", numSec, this.miNombre, i);
            } catch (InterruptedException ex) {
            }
        }
    }
}

class HiloConsumidor implements Runnable {

    final Contenedor<Integer> cont;
    String miNombre;

    HiloConsumidor(Contenedor<Integer> cont, String miNombre) {
        this.cont = cont;
        this.miNombre = miNombre;
    }

    @Override
    public void run() {
        Random r = new Random();
        while (true) {
            try {
                Thread.sleep(10 + r.nextInt(50 - 10 + 1));
                Integer dato = this.cont.get();
                long numSec = Secuencia.getNumSec();
                System.out.printf("%d-Hilo %s consume valor %d.\n", numSec, this.miNombre, dato);
            } catch (InterruptedException ex) {
            }
        }
    }
}

class Secuencia {
    static private long numSec = 0;

    synchronized static public long getNumSec() {
        return numSec++;
    }
}

public class ProductorConsumidorContenedorThreadSafe {

    public static void main(String[] args) {
        Contenedor<Integer> almacen = new Contenedor<>();
        Thread hprod1 = new Thread(new HiloProductor(almacen, "P1"));
        Thread hprod2 = new Thread(new HiloProductor(almacen, "P2"));
        Thread hcons1 = new Thread(new HiloConsumidor(almacen, "C1"));
        Thread hcons2 = new Thread(new HiloConsumidor(almacen, "C2"));
        hprod1.start();
        hprod2.start();
        hcons1.start();
        hcons2.start();
    }
}
