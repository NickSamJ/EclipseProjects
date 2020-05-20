import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsPracticeAppl {
public static void main(String[] args) {
//	displayAverageMinMaxSumArray(new int[] {1, 2, 3, 4, 5});
//	displayAverageMinMaxSumList	(Arrays.asList(1, 2, 3, 4, 5));
	int[] array = {1, 2, 9, 10, 20, 500, 700, 100000, 200000};
//	displayNumbersGroupedByDigitsAmmount(array);
//	displayNumbersGroupedByEvenOdd(array);
	displayNumbersGroupedByEvenOdd1(array);
	
}

private static void displayNumbersGroupedByEvenOdd(int[] arr) {
	Map<String, List<Integer>> mp = Arrays.stream(arr).boxed()
			.collect(Collectors.groupingBy(n -> n % 2  == 0 ? "even" : "odd"));
			
	System.out.println(mp);
	
}
private static void displayNumbersGroupedByEvenOdd1(int[] arr) {
	Map<String, Long> mp = Arrays.stream(arr).boxed()
			.collect(Collectors.groupingBy(n -> n % 2  == 0 ? "even" : "odd", Collectors.counting()));
	
	System.out.println(mp);
	
}

private static void displayNumbersGroupedByDigitsAmmount(int[] arr) {
	Map<Integer, List<Integer>> mp = Arrays.stream(arr).boxed()
	.collect(Collectors.groupingBy(e -> Integer.toString(e).length()));
	
	System.out.println(mp);
}

private static void displayAverageMinMaxSumList(List<Integer> list) {
	IntSummaryStatistics stats = list.stream().mapToInt(e-> e).summaryStatistics();
	System.out.println(stats);
	
}

private static void displayAverageMinMaxSumArray(int[] arr) {
	IntSummaryStatistics stats = Arrays.stream(arr).summaryStatistics();
	System.out.println(stats);
}
}
