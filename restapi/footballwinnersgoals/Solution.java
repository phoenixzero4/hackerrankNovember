
package restapi.footballwinnersgoals;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import java.io.IOException;                             // import for handling IO exceptions
import java.net.URI;                                    // import for building URIs
import java.net.http.HttpClient;                        // import for HTTP client
import java.net.http.HttpRequest;                       // import for HTTP request
import java.net.http.HttpResponse;                      // import for HTTP response
import java.net.URLEncoder;                             // import for URL encoding
import java.nio.charset.StandardCharsets;               // import for UTF-8 charset
import com.google.gson.*;                                // import Gson for JSON parsing

class Result {                                          // class container provided by the platform
    // Complete the function below
    public static int getWinnerTotalGoals(String competition, int year) {     // function signature to implement
        try {                                                                // wrap network code in try-catch
            HttpClient client = HttpClient.newHttpClient();                  // build a reusable HTTP client

            // Step 1: get the competition winner for the given year
            String compUrl = "https://jsonmock.hackerrank.com/api/football_competitions"
                    + "?name=" + url(competition)                            // append encoded competition name
                    + "&year=" + year;                                       // append year
            JsonObject compJson = getJson(client, compUrl);                  // fetch JSON for competition
            JsonArray data = compJson.getAsJsonArray("data");                // extract the "data" array
            if (data.size() == 0) return 0;                                  // no competition found then zero goals
            String winner = data.get(0).getAsJsonObject()                    // get first object in array
                               .get("winner").getAsString();                 // read the "winner" field

            // Step 2: sum goals where winner is team1 across all pages
            int total = 0;                                                   // accumulator for total goals
            total += sumGoalsFor(client, competition, year, "team1", winner);// add goals as team1
            total += sumGoalsFor(client, competition, year, "team2", winner);// add goals as team2
            return total;                                                    // return the computed total
        } catch (Exception e) {                                              // catch any unexpected exception
            return 0;                                                        // per challenge conventions return 0 on failure
        }
    }

    private static int sumGoalsFor(HttpClient client, String competition, int year,
                                   String teamSide, String teamName) throws IOException, InterruptedException {
        // build the base URL for matches endpoint for a side (team1 or team2)
        String base = "https://jsonmock.hackerrank.com/api/football_matches"
                + "?competition=" + url(competition)                         // competition query
                + "&year=" + year                                            // year query
                + "&" + teamSide + "=" + url(teamName);                      // team1= or team2= query

        // fetch the first page to learn total_pages
        JsonObject first = getJson(client, base + "&page=1");                // first page fetch
        int pages = first.get("total_pages").getAsInt();                      // total pages count
        int sum = 0;                                                          // local accumulator

        // process page 1 separately then the rest in loop
        sum += goalsFromPage(first, teamSide);                                // accumulate from page 1
        for (int p = 2; p <= pages; p++) {                                    // iterate remaining pages
            JsonObject page = getJson(client, base + "&page=" + p);          // fetch page p
            sum += goalsFromPage(page, teamSide);                             // add goals from page p
        }
        return sum;                                                           // return summed goals for this side
    }

    private static int goalsFromPage(JsonObject pageJson, String teamSide) {  // helper to sum goals on a page
        JsonArray items = pageJson.getAsJsonArray("data");                    // read "data" array of matches
        int sum = 0;                                                          // per-page accumulator
        String goalsKey = teamSide + "goals";                                 // key is "team1goals" or "team2goals"
        for (JsonElement el : items) {                                        // iterate matches
            JsonObject obj = el.getAsJsonObject();                            // cast to object
            sum += Integer.parseInt(obj.get(goalsKey).getAsString());         // add goals converted to int
        }
        return sum;                                                           // return page sum
    }

    private static String url(String s) {                                     // URL-encode helper
        return URLEncoder.encode(s, StandardCharsets.UTF_8);                  // encode with UTF-8
    }

    private static JsonObject getJson(HttpClient client, String url)          // HTTP GET and parse JSON helper
            throws IOException, InterruptedException {                        // declare checked exceptions
        HttpRequest req = HttpRequest.newBuilder(URI.create(url)).GET().build(); // build GET request
        HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString()); // send and get body
        return JsonParser.parseString(resp.body()).getAsJsonObject();         // parse string to JsonObject
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String competition = bufferedReader.readLine();

        int year = Integer.parseInt(bufferedReader.readLine().trim());

        int result = Result.getWinnerTotalGoals(competition, year);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
