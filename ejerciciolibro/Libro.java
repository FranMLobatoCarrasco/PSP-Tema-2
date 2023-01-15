package ejerciciolibro;

public class Libro {
	public static boolean[] libros = new boolean[9];

	public synchronized void reservaLibros( int libro1, int libro2) {
		while (libros[libro1] == true || libros[libro2] == true) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// Reservo los libros
		libros[libro1] = true;
		libros[libro2] = true;
	}

	public synchronized void liberaLibros(int libro1, int libro2) {
		libros[libro1] = false;
		libros[libro2] = false;
		this.notifyAll();
	}

}
