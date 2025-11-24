package prepare.java.datastructures.hashset;

import java.io.*;                  // For BufferedReader, InputStreamReader, FileWriter, PrintWriter
import java.util.*;                // For HashSet

// Standard HackerRank class name
public class Solution {            // Entry class
    public static void main(String[] args) throws Exception { // Main method; throws for brevity on I/O
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // Fast stdin reader
        int t = Integer.parseInt(br.readLine().trim());         // Read number of pairs

        HashSet<String> set = new HashSet<>(t * 2);             // Set of unique pairs (composite key)
        StringBuilder out = new StringBuilder();                 // Buffer outputs to avoid slow println in loops
        StringBuilder session = new StringBuilder();             // Copy of session lines for a text file

        for (int i = 0; i < t; i++) {                            // Process each pair line
            String line = br.readLine();                         // Read a line containing two tokens
            while (line != null && line.isEmpty()) {             // Skip any accidental blank lines
                line = br.readLine();                            // Read next non-empty line
            }
            String[] parts = line.trim().split("\\s+");          // Split on 1+ whitespace to handle extra spaces
            String a = parts[0];                                 // First token
            String b = parts[1];                                 // Second token

            // Build a collision-safe composite key; use a rarely-used delimiter (ASCII SOH)
            String key = a + "\u0001" + b;                       // Unique key per ordered pair (a,b)

            set.add(key);                                        // Insert pair; set handles uniqueness
            out.append(set.size()).append('\n');                 // Append current unique count

            session.append(line).append('\n');                   // Save the raw input line for the session copy
        }

        System.out.print(out.toString());                        // Print all counts at once

        // Best-effort: write a copy of the session to a text file; ignored on platforms that block FS writes
        try (PrintWriter pw = new PrintWriter(new FileWriter("session_copy.txt"))) { // Open/create file
            pw.print(session.toString());                        // Write lines
        } catch (Exception ignore) { }                           // Silently ignore if not permitted (e.g., HackerRank)
    }
}
