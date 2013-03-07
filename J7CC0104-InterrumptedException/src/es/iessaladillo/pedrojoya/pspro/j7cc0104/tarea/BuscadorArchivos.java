package es.iessaladillo.pedrojoya.pspro.j7cc0104.tarea;

import java.io.File;

// Busca un archivo en una carpeta y sus subcarpetas.
public class BuscadorArchivos implements Runnable {

    // Variables locales.
    private String pathCarpeta; // Carpeta inicial de búsqueda.
    private String nombreArchivo; // Nombre del archivo a buscar.

    // Constructor.
    public BuscadorArchivos(String carpeta, String archivo) {
        // Realizo la copia local de los parámetros.
        this.pathCarpeta = carpeta;
        this.nombreArchivo = archivo;
    }

    // Método que ejecuta el hilo.
    @Override
    public void run() {
        // Obtengo la carpeta inicial de búsqueda.
        File file = new File(pathCarpeta);
        // Si es una carpeta.
        if (file.isDirectory()) {
            try {
                // Proceso la carpeta
                procesarCarpeta(file);
            } catch (InterruptedException e) {
                // Si capturo esta excepción es porque el hilo
                // ha sido marcado como interrumpido.
                System.out.printf("%s: Se ha interrumpido el hilo de búsqueda",
                        Thread.currentThread().getName());
            }
        }
    }

    /**
     * Procesa una carpeta buscando el archivo
     * 
     * @param carpeta
     *            : Directorio en el que buscar
     * @throws InterruptedException
     *             : Si el hilo es marcado como interrumpido.
     */
    private void procesarCarpeta(File carpeta) throws InterruptedException {
        // Obtengo la lista de archivos y subcarpetas de la carpeta.
        File lista[] = carpeta.listFiles();
        if (lista != null) {
            // Proceso cada elemento de la lista.
            for (int i = 0; i < lista.length; i++) {
                // Si es una subcarpeta llamo recursivamente.
                if (lista[i].isDirectory()) {
                    procesarCarpeta(lista[i]);
                } else {
                    // If is a file, process it
                    procesarArchivo(lista[i]);
                }
            }
        }
        // Si he sido marcado como interrumpido genero la excepción
        // correspondiente que se propagará hacia el método llamador.
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
    }

    /**
     * Procesa un archivo comparando su nombre con el nombre del. archivo
     * buscando. Si es el buscado muestra su path absoluto.
     * 
     * @param archivo
     *            : Archivo a procesar.
     * @throws InterruptedException
     *             : Lanza esta excepción si el hilo está marcado como
     *             interrumpido.
     */
    private void procesarArchivo(File archivo) throws InterruptedException {
        // Comparo el nombre del archivo con el del archivo buscado.
        if (archivo.getName().equals(nombreArchivo)) {
            // Si lo he encontrado informo de us path absoluto.
            System.out.printf("%s : %s\n", Thread.currentThread().getName(),
                    archivo.getAbsolutePath());
        }
        // Si he sido marcado como interrumpido genero la excepción
        // correspondiente que se propagará hacia el método llamador.
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
    }

}
