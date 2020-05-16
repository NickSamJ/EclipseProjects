package telran.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamsFunctionality {
	public static void displaySportLotoNumbers(int min, int max, int nNumbers) {
		
		new Random()
				.ints( min, max+1)
				.distinct()
				.limit(nNumbers)
				.forEach(e -> {
					if(max-min+1 < nNumbers) throw new RuntimeException("Can't fillwith not repeatsble numbers"); 
					System.out.print(e + ", ");
					});
	}
	
	public static void displayShufflingArray(int[] arr) {
		new Random().ints(0, arr.length)
			.distinct()
			.limit(arr.length)
			.forEach(e -> System.out.print(arr[e] + ", "));
	}
	public static void main(String[] args) {
		
		displaySportLotoNumbers(99, 100, 2);
		
		int[] arr = {1, 2, 3, 4, 5, 6};
		displayShufflingArray(arr);
		
	}
}
