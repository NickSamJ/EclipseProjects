package telran.util;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class AdvancedStreamsFunctionality {
	public static void displayDigitStatistics() {
		new Random().ints(1, 10).limit(100)
			.flatMap(e -> Integer.toString(e).chars())
//			.map(e -> e)
			.boxed()
//			.forEach(System.out::println);
			.collect(Collectors.groupingBy(e -> e, Collectors.counting()))
			.entrySet()
			.stream()
			.sorted((e1, e2) -> Long.compare(e1.getValue() - e2.getValue(), 0))
			.forEach(System.out::println);
	}

	private static void displayDigitStatisticss() {
		new Random().ints(1_000_000, 1, Integer.MAX_VALUE)
		.flatMap(n -> Integer.toString(n).chars())/* getting stream of digits */
		.boxed() /* getting Stream<Integer> */
		.collect(Collectors.groupingBy(n -> n, Collectors.counting()))
		.entrySet().stream().sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
		.forEach(e -> System.out.printf("%c : %d\n", e.getKey(),e.getValue()));
		
	}
	public static void main(String[] args) {
		displayDigitStatisticss();
	}
	
}
