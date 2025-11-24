package restapi.numberofmatches;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URI;                       // Import URI to build request targets
import java.net.http.HttpClient;           // Import HttpClient for HTTP requests
import java.net.http.HttpRequest;          // Import HttpRequest to create GET requests
import java.net.http.HttpResponse;         // Import HttpResponse to capture responses
import java.util.regex.Matcher;            // Import Matcher for regex search
import java.util.regex.Pattern;            // Import Pattern to compile regex

class Result {                             // Begin Result container used by the platform

    public static int getNumDraws(int year) throws Exception { // Function required by the prompt
        final String BASE = "https://jsonmock.hackerrank.com/api/football_matches"; // Base API endpoint
        HttpClient client = HttpClient.newHttpClient();         // Create a reusable HTTP client
        Pattern TOTAL = Pattern.compile("\"total\"\\s*:\\s*(\\d+)"); // Precompile regex to capture the total field
        int sum = 0;                                            // Accumulator for all draw counts

        for (int g = 0; g <= 10; g++) {                         // Loop all plausible equal goals values 0 to 10
            String url = BASE                                    // Build the request URL
                    + "?year=" + year                            // Append the year query parameter
                    + "&team1goals=" + g                         // Append team1goals equal to g
                    + "&team2goals=" + g;                        // Append team2goals equal to g

            HttpRequest req = HttpRequest.newBuilder()           // Start building the HTTP request
                    .uri(URI.create(url))                        // Set the target URI
                    .GET()                                       // Use HTTP GET
                    .build();                                    // Finalize the request

            HttpResponse<String> res = client.send(              // Send the request synchronously
                    req, HttpResponse.BodyHandlers.ofString());  // Capture body as a string

            String body = res.body();                            // Read the response payload
            Matcher m = TOTAL.matcher(body);                     // Create a matcher to find the total field
            if (!m.find()) throw new IllegalStateException(      // Guard if total is missing
                    "total not found in response for goals " + g);// Provide helpful error context
            int totalForG = Integer.parseInt(m.group(1));        // Parse the captured number
            sum += totalForG;                                    // Add to the accumulator
        }

        return sum;                                              // Return the final count of drawn matches
    }
}

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int year = Integer.parseInt(bufferedReader.readLine().trim());

        int result = Result.getNumDraws(year);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}