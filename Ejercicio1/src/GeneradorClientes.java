import java.util.concurrent.BlockingQueue;

public class GeneradorClientes implements Runnable {
	private int clientesIniciales;
	private int clientesPorSegundo;
	private long tiempoMaximo;
	private BlockingQueue q;
	private int numCliente = 0;
	
	public GeneradorClientes(int clientesIniciales, int clientesPorSegundo, long tiempoMaximo, BlockingQueue q) {
		super();
		this.clientesIniciales = clientesIniciales;
		this.clientesPorSegundo = clientesPorSegundo;
		this.tiempoMaximo = tiempoMaximo;
		this.q = q;
	}
	
	@Override
	public void run() {
		long tiempoInicial = System.nanoTime();
		// TODO: generamos "clientesIniciales" y los encolamos
		
		try {
		
			generarClientes(clientesIniciales);
			
			// mientras no nos pasemos del tiempoMaximo 
			while ((System.nanoTime() - tiempoInicial) < tiempoMaximo) {
				// TODO: esperar y generar cliente segÃºn "clientesPorSegundo".
				Thread.sleep(1000);
				//Generar clientes según "clientesPorSegundo".
				generarClientes(clientesPorSegundo);
			}
		
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		
		// TODO: Imprimir "Cerrando supermercado, ya no entran mÃ¡s clientes."
		System.out.println("Cerrando supermercado, ya no entran más clientes.");
		
	}

	//Método generador de Clientes
	public void generarClientes(int num) throws InterruptedException {
		
		for(int i = 0; i < num; i++) {
			Cliente cliente = new Cliente(numCliente);
			numCliente = numCliente +1;
			q.put(cliente);
		}
	}
	
}

