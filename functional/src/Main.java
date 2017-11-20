import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {

        System.out.printf("Hilo principal: %s\n", Thread.currentThread().getName());

        // Nombre.
        System.out.println("\n---");
        CompletableFuture<String> futuroNombre = obtenerNombre();
        String nombre;
        try {
            nombre = futuroNombre.get();
        } catch (InterruptedException e) {
            nombre = "<No disponible>";
        } catch (ExecutionException e) {
            nombre = "<No disponible>";
        }
        System.out.printf("Nombre: %s\n", nombre);
        System.out.println("---");

        // Apellidos.
        System.out.println("\n---");
        CompletableFuture<String> futuroApellidos = obtenerApellidos();
        String apellidos;
        try {
            apellidos = futuroApellidos.get();
        } catch (InterruptedException e) {
            apellidos = "<No disponible>";
        } catch (ExecutionException e) {
            apellidos = "<No disponible>";
        }
        System.out.printf("Apellidos: %s\n", apellidos);
        System.out.printf("Nombre completo: %s %s\n", nombre, apellidos);
        System.out.println("---");

        // Nombre y apellidos encadenados.
        System.out.println("\n---");
        CompletableFuture<String> futuroNombreCompleto = obtenerNombreCompleto();
        String nombreCompleto;
        try {
            nombreCompleto = futuroNombreCompleto.get();
        } catch (InterruptedException e) {
            nombreCompleto = "<No disponible>";
        } catch (ExecutionException e) {
            nombreCompleto = "<No disponible>";
        }
        System.out.printf("Nombre completo: %s\n", nombreCompleto);
        System.out.println("---");

        // Nombre y apellidos encadenados e impresos asíncronamente.
        CompletableFuture<Void> futuroImprimirNombreCompleto = imprimirNombreCompleto();
        // Esperamos a que el CompletableFuture termine.
        futuroImprimirNombreCompleto.join();

        // Nombre completo en paralelo.
        System.out.println("\n---");
        CompletableFuture<Void> futuroNombreCompletoEnParalelo = obtenerNombreCompletoEnParalelo();
        // Esperamos a que el CompletableFuture termine.
        futuroNombreCompletoEnParalelo.join();

    }

    private static CompletableFuture<String> obtenerNombre() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("Obteniendo nombre...");
                Thread.sleep(2000);
                return "Baldomero";
            } catch (InterruptedException e) {
                return "<No disponible>";
            }
        });
    }

    private static CompletableFuture<String> obtenerApellidos() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("Obteniendo apellidos...");
                Thread.sleep(2000);
                return "Llégate Ligero";
            } catch (InterruptedException e) {
                return "<No disponible>";
            }
        });
    }

    private static CompletableFuture<String> obtenerNombreCompleto() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("Obteniendo nombre...");
                Thread.sleep(2000);
                return "Baldomero";
            } catch (InterruptedException e) {
                return "<No disponible>";
            }
        }).thenApply(nombre -> {
            try {
                System.out.println("Obteniendo apellidos...");
                Thread.sleep(2000);
                return nombre + " " + "Llégate Ligero";
            } catch (InterruptedException e) {
                return nombre + " " + "<No disponible>";
            }
        });
    }

    private static CompletableFuture<Void> imprimirNombreCompleto() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("\n---");
                System.out.printf("%s - Obteniendo nombres...\n", Thread.currentThread().getName());
                Thread.sleep(2000);
                return "Baldomero";
            } catch (InterruptedException e) {
                return "<No disponible>";
            }
        }).thenApplyAsync(nombre -> {
            try {
                System.out.printf("%s - Obteniendo apellidos...\n", Thread.currentThread().getName());
                Thread.sleep(2000);
                return nombre + " " + "Llégate Ligero";
            } catch (InterruptedException e) {
                return nombre + " " + "<No disponible>";
            }
        }).thenAcceptAsync(nombreCompleto -> {
            System.out.printf("%s - Imprimiendo nombre completo...\n", Thread.currentThread().getName());
            System.out.printf("Nombre completo: %s\n", nombreCompleto);
            System.out.println("---");
        });
    }

    private static CompletableFuture<Void> obtenerNombreCompletoEnParalelo() {
        CompletableFuture<String> futureNombre = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("Obteniendo nombre...");
                Thread.sleep(2000);
                return "Baldomero";
            } catch (InterruptedException e) {
                return "<No disponible>";
            }
        });
        CompletableFuture<String> futureApellidos = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("Obteniendo apellidos...");
                Thread.sleep(2000);
                return "Llégate Ligero";
            } catch (InterruptedException e) {
                return "<No disponible>";
            }
        });
        // Como se ejecutan en paralelo el nombres y los apellidos se están calculando a la vez.
        return CompletableFuture.allOf(futureNombre, futureApellidos).thenRunAsync(()-> {
            try {
                System.out.printf("%s - Imprimiendo nombre completo...\n", Thread.currentThread().getName());
                System.out.printf("Nombre completo: %s %s\n", futureNombre.get(), futureApellidos.get());
                System.out.println("---");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

}