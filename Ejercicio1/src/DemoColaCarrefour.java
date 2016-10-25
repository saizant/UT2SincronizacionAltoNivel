import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class DemoColaCarrefour {
	public static void main(String[] args) throws InterruptedException {
		// TODO: creamos nuestra BloquingQueue
		
		BlockingQueue<Cliente> q = new ArrayBlockingQueue<Cliente>(1000);
		ArrayList<Thread> cajeros = new ArrayList<>();
		
		// TODO: Creamos y arrancamos el generador de clientes. Por ejemplo:
		// 30 clientes iniciales
		// 2 clientes nuevos por segundo
		// 100 segundos antes de cerrar las puertas del super...
		
		GeneradorClientes generador = new GeneradorClientes(30, 2, 100, q);
		new Thread(generador).start();
		
		// 3 cajeros para atender
		Cajero cajero;
		Thread thread;
		
		for(int i=0; i<3; i++) {
			// TODO: Creamos y arrancamos los cajeros en sus Threads
			cajero = new Cajero(i, 2, 10, q);
			thread = new Thread(cajero);
			thread.start();
			cajeros.add(thread);
		}
		
		// TODO: Imprimir cada 2segundos el tamaÃ±o de la cola
		Thread.sleep(2000);
		
		//Mientras haya clientes
		while (q.size() > 0) {
			//Imprimimos el número de clientes encolados
			System.out.println("Hay " + q.size() + " clientes en cola.");
			Thread.sleep(2000);
		}
		
		// TODO: Esperamos a que se vayan los clientes y cajeros para cerrar el super
		// y imprimimos "SUPERMERCADO CERRADO."
		
		int num = cajeros.size();
		
		//Mientras queden cajeros (threads)
		while (num > 0) {
			num = cajeros.size();
			for(int i = 0; i < 3; i++) {
				//Disminuimos la cantidad de cajeros por cada cajero terminado
				if (!cajeros.get(i).isAlive()) {
					num -= 1;
				}
			}
		}
		
		System.out.println("SUPERMERCADO CERRADO.");
		
	}
}
