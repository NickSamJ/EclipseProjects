package telran.util;

import java.util.Iterator;

public class TreeSetTestApplication {
	public static void main(String[] args) {
		TreeSet<Integer> tree = new TreeSet<>();
		tree.add(15);
		tree.add(1);
		tree.add(5);
		tree.add(20);
		tree.add(10);
		tree.add(30);
		System.out.println();
		System.out.println("______________");
		for(int i : tree) {
			System.out.println(i);
		}
	}
}	