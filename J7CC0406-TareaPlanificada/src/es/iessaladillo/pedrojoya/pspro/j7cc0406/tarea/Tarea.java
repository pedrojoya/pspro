package es.iessaladillo.pedrojoya.pspro.j7cc0406.tarea;

import java.util.Date;
import java.util.concurrent.Callable;

// Simula una tarea consistente en mostrar un mensaje
// con la fecha/hora actual y retrona la cadena Hola Mundo.
public class Tarea implements Callable<String> {

    // Variables miembro.
    private String nombre;

    // Constructor.
    public Tarea(String nombre) {
        this.nombre = nombre;
    }

    // Escribe la fecha/hora actual y retorna Hola Mundo.
    @Override
    public String call() throws Exception {
        System.out.printf("%s -> Iniciada en: %s\n", nombre, new Date());
        return "Hola Mundo";
    }

}
