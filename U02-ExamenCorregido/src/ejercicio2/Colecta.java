package ejercicio2;

public class Colecta {

    int cantidadRecaudada = 0;

    public Colecta() {
        cantidadRecaudada = 0;
    }

    public int getCantidadRecaudada() {
        return cantidadRecaudada;
    }

    public synchronized void añadeDinero(int cantidad) {
        cantidadRecaudada += cantidad;
    }

    public synchronized void recogerDinero(int cantidad) {
        cantidadRecaudada -= cantidad;
    }
}
