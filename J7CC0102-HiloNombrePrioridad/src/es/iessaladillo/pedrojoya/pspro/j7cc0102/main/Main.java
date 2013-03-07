package es.iessaladillo.pedrojoya.pspro.j7cc0102.main;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread.State;

import es.iessaladillo.pedrojoya.pspro.j7cc0102.tarea.Calculadora;

public class Main {

    public static void main(String[] args) {

        // Variables locales.
        Thread hilos[];
        Thread.State estados[];

        // Información sobre prioridades de los hilos.
        System.out.printf("Prioridad mínima: %s\n", Thread.MIN_PRIORITY);
        System.out.printf("Prioridad normal: %s\n", Thread.NORM_PRIORITY);
        System.out.printf("Prioridad máxima: %s\n", Thread.MAX_PRIORITY);

        // Inicio 10 hilos que realizan el cálculo con números diferentes.
        hilos = new Thread[10];
        estados = new Thread.State[10];
        for (int i = 0; i < 10; i++) {
            hilos[i] = new Thread(new Calculadora(i));
            // Los 5 hilos pares tendrán la prioridad máxima y
            // los otros 5 hilos impares tendrán la prioridad mínima.
            if ((i % 2) == 0) {
                hilos[i].setPriority(Thread.MAX_PRIORITY);
            } else {
                hilos[i].setPriority(Thread.MIN_PRIORITY);
            }
            // Establezco el nombre a cada hilo.
            hilos[i].setName("Hilo " + i);
        }

        // Registro en el archivo los cambios de estado de los hilos hasta
        // que todos finalizan.
        try {

            // Creo el archivo para guardar la info y el objeto escritor.
            FileWriter archivo = new FileWriter(".\\data\\log.txt");
            PrintWriter escritor = new PrintWriter(archivo);

            // Inicializo el array de estados de los hilos con el estado
            // de cada hilo antes de iniciarse (NEW), imprimiéndolo en el
            // archivo.
            escritor.println("****************************");
            for (int i = 0; i < 10; i++) {
                escritor.println(hilos[i].getName() + " - Estado: "
                        + hilos[i].getState());
                estados[i] = hilos[i].getState();
            }

            // Inicio los 10 hilos.
            for (int i = 0; i < 10; i++) {
                hilos[i].start();
            }

            // Mientras no hayan finalizado los 10 hilos compruebo si
            // se ha producido el cambio en el estado de algún hilo y escribo
            // la info en el archivo.
            boolean finalizados = false;
            while (!finalizados) {
                for (int i = 0; i < 10; i++) {
                    // Si hay un cambio en el estado del hilo
                    if (hilos[i].getState() != estados[i]) {
                        // Escribo la info del hilo en el archivo.
                        escribirInfoHilo(escritor, hilos[i], estados[i]);
                        // Actualizo el estado del hilo en el array de estados.
                        estados[i] = hilos[i].getState();
                    }
                }
                // Compruebo si todos los hilos han finalizado.
                finalizados = true;
                for (int i = 0; i < 10; i++) {
                    finalizados = finalizados
                            && (hilos[i].getState() == State.TERMINATED);
                }
            }

            // Cierro el archivo.
            archivo.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Escribe el estado de un hilo en un archivo.
     * 
     * @param escritor
     *            : Escritor para escribir los datos.
     * @param hilo
     *            : Hilo cuya información quiere escribirse
     * @param estadoAnterior
     *            : Estado anterior del hilo.
     */
    private static void escribirInfoHilo(PrintWriter escritor, Thread hilo,
            State estadoAnterior) {
        escritor.println("**************************************");
        escritor.println(hilo.getName() + " - Id: " + hilo.getId());
        escritor.println(hilo.getName() + " - Prioridad: " 
                + hilo.getPriority());
        escritor.println(hilo.getName() + " - Estado anterior: "
                + estadoAnterior);
        escritor.println(hilo.getName() + " - Estado actual: "
                + hilo.getState());
        escritor.println("**************************************");
    }

}
