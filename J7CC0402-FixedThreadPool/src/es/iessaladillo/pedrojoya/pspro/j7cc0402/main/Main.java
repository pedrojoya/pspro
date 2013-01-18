package es.iessaladillo.pedrojoya.pspro.j7cc0402.main;

import es.iessaladillo.pedrojoya.pspro.j7cc0402.tarea.Servidor;
import es.iessaladillo.pedrojoya.pspro.j7cc0402.tarea.Tarea;

public class Main {

    public static void main(String[] args) {
        // Creo el servidor
        Servidor servidor = new Servidor();        
        // Envío 50 peticiones al servidor y finalizo.
        for (int i = 0; i < 50; i++) {
            Tarea tarea = new Tarea("Tarea " + i);
            servidor.ejecutarTarea(tarea);
        }
        // Finalizo el servidor
        servidor.apagarServidor();
    }

}