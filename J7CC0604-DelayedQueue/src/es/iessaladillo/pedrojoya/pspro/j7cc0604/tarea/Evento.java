package es.iessaladillo.pedrojoya.pspro.j7cc0604.tarea;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

// Simula un evento con fecha de activación.
public class Evento implements Delayed {

    // Variables miembro.
    private String nombre;
    private Date activacion;

    // Constructor. Recibe el nombre y la fecha de activación.
    public Evento(String nombre, Date activacion) {
        this.nombre = nombre;
        this.activacion = activacion;
    }

    // Getters.
    public String getNombre() {
        return nombre;
    }

    public Date getActivacion() {
        return activacion;
    }

    // Compara cuánto falta para la activación de dos eventos. Retorna un valor
    // menor, igual o mayor que cero dependiendo de si al objeto sobre el que se
    // ejecuta el método le falta para activarse menos, igual o más que al
    // evento recibido como parámetro.
    @Override
    public int compareTo(Delayed o) {
        long resultado = this.getDelay(TimeUnit.NANOSECONDS)
                - o.getDelay(TimeUnit.NANOSECONDS);
        if (resultado < 0) {
            return -1;
        } else if (resultado > 0) {
            return 1;
        }
        return 0;
    }

    // Retorna cuánto falta para que el evento se active.
    @Override
    public long getDelay(TimeUnit unit) {
        Date ahora = new Date();
        long diferencia = activacion.getTime() - ahora.getTime();
        return unit.convert(diferencia, TimeUnit.MILLISECONDS);
    }

}
