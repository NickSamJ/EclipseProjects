package telran.util;

import java.util.Iterator;

public class TreeSetTestApplication {
	public static void main(String[] args) {
		TreeSet<Integer> tree = new TreeSet<>();
//		tree.add(10);
//		tree.add(20);
//		tree.add(11);   // right from 10
//		tree.add(-8);   // right from 10, left from 20
//		tree.add(7);   // right from 10, left from 20
//		tree.add(13);   // right from 10, left from 20
//		tree.add(55);
//		tree.remove(55);
//		tree.add(10);
//		tree.add(5);
//		tree.add(12);
		
//		Iterator<Integer> it = tree.iterator();
//		while(it.hasNext()) {
//			if(it.next() == 10) {
//				it.remove();	
//			}
//		}
		tree.add(888);
		tree.add(1);
		tree.add(943532);
		tree.add(4);
		tree.add(5);
		tree.add(6);
		tree.add(7);
		tree.add(8);
		tree.add(9);
		tree.add(10);
		tree.add(11);
		tree.add(12);
		tree.add(13);
		tree.add(14);
		tree.add(15);
		tree.add(16);
		tree.add(17);
		tree.add(18);
		tree.add(19);
		tree.add(20);
		tree.add(21);
		tree.add(22);
		tree.add(23);
		tree.add(24);
		tree.add(25);
		tree.add(26);
		tree.add(27);
		tree.add(28);
		tree.add(29);
		tree.add(30);
		tree.add(31);
		tree.add(32);
		tree.add(33);
		tree.add(943539);
		Iterator<Integer> it = tree.iterator();
		while(it.hasNext()) {
			int nxt = it.next(); 
//			System.out.println(nxt);

			if(nxt%2 == 0) {
				System.out.println(nxt);
				it.remove();
			}
		}
		System.out.println("______________");
		for(int i : tree) {
			System.out.println(i);
		}
	}
}	