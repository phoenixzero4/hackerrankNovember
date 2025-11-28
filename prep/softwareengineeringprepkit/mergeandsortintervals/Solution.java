/**
 * phoenix	Nov 27, 2025
 */


package prep.softwareengineeringprepkit.mergeandsortintervals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'mergeHighDefinitionIntervals' function below.
     *
     * The function is expected to return a 2D_INTEGER_ARRAY.
     * The function accepts 2D_INTEGER_ARRAY intervals as parameter.
     */

    public static List<List<Integer>> mergeHighDefinitionIntervals(List<List<Integer>> intervals) {
    
    	
    	if(intervals == null || intervals.isEmpty()) return new ArrayList<>();
    	
    	intervals.sort(Comparator.comparingInt(a -> a.get(0)));  // sort intervals ascending
    	
    	List<List<Integer>> merged = new ArrayList<>();
    	
    	int start = intervals.get(0).get(0);
    	int end = intervals.get(0).get(1);
    	
    	for(int i = 1; i < intervals.size(); i++) {
    		int s = intervals.get(i).get(0);
    		int e = intervals.get(i).get(1);
    		
    		if(s <= end) {
    			end = Math.max(e, end);
    		}else {
    			merged.add(Arrays.asList(start,end));
    			start = s;
    			end = e;
    		}
    	}
    	
    	merged.add(Arrays.asList(start,end));
    	return merged;
   
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int intervalsRows = Integer.parseInt(bufferedReader.readLine().trim());
        int intervalsColumns = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> intervals = new ArrayList<>();

        IntStream.range(0, intervalsRows).forEach(i -> {
            try {
                intervals.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<List<Integer>> result = Result.mergeHighDefinitionIntervals(intervals);

        result.stream()
           .map(
                r -> r.stream()
                    .map(Object::toString)
                    .collect(joining(" "))
            )
            .collect(toList())
            .forEach(System.out::println);

        bufferedReader.close();
    }
}
}