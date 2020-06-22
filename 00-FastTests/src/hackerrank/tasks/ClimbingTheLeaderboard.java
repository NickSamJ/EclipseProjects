package hackerrank.tasks;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ClimbingTheLeaderboard {
	public static void main(String[] args) {
//		int[] scores = new int[] { 100, 100, 50, 40, 40, 20, 10 };
		int[] scores = new int[] { 100, 90, 90, 80, 75, 60 };
//		int[] alice = new int[] { 5, 25, 50, 120 };
		int[] alice = new int[] { 50, 65, 77, 90, 102};
		int[] res = climbingLeaderboard(scores, alice);
		for(int i : res) {
			System.out.println(i);
		}
	}

	static int[] climbingLeaderboard(int[] scores, int[] alice) {
		int[] positions = new int[alice.length];

		int end = scores.length;
		Set<Integer> set = new HashSet<Integer>();

		for (int i = 0; i < end; i++) set.add(scores[i]);

		int[] scoresArr = new int[set.size()];
		int counter = 0;
		for (int i : set) scoresArr[counter++] = i;
		
		Arrays.sort(scoresArr);
		scoresArr = reverse(scoresArr);
		counter = 0;
		for (int i : alice) {
			positions[counter++] = getPosition(scoresArr, i);
		}
		return positions;

	}

	private static int getPosition(int[] scoresArr, int currScore) {
		int counter = 1;
		for(int score : scoresArr) {
			if(score == currScore || score < currScore) {
				return counter;
			}else {
				 counter++;
			}
			
		}
		return counter;
	}

	static int[] reverse(int a[]) {
		int n = a.length;
		int[] b = new int[n];
		int j = n;
		for (int i = 0; i < n; i++) {
			b[j - 1] = a[i];
			j = j - 1;
		}
		return b;
	}
}
