package NumeroOculto;

import static NumeroOculto.NumeroOculto.*;

class NumeroOculto extends Thread {

    public static int numeroOculto;
    private static int comprobacion = 0;

    public synchronized static int propuestaNumero(int numero) {
        if (comprobacion == 1)
            comprobacion = -1;
        if (numero == numeroOculto)
            comprobacion = 1;
        return comprobacion;
    }

    public NumeroOculto() {
        super();
    }

    @Override
    public void run() {
        int numero = (int) (Math.random() * 100 + 1);
        if (propuestaNumero(numero) == 1)
            Thread.currentThread().interrupt();
        else {
            do {
                numero = (int) (Math.random() * 100 + 1);
                if (propuestaNumero(numero) == 1) {
                    System.out.println("Encontrado por el " + Thread.currentThread().getName());
                }
            } while (propuestaNumero(numero) != -1);
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        numeroOculto = (int) (Math.random() * (100 + 1));

        NumeroOculto hilo1 = new NumeroOculto();
        NumeroOculto hilo2 = new NumeroOculto();
        NumeroOculto hilo3 = new NumeroOculto();
        NumeroOculto hilo4 = new NumeroOculto();
        NumeroOculto hilo5 = new NumeroOculto();
        NumeroOculto hilo6 = new NumeroOculto();
        NumeroOculto hilo7 = new NumeroOculto();
        NumeroOculto hilo8 = new NumeroOculto();
        NumeroOculto hilo9 = new NumeroOculto();
        NumeroOculto hilo10 = new NumeroOculto();

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