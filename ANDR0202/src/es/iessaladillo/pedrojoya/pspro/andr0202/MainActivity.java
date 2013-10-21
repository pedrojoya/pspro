package es.iessaladillo.pedrojoya.pspro.andr0202;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

    private ProgressBar prbBarra;
    private TextView lblMensaje;
    private ProgressBar prbCirculo;

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
        // Se hacen visibles las vistas.
        prbBarra.setVisibility(View.VISIBLE);
        lblMensaje.setVisibility(View.VISIBLE);
        prbCirculo.setVisibility(View.VISIBLE);
        // Se crea la tarea secundaria.
        Runnable tarea = new Runnable() {
            @Override
            public void run() {
                // Se realizan diez pasos.
                for (int i = 0; i < 10; i++) {
                    // Variable para envío de paquete.
                    final int valor = i;
                    // Se pone a trabajar.
                    trabajar();
                    // Envía un paquete al hilo principal para que lo ejecute.
                    prbBarra.post(new Runnable() {
                        @Override
                        public void run() {
                            lblMensaje.setText(getString(R.string.trabajando)
                                    + " " + (valor + 1) + " de 10");
                            prbBarra.setProgress(valor + 1);
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

    // Simula un trabajo de 1 segundo.
    private void trabajar() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
}
