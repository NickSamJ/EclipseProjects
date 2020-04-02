package telran.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class TreeSetTestApplication {
	public static void main(String[] args) {
		TreeSet<Integer> tree = new TreeSet<>();
//		tree.add(1);
//		tree.add(2);
//		tree.add(3);
//		tree.add(4);
//		tree.add(5);
//		tree.add(6);
//		tree.add(7);
		
		fill(tree);
		
//		Random random = new Random();
//		for(int i : tree) {
//			System.out.println(i);
//		}
		
		tree.RotatedTreeDisplay();
		ArrayList<ArrayList<Integer>> a= tree.getObjectsByLevels();
		
		
		System.out.println("______________");
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