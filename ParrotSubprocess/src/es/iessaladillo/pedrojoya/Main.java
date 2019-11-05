package es.iessaladillo.pedrojoya;

import java.io.*;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        try {
            algo2();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        try {
//            showLibrary();
//        } catch (IOException e) {
//            System.out.println("Error showing library");
//        }
    }

    public static void showLibrary() throws IOException {
        ArrayList<String> command = new ArrayList<>(Arrays.asList("ls", "-ltra", "/Library"));
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        Process process = null;
        process = processBuilder.start();
        System.out.printf("Receiving from command %s ...\n\n", String.join(" ", command));
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                process.getInputStream()))) {
            bufferedReader.lines().forEach(System.out::println);
        }
    }

    public static void getInfoTest() throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder("echo", "Hello World!");
        String notAvailable = "<not available>";
        Process process = processBuilder.start();
        ProcessHandle.Info info = process.info();
        System.out.printf("Process ID: %s\n", process.pid());
        System.out.printf("Command name: %s\n", info.command().orElse(notAvailable));
        System.out.printf("Command line: %s\n", info.commandLine().orElse(notAvailable));
        System.out.printf("Start time: %s\n",
                info.startInstant().map(i -> i.atZone(ZoneId.systemDefault())
                        .toLocalDateTime().toString())
                        .orElse(notAvailable));
        System.out.printf("Arguments: %s\n",
                info.arguments().map(arguments -> Stream.of(arguments)
                        .collect(Collectors.joining(" ")))
                        .orElse(notAvailable));
        System.out.printf("User: %s\n", info.user().orElse(notAvailable));
    }

    public static void redirectToFileTest() throws IOException, InterruptedException {
        File outFile = new File("out.tmp");
        Process p = new ProcessBuilder("ls", "-la")
                .redirectOutput(outFile)
                .redirectError(ProcessBuilder.Redirect.INHERIT)
                .start();
        int status = p.waitFor();
        if (status == 0) {
            p = new ProcessBuilder("cat", outFile.toString())
                    .inheritIO()
                    .start();
            p.waitFor();
        }
    }

    private static void startProcessesTest() throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder("ls", "-la");
        Process process = processBuilder.start();
        process.onExit().thenAccept(p -> printResults(p));
        // The parent process can go on executing other things.
        // ...
        System.out.println("\nPress enter to continue ...\n");
        System.in.read();
    }

    private static void printResults(Process p) {
        try {
            System.out.printf("Exit %d, status %d%n%s%n%n",
                    p.pid(), p.exitValue(), output(p.getInputStream()));
        } catch (IOException e) {
            System.out.println("Error printing results");
        }
    }

    private static String output(InputStream inputStream) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            return br.lines().collect(Collectors.joining(System.getProperty("line.separator")));
        }
    }

    // ...

    static void showUserProcesses() {
        Optional<String> currUser = ProcessHandle.current().info().user();
        ProcessHandle.allProcesses()
                .filter(p -> p.info().user().equals(currUser))
                .forEach(p -> showProcess(p));
    }

    static void showProcess(ProcessHandle ph) {
        ProcessHandle.Info info = ph.info();
        System.out.printf("pid: %d, user: %s, cmd: %s%n",
                ph.pid(), info.user().orElse("none"), info.command().orElse("none"));
    }

    static void algo() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://httpbin.org/get"))
                .build();
        httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> {
                    System.out.println(response.statusCode());
                    return response;
                })
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println);
        Thread.sleep(3000);
    }

    public static HttpRequest.BodyPublisher ofFormData(Map<Object, Object> data) {
        var builder = new StringBuilder();
        for (Map.Entry<Object, Object> entry : data.entrySet()) {
            if (builder.length() > 0) {
                builder.append("&");
            }
            builder.append(URLEncoder.encode(entry.getKey().toString(),
                    StandardCharsets.UTF_8));
            builder.append("=");
            builder.append(URLEncoder.encode(entry.getValue().toString(),
                    StandardCharsets.UTF_8));
        }
        return HttpRequest.BodyPublishers.ofString(builder.toString());
    }

    static void algo2() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        Map<Object, Object> data = Map.of(
                "username", "abc",
                "password", "123",
                "custom", "secret",
                "ts", System.currentTimeMillis());
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .POST(ofFormData(data))
                .uri(URI.create("https://httpbin.org/post"))
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();
        HttpResponse<String> httpResponse =
                httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(httpResponse.statusCode());
        System.out.println(httpResponse.body());
    }

    private boolean sleep(int timeInMillis) {
        try {
            Thread.sleep(timeInMillis);
            return true;
        } catch (InterruptedException e) {
            return false;
        }
    }
}