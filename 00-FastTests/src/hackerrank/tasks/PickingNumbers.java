package hackerrank.tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class PickingNumbers {

	public static void main(String[] args) {
//		List<Integer> list = Arrays.asList(4, 2, 3, 4, 4, 9, 98, 98, 3, 3, 3, 4, 2, 98, 1, 98, 98, 1, 1, 4, 98, 2, 98, 3, 9, 9, 3, 1, 4, 1, 98, 9, 9, 2, 9, 4, 2, 2, 9, 98, 4, 98, 1, 3, 4, 9, 1, 98, 98, 4, 2, 3, 98, 98, 1, 99, 9, 98, 98, 3, 98, 98, 4, 98, 2, 98, 4, 2, 1, 1, 9, 2, 4);
		List<Integer> list = Arrays.asList(4, 97, 5, 97, 97, 4, 97, 4, 97, 97, 97, 97, 4, 4, 5, 5, 97, 5, 97, 99, 4, 9, 5, 97, 97,  97);
//		List<Integer> list = Arrays.asList(1, 2, 2, 3, 1, 2);
		int res = pickingNumbers(list);
		System.out.println(res);
	}
	public static int pickingNumbers(List<Integer> a) {
		int res = 1;
		a.sort(Comparator.naturalOrder());

		HashMap<Integer, Integer> counts = new HashMap<>();
		HashSet<Integer> uniquesSet = new HashSet<Integer>();
		
		setupBasics(a, counts, uniquesSet);
		if(counts.size() == 1) {
			return a.size();
		}
		System.out.println(counts);
		int[] uniquesArr = new int[uniquesSet.size()];
		generateUniqueArr(uniquesSet, uniquesArr);
		Arrays.sort(uniquesArr);
		
		for(int i = 0; i < uniquesArr.length - 1; i++) {
			int curr = uniquesArr[i]; 
			int next = uniquesArr[i+1]; 
			res = calculateSum(res, counts, curr, next);
		}
		return res;

    }
	private static void setupBasics(List<Integer> a, HashMap<Integer, Integer> counts, HashSet<Integer> uniquesSet) {
		for(int i : a ){
			int old = counts.getOrDefault(i, 0);
			counts.put(i, old + 1);
			uniquesSet.add(i);
		}
	}
	private static void generateUniqueArr(HashSet<Integer> uniquesSet, int[] uniquesArr) {
		int counter = 0;
		for(Object o : uniquesSet) {
			uniquesArr[counter] = (int)o;
			counter++;
		}
	}
	private static int calculateSum(int res, HashMap<Integer, Integer> counts,  int curr, int next) {
		int sum = 1;
		if(next - curr <= 1) {
			sum = counts.get(next) + counts.get(curr);
		}else {
			sum = counts.get(next);
		}
		if(sum > res) {
			res = sum;
		}
		return res;
	}
}
