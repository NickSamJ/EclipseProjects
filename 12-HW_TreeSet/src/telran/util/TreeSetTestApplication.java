package telran.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class TreeSetTestApplication {
	public static void main(String[] args) {
		TreeSet<Integer> tree = new TreeSet<>();
		
		fill(tree);
		
//		Random random = new Random();
//		for(int i : tree) {
//			System.out.println(i);
//		}
//		tree.RotatedTreeDisplay();
//		ArrayList<ArrayList<String>> a= tree.printTree();
//		ArrayList<ArrayList<Integer>> a= tree.getObjectsByLevels();
//		ArrayList<ArrayList<Integer>> b= tree.getObjectsByLevels();
		
		
		System.out.println("");
		System.out.println("______________");
		tree.RotatedTreeDisplay();
		System.out.println("Width = " + tree.width() );
		System.out.println("Height = " + tree.height() );
	}
	private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	private static void fill(TreeSet<Integer> set) {
		Integer random = 0;
		while(set.size() != 7) {
			random = getRandomNumberInRange(1, 7);
			if(!set.contains(random)) {
				set.add(random);
			}
		}
	}
}	