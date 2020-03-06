package telran.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;


public class IndexedLinkedList<T> implements IndexedList<T>{

	private static class Node<T>{
		public T obj;
		public Node<T> next;
		public Node<T> prev;
		public Node(T obj) {
			this.obj = obj;
		}
	}
	
	private Node<T> head;
	private Node<T> tail;
	private int size;
	
	public IndexedLinkedList(){
		
	}
	public IndexedLinkedList(int dummy){
		
	}
	
	@Override
	public void add(T obj) {
		Node<T> newNode = new Node<>(obj);
		if(head == null) {
			head = tail = newNode;
		} else {
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
		}
		size++;
	}

	// Homework
	@Override
	public boolean add(int index, T obj) {
		Node<T> newNode = new Node<>(obj);
		
		Node<T> nextNode = find(index);
		
		if (index==0) {
			head.prev = newNode;
			head = newNode;
			newNode.next = nextNode;
			nextNode.prev = newNode;
			size++;
			return true;
		}else if (index==size) {
			add(obj);
			return true;
		}else if (isValidIndex(index)) {
				nextNode.prev.next = newNode;
				newNode.next = nextNode;					
				newNode.prev = nextNode.prev;
				nextNode.prev = newNode;	
				size++;
				return true;
		} else {
			return false;
		}
	}

	@Override
	public int binarySearch(T pattern) {
		return binarySearch(pattern, (Comparator<T>)Comparator.naturalOrder());
	}

	@Override
	public int binarySearch(T pattern, Comparator<T> comp) {
		int left = 0; 
		int right = size - 1;
		int middle = (left + right) / 2;
		while (left <= right && !pattern.equals(find(middle).obj)) {
			if (comp.compare(pattern, find(middle).obj) < 0) {
				right = middle - 1;
			} else {
				left = middle + 1;
			}
			middle = (left + right) / 2;
		}
		return left > right ? -(left + 1) : middle;
	}

	@Override
	public IndexedLinkedList<T> filter(Predicate<T> predicate) {
		IndexedLinkedList<T> res = new IndexedLinkedList<T>();
		Node<T> current = head;
		for (int i = 0; i < size; i++) {
			if (predicate.test(current.obj)) {
				res.add(current.obj);
			}
			current = current.next;
		}
		return res;
	}

	@Override
	public T get(int ind) {
		T res = null;
		if(isValidIndex( ind)) {
			res = find(ind).obj;
		}
		return res;
//		return null;
	}
	
//	private T getFromRight(int ind) {
//		Node<T> current = tail;
//		for (int i = size-1; i > ind; i--) {
//			current = current.prev;
//		}
//		return current.obj;
//	}
//
//	private T getFromLeft(int ind) {
//		Node<T> current = head;
//		for (int i = 0; i < ind; i++) {
//			current = current.next;
//		}
//		return current.obj;
//	}
	private Node<T> getFromRight(int ind) {
		Node<T> current = tail;
		for (int i = size-1; i > ind; i--) {
			current = current.prev;
		}
		return current;
	}

	private Node<T> getFromLeft(int ind) {
		Node<T> current = head;
		for (int i = 0; i < ind; i++) {
			current = current.next;
		}
		return current;
	}
	private Node<T> find(int index){
		return index<size/2 ? getFromLeft(index) : getFromRight(index); 
	}
	private boolean isValidIndex(int ind) {
		return ind >= 0 && ind <size;
	}

	@Override
	public int indexOf(Object pattern) {
		int res = -1;
		if (pattern != null) {
			Node<T> current = head;
			for (int i = 0; i < size; i++) {
				if(current.obj.equals(pattern)) {
					res = i;
					break;
				}
				current = current.next;
			}
		}
		return res;
	}

	@Override
	public int lastIndexOf(Object pattern) {
		int res = -1;
		if(pattern!=null) {
			Node<T> current = tail;
			for (int i = size-1; i >= 0; i--) {
				if(current.obj.equals(pattern)) {
					res = i;
					break;
				}
				current = current.prev;
			}
		}
		return res;
	}

	// Homework
	@Override
	public T remove(int index) {
		T result = null;
		if(isValidIndex(index)) {
			result = this.get(index);
			Node<T> toRemove = find(index);
			if(index == 0) {
				head = toRemove.next;
				head.prev = head;
			}else if (index==size-1) {
				tail = toRemove.prev;
				tail.next = tail;
			}else {
				toRemove.next.prev = toRemove.prev;
				toRemove.prev.next = toRemove.next;
			}

			toRemove = null;
			size--;
		}
		return result;
	}

	@Override
	public Object remove(Object pattern) {
		T res = null;
		int index = this.indexOf(pattern);
		if(index>=0) {
			this.remove(index);
		}
		return res;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		IndexedLinkedList<T> newList = filter(predicate);
		boolean res = false;
		int startSize = this.size;
		if (newList!=null){
			Node<T> current = newList.head;
			for (int i = 0; i < newList.size; i++) {
				this.remove(current.obj);
				current = current.next;
			}	
			res = true;
		}
		return startSize!=this.size;
	}

	@Override
	public Object set(int index, T newObject) {
		T res = null;
		if (isValidIndex(index)) {
			Node<T> temp = this.find(index);
			res = temp.obj;
			temp.obj = newObject;
			
		}
		return res;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public void sort() {
		sort((Comparator<T>)Comparator.naturalOrder());
	}

	@Override
	public void sort(Comparator<T> comp) {
		boolean flSort = false;
		int length = size;
		do {
			flSort = true;
			length--;
			Node<T> current = head;
			for (int i = 0; i < length; i++) {
				if (comp.compare(current.obj, current.next.obj) > 0) {
					T tmp = current.obj;
					current.obj = current.next.obj;
					current.next.obj = tmp;
					flSort = false;
				}
				current = current.next;
			}
		}while (!flSort);
	}
	
	private class ArrayIterator<T> implements Iterator<T> {
		int currentIndex = 0;
		
		@Override
		public boolean hasNext() {
			return currentIndex<size;
		}

		@Override
		public T next() {
			return (T)IndexedLinkedList.this.find(currentIndex++).obj;
		}
		
		@Override
		public void remove() {
			IndexedLinkedList.this.remove(--currentIndex);
		}
	}
	@Override
	public Iterator<T> iterator() {
		return new ArrayIterator();
	}

}
