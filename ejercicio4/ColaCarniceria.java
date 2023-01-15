package ejercicio4;

import java.util.concurrent.Semaphore;

public class ColaCarniceria implements Runnable {

	
	public static Semaphore semaforoCarniceria = new Semaphore(20);
	public static Semaphore semaforoCharcuteria = new Semaphore(10);
	
	private boolean atendidoCarniceria=false;
	private boolean atendidoCharcuteria=false;
	
	private int id;
	
	public ColaCarniceria(int id) {
		super();
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void carniceria() {
		try {
			semaforoCarniceria.acquire();
			System.out.println(Thread.currentThread().getName() + " pidiendo en la carnicería");
			Thread.sleep(1000);
			System.out.println(Thread.currentThread().getName() + " ha terminado en la carnicería");
			semaforoCarniceria.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void charcuteria() {
		try {
			semaforoCharcuteria.acquire();
			System.out.println(Thread.currentThread().getName() + " pidiendo en la charcutería");
			Thread.sleep(1000);
			System.out.println(Thread.currentThread().getName() + " ha terminado en la charcutería");
			semaforoCharcuteria.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(!atendidoCarniceria || !atendidoCharcuteria) {
			if(semaforoCarniceria.availablePermits()>0 && !atendidoCarniceria) {
				carniceria();
				atendidoCarniceria = true;
			} 
			if (semaforoCharcuteria.availablePermits()>0 && !atendidoCharcuteria) {	
				charcuteria();
				atendidoCharcuteria = true;
			}
		}
	}
	
	public static void main(String[] args) {
		for(int i=1;i<=100;i++) {
			Thread hilo = new Thread(new ColaCarniceria(i));
			hilo.setName("Hilo"+i);
			hilo.setPriority(i/90+1);
			hilo.start();
		}

	}

	

}
