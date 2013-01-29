package es.iessaladillo.pedrojoya.pspro.j7cc0607.tarea;

import java.util.concurrent.atomic.AtomicLong;

// Simula una cuenta bancaria.
public class Cuenta {

    // Propiedades.
    private AtomicLong saldo;

    // Constructores.
    public Cuenta() {
        saldo = new AtomicLong();
    }

    public Cuenta(Long saldoInicial) {
        this();
        saldo.set(saldoInicial);
    }

    // Getters y Setters.
    public long getSaldo() {
        return saldo.get();
    }

    public void setSaldo(long nuevoSaldo) {
        saldo.set(nuevoSaldo);
    }

    // Hace un abono en cuenta. Recibe la cantidad.
    public void abonar(long cantidad) {
        saldo.getAndAdd(cantidad);
    }

    // Hace un cargo en cuenta. Recibe la cantidad.
    public void cargar(long cantidad) {
        saldo.getAndAdd(-cantidad);
    }

}
