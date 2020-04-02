package telran.util;

import java.util.ArrayList;
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


	private static final int SPACES_PER_LEVEL = 2;
	

	Node<T> root;
	Integer size = 0;
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
	public void RotatedTreeDisplay(){
		rotateDisplay(root, 1);
	}
	private void rotateDisplay(Node<T> root, int level) {
		if(root != null) {
			rotateDisplay(root.right, level + 1);
			displayRoot(root, level);
			rotateDisplay(root.left, level + 1);
			
		}
	}
	private void displayRoot(Node<T> root, int level) {
		printOfSet(level);
		System.out.println(root.obj);
	}
	private void printOfSet(int level) {
		int limit = level * SPACES_PER_LEVEL;
		for (int i = 0; i < limit; i++) {
			System.out.print(" ");
		}
		
	}
	public Integer height() {
		return height(root);
	}
	private Integer height(Node<T> root) {
		int res = 0;
		if (root != null) {
			int hightLeft = height(root.left);
			int hightRight = height(root.right);
			res = 1 + Math.max(hightLeft, hightRight);	
		}
		
		if (res == 0) {
			return 0;
		}
		return res;
	}
	public Integer width() {
		return width(root);
	}
	private int width(Node<T> root) {	
		if(root == null) {
			return 0;
		}
		if(root.left == null && root.right == null) {
			return 1;
		}
		return width(root.left) + width(root.right);
			
	}
	public ArrayList<ArrayList<T>> getObjectsByLevels() {
		
		return getObjectsByLevels(root, 0);	
	}
	private ArrayList<ArrayList<T>> getObjectsByLevels(Node<T> root2, int level) {
		ArrayList<ArrayList<T>>  res = new ArrayList<>(); 
		
		addToLevel(root, level, res);
		return res;
	}
	private void addToLevel(Node<T> root, int level, ArrayList<ArrayList<T>> res) {
		if(root!= null) {
			if(res.size() < level+1) {
				res.add(new ArrayList<T>());
			}
			res.get(level).add(root.obj);
			addToLevel(root.left, level+1, res);
			addToLevel(root.right, level+1, res);
		}
	}	
}


