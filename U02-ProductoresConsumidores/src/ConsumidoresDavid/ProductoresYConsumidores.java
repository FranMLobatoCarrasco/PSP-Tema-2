package ConsumidoresDavid;

import java.util.ArrayList;

public class ProductoresYConsumidores implements Runnable {

    public static final ArrayList<String> productos = new ArrayList<>();
    private final Tipo tipo;
    private final int id;

    public ProductoresYConsumidores(Tipo tipo, int id) {
        this.tipo = tipo;
        this.id = id;
    }

    private void consumir() {
        synchronized (productos) {
            //Esperamos hasta que haya stock
            while (productos.size() <= 0) {
                try {
                    System.out.println("El consumidor " + id + " esta esperando a que haya algun producto disponible");
                    productos.wait();
                } catch (InterruptedException e) {
                    System.out.println("Se interrumpio la espera del consumidor " + id);
                }
            }
            //Compramos el producto
            productos.remove(0);
            System.out.println("El consumidor " + id + " compro un producto");
            System.out.print("Productos disponible: ");
            System.out.println(productos);
            productos.notifyAll();
        }
        //Tiempo de espara entre compra y compra
        try {
            Thread.sleep((int) (Math.random() * 5000 + 1000));
        } catch (InterruptedException e) {
            System.out.println("Se interrumpio la espera del consumidor " + id);
        }
    }

    private void producir() {
        //Tiempo en el que se produce el producto
        try {
            Thread.sleep((int) (Math.random() * 5000 + 1000));
        } catch (InterruptedException e) {
            System.out.println("Se interrumpio la espera del productor " + id);
        }
        synchronized (productos) {
            //Esperamos hasta que haya hueco para el producto
            while (productos.size() > 10) {
                try {
                    System.out.println("El productor " + id + " esta esperando a que haya hueco para insertar un nuevo producto");
                    productos.wait();
                } catch (InterruptedException e) {
                    System.out.println("Se interrumpio la espera del productor " + id);
                }
            }
            //Introducimos el producto
            productos.add("producto");
            System.out.println("El productor " + id + " introdujo un nuevo porducto en la tienda");
            System.out.print("Productos disponible: ");
            System.out.println(productos);
            productos.notifyAll();
        }
    }

    @Override
    public void run() {
        //Segun el tipo llamaremos a un metodo u otro
        if (tipo == Tipo.CONSUMIDOR) {
            while (true) {
                consumir();
            }
        } else {
            while (true) {
                producir();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new ProductoresYConsumidores(Tipo.CONSUMIDOR, 1)).start();
        new Thread(new ProductoresYConsumidores(Tipo.PRODUCTR, 1)).start();
        new Thread(new ProductoresYConsumidores(Tipo.PRODUCTR, 2)).start();
    }
}
