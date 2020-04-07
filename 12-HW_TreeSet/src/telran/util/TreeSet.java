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


	private static final int SPACES_PER_LEVEL = 3;
	

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
		Node<T> current = node;,
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
	
/***********************
 * 
 *  	Homework(03.04.2020)
 *  	getObjectsByLevels()
 *  
 **********************/
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
			ArrayList<T> currentSublist = res.get(level);
			if(currentSublist.size() == 0) {
				System.out.println("");
				for(int i = 0; i<(this.width()-level)*SPACES_PER_LEVEL; i++) {					
					System.out.print("*");
				}
			}
			System.out.print(root.obj);
			currentSublist.add(root.obj);
			addToLevel(root.right, level+1, res);
			addToLevel(root.left, level+1, res);
		}else {
			System.out.print(".");
		}
	}	
	
/***************
 * 	
 * 		printTree()
 * 
 **************** */
	public ArrayList<ArrayList<String>> printTree() {
		ArrayList<ArrayList<String>> res = generateTreeForPrint(root, 0);
		drawTreeFromGenerated(res);
		return res;	
	}
	private void drawTreeFromGenerated(ArrayList<ArrayList<String>> res) {
		for (int i = 0; i < res.size(); i++) {
			ArrayList<String> element = res.get(i);
			for (int j = 0; j < element.size(); j++) {
				
				if(j == 0) {
					System.out.println("");
					for(int k = 0; k<(Math.max(this.width(), this.height())-i)*SPACES_PER_LEVEL; k++) {					
						System.out.print("*");
					}
				}
				System.out.printf(" %s   ", res.get(i).get(j));
			}
		}
	}
	private ArrayList<ArrayList<String>> generateTreeForPrint(Node<T> root, int level) {
		ArrayList<ArrayList<String>>  res = new ArrayList<>(); 
		
		addToLevelForPrint(root, level, res);
		return res;
	}
	private void addToLevelForPrint(Node<T> root, int level, ArrayList<ArrayList<String>> res) {
		if(root!= null) {
			if(res.size() < level+1) {
				res.add(new ArrayList<String>());
			}
			ArrayList<String> currentSublist = res.get(level);
			currentSublist.add(root.obj.toString());
			addToLevelForPrint(root.left, level+1, res);
			addToLevelForPrint(root.right, level+1, res);
		}else {
			if(res.size() < level+1) {
				res.add(new ArrayList<String>());
			}
			ArrayList<String> currentSublist = res.get(level);
			currentSublist.add(".");
			if(level<this.height()) {
				addToLevelForPrint(null, level+1, res);
			}
		}
	}	

	int seqNumber;
	public TreePresentation<T> getTreePresentation() {
		TreePresentation<T> res = new TreePresentation<>();
		ArrayList<ArrayList<TreePresentation.Node<T>>> levels = 
				new ArrayList<>();
		//levels.get(i) - array of presentation nodes at level i
		//levels - array of arrays of presentation nodes
		seqNumber = 0;
		int nLevels = height();
		for(int i = 0; i < nLevels; i++) {
			levels.add(new ArrayList<>());
		}
		fillLevelsPresentation(root,  0, levels);
		res.levelsNodes = levels;
		
		return res;
	}
	private void fillLevelsPresentation(Node<T> root, int level,
			ArrayList<ArrayList<TreePresentation.Node<T>>> levels) {
		if (root != null) {
			fillLevelsPresentation(root.left, level + 1, levels);
			TreePresentation.Node<T> node = new TreePresentation.Node<>();
			node.obj = root.obj;
			node.seqNumber = seqNumber++;
			levels.get(level).add(node);
			fillLevelsPresentation(root.right, level + 1, levels);
		}
	}
	
/***************************
 * 
 *		Tree Balancing	 
 * 
 **************************/
	public void balance() {
		Node<T>[] arrayNodes = new Node[size];
		fillArrayNodes(arrayNodes, root);
		Iterator<T> it = iterator();
		int i = 0;
		while(it.hasNext()) {
			T current = 
//			arrayNodes[i] = 
		}
		 // fills array of the nodesroot = balance(arrayNodes, 0, size –1, null);
		//0 –left index, size –1 –right //index; null –parent for new root}
	}
private void fillArrayNodes(Node<T>[] arrayNodes, Node<T> root2) {
	// TODO Auto-generated method stub
	
}
}



