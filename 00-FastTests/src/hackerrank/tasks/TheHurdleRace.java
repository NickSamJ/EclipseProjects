package hackerrank.tasks;

import java.util.Arrays;

public class TheHurdleRace {

	public static void main(String[] args) {
		int[] heights = {1, 6, 3, 5, 2};
		int maxHeightToJump = 4;
		int res = hurdleRace(maxHeightToJump, heights);
		System.out.println(res);

	}
 	static int hurdleRace(int k, int[] height) {
        int max = Arrays.stream(height).max().orElse(0);
        
        return max > k ? max-k : 0;
    }
}
