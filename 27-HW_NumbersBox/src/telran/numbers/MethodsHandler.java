package telran.numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;
@SuppressWarnings("unchecked")

public class MethodsHandler implements NumbersBox {
	Object obj;
	MethodsHandler(Object obj){
		this.obj = obj;
	}
	@Override
	public Iterator<Integer> iterator() {
		Iterator<Integer> iter = ((Collection)obj).iterator(); 
		return iter;
	}

	@Override
	public void addNumber(int number) {
			((Collection<Integer>) obj).add(number);
	}

	@Override
	public void removeNumber(int number) {
			((Collection<Integer>) obj).remove(number);
	}

	@Override
	public int removeRepeated() {
		LinkedHashSet<Integer> orderedSet = new LinkedHashSet<>((Collection<Integer>) obj);
		int numberRemoved = ((Collection<Integer>)obj).size() - orderedSet.size();
		((Collection<Integer>)obj).clear();
		((Collection<Integer>)obj).addAll(orderedSet);
		
		return numberRemoved;
	}

	@Override
	public int removeNumbersInRange(int from, int to) {
		int prevSize = ((Collection)obj).size();
		if(obj instanceof List) {
			((List)obj).removeIf(u -> ((int)u > from && (int)u < to));
			return  prevSize-((Collection)obj).size();
		}
		if(obj instanceof Set) {
			System.out.println("TRUE");
			((Set)obj).removeIf(u -> ((int)u > from && (int)u < to));
		}
		return  prevSize-((Collection)obj).size();
	}

}
