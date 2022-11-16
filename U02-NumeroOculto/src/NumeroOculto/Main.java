package NumeroOculto;

import static NumeroOculto.NumeroOculto.*;

class NumeroOculto extends Thread {

    public static int numeroOculto;
    private static int comprobacion = 0;

    synchronized public static int propuestaNumero(int numero) {
        if (comprobacion == 1)
            comprobacion = -1;
        if (numero == numeroOculto)
            comprobacion = 1;
        return comprobacion;
    }
}

public class Main extends Thread {

    public Main() {
        super();
    }

    @Override
    public void run() {
        int numero = (int) (Math.random() * (10000 + 1));
        if (propuestaNumero(numero) == 1)
            Thread.currentThread().interrupt();
        else {
            do {
                numero = (int) (Math.random() * (10000 + 1));
                if (propuestaNumero(numero) == 1) {
                    System.out.println("Encontrado por el " + Thread.currentThread().getName());
                }
            } while (propuestaNumero(numero) != -1);
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        numeroOculto = (int) (Math.random() * (100 + 1));

        Main hilo1 = new Main();
        Main hilo2 = new Main();
        Main hilo3 = new Main();
        Main hilo4 = new Main();
        Main hilo5 = new Main();
        Main hilo6 = new Main();
        Main hilo7 = new Main();
        Main hilo8 = new Main();
        Main hilo9 = new Main();
        Main hilo10 = new Main();

        hilo1.setName("hilo 1");
        hilo2.setName("hilo 2");
        hilo3.setName("hilo 3");
        hilo4.setName("hilo 4");
        hilo5.setName("hilo 5");
        hilo6.setName("hilo 6");
        hilo7.setName("hilo 7");
        hilo8.setName("hilo 8");
        hilo9.setName("hilo 9");
        hilo10.setName("hilo 10");

        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();
        hilo6.start();
        hilo7.start();
        hilo8.start();
        hilo9.start();
        hilo10.start();
    }
}