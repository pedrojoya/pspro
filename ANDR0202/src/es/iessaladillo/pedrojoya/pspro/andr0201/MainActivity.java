package es.iessaladillo.pedrojoya.pspro.andr0201;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

    private ProgressBar prbBarra;
    private TextView lblMensaje;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Se obtienen las vistas necesarias.
        prbBarra = (ProgressBar) findViewById(R.id.prbBarra);
        lblMensaje = (TextView) findViewById(R.id.lblMensaje);
    }

    // Cuando se hace click en btnIniciar.
    public void btnIniciarOnClick(View view) {
        // Se hacen visibles las vistas.
        prbBarra.setVisibility(View.VISIBLE);
        lblMensaje.setVisibility(View.VISIBLE);
        // Se crea la tarea secundaria.
        Runnable tarea = new Runnable() {
            @Override
            public void run() {
                // Se realizan diez pasos.
                for (int i = 0; i <= 10; i++) {
                    // Variable para envío de paquete.
                    final int valor = i;
                    // Se pone a trabajar.
                    trabajar();
                    // Envía un paquete al hilo principal para que lo ejecute.
                    prbBarra.post(new Runnable() {
                        @Override
                        public void run() {
                            lblMensaje.setText(getString(R.string.actualizando)
                                    + " " + valor + " de 10");
                            prbBarra.setProgress(valor);
                        }
                    });
                }
                // Se resetean los campos.
                prbBarra.post(new Runnable() {

                    @Override
                    public void run() {
                        resetearVistas();
                    }
                });
            }
        };
        // Se crea el hilo y se inicia.
        Thread hiloSecundario = new Thread(tarea);
        hiloSecundario.start();
    }

    // Simula un trabajo de 2 segundos.
    private void trabajar() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Resetea las vistas.
    private void resetearVistas() {
        prbBarra.setVisibility(View.INVISIBLE);
        prbBarra.setProgress(0);
        lblMensaje.setVisibility(View.INVISIBLE);
    }
}
