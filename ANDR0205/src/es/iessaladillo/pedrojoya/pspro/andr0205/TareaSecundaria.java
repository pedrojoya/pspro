package es.iessaladillo.pedrojoya.pspro.andr0205;

import android.os.AsyncTask;

// Clase interna para la Tarea Secundaria. Tipos recibidos:
// - Entrada: El tipo del valor recibido por doInBackground y que se pasa en
// el m�todo execute().
// - Progreso: El tipo del valor recibido por onProgressUpdate y que se pasa
// al hacer publishProgress().
// - Salida: El tipo del valor recibido por onPostExecute y que corresponde
// al valor de retorno del doInBackground.
public class TareaSecundaria extends AsyncTask<Integer, Integer, Integer> {

    // Definimos una interfaz para que la actividad sea informada cuando se
    // haya finalizado la tarea.
    public interface Callbacks {
        void onPreExecute();

        void onProgressUpdate(int progress);

        void onPostExecute(int result);
    }

    // Variables a nivel de clase.
    Callbacks listener;

    // Constructor.
    public TareaSecundaria(Callbacks listener) {
        this.listener = listener;
    }

    // Llamado antes de lanzar el hilo secundario. Se ejecuta en el hilo
    // principal.
    @Override
    protected void onPreExecute() {
        listener.onPreExecute();
    }

    // Llamado al lanzar el hilo secundario y corresponde al c�digo que �ste
    // ejecuta. Recibe lo que se le pase al m�todo execute() cuando se
    // ejecute la tarea as�ncrona. Se puede informar del progreso mediante
    // el m�todo publishProgress().
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
        // Se retorna el n�mero de trabajos realizados.
        return numTrabajos;
    }

    // Cada vez que se llama a publishProgress()
    @Override
    protected void onProgressUpdate(Integer... values) {
        listener.onProgressUpdate(values[0].intValue());
    }

    @Override
    protected void onCancelled() {
        // Se libera el listener.
        listener = null;
    }

    // Es lanzado autom�ticamente cuando se termina de ejecutar
    // doInBackground. Recibe lo que haya retornado �ste. Se ejecuta en el
    // hilo principal.
    @Override
    protected void onPostExecute(Integer result) {
        if (listener != null) {
            listener.onPostExecute(result.intValue());
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
