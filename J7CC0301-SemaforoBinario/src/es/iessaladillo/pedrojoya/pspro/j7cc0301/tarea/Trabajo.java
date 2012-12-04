package es.iessaladillo.pedrojoya.pspro.j7cc0301.tarea;

// Trabajo que envía un documento a la impresora.
public class Trabajo implements Runnable {

    // Propiedades.
    private Impresora impresora;
    
    // Constructor
    public Trabajo(Impresora impresora){
        this.impresora = impresora;
    }
    
    @Override
    public void run() {
        impresora.imprimir(new Object());
    }
}
