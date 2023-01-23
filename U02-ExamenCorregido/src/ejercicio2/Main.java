package ejercicio2;

public class Main {

    public static final int PRODUCTORES = 4;
    public static final int CONSUMIDORES = 4;

    public static void main(String[] args) {
        int prioridad = 1;
        Colecta c = new Colecta();

        for (int i = 1; i <= PRODUCTORES; i++) {
            Thread hilo = new Thread(new Recolector(c));
            hilo.setPriority(prioridad);
            hilo.setName("Productor " + i);
            prioridad += 3;
            hilo.start();
        }

        for (int i = 1; i <= CONSUMIDORES; i++) {
            Thread hilo = new Thread(new Consumidor(c));
            hilo.setName("Consumidor " + i);
            hilo.start();
        }
    }

}
