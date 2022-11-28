package Main;

import java.util.Random;

public class Estudiante implements Runnable {

    public static void main(String[] args) {
        Estudiante e = new Estudiante();
        for (int i = 0; i < 4; i++) {
            Thread hilo = new Thread(e);
            hilo.setName("El estudiante " + (i + 1));
            hilo.start();
        }
    }

    private static final boolean[] libros = new boolean[9];

    private synchronized void reservarLibros(int libro1, int libro2) {
        while (libros[libro1] || libros[libro2]) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                System.err.println("Error. " + e.getMessage());
            }
        }
        libros[libro1] = true;
        libros[libro2] = true;
    }

    public synchronized void libroLibre(int libro1, int libro2) {
        libros[libro1] = false;
        libros[libro2] = false;
        this.notify();
    }

    @Override
    public void run() {
        try {
            while (true) {
                int libro1 = new Random().nextInt(9);
                int libro2 = new Random().nextInt(9);
                while (libro2 == libro1)
                    libro2 = new Random().nextInt(9);
                reservarLibros(libro1, libro2);
                System.out.println(Thread.currentThread().getName() + " ha reservado los libros " + libro1 + " y " + libro2);
                Thread.sleep((long) (Math.random() * 3000) + 1);
                System.out.println("Se ha terminado de leer el libro. " + Thread.currentThread().getName());
                libroLibre(libro1, libro2);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.err.println("Error. " + e.getMessage());
        }
    }
}
