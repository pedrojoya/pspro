package es.iessaladillo.pedrojoya.pspro.j7cc0304.utilidades;

import java.util.Random;

// Matriz que se rellena con n�mero aleatorios de 1 al 9.
public class Matriz {

    // Matriz de datos.
    private int datos[][];
    
    // Constructor. Recibe el n�mero de filas, el n�mero de columnas y 
    // el valor del que queremos que se nos informe de cu�ntos
    // veces se ha usado en el relleno de la matriz.
    public Matriz(int filas, int columnas, int numero) {
        // Inicializo el contador.
        int contador = 0;
        // Creo la matriz de datos con el n�mero de filas y columnas
        // especificado.
        datos=new int[filas][columnas];
        // Recorro la matriz rellen�ndola con n�meros aleatorios 
        // del 0 al 9, contando cu�ntas veces se va a usar el 
        // valor indicado en la matriz.
        Random random=new Random();
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++){
                datos[i][j] = random.nextInt(10);
                if (datos[i][j] == numero){
                    contador++;
                }
            }
        }
        System.out.printf("Matriz: Hay %d ocurrencias del valor %d.\n", contador, numero);
    }
    
    // Retorna el array correspondiente a una fila de la matriz o null si no existe.
    // Recibe el n�mero de fila que se quiere obtener.
    public int[] getFila(int fila){
        // Si la fila es v�lida la retorno.
        if ((fila >= 0) && (fila < datos.length)) {
            return datos[fila];
        }
        // Si la fila no es v�lida retorno null.
        return null;
    }

}
