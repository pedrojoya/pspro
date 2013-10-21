package es.iessaladillo.pedrojoya.pspro.andr0203;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

@SuppressLint("SimpleDateFormat")
public class MainActivity extends Activity implements OnClickListener {

    private Button btnIniciar;
    private Thread hiloSecundario;
    private TextView lblTiempo;
    private SimpleDateFormat formateador = new SimpleDateFormat("HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getVistas();
    }

    private void getVistas() {
        lblTiempo = (TextView) this.findViewById(R.id.lblTiempo);
        lblTiempo.setText(formateador.format(new Date()));
        btnIniciar = (Button) this.findViewById(R.id.btnIniciar);
        btnIniciar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Dependiendo del bot�n pulsado.
        switch (v.getId()) {
        case R.id.btnIniciar:
            if (btnIniciar.getText().toString()
                    .equals(getString(R.string.iniciar))) {
                iniciar();
            } else {
                parar();
            }
            break;
        }
    }

    private void iniciar() {
        // Se crea el hilo pas�ndole el tiempo guardado al objeto Crono.
        hiloSecundario = new Thread(new Reloj(), "Secundario");
        // Se inicia el hilo.
        hiloSecundario.start();
        // Se cambia el texto del bot�n.
        btnIniciar.setText(R.string.parar);
    }

    private void parar() {
        // Se interrumple el hilo.
        hiloSecundario.interrupt();
        // Se cambia el texto del bot�n.
        btnIniciar.setText(R.string.iniciar);
    }

    private class Reloj implements Runnable {

        // Variables a nivel de clase.
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat formateador = new SimpleDateFormat("HH:mm:ss");

        @Override
        public void run() {
            while (true) {
                // Se obtiene la representaci�n en cadena de la hora actual.
                // La variable debe ser final para que se pueda enviar en el
                // Runnable que se env�a al hilo principal.
                final String cadena = formateador.format(new Date());
                // Se env�a la actualizaci�n al hilo principal.
                lblTiempo.post(new Runnable() {

                    @Override
                    public void run() {
                        lblTiempo.setText(cadena);
                    }

                });
                // Espera un segundo.
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // Se finaliza el hilo si se produce la interrupci�n
                    // mientras se duerme.
                    return;
                }
            }
        }

    }

}
