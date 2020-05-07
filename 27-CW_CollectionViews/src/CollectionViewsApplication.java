import java.util.*;
public class CollectionViewsApplication {
	public static void main(String[] args) {
		Integer numbers[] = {100, 20, -10, 200, 50, 12, 38, 70};
		List<Integer> listNumbers = Arrays.asList(numbers);
		ArrayList<Integer> listNumbersArray = new ArrayList<>(Arrays.asList(numbers));
		
		List<Integer> subList = listNumbersArray.subList(2, 5);
		
		
		TreeSet<Integer> treeNumbers = new TreeSet<>(Arrays.asList(numbers));
		displayColelction(treeNumbers);
		subList.remove((Integer)(-10));
		System.out.println("Original numbers:");
		displayColelction(listNumbersArray);
		
		displayColelction(subList);
//		listNumbersArray.add(20);
		
		TreeSet<Integer> tree30_80 = (TreeSet<Integer>) treeNumbers.subSet(30, true, 80, true);
		tree30_80.clear();
		System.out.println("Original tree after removint 30-80");
		displayColelction(treeNumbers);
	}

	private static void displayColelction(Collection<Integer> collection) {
		for(Integer num : collection) {
			System.out.print(num + " ");
		}
		// TODO Auto-generated method stub
		
	}
}
