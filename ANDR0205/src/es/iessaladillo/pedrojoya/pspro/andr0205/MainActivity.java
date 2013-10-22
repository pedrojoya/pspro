package es.iessaladillo.pedrojoya.pspro.andr0205;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity implements TareaSecundaria.Callbacks {

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
        tarea = new TareaSecundaria(this);
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
    public void onPreExecute() {
        // Se hacen visibles las vistas para el progreso.
        mostrarBarras();
    }

    @Override
    public void onProgressUpdate(int progress) {
        // Se actualiza la barra.
        lblMensaje.setText(getString(R.string.trabajando) + " " + progress
                + " de 10");
        prbBarra.setProgress(progress);
    }

    @Override
    public void onPostExecute(int result) {
        // Se muestra el mensaje de finalización.
        lblMensaje.setText(getString(R.string.realizadas) + " " + result + " "
                + getString(R.string.tareas));
        resetearVistas();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (tarea != null) {
            tarea.cancel(true);
            tarea = null;
        }
    }

}
