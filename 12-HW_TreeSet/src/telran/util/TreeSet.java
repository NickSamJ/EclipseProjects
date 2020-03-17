package telran.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;

public class TreeSet<T> implements Set<T> {

	private static class Node<T>{
		T obj;
		Node<T> parrent;
		Node<T> left;  // object with value less that current
		Node<T> right; // object with value greater that current
		public Node(T obj) {
			this.obj = obj;
		}
	}
	

	Node<T> root;
	Integer size;
	TreeSet(Comparator<T> comparator){
		this.comparator = comparator;
	}
	TreeSet(){
		this((Comparator<T>)Comparator.naturalOrder());
	}
	Comparator<T> comparator;
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(T obj) {
		if (root == null) {
			addRoot(obj);   //addRoot creates new node, that will be root
			return true;
		}
		Node<T> parrent = getParrent(obj);
		// If obj already exists -> returns "false"
		if (parrent == null) {
			return false;
		}
		Node<T> newNode =new Node<>(obj);
		if (comparator.compare(obj, parrent.obj) < 0) {
			parrent.left = newNode;
		}else {
			parrent.right = newNode;
		}
		size++;
		newNode.parrent = parrent;
		return false;
	}

	private Node<T> getParrent(T obj) {
		Node<T> currentNode = root;
		Node<T> res = root;
		while(currentNode != null) {
			int ratio = comparator.compare(obj, currentNode.obj); 
			res = currentNode;

			if(ratio < 0) {
				currentNode = currentNode.left;
			}else if(ratio > 0){
				currentNode = currentNode.right;
			}else {
				currentNode = null;
			}
		}
		return res;
	}
	
	private void addRoot(T obj) {

		size = 1;
		root = new Node<>(obj);
	}
	@Override
	public Set<T> filter(Predicate<T> predicate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T remove(T pattern) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(T pattern) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

}
