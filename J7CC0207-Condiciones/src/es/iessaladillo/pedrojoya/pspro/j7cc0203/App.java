package es.iessaladillo.pedrojoya.pspro.j7cc0203;

public class App {

    public static void main(String[] args) {
        Almacen almacen = new Almacen();
        Productor productor = new Productor(almacen);
        Consumidor consumidor = new Consumidor(almacen);
        productor.start();
        consumidor.start();
    }

}
