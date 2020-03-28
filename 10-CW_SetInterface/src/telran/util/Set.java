package telran.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;

public interface Set<T> extends Iterable<T>{

	boolean add(T obj);
	Set<T> filter(Predicate<T> predicate);
	T remove(T pattern);
//	boolean removeIf(Predicate<T> predicate);
	boolean contains(T pattern);
	int size();
	default boolean removeIf(Predicate<T> predicate) {
		int initialSize = size();
		Iterator<T> it = iterator();
		boolean res = false;
		while(it.hasNext()) {
			var current = it.next();
			if(predicate.test(current)) {
				it.remove();
				res = true;
			}
		}
		
		
		return res;
	}
}
