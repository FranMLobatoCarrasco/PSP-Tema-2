package ejercicio3;

import java.util.ArrayList;
import java.util.List;

public class GestorHojas extends Thread {

	private static List<String> lista = new ArrayList<String>();

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			lista.add(new String("Texto" + i));
		}

		for (String string : lista) {
			System.out.println(string);
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new GestorHojas().start();
		}

	}

}
