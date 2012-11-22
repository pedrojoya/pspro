package es.iessaladillo.pedrojoya.pspro.j7cc0102.main;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread.State;

import es.iessaladillo.pedrojoya.pspro.j7cc0101.tarea.Calculator;

public class Main {

	public static void main(String[] args) {

		// Información sobre prioridades de los hilos. 
		System.out.printf("Minimum Priority: %s\n",Thread.MIN_PRIORITY);
		System.out.printf("Normal Priority: %s\n",Thread.NORM_PRIORITY);
		System.out.printf("Maximun Priority: %s\n",Thread.MAX_PRIORITY);
		
		Thread hilos[];			// Array de hilos.
		Thread.State estados[];	// Array de estados.
		
		// Inicio 10 hilos que realizan el cálculo con números diferentes.
		// Los 5 hilos pares tendrán la prioridad máxima y 
		// los otros 5 hilos impares tendrán la prioridad mínima.
		// Establezco el nombre a cada hilo.
		hilos = new Thread[10];
		estados = new Thread.State[10];
		for (int i=0; i<10; i++){
			hilos[i]=new Thread(new Calculator(i));
			if ((i%2)==0){
				hilos[i].setPriority(Thread.MAX_PRIORITY);
			} else {
				hilos[i].setPriority(Thread.MIN_PRIORITY);
			}
			hilos[i].setName("Thread "+i);
		}
		
		
		// Wait for the finalization of the threads. Meanwhile, 
		// write the status of those threads in a file
		try (FileWriter file = new FileWriter(".\\data\\log.txt");PrintWriter pw = new PrintWriter(file);){
			
			for (int i=0; i<10; i++){
				pw.println("Main : Status of Thread "+i+" : "+hilos[i].getState());
				estados[i]=hilos[i].getState();
			}

			for (int i=0; i<10; i++){
				hilos[i].start();
			}
			
			boolean finish=false;
			while (!finish) {
				for (int i=0; i<10; i++){
					if (hilos[i].getState()!=estados[i]) {
						writeThreadInfo(pw, hilos[i],estados[i]);
						estados[i]=hilos[i].getState();
					}
				}
				
				finish=true;
				for (int i=0; i<10; i++){
					finish=finish &&(hilos[i].getState()==State.TERMINATED);
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 *  This method writes the state of a thread in a file
	 * @param pw : PrintWriter to write the data
	 * @param thread : Thread whose information will be written
	 * @param state : Old state of the thread
	 */
	private static void writeThreadInfo(PrintWriter pw, Thread thread, State state) {
		pw.printf("Main : Id %d - %s\n",thread.getId(),thread.getName());
		pw.printf("Main : Priority: %d\n",thread.getPriority());
		pw.printf("Main : Old State: %s\n",state);
		pw.printf("Main : New State: %s\n",thread.getState());
		pw.printf("Main : ************************************\n");
	}

}
