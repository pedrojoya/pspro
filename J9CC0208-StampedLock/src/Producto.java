import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

public class Producto {

    private double precio;
    private StampedLock cerrojo = new StampedLock();
    SimpleDateFormat formateador = new SimpleDateFormat("HH:mm:ss");

    public Producto(double precio){
        this.precio = precio;
    }

    public double getPrecio() {
        // Se obtiene el cerrojo de lectura de manera optimista.
        long stamp = cerrojo.tryOptimisticRead();
        // Se informa
        System.out.printf("%s -> %s - Consultando precio...\n",
                formateador.format(new Date()),
                Thread.currentThread().getName());
        // Se obtiene el precio simulando que se tarda 3 segundos.
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        double valor = precio;
        // Si ya no es válido, se obtiene el cerrojo de manera pesimista.
        if (!cerrojo.validate(stamp)) {
            // Se obtiene el cerrojo para lectura de forma pesimista.
            stamp = cerrojo.readLock();
            valor = precio;
            // Se libera el cerrojo obtenido de forma pesimista.
            cerrojo.unlockRead(stamp);

        }
        // NO es necesario liberar el cerrojo si se ha obtenido de forma optimista.
        // Se informa.
        System.out.printf("%s -> %s - Precio: %.2f\n",
                formateador.format(new Date()),
                Thread.currentThread().getName(),
                valor);
        // Se retorna el precio.
        return valor;
    }

    public void setPrecio(double precio) {
        // Se obtiene el cerrojo en modo pesimista.
        long stamp = cerrojo.writeLock();
        // Se informa.
        System.out.printf("%s -> %s - Cambiando precio...\n",
                formateador.format(new Date()),
                Thread.currentThread().getName());
        // Se cambia el precio simulando que se tarda 4 segundos.
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.precio = precio;
        // Se informa.
        System.out.printf("%s -> %s - Nuevo Precio: %.2f\n",
                formateador.format(new Date()),
                Thread.currentThread().getName(),
                this.precio);
        cerrojo.unlockWrite(stamp);
    }

}
