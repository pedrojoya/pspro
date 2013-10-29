package es.iessaladillo.pedrojoya.pspro.andr0205;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

    // Variables a nivel de clase.
    private ProgressBar prbBarra;
    private TextView lblMensaje;
    private ProgressBar prbCirculo;
    private TareaSecundaria tarea;

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
        // Se crea la tarea secundaria.
        tarea = new TareaSecundaria();
        // Lanzo la tarea secundaria indicando que debe hacer 10 trabajos.
        tarea.execute(10);
    }

    private void mostrarBarras() {
        prbBarra.setVisibility(View.VISIBLE);
        lblMensaje.setText(R.string.trabajando);
        lblMensaje.setVisibility(View.VISIBLE);
        prbCirculo.setVisibility(View.VISIBLE);
    }

    // Resetea las vistas.
    private void resetearVistas() {
        prbBarra.setVisibility(View.INVISIBLE);
        prbBarra.setProgress(0);
        prbCirculo.setVisibility(View.INVISIBLE);
        prbCirculo.setProgress(0);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (tarea != null) {
            tarea.cancel(true);
            tarea = null;
        }
    }

    // Clase interna para la Tarea Secundaria. Tipos recibidos:
    // - Entrada: El tipo del valor recibido por doInBackground y que se pasa en
    // el método execute().
    // - Progreso: El tipo del valor recibido por onProgressUpdate y que se pasa
    // al hacer publishProgress().
    // - Salida: El tipo del valor recibido por onPostExecute y que corresponde
    // al valor de retorno del doInBackground.
    private class TareaSecundaria extends AsyncTask<Integer, Integer, Integer> {

        // Llamado antes de lanzar el hilo secundario. Se ejecuta en el hilo
        // principal.
        @Override
        protected void onPreExecute() {
            // Se hacen visibles las vistas para el progreso.
            mostrarBarras();
        }

        // Llamado al lanzar el hilo secundario y corresponde al código que éste
        // ejecuta. Recibe lo que se le pase al método execute() cuando se
        // ejecute la tarea asíncrona. Se puede informar del progreso mediante
        // el método publishProgress(). Retorna el resultado de la tarea.
        @Override
        protected Integer doInBackground(Integer... params) {
            int numTrabajos = params[0].intValue();
            // Se realizan los pasos.
            for (int i = 0; i < numTrabajos; i++) {
                // Se pone a trabajar.
                trabajar();
                // Si la tarea ha sido cancelada se sale del bucle.
                if (isCancelled()) {
                    break;
                }
                // Informa del progreso.
                publishProgress(i + 1);
            }
            // Se retorna el número de trabajos realizados.
            return numTrabajos;
        }

        // Cada vez que se llama a publishProgress(). Recibe lo que éste le
        // pasa. Se ejecuta en el hilo principal.
        @Override
        protected void onProgressUpdate(Integer... values) {
            // Se actualiza la barra.
            int progreso = values[0].intValue();
            lblMensaje.setText(getString(R.string.trabajando) + " " + progreso
                    + " de 10");
            prbBarra.setProgress(progreso);

        }

        // Es lanzado automáticamente cuando se termina de ejecutar
        // doInBackground. Recibe lo que haya retornado éste. Se ejecuta en el
        // hilo principal.
        @Override
        protected void onPostExecute(Integer result) {
            if (!isCancelled()) {
                // Se muestra el mensaje de finalización.
                int resultado = result.intValue();
                lblMensaje.setText(getString(R.string.realizadas) + " "
                        + resultado + " " + getString(R.string.tareas));
                resetearVistas();

            }
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

}
