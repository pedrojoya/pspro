package es.iessaladillo.pedrojoya.pspro.andr0201;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

    private Button btnIniciar;
    private Button btnParar;
    private Thread hiloSecundario;
    private TextView lblTiempo;
    private long tiempo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getVistas();
    }

    private void getVistas() {
        lblTiempo = (TextView) this.findViewById(R.id.lblTiempo);
        btnIniciar = (Button) this.findViewById(R.id.btnIniciar);
        btnParar = (Button) this.findViewById(R.id.btnParar);
        btnIniciar.setOnClickListener(this);
        btnParar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Dependiendo del botón pulsado.
        switch (v.getId()) {
        case R.id.btnIniciar:
            if (btnIniciar.getText().toString()
                    .equals(getString(R.string.iniciar))) {
                iniciar();
            } else {
                pausar();
            }
            break;
        case R.id.btnParar:
            parar();
            break;
        }
    }

    private void iniciar() {
        // Se crea el hilo pasándole el tiempo guardado al objeto Crono.
        hiloSecundario = new Thread(new Crono(tiempo), "Secundario");
        // Se inicia el hilo.
        hiloSecundario.start();
        // Se cambia el texto del botón.
        btnIniciar.setText(R.string.pausar);
    }

    private void pausar() {
        // Se interrumpe el hilo.
        hiloSecundario.interrupt();
        // Se cambia el texto del botón.
        btnIniciar.setText(R.string.iniciar);
    }

    private void parar() {
        // Se interrumple el hilo.
        hiloSecundario.interrupt();
        // Se cambia el texto del botón.
        btnIniciar.setText(R.string.iniciar);
        // Se resetea los segundos guardados;
        tiempo = 0;
        // Se actualiza el tiempo mostrado.
        lblTiempo.setText("00:00:00");
    }

    private class Crono implements Runnable {

        // Propieades.
        long milisegundos;

        // Constructores.
        public Crono(long milisegundos) {
            this.milisegundos = milisegundos;
        }

        public Crono() {
            milisegundos = 0;
        }

        @SuppressLint("DefaultLocale")
        @Override
        public void run() {
            while (true) {
                // Se obtiene la representación en cadena del número de
                // segundos.
                final String cadena = String.format("%02d:%02d:%02d",
                        (milisegundos / 1000) / 3600,
                        ((milisegundos / 1000) % 3600) / 60,
                        (milisegundos / 1000) % 60);
                // Se envía la actualización al control.
                lblTiempo.post(new Runnable() {

                    @Override
                    public void run() {
                        lblTiempo.setText(cadena);
                        tiempo = milisegundos;

                    }
                });
                // Espera un segundo.
                try {
                    Thread.sleep(1000 - (milisegundos % 1000));
                } catch (InterruptedException e) {
                    // Se finaliza el hilo si se produce la interrupción
                    // mientras se duerme.
                    return;
                }
                // Se incrementa un segundo.
                milisegundos += 1000;
            }
        }

    }

}
