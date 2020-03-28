package telran.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;

public class TreeSet<T> implements SortedSet<T> {

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
//	@Override
//	public boolean removeIf(Predicate<T> predicate) {
//		Iterator<T> it = iterator();
//		boolean res = false;
//		while(it.hasNext()) {
//			var current = it.next();
//			if(predicate.test(current)) {
//				it.remove();
//				res = true;
//			}
//		}
//		
//		
//		return res;
//	}

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
			Node<T> next = TreeSet.this.removeNode(prev);
			if(hasNext() && next != null) {
				current = next;
			}
		}
	}
	
	private Node<T> removeNode(Node<T> node) {
		Node<T> next = null;
		if (isJunction(node)) {
			Node<T> substitude = getLeastNode(node.right);
			node.obj = substitude.obj;
			next = node;
			node = substitude;
		}
		removeNonJunctionNode(node);
		size--;
		
		return next;
	}

	private void setParrent(Node<T> node, Node<T> nodeSide) {
	
		if(root == node) {
			if(nodeSide == null) {
				// the tree contains only root
				root = null;
			}else if(node.left != null) {
				root.obj = node.left.obj;
				root = node.left;
				root.parrent = null;
			}else {
				root.obj = node.right.obj;
				root = node.right;
				root.parrent = null;
			}
		}else {
			if(node.parrent.left == node) {
				node.parrent.left = nodeSide;
			}else if(node.parrent.right == node) {
				node.parrent.right = nodeSide;
			}
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
	@Override
	public T getMin() {
		Node<T> current = root;
		while(current.left !=null) {
			current = current.left;
		}
		return current.obj;
	}
	@Override
	public T getMax() {
		Node<T> current = root;
		while(current.right !=null) {
			current = current.right;
		}
		return current.obj;
	}
	
	private boolean checkIfFinal(T pattern, Node<T> node) {
		boolean res =  false;
		int ratioCurrent = comparator.compare(pattern, node.obj);
		if(node.right != null) {
			int ratioNext = comparator.compare(pattern, node.right.obj);
			if(ratioCurrent > 0 && ratioNext < 0 ) {
				res = true;
			}	
		}
		
		return res;
	}
	
	private boolean hasChildren(Node<T> node) {
		return node.left != null || node.right != null;
	}
	public Node<T> findNodeFrom(T pattern, boolean isIncluded) {
		Node<T> currentNode = root;
		Node<T> res = root;
		int ratio;
		while(currentNode != null) {
			ratio = comparator.compare(pattern, currentNode.obj);
			if(ratio < 0) {
				res = currentNode;
				currentNode = currentNode.left;
			}else if(ratio > 0) {
				currentNode = currentNode.right;
			}else {
				if(isIncluded) {
					res = currentNode;
				}else {
					if(currentNode.right != null) {
						res = getLeastNode(currentNode.right);
					}
				}
				break;
			}
			
		}
		return res;
	}
	@Override
	public SortedSet<T> subset(T from, boolean isIncludedFrom, T to, boolean isIncludedTo) {
		Node<T> startNode = findNode(from);
		Node<T> prevNode = startNode;
		Node<T> finishNode = findNode(to);
		TreeSet<T> res = new TreeSet<>();
		boolean hasNext = true;
//		while(comparator.compare(startNode.obj, to ) <= 0 ) {			
//
//				res.add(startNode.obj);
//				prevNode = startNode;
//				current = current.right != null ? 
//						getLeastNode(current.right) : 
//							getParrentFromLeft(current);
//				
//		}
		
		return res;
	}
	@Override
	public SortedSet<T> head(T key, boolean isIncluded) {
		TreeSet<T> res = new TreeSet<>();
		
		
		return res;
	}	
	@Override
	public SortedSet<T> tail(T key, boolean isIncluded) {
		// TODO Auto-generated method stub
		return null;
	}
	
public static void main(String[] args) {
	TreeSet<Integer> tree = new TreeSet<>();
	tree.add(15);
	tree.add(1);
	tree.add(5);
	tree.add(20);
	tree.add(10);
	tree.add(-10);
	tree.add(-5);
	tree.add(0);
	tree.add(-3);
	tree.add(30);
	
	System.out.println(tree.findNodeFrom(20, true).obj);
	System.out.println("______________");
//	for(int i : tree) {
//		System.out.println(i);
//	}
}
}

