package Main;

public class Filosofos extends Thread {

    public static void main(String[] args) {
        Mesa m = new Mesa();
        for (int i = 1; i <= 5; i++) {
            Filosofos f = new Filosofos(m, i);
            f.setName("Filosofo " + (i + 1));
            f.start();
        }
    }

    private final Mesa mesa;
    private final int indiceComensal;

    public Filosofos(Mesa m, int comensal) {
        this.mesa = m;
        this.indiceComensal = comensal - 1;
    }

    public void run() {
        while (true) {
            this.pensando();
            this.mesa.cogerTenedores(this.indiceComensal);
            this.comiendo();
            System.out.println(Thread.currentThread().getName() + " deja de comer, tenedores libres " + (this.mesa.tenedorIzquierda(this.indiceComensal) + 1) + ", " + (this.mesa.tenedorDerecha(this.indiceComensal) + 1));
            this.mesa.dejarTenedores(this.indiceComensal);
        }
    }

    public void pensando() {
        System.out.println(Thread.currentThread().getName() + " esta pensando");
        try {
            sleep((long) (Math.random() * 4000));
        } catch (InterruptedException ex) {
            System.err.println("Error inesperado. " + ex.getMessage());
        }
    }

    public void comiendo() {
        System.out.println(Thread.currentThread().getName() + " esta comiendo con tenedores " + (this.mesa.tenedorIzquierda(this.indiceComensal) + 1) + ", " + (this.mesa.tenedorDerecha(this.indiceComensal) + 1));
        try {
            sleep((long) (Math.random() * 4000));
        } catch (InterruptedException ex) {
            System.err.println("Error inesperado. " + ex.getMessage());
        }
    }
}
