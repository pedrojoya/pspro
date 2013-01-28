package es.iessaladillo.pedrojoya.pspro.j7cc0605.tarea;

import java.util.concurrent.ConcurrentSkipListMap;

// Almacena en el mapa 1000 contactos de la letra correspondiente.
public class Tarea implements Runnable {

    // Variables a nivel de clase.
    private ConcurrentSkipListMap<String, Contacto> mapa;
    private String letra;

    // Constructor. Recibe la lista y la letra.
    public Tarea(ConcurrentSkipListMap<String, Contacto> mapa, String letra) {
        this.letra = letra;
        this.mapa = mapa;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            Contacto contacto = new Contacto(letra + String.format("%03d", i),
                    "956" + String.format("%06d", i));
            mapa.put(contacto.getNombre() + "-" + contacto.getTlf(), contacto);
        }
    }

}
