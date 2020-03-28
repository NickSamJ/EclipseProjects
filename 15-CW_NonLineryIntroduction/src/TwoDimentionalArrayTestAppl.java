
public class TwoDimentionalArrayTestAppl {

	public static void main(String[] args) {
		int ar[][] = {
				{10, -5}, 
				{20}, 
				{13, 8, 7}
			};
		displayTwoDimentional(ar);
	}

	private static void displayTwoDimentional(int[][] ar) {
		for (int i = 0; i < ar.length; i++) {
			for (int j = 0; j < ar[i].length; j++) {
				System.out.print("  -> " + ar[i][j]);
			}
		}
	}
}
