package ejercicio1;

public class HilosDurmientes extends Thread {
	
	@Override
	public void run() {
		while(true) {
			long tiempoDormir = (long) (Math.random()*10000)+1;
			String nombre = Thread.currentThread().getName();
			System.out.println("Soy el bucle " + nombre + " y estoy trabajando");
			
			try {
				Thread.sleep(tiempoDormir);
			} catch (InterruptedException e) {
				System.out.println("El hilo " + nombre + " ha sido interrumpido.");
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		for(int i=1; i<=5; i++) {
			HilosDurmientes hilo = new HilosDurmientes();
			hilo.setName("Hilo " + i);
			hilo.start();
		}
		
	}

}
