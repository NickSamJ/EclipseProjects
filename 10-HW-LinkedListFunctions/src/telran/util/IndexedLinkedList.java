package telran.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;


public class IndexedLinkedList<T> implements IndexedList<T>{
	
private static class Node<T> {
	public T obj;
	public Node<T> next;
	public Node<T> prev;
	public Node(T obj) {
		this.obj = obj;
	}
}
private class ListIterator implements Iterator<T> {
Node<T> current = head;
	@Override
	public boolean hasNext() {
		return current != null;
	}

	@Override
	public T next() {
		T res = current.obj;
		current = current.next;
		return res;
	}
	@Override
	public void remove() {
		if (current == null) {
			removeTail();
		} else {
			removeNode(current.prev);
		}
	}
	
}
private Node<T> head;
private Node<T> tail;
private int size;
private T[] array=null;

public IndexedLinkedList() {
	
}
public IndexedLinkedList(int dummy) {
	
}
	@Override
	public Iterator<T> iterator() {
		return new ListIterator();
	}

	@Override
	public void add(T obj) {
		Node<T> newNode = new Node<>(obj);
		addNodeTail(newNode);
		array = null;
		
	}
	

	@Override
	public boolean add(int index, T obj) {
		boolean res = true;
		Node<T> newNode = new Node<>(obj);
	
		if(index == 0) {
			addNodeHead(newNode);
		} else if (index == size) {
			addNodeTail(newNode);
		} else if (isValidIndex(index)) {
			Node<T> beforeNode = getNode(index);
			addNodeMiddle(newNode, beforeNode);
		} else {
			res = false;
		}
		if(isValidIndex(index) && index == size) {
			array=null;
		}
		return res;
		
	}

	private void addNodeMiddle(Node<T> newNode,
			Node<T> beforeNode) {
		newNode.next = beforeNode;
		newNode.prev = beforeNode.prev;
		beforeNode.prev.next = newNode;
		beforeNode.prev = newNode;
		size++;
		
	}
	private void addNodeHead(Node<T> newNode) {
		if(head == null) {
			head = tail = newNode;
		} else {
			newNode.next = head;
			head.prev = newNode;
			head = newNode;
		}
		size++;
		
	}
	private void addNodeTail(Node<T> newNode) {
		if(head == null) {
			head = tail = newNode;
		} else {
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
		}
		size++;
		
	}
	private Node<T> getNode(int ind) {
		return ind < size / 2 ? getFromLeft(ind) : getFromRight(ind);
	}
	@SuppressWarnings("unchecked")
	@Override
	public int binarySearch(T pattern) {
		return binarySearch(pattern, (Comparator<T>)Comparator.naturalOrder());
	}

	@Override
	public int binarySearch(T pattern, Comparator<T> comp) {
		if(array==null) {
			sort(comp);
		}
		

		int res = Arrays.binarySearch(array, pattern );
		return res;
	}

	@Override
	public IndexedList<T> filter(Predicate<T> predicate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T get(int ind) {
		T res = null;
		if (isValidIndex(ind)) {
			Node<T>nodeRes = getNode(ind);
			res = nodeRes.obj;
		}
		return res;
	}

	private Node<T> getFromRight(int ind) {
		Node<T> current = tail;
		for (int i = size - 1; i > ind; i--) {
			current = current.prev;
		}
		return current;
	}

	private Node<T> getFromLeft(int ind) {
		
		Node<T> current = head;
		for( int i = 0; i < ind; i++) {
			current = current.next;
		}
		return current;
	}
	
	private boolean isValidIndex(int ind) {
		return ind >= 0 && ind < size;
	}

	@Override
	public int indexOf(Object pattern) {
		if (pattern == null) {
			return -1;
		}
		int index=0;
		Node<T> current = head;
		while(index<size && !pattern.equals(current.obj)) {
			index++;
			current = current.next;
		}
		return index < size ? index : -1;
	}

	@Override
	public int lastIndexOf(Object pattern) {
		if (pattern == null) {
			return -1;
		}
		int index=size - 1;
		Node<T> current = tail;
		while(index >= 0 && !pattern.equals(current.obj)) {
			index--;
			current = current.prev;
		}
		return index >= 0 ? index : -1;
	}

	@Override
	public Object remove(int ind) {
		Object res = null;
		if (isValidIndex(ind)) {
			Node<T> removedNode = getNode(ind);
			res = removedNode.obj;
			removeNode(removedNode);
			
		}
		return res;
	}
	private void removeNode(Node<T> removedNode) {
		if (removedNode == head ) {
			removeHead();
		} else if(removedNode == tail) {
			removeTail();
		} else {
			removeNodeMiddle(removedNode);
		}
	}
	private void removeNodeMiddle(Node<T> removedNode) {
		removedNode.next.prev = removedNode.prev;
		removedNode.prev.next = removedNode.next;
		size--;
	}

	private void removeTail() {
		if (head == tail) {
			head = tail = null;
		} else {
			tail.prev.next = null;
		tail = tail.prev;
		}
		size--;
		
	}
	private void removeHead() {
		if (head == tail) {
			head = tail = null;
		} else {
			head.next.prev = null;
		head = head.next;
		}
		size--;
		
	}
	@Override
	public Object remove(Object pattern) {
		//variant with existing methods but with two passes
//		int index = indexOf(pattern);
//		Object res = null;
//		if (index >= 0) {
//			Node<T> node = getNode(index);
//			res = node.obj;
//			removeNode(node);
//		}
//		return res;
		//variant with only one pass over the list
		Node<T> current = head;
		while(current != null && !current.obj.equals(pattern)) {
			current = current.next;
		}
		Object res = null;
		if(current != null) {
			res = current.obj;
			removeNode(current);
		}
		return res;
		
		
		
		
		
		
		
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		Iterator<T> it = iterator();
		boolean result = false;
		while(it.hasNext()) {
			T value = it.next();
			if(predicate.test(value)) {
				it.remove();
				result = true;
			}
		}
		return result;
	}

	@Override
	public Object set(int ind, T newObj) {
		T res = null;
		if (isValidIndex(ind)) {
			Node<T> temp = this.getNode(ind);
			res = temp.obj;
			temp.obj = newObj;
			
		}
		return res;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void sort() {
		sort((Comparator<T>)Comparator.naturalOrder());
	}

	@Override
	public void sort(Comparator<T> comp) {
		array = (T[]) new Object[size];
        Node<T> current = head;
        for (int  i= 0; i < size; i++) {
			array[i] = current.obj;
			current = current.next;
		}

        Arrays.sort(array, comp);

		current = head;
		for(T item : array) {
			current.obj=item;
			current = current.next;
		};
	}

}
