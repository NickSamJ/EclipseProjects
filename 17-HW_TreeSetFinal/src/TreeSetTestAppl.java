import java.util.ArrayList;

import telran.util.TreePresentation;
import telran.util.TreeSet;

public class TreeSetTestAppl {

	private static final int N_NUMBERS = 7;

	public static void main(String[] args) {
		TreeSet<Integer> tree = new TreeSet<>();
		fillTree(tree, N_NUMBERS, 1, 7);
		tree.rotatedTreeDisplay();
		System.out.printf("width = %d; height = %d\n",tree.width(),
				tree.height());
		ArrayList<ArrayList<Integer>> levelsArray = 
				tree.getObjectsByLevels();
		int nLevels = levelsArray.size(); //number of levels
		for(int level = 0; level < nLevels; level++) {
			System.out.printf("level %d contains %s\n", level, 
					levelsArray.get(level));
		}
		TreePresentation<Integer> treePresentation = tree.getTreePresentation();
		System.out.println(treePresentation);

}

	private static void fillTree(TreeSet<Integer> tree, int nNumbers, int min, int max) {
		if(max - min + 1 < nNumbers) {
			System.out.println("Wrong input data");
			return;
		}
		int number = 0;
		boolean res = false;
		for(int i = 0; i < nNumbers; i++) {
			do {
				number = getRandomNumber(min, max);
				res = tree.add(number);
				
			}while(!res);
		}
		

	}

	private static int getRandomNumber(int min, int max) {
		
		return (int) (min + Math.random() * (max - min + 1));
	}

}
