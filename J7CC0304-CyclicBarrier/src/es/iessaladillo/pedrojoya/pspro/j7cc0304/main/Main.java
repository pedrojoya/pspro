package es.iessaladillo.pedrojoya.pspro.j7cc0304.main;

import java.util.concurrent.CyclicBarrier;


import es.iessaladillo.pedrojoya.pspro.j7cc0304.tarea.Sumador;
import es.iessaladillo.pedrojoya.pspro.j7cc0304.tarea.Buscador;
import es.iessaladillo.pedrojoya.pspro.j7cc0304.utilidades.Matriz;
import es.iessaladillo.pedrojoya.pspro.j7cc0304.utilidades.Resultado;

public class Main {

    public static void main(String[] args) {

        // Constantes.
        final int FILAS = 10000;
        final int COLUMNAS = 1000;
        final int VALOR_A_BUSCAR = 5; 
        final int BUSCADORES = 5;
        final int FILAS_POR_BUSCADOR = 2000;
        
        // Creo una matriz con el número de filas y de columnas
        // especificado y la relleno con números aleatorios del 0 al 9.
        // Paso también el valor a buscar para que me informe de
        // cuántas veces aparece ese número en la matriz y así poder
        // comprobar después que la búsqueda es correcta.
        Matriz matriz = new Matriz(FILAS, COLUMNAS, VALOR_A_BUSCAR);
        
        // Creo el objeto para el resultado.
        // Corresponde a un array con tantos elementos como filas tenga
        // la matriz, en el que los buscadores escriben
        // cuántas veces aparece el valor en dicha fila. 
        Resultado resultado = new Resultado(FILAS);
        
        // Creo el objeto sumador de los resultados parciales de las filas,
        // que recorrerá el array de resultados parciales, sumando los resultados.
        Sumador sumador = new Sumador(resultado);
        
        // Creo el objeto CyclicBarrier object. Cuando los cinco hilos participantes
        // terminen su búsqueda parcial, el CyclicBarrier ejecutará un hilo
        // para el objeto sumador.
        CyclicBarrier barrera = new CyclicBarrier(BUSCADORES, sumador);
        
        // Creo e inicio los cinco hilos buscadores
        int primeraFila;
        int ultimaFila;
        for (int i = 0; i < BUSCADORES; i++) {
            primeraFila = i * FILAS_POR_BUSCADOR;
            ultimaFila = primeraFila + FILAS_POR_BUSCADOR;
            Thread hiloBuscador = new Thread(new Buscador(primeraFila, ultimaFila, matriz, 
                                                    resultado, VALOR_A_BUSCAR, barrera),
                                       "Buscador " + i);
            hiloBuscador.start();
        }
    }

}
