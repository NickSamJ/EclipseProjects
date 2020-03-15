package telran.util;
import java.util.Iterator;
import java.util.function.Predicate;

import telran.util.IndexedLinkedList;
import telran.util.IndexedList;
import telran.util.Set;

public class HashSet<T> implements Set<T> {

	IndexedList<T>[] hashTable;
	int size;
	private static float FACTOR = 0.75f;
			
	@SuppressWarnings("unchecked")
	public HashSet(int initSize) {
		hashTable = new IndexedList[initSize];
	}
	public HashSet() {
		this(16);
	}
	private class HashSetIterator implements Iterator<T> {
		
		int globalIndex = 0;
		int tableIndex = 0;
		
		Iterator<T> iterator;
		
		HashSetIterator() {};
		
		private void getNext() {
			while((iterator==null || !iterator.hasNext()) && this.hasNext()) {
				if (hashTable[tableIndex++] != null ) {
					iterator = hashTable[tableIndex-1].iterator();
				}
			}
		}
		@Override
		public boolean hasNext() {
			return globalIndex<size ;
		}

		@Override
		public T next() {
			getNext();
			globalIndex++;
			
			return iterator == null ? null : iterator.next();
		}
		
		@Override
		public void remove() {
			iterator.remove();
			size--;
			globalIndex--;
		}
	}
	@Override
	public Iterator<T> iterator() {
		return new HashSetIterator();
	}

	@Override
	public boolean add(T obj) {
		if(contains(obj)) {
			return false;
		}
		size++;
		if (size > FACTOR * hashTable.length) {
			recreateHashTable();
		}
		int index = getHashCodeIndex(obj);
		if (hashTable[index] == null) {
			hashTable[index] = new IndexedLinkedList<T>();
		}
		hashTable[index].add(obj);
		return true;

	}

	private void recreateHashTable() {
			HashSet<T> tmp = new HashSet<>(hashTable.length * 2);
			
			for (IndexedList<T> list : hashTable) {
				if (list != null) {
					for (T obj: list) {	
						tmp.add(obj);
					}
				}
			}
		hashTable = tmp.hashTable;
	}
	@Override
	public Set<T> filter(Predicate<T> predicate) {
		Set<T> res = new HashSet<>();
		
		Iterator it = iterator();
		for(T item : this) {
			if (predicate.test(item)) {
				res.add(item);
			}
		}
		
		return res;
	}

	@Override
	public T remove(T pattern) {
		T res = (T) hashTable[getHashCodeIndex(pattern)].remove(pattern);
		if (res != null) {
			size--;
		}
		return res;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		Iterator<T> it = new HashSetIterator(); 
		boolean result = false;
		
		while(it.hasNext()) {
			if (predicate.test(it.next())) {
				it.remove();
				result = true;
			}
		}
		

		return result;
	}

	@Override
	public boolean contains(T pattern) {
		int index = getHashCodeIndex(pattern);
		return hashTable[index] != null && hashTable[index].indexOf(pattern) >= 0;
	}
	private int getHashCodeIndex(T pattern) {
		int hashCode = pattern.hashCode();
		int index = Math.abs(hashCode) % hashTable.length;
		
		return index;
	}

	@Override
	public int size() {
		return size;
	}

}
