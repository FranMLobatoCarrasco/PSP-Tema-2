package CharcuteriaCarniceria;

import java.util.concurrent.Semaphore;

public class CarniceriaCharcuteria implements Runnable {

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            Thread hilo = new Thread(new CarniceriaCharcuteria());
            hilo.setName(String.valueOf(i));
            hilo.start();
        }
    }

    public static Semaphore semaforoCarniceria = new Semaphore(4); // Permitimos 4 ejecuciones a la vez
    public static Semaphore semaforoCharcuteria = new Semaphore(2); // Permitimos 2 ejecuciones a la vez
    private boolean esAtendidoCharcuteria = false; // Booleano para comprobar si esta atendido el hilo
    private boolean esAtendidoCarniceria = false; // Booleano para comprobar si esta atendido el hilo

    public CarniceriaCharcuteria() { // Constructor de los hilos
        super();
    }

    public void charcuteria() { // Metodo para ejecutar el hilo de la charcuteria
        try {
            semaforoCharcuteria.acquire(); // Si hay alguien libre, entra
            System.out.println("El cliente " + Thread.currentThread().getName() + " pidiendo en la charcutería"); // Mensaje de que está siendo atendido
            Thread.sleep((long) (Math.random() * 10000)); // Esperamos un tiempo aleatorio entre 1 y 10 segundos
            System.out.println("El cliente " + Thread.currentThread().getName() + " ha terminado en la charcutería"); // Mensaje de que ha terminado
            semaforoCharcuteria.release();
        } catch (InterruptedException e) {
            System.err.println("Error inesperado. " + e.getMessage());
        }
    }

    public void carniceria() { // Metodo para ejecutar el hilo de la carniceria
        try {
            semaforoCarniceria.acquire(); // Si hay alguien libre, entra
            System.out.println("El cliente " + Thread.currentThread().getName() + " pidiendo en la carnicería"); // Mensaje de que está siendo atendido
            Thread.sleep((long) (Math.random() * 10000)); // Esperamos un tiempo aleatorio entre 1 y 10 segundos
            System.out.println("El cliente " + Thread.currentThread().getName() + " ha terminado en la carnicería"); // Mensaje de que ha terminado
            semaforoCarniceria.release();
        } catch (InterruptedException e) {
            System.err.println("Error inesperado. " + e.getMessage());
        }
    }

    @Override
    public void run() {
        while (!esAtendidoCarniceria || !esAtendidoCharcuteria) {
            if (semaforoCarniceria.availablePermits() > 0 && !esAtendidoCarniceria) { // Comprobamos si hay hueco, y si está atendido el proceso
                carniceria();
                esAtendidoCarniceria = true;
            }
            if (semaforoCharcuteria.availablePermits() > 0 && !esAtendidoCharcuteria) { // Comprobamos si hay hueco, y si está atendido el proceso
                charcuteria();
                esAtendidoCharcuteria = true;
            }
        }
    }
}
