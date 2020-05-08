package telran.numbers;

import java.util.TreeSet;

public class TreeSetNumbersBox extends MethodsHandler {
	private static TreeSet<Integer> set = new TreeSet<>();
	
	public TreeSetNumbersBox(){
		super(set);
	}
	
	@Override
	public int removeNumbersInRange(int from, int to) {
		int startSize = set.size();
		set.subSet(from, to).clear();
		return startSize-set.size();
	}

}
