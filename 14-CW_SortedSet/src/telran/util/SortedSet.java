package telran.util;

public interface SortedSet<T> extends Set<T>{
	T getMin();    	// Minimal element
	T getMax();  // Maximal Element
	SortedSet<T> subset(T from, boolean isIncludedFrom, T to, boolean isIncludedTo); // range is open or closed
	SortedSet<T> head(T key, boolean isIncluded); // All elements that either strong less or less/equals than key
	SortedSet<T> tail(T key, boolean isIncluded); // All elements that strong greater or greater/equals than
	
}
