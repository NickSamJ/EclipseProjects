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
	public TreeSet(){
		this((Comparator<T>)Comparator.naturalOrder());
	}
	Comparator<T> comparator;
	@Override
	public Iterator<T> iterator() {
		return new TreeIterator();
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
		Node<T> newNode = new Node<>(obj);
		if (comparator.compare(obj, parrent.obj) < 0) {
			parrent.left = newNode;
		}else {
			parrent.right = newNode;
		}
		size++;
		newNode.parrent = parrent;
		return true;
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
				res = null;
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
		TreeSet<T> res = new TreeSet<T>();
		Iterator<T> it = iterator();
		while(it.hasNext()) {
			T current = it.next();
			if (predicate.test(current)) {
				res.add(current);
			}
		}
		return res;
	}

	@Override
	public T remove(T pattern) {
		
		T res = null;
		Node<T> node = findNode(pattern);
		if(node != null) {
			res = node.obj;
			removeNode(node);
		}
		return res;
	}

	public Node<T> findNode(T pattern) {
		Node<T> currentNode = root;
		Node<T> res = root;
		while(currentNode != null) {
			int ratio = comparator.compare(pattern, currentNode.obj); 
			res = currentNode;

			if(ratio < 0) {
				currentNode = currentNode.left;
				res = null;
			}else if(ratio > 0){
				currentNode = currentNode.right;
				res = null;
			}else {
				res = currentNode;
				currentNode = null;
			}
		}
		return res;
	}
	@Override
	public boolean removeIf(Predicate<T> predicate) {
		Iterator<T> it = iterator();
		while(it.hasNext()) {
			var current = it.next();
			if(predicate.test(current)) {
				it.remove();
			}
		}
		
		
		return false;
	}

	@Override
	public boolean contains(T pattern) { 
		return size > 0 && getParrent(pattern)==null;
	}

	@Override
	public int size() {
		return size;
	}
	private Node<T> getLeastNode(Node<T> node){
		Node<T> current = node;
		while(current.left !=null) {
			current = current.left;
		}
		return current;
	}
	private Node<T> getParrentFromLeft(Node<T> node){
		while (node.parrent != null && node.parrent.right == node) {
			node = node.parrent;
		}
		return node.parrent;
	}
	private class TreeIterator implements Iterator<T>{
		Node<T> current = getLeastNode(root);
		Node<T> prev = getLeastNode(root);
		
		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			T res = current.obj;
			prev = current;
			current = current.right != null ? 
					getLeastNode(current.right) : 
						getParrentFromLeft(current);
			
			return res;
		}
		
		@Override
		public void remove() {
			removeNode(current);
		}
	}
	
	private void removeNode(Node<T> node) {
		if (isJunction(node)) {
			Node<T> substitude = getLeastNode(node.right);
			node.obj = substitude.obj;
			node = substitude;
		}
		removeNonJunctionNode(node);
		size--;
	}

	private void setParrent(Node<T> node, Node<T> nodeSide) {
		if(node.parrent.left == node) {
			node.parrent.left = nodeSide;
		}else {
			node.parrent.right = nodeSide;
		}
	}
	private void removeNonJunctionNode(Node<T> node) {
		
		if(node.left != null) {
			node.left.parrent = node.parrent;
			setParrent(node, node.left);
		}else if(node.right != null) {
			node.right.parrent = node.parrent;
			setParrent(node, node.right);
		}else {
			setParrent(node, null);
		}
	}
	private boolean isJunction(Node<T> node) {
		return node.left != null && node.right != null;
	}

}
