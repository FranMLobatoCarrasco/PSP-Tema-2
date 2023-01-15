package ejercicio2;

public class NumeroOculto extends Thread {
	public static int numeroAdivinar;
	public static boolean terminado = false;
	private int numero;
	

	public NumeroOculto(int numero) {
		super();
		this.numero = numero;
	}
	
	

	public int getNumero() {
		return numero;
	}



	public void setNumero(int numero) {
		this.numero = numero;
	}



	@Override
	public void run() {
		int max = 10;
		int min = 0;
		int res = propuestaNumero(this.getNumero());
		
		while(res==0) {		
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			if(numero>numeroAdivinar) {
				max = numero;
			} else {
				min = numero;
			}
			this.setNumero((int) (Math.random()*(max-min))+min);
			res = propuestaNumero(this.getNumero());
		}
		
	}

	public void compruebaResultado(int res) {
		if(res == -1) {
			System.out.println("Un hilo ya ha adivinado el número");
			currentThread().interrupt();
		} else if (res == 1){
			System.out.println("¡¡ENHORABUENA!! ¡HAS ACERTADO! " + Thread.currentThread().getName());
			currentThread().interrupt();
		}
	}
	
	synchronized public static int propuestaNumero(int num) {
		int res = 0;
	
		if (num == numeroAdivinar && !terminado) {
			res = 1;
			terminado = true;
			System.out.println("¡¡ENHORABUENA!! ¡HAS ACERTADO! " + Thread.currentThread().getName());
			Thread.currentThread().interrupt();
			
		} else if (terminado) {
			res = -1;
			System.out.println(Thread.currentThread().getName() + " Lo siento, un hilo ya ha adivinado el número");
			currentThread().interrupt();			
		}
		
		return res;
	}

	public static void main(String[] args) {
		numeroAdivinar = (int) (Math.random() * 10) + 1;
		for (int i = 10; i >= 1; i--) {
			int numero = (int) (Math.random() * 10) + 1;
			NumeroOculto no = new NumeroOculto(numero);
			no.setName("hilo"+i);
			no.start();
		}
	}

}
