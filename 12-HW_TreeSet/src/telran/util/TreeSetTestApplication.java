package telran.util;

public class TreeSetTestApplication {
	public static void main(String[] args) {
		TreeSet<Integer> tree = new TreeSet<>();
		tree.add(10);
		tree.add(5);	// Left from 10
		tree.add(20);   // right from 10
		tree.add(15);   // right from 10, left from 20
		tree.add(25);   // right from 10, left from 20
		tree.add(10);   // right from 10, left from 20
	}
}
