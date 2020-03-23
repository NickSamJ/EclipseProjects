package telran.util;

import java.util.Iterator;

public class TreeSetTestApplication {
	public static void main(String[] args) {
		TreeSet<Integer> tree = new TreeSet<>();
		tree.add(10);
		tree.add(20);
		tree.add(11);   // right from 10
		tree.add(-8);   // right from 10, left from 20
		tree.add(7);   // right from 10, left from 20
		tree.add(13);   // right from 10, left from 20
		tree.add(55);
		tree.remove(55);

	}
}	