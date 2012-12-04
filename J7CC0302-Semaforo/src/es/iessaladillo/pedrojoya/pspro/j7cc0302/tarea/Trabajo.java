package es.iessaladillo.pedrojoya.pspro.j7cc0302.tarea;

/**
 * This class simulates a job that send a document to print.
 *
 */
public class Trabajo implements Runnable {

	/**
	 * Queue to print the documents
	 */
	private ColaImpresion printQueue;
	
	/**
	 * Constructor of the class. Initializes the queue
	 * @param printQueue
	 */
	public Trabajo(ColaImpresion printQueue){
		this.printQueue=printQueue;
	}
	
	/**
	 * Core method of the Job. Sends the document to the print queue and waits
	 *  for its finalization
	 */
	@Override
	public void run() {
		System.out.printf("%s: Going to print a job\n",Thread.currentThread().getName());
		printQueue.imprimir(new Object());
		System.out.printf("%s: The document has been printed\n",Thread.currentThread().getName());		
	}
}
