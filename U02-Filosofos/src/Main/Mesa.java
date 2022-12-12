package Main;

public class Mesa {
    public boolean[] tenedores;

    public Mesa() {
        this.tenedores = new boolean[5];
    }

    public int tenedorIzquierda(int i) {
        return i;
    }

    public int tenedorDerecha(int i) {
        int numTenedor = i - 1;
        if (i == 0)
            numTenedor = this.tenedores.length - 1;
        return numTenedor;
    }

    public synchronized void cogerTenedores(int filosofo) {
        while (tenedores[tenedorIzquierda(filosofo)] || tenedores[tenedorIzquierda(filosofo)]) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                System.err.println("Error esperando 1. " + e.getMessage());
            }
        }
        tenedores[tenedorIzquierda(filosofo)] = true;
        tenedores[tenedorDerecha(filosofo)] = true;
    }

    public synchronized void dejarTenedores(int comensal) {
        tenedores[tenedorIzquierda(comensal)] = false;
        tenedores[tenedorDerecha(comensal)] = false;
        notifyAll();
    }
}
