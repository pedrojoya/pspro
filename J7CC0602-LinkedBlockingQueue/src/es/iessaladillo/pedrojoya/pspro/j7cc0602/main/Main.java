package es.iessaladillo.pedrojoya.pspro.j7cc0602.main;

import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import es.iessaladillo.pedrojoya.pspro.j7cc0602.tarea.TareaAdicion;

public class Main {

    public static void main(String[] args) throws Exception {
        // Creo una lista de como máximo 3 elementos.
        LinkedBlockingDeque<String> lista = new LinkedBlockingDeque<String>(3);
        // Creo e inicio una tarea de adición que tratará de insertar 15
        // elementos en la lista.
        Thread hiloAdicion = new Thread(new TareaAdicion(lista));
        hiloAdicion.start();
        // Extraido 15 elementos de la lista en 5 ciclos de 3 elementos.
        for (int i = 0; i < 5; i++) {
            System.out.printf("%s -> Ciclo %d de extracción\n", new Date(),
                    i + 1);
            for (int j = 0; j < 3; j++) {
                String elemento = lista.take();
                System.out.printf("%s -> Extraído: %s. Lista: %d\n",
                        new Date(), elemento, lista.size());
            }
            // Espero tres milisegundos entre ciclos.
            TimeUnit.MILLISECONDS.sleep(300);
        }
    }

}