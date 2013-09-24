package es.iessaladillo.pedrojoya.pspro.gar0101;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        // Se obtiene en entorno de ejecución.
        Runtime entorno = Runtime.getRuntime();
        String orden = "NOTEPAD";
        // Se inicia el proceso de NOTEPAD.
        try {
            @SuppressWarnings("unused")
            Process proceso = entorno.exec(orden);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}