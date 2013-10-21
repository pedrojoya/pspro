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
    protected static final int onProgressUpdate = 0;
    protected static final int onPostExecute = 1;

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
        // Se hacen visibles las vistas para el progreso.
        mostrarBarras();
        // Se crea el manejador.
        manejador = new Manejador();
        // Se crea la tarea secundaria.
        Runnable tarea = new TareaSecundaria();
        // Se crea el hilo y se inicia.
        Thread hiloSecundario = new Thread(tarea);
        hiloSecundario.start();
    }

    private void mostrarBarras() {
        prbBarra.setVisibility(View.VISIBLE);
        lblMensaje.setVisibility(View.VISIBLE);
        prbCirculo.setVisibility(View.VISIBLE);
    }

    // Resetea las vistas.
    private void resetearVistas() {
        prbBarra.setVisibility(View.INVISIBLE);
        prbBarra.setProgress(0);
        lblMensaje.setVisibility(View.INVISIBLE);
        lblMensaje.setText(R.string.trabajando);
        prbCirculo.setVisibility(View.INVISIBLE);
        prbCirculo.setProgress(0);
    }

    // Clase interna para la Tarea Secundaria.
    private class TareaSecundaria implements Runnable {

        @Override
        public void run() {
            // Se realizan diez pasos.
            for (int i = 0; i < 10; i++) {
                // Se pone a trabajar.
                trabajar();
                // Crea y envía un mensaje de actualización al manejador.
                Message mensaje = new Message();
                mensaje.what = onProgressUpdate;
                mensaje.arg1 = i + 1;
                manejador.sendMessage(mensaje);
            }
            // Crea y envía el mensaje de fin de ejecución al manejador.
            Message mensaje = new Message();
            mensaje.what = onPostExecute;
            manejador.sendMessage(mensaje);
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
            // Actualización barra.
            case onProgressUpdate:
                int valor = mensaje.arg1;
                lblMensaje.setText(getString(R.string.trabajando) + " " + valor
                        + " de 10");
                prbBarra.setProgress(valor);
                break;
            // Termina el hilo secundario.
            case onPostExecute:
                resetearVistas();
                break;
            }
        }

    }

    /**
     * Instances of static inner classes do not hold an implicit reference to
     * their outer class.
     * 
     * private static class MyHandler extends Handler { private final
     * WeakReference<SampleActivity> mActivity;
     * 
     * public MyHandler(SampleActivity activity) { mActivity = new
     * WeakReference<SampleActivity>(activity); }
     * 
     * @Override public void handleMessage(Message msg) { SampleActivity
     *           activity = mActivity.get(); if (activity != null) { // ... } }
     *           }
     **/

}
