package ejerciciolibro;

import java.util.Random;

public class EstudianteClaseLibro implements Runnable {

	public Libro l;
	
	public EstudianteClaseLibro(Libro l) {
		this.l = l;
	}
	
	@Override
	public void run() {
		try {
			while (true) {
				int libro1 = new Random().nextInt(9);
				int libro2 = new Random().nextInt(9);
				while (libro2 == libro1) {
					libro2 = new Random().nextInt(9);
				}
				l.reservaLibros(libro1, libro2);
				
				System.out.println(
						Thread.currentThread().getName() + " tiene reservados los libros " + libro1 + " y " + libro2);
				Thread.sleep((long) (Math.random() * 3000));
				System.out.println(Thread.currentThread().getName() + " ha terminado de leer.");

				l.liberaLibros(libro1, libro2);

				Thread.sleep(1000);
			}
		} catch (

		InterruptedException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		Libro libro = new Libro();
		EstudianteClaseLibro e = new EstudianteClaseLibro(libro);
		for (int i = 1; i <= 4; i++) {
			Thread hilo = new Thread(e);
			hilo.setName("Estudiante " + i);
			hilo.setPriority(i*2);
			hilo.start();
		}
	}
}
