package ejerciciolibro;

import java.util.Random;

public class Estudiante implements Runnable {

	// Los libros son el recurso compartido
	public static boolean[] libros = new boolean[9];
	public static Object o = new Object();

	@Override
	public void run() {
		try {
			while (true) {
				int libro1 = new Random().nextInt(9);
				int libro2 = new Random().nextInt(9);
				
				while (libro2 == libro1) {
					libro2 = new Random().nextInt(9);
				}
				
				synchronized (o) {
					while (libros[libro1] == true || libros[libro2] == true) {
						try {
							o.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					// Reservo los libros
					libros[libro1] = true;
					libros[libro2] = true;
				}
				
				System.out.println(
						Thread.currentThread().getName() + " tiene reservados los libros " + libro1 + " y " + libro2);
				Thread.sleep((long) (Math.random() * 3000));
				System.out.println(Thread.currentThread().getName() + " ha terminado de leer.");

				synchronized (o) {
					libros[libro1] = false;
					libros[libro2] = false;
					o.notifyAll();
				}
				
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		Estudiante e = new Estudiante();
		for (int i = 1; i <= 4; i++) {
			Thread hilo = new Thread(e);
			hilo.setName("Estudiante " + i);
			hilo.start();
		}
	}
}
