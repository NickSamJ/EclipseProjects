package telran.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;

public interface Set<T> extends Iterable<T>{

	boolean add(T obj);
	Set<T> filter(Predicate<T> predicate);
	T remove(T pattern);
	boolean removeIf(Predicate<T> predicate);
	boolean contains(T pattern);
	int size();
	
}
