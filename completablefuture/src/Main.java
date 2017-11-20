import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //runAsyncExample();
        //runAsyncExecutorExample();
        //supplyAsyncExample();
        //thenRunExample();
        //getNowExample();
        //thenAcceptExample();
        //thenAcceptAsyncExample();
        //thenApplyExample();
        //thenApplyStringExample();
        //completeExample();
        //throwExceptionExample();
        //exceptionallyExample();
        //exceptionallyThrowExample();
        //handleErrorExample();
        handleResultExample();
    }

    private static void runAsyncExample() {
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> System.out.printf("%s - Tarea asíncrona\n", Thread.currentThread().getName()));
        System.out.printf("%s - Principal\n", Thread.currentThread().getName());
    }

    private static void runAsyncExecutorExample() {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(
                () -> System.out.printf("%s - Tarea asíncrona\n", Thread.currentThread().getName()),
                executor
        );
        System.out.printf("%s - Final\n", Thread.currentThread().getName());
    }

    private static void supplyAsyncExample() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.printf("%s - Tarea asíncrona\n", Thread.currentThread().getName());
            return 2;
        });
        System.out.printf("%s - %d\n", Thread.currentThread().getName(), completableFuture.get());
    }

    private static Integer generarEntero() {
        int numero = 2;
        System.out.printf("%s - Genera %d\n", Thread.currentThread().getName(), numero);
        return numero;
    }

    private static void thenRunExample() {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        CompletableFuture.runAsync(
                () -> System.out.printf("%s - Tarea asíncrona\n", Thread.currentThread().getName()),
                executor
        ).thenRun(() -> System.out.printf("%s - Principal\n", Thread.currentThread().getName()));
    }

    private static void getNowExample() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(Main::generarEntero);
        System.out.printf("%s - %d\n", Thread.currentThread().getName(), completableFuture.getNow(-1));
    }

    private static void thenAcceptExample() {
        CompletableFuture.supplyAsync(Main::generarEntero).thenAccept(Main::imprimir);
        System.out.printf("%s - Principal\n", Thread.currentThread().getName());
    }

    private static void imprimir(Integer valor) {
        System.out.printf("%s - Consume %d\n", Thread.currentThread().getName(), valor);
    }

    private static void thenAcceptMainExample() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(Main::generarEntero);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Se consume en el hilo principal porque el hilo principal no tiene nada que hacer.
        // Por tanto, se recomienda siempre indicar el ejecutor, para asegurarnos de que
        // se ejecuta en un hilo secundario.
        future.thenAccept(Main::imprimir);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("%s - Principal\n", Thread.currentThread().getName());
    }

    private static void thenAcceptAsyncExample() {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(Main::generarEntero, executor);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        future.thenAcceptAsync(Main::imprimir, executor);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("%s - Principal\n", Thread.currentThread().getName());
    }

    private static void thenApplyExample() {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        CompletableFuture.supplyAsync(Main::generarEntero, executor)
                .thenApplyAsync(Main::duplicar, executor)
                .thenAcceptAsync(Main::imprimir, executor);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("%s - Principal\n", Thread.currentThread().getName());
    }

    private static Integer duplicar(Integer valor) {
        int result = valor * 2;
        System.out.printf("%s - Transforma duplicando a %d\n", Thread.currentThread().getName(), result);
        return result;
    }

    private static void thenApplyStringExample() {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        CompletableFuture.supplyAsync(Main::generarEntero, executor)
                .thenApplyAsync(valor -> {
                    System.out.printf("%s - Transforma %d en \"Valor: %d\"\n",
                            Thread.currentThread().getName(), valor, valor);
                    return "Valor: " + valor;
                }, executor)
                .thenAcceptAsync(cadena -> System.out.printf("%s - Consume %s\n",
                        Thread.currentThread().getName(), cadena), executor);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("%s - Principal\n", Thread.currentThread().getName());
    }

    private static void completeExample() {
        // Se construye la cadena de operaciones, que no es ejecutada hasta que no se completa explícitamente
        // el CompletableFuture original.
        CompletableFuture<String> completableFuture = saludoAsincrono();
        completableFuture.thenAccept(saludo -> System.out.printf("%s - Consume %s\n",
                Thread.currentThread().getName(), saludo));
        System.out.printf("%s - Principal\n", Thread.currentThread().getName());
    }

    public static CompletableFuture<String> saludoAsincrono() {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        Executors.newCachedThreadPool().submit(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String saludo = "Quillo que";
            System.out.printf("%s - Genera %s\n", Thread.currentThread().getName(), saludo);
            completableFuture.complete(saludo);
        });
        return completableFuture;
    }

    private static void throwExceptionExample() {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        // El resto de operaciones de la cadena no serán ejecutadas porque la primera lanza una
        // excepción.
        CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("Algo ha ido mal");
        }, executor)
                .thenApplyAsync(valor -> {
                    System.out.printf("%s - Transforma %d en \"Valor: %d\"\n",
                            Thread.currentThread().getName(), valor, valor);
                    return "Valor: " + valor;
                }, executor)
                .thenAcceptAsync(cadena -> System.out.printf("%s - Consume %s\n",
                        Thread.currentThread().getName(), cadena), executor);
        System.out.printf("%s - Principal\n", Thread.currentThread().getName());
    }

    private static void exceptionallyExample() {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        CompletableFuture.supplyAsync(() -> {
                    throw new RuntimeException("Algo ha ido mal");
                }, executor)
                .exceptionally(throwable -> {
                    System.out.printf("%s - Detecta error %s\n",
                            Thread.currentThread().getName(), throwable.getMessage());
                    return 0;
                })
                .thenApplyAsync(valor -> {
                    System.out.printf("%s - Transforma %d en \"Valor: %d\"\n",
                            Thread.currentThread().getName(), valor, valor);
                    return "Valor: " + valor;
                }, executor)
                .thenAcceptAsync(cadena -> System.out.printf("%s - Consume %s\n",
                        Thread.currentThread().getName(), cadena), executor);
        System.out.printf("%s - Principal\n", Thread.currentThread().getName());
    }

    private static void exceptionallyThrowExample() {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        CompletableFuture.supplyAsync(() -> {
                    throw new RuntimeException("Algo ha ido mal");
                }, executor)
                .exceptionally(throwable -> {
                    System.out.printf("%s - Detecta error %s\n",
                            Thread.currentThread().getName(), throwable.getMessage());
                    throw new RuntimeException(throwable.getMessage());
                })
                .thenApplyAsync(valor -> {
                    System.out.printf("%s - Transforma %d en \"Valor: %d\"\n",
                            Thread.currentThread().getName(), valor, valor);
                    return "Valor: " + valor;
                }, executor)
                .thenAcceptAsync(cadena -> System.out.printf("%s - Consume %s\n",
                        Thread.currentThread().getName(), cadena), executor);
        System.out.printf("%s - Principal\n", Thread.currentThread().getName());
    }

    private static void handleErrorExample() {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("Algo ha ido mal");
        }, executor)
                .handle((result, throwable) -> {
                    if (throwable != null) {
                        System.out.printf("%s - Maneja error %s\n",
                                Thread.currentThread().getName(), throwable.getMessage());
                        return throwable.getMessage();
                    } else {
                        System.out.printf("%s - Manejando %d\n",
                                Thread.currentThread().getName(), result);
                        return result;
                    }
                })
                .thenApplyAsync(valor -> {
                    System.out.printf("%s - Transforma %d en \"Valor: %d\"\n",
                            Thread.currentThread().getName(), valor, valor);
                    return "Valor: " + valor;
                }, executor)
                .thenAcceptAsync(cadena -> System.out.printf("%s - Consume %s\n",
                        Thread.currentThread().getName(), cadena), executor);
        System.out.printf("%s - Principal\n", Thread.currentThread().getName());
    }

    private static void handleResultExample() {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        CompletableFuture.supplyAsync(Main::generarEntero, executor)
                .handle((result, throwable) -> {
                    if (throwable != null) {
                        System.out.printf("%s - Maneja error %s\n",
                                Thread.currentThread().getName(), throwable.getMessage());
                        return throwable.getMessage();
                    } else {
                        System.out.printf("%s - Maneja %d\n",
                                Thread.currentThread().getName(), result);
                        return result;
                    }
                })
                .thenApplyAsync(valor -> {
                    System.out.printf("%s - Transforma %d en \"Valor: %d\"\n",
                            Thread.currentThread().getName(), valor, valor);
                    return "Valor: " + valor;
                }, executor)
                .thenAcceptAsync(cadena -> System.out.printf("%s - Consume %s\n",
                        Thread.currentThread().getName(), cadena), executor);
        System.out.printf("%s - Principal\n", Thread.currentThread().getName());
    }

}
