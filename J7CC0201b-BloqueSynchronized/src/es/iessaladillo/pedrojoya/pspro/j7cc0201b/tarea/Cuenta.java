package es.iessaladillo.pedrojoya.pspro.j7cc0201b.tarea;

// Cuenta bancaria
public class Cuenta {

    // Propiedades.
    private double saldo;

    // Constructor.
    public Cuenta(double saldo) {
        this.saldo = saldo;
    }

    // Getters y Setters.
    public double getSaldo() {
        return saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    // Realiza un ingreso en la cuenta. Recibe la cantidad a ingresar.
    public void ingresar(double cantidad) {
        synchronized (this) {
            // Incremento el saldo.
            saldo += cantidad;
        }
        // Duermo el hilo durante dos décimas de segundo 
        // para simular el ingreso.
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    // Realiza un cargo en la cuenta. Recibe la cantidad a cargar.
    public void cargar(double cantidad) {
        synchronized (this) {
            // Decremento el saldo.
            saldo -= cantidad;
        }
        // Duermo el hilo durante dos décimas de segundo 
        // para simular el cargo.
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}
