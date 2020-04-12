import telran.tests.performance.IndexedListOperations;
import telran.util.Array;
import telran.util.IndexedLinkedList;

public class ListOperationsAppl {
public static void main(String[] args) {
	performanceTest(50);
}
static  void performanceTest(int probGet ) {
	int nRuns = 100000;
	int nNumbers = 100000;

	Array array = new Array();
	IndexedListOperations arrayObject = new IndexedListOperations(
					"Test Array", 
					nRuns, 
					nNumbers, 
					array, 
					probGet
			);
	
	IndexedLinkedList indexedLinkedList = new IndexedLinkedList();
	IndexedListOperations indexedLinkedListObject = new IndexedListOperations(
					"Test indexedLinkedList", 
					nRuns, 
					nNumbers, 
					indexedLinkedList, 
					probGet
			);
	arrayObject.run();
	indexedLinkedListObject.run();
}
}