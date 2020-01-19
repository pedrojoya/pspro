package es.iessaladillo.pedrojoya.httpurlconnection;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

@SuppressWarnings("GrazieInspection")
public class HttpUtils {

    private HttpUtils() {
    }

    public static void doHttpRequest(URL url, String requestMethod, Map<String, String> requestHeaders,
                                     byte[] requestBody, int timeout) {
        HttpURLConnection httpUrlConnection = null;
        try {
            // 1. Create connection with the URL.
            httpUrlConnection = (HttpURLConnection) url.openConnection();
            // 2. Setup request
            // 2.1. Request method.
            httpUrlConnection.setRequestMethod(requestMethod);
            // PATCH method is not allowed as requesMethod, so is hacked as POST method.
            if ("PATCH".equals(requestMethod)) {
                httpUrlConnection.setRequestProperty("X-HTTP-Method-Override", "PATCH");
                httpUrlConnection.setRequestMethod("POST");
            }
            // 2.2. Request timeouts.
            httpUrlConnection.setConnectTimeout(timeout);
            httpUrlConnection.setReadTimeout(timeout);
            // 2.3. Request headers
            if (requestHeaders != null) {
                for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
                    httpUrlConnection.addRequestProperty(header.getKey(), header.getValue());
                }
            }
            // 2.4. Request body.
            if (requestBody != null) {
                httpUrlConnection.setDoOutput(true);
                try (OutputStream out = httpUrlConnection.getOutputStream()) {
                    out.write(requestBody);
                    out.flush();
                }
            }
            // 3. Connect (optional, internally called by getResponseCode())
            httpUrlConnection.connect();
            // 4. Process response
            // 4.1. Response code
            System.out.print(httpUrlConnection.getResponseCode());
            // 4.2. Response message
            System.out.println(" " + httpUrlConnection.getResponseMessage());
            // 4.2. Response headers.
            if (httpUrlConnection.getHeaderFields() != null) {
                for (Map.Entry<String, List<String>> responseHeader : httpUrlConnection.getHeaderFields().entrySet()) {
                    if (responseHeader.getKey() != null) {
                        System.out.print(responseHeader.getKey() + ": ");
                    }
                    System.out.println(String.join(", ", responseHeader.getValue()));
                }
            }
            if (httpUrlConnection.getResponseCode() >= 200 && httpUrlConnection.getResponseCode() < 300) {
                // 4.3 Response body
                System.out.println("\n" + readInputStream(httpUrlConnection.getInputStream()));
            }
        } catch (IOException e) {
            // 5. Process failure.
            System.out.println("\nERROR IN CONNECTION");
        } finally {
            // 6. Disconnect
            if (httpUrlConnection != null) {
                httpUrlConnection.disconnect();
            }
        }
    }

    private static String readInputStream(InputStream inputStream) throws IOException {
        try (BufferedReader bufferedReader =
                     new BufferedReader(new InputStreamReader(inputStream))) {
            String inputLine;
            StringBuilder stringBuilder = new StringBuilder();
            while ((inputLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(inputLine);
            }
            return stringBuilder.toString();
        }
    }
}
