package Oculto;

public class NumeroOculto extends Thread {

    public static int numeroAdivinar;
    public static boolean encontrado = false;

    @Override
    public void run() {
        int numero = (int) (Math.random() * 100 + 1);
        int res = propuestaNumero(numero);

        while (res == 1 || res == -1) {
            numero = (int) (Math.random() * 100 + 1);
            res = propuestaNumero(numero);
        }
    }

    public synchronized static int propuestaNumero(int numero) {
        int res = 0;
        if (numero == numeroAdivinar) {
            res = 1;
            encontrado = true;
        } else if (encontrado) {
            res = -1;
        }
        return res;
    }

    public static void main(String[] args) {
        numeroAdivinar = (int) (Math.random() * 100 + 1);
        for (int i = 0; i < 10; i++) {
            new NumeroOculto().start();
        }
    }
}
