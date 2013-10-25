package es.iessaladillo.pedrojoya.pspro.andr0204;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

    // Constantes (se definen con esos nombres para introducir las AsyncTasks.
    protected static final int onPreExecute = 0;
    protected static final int onProgressUpdate = 1;
    protected static final int onPostExecute = 2;

    // Variables a nivel de clase.
    private ProgressBar prbBarra;
    private TextView lblMensaje;
    private ProgressBar prbCirculo;
    private Handler manejador;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Se obtienen las vistas necesarias.
        prbBarra = (ProgressBar) findViewById(R.id.prbBarra);
        lblMensaje = (TextView) findViewById(R.id.lblMensaje);
        prbCirculo = (ProgressBar) findViewById(R.id.prbCirculo);
    }

    // Cuando se hace click en btnIniciar.
    public void btnIniciarOnClick(View view) {
        // Se crea el manejador.
        manejador = new Manejador();
        // Se crea la tarea secundaria.
        Runnable tarea = new TareaSecundaria();
        // Se crea el hilo y se inicia.
        Thread hiloSecundario = new Thread(tarea);
        hiloSecundario.start();
    }

    // Hace visibles las vistas relacionadas con el progreso.
    private void mostrarBarras() {
        prbBarra.setVisibility(View.VISIBLE);
        lblMensaje.setText(R.string.trabajando);
        lblMensaje.setVisibility(View.VISIBLE);
        prbCirculo.setVisibility(View.VISIBLE);
    }

    // Actualiza el valor de las barras de progreso.
    private void actualizarBarras(int progreso) {
        lblMensaje.setText(getString(R.string.trabajando) + " " + progreso
                + " de 10");
        prbBarra.setProgress(progreso);
    }

    // Resetea las vistas relacionadas con el progreso.
    private void resetearVistas() {
        prbBarra.setVisibility(View.INVISIBLE);
        prbBarra.setProgress(0);
        prbCirculo.setVisibility(View.INVISIBLE);
        prbCirculo.setProgress(0);
    }

    // Clase interna para la Tarea Secundaria.
    private class TareaSecundaria implements Runnable {

        @Override
        public void run() {
            // Crea y envía el mensaje de inicio de ejecución al manejador.
            Message msgInicio = new Message();
            msgInicio.what = onPreExecute;
            manejador.sendMessage(msgInicio);
            // Se realizan diez pasos.
            for (int i = 0; i < 10; i++) {
                // Se pone a trabajar.
                trabajar();
                // Crea y envía un mensaje de actualización al manejador.
                Message msgProgreso = new Message();
                msgProgreso.what = onProgressUpdate;
                msgProgreso.arg1 = i + 1;
                manejador.sendMessage(msgProgreso);
            }
            // Crea y envía el mensaje de fin de ejecución al manejador.
            Message msgFin = new Message();
            msgFin.what = onPostExecute;
            msgFin.arg1 = 10;
            manejador.sendMessage(msgFin);
        }

        // Simula un trabajo de 1 segundo.
        private void trabajar() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    // Clase interna para el Manejador.
    private class Manejador extends Handler {

        // Debemos sobrescribir este método para recibir mensajes.
        @Override
        public void handleMessage(Message mensaje) {
            // Dependiendo del mensaje recibido.
            switch (mensaje.what) {
            // Mensaje de inicio del hilo secundario.
            case onPreExecute:
                // Se hacen visibles las vistas para el progreso.
                mostrarBarras();
                break;
            // Mensaje de progreso del hilo secundario.
            case onProgressUpdate:
                // Se actualizan las barras.
                int progreso = mensaje.arg1;
                actualizarBarras(progreso);
                break;
            // Mensaje de fin del hilo secundario.
            case onPostExecute:
                // Se informa al usuario y se resetean las vistas.
                int tareas = mensaje.arg1;
                lblMensaje.setText(getString(R.string.realizadas) + " "
                        + tareas + " " + getString(R.string.tareas));
                resetearVistas();
                break;
            }
        }

    }

    /**
     * Para que no haya problemas de memoria, se debería crear la clase
     * Manejador como clase estática. Dado que las clases internas estáticas no
     * mantienen una referencia implícita a la clase exterior, será necesario
     * pasarle al constructor del manejador la actividad, que será almacenada
     * internamente en forma de WeakReference (referencia débil)
     * 
     * private static class Manejador extends Handler {
     * 
     * private final WeakReference<SampleActivity> actividadDebil;
     * 
     * public MyHandler(MainActivity actividad) { actividadDebil = new
     * WeakReference<MainActivity>(actividad); }
     * 
     * @Override public void handleMessage(Message msg) { MainActivity actividad
     *           = actividadDebil.get(); if (actividad != null) { // ... } } }
     **/

}
