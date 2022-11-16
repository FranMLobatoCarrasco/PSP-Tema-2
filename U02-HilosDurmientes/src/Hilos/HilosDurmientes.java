package Hilos;

public class HilosDurmientes extends Thread {

    /**
     * Creamos el constructor de HilosDurmientes
     */
    public HilosDurmientes() {
        super();
    }

    /**
     * Sobreescribimos el método run(), y le añadimos nuestro código, en este caso, un bucle infinito
     */
    @Override
    public void run() {
        for (; ; ) { // Bucle for sin fin
            System.out.println("Soy un el bucle " + Thread.currentThread().getName() + " y estoy trabajando."); // Pintamos por pantalla el nombre de cada hilo
            try { // Controlamos las posibles excepciones
                Thread.sleep((long) (Math.random() * (10000 - 1000 + 1) + 1000)); // Lo mandamos a dormir
            } catch (InterruptedException e) {
                System.err.println("Error " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        // Instanciamos los hilos
        HilosDurmientes hilo1 = new HilosDurmientes();
        HilosDurmientes hilo2 = new HilosDurmientes();
        HilosDurmientes hilo3 = new HilosDurmientes();
        HilosDurmientes hilo4 = new HilosDurmientes();
        HilosDurmientes hilo5 = new HilosDurmientes();

        // Establecemos los nombres de cada uno
        hilo1.setName("hilo 1");
        hilo2.setName("hilo 2");
        hilo3.setName("hilo 3");
        hilo4.setName("hilo 4");
        hilo5.setName("hilo 5");

        // Y los comenzamos
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();
    }
}
