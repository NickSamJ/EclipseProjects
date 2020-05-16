import java.util.*;
import java.util.stream.Collectors;
public class StreamsTestAppl {
	// sum of n  even random numbers	
	public static void main(String[] args) {
//		int N = 10;
//		int sum = new Random().ints(100, 1000)
//			.filter(n -> n % 2 == 0 )
//			.limit(N)
//			.sum();
		
		// create list of N odd numbers  and display as list.toString()
		
//		List<Integer> list = new Random().ints(100, 1000)
//									.filter(n -> n != 1)
//									.limit(N)
//									.boxed()
//									.collect(Collectors.toList());
//		
//		System.out.println(sum);
//		System.out.println(list);
		
		List<Integer> listOne = Arrays.asList(1, 2, 3);
		List<Integer> listTwo = Arrays.asList(10, 20);
		List<Integer> listThree = Arrays.asList(30, 40, 100);
		List[] arrList = {listOne, listTwo, listThree};
		
		// Getting stream from array
		int sum = Arrays.stream(arrList).flatMap(l -> l.stream())
		.mapToInt(x -> (int)x).sum();
		
		System.out.println(sum);
		
	}
}
