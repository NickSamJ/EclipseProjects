import telran.tests.performance.IndexedListOperations;
import telran.util.Array;
import telran.util.IndexedLinkedList;

public class ListOperationsAppl {
public static void main(String[] args) {
	performanceTest(70);
}
static  void performanceTest(int probGet ) {
	int nRuns = 10000;
	int nNumbers = 10000;

	Array array = new Array();
	IndexedListOperations arrayObject = new IndexedListOperations(
					"Test Array", 
					nRuns, 
					nNumbers, 
					array
			);
	arrayObject.setGetProb(probGet);
	
	IndexedLinkedList indexedLinkedList = new IndexedLinkedList();
	IndexedListOperations indexedLinkedListObject = new IndexedListOperations(
					"Test indexedLinkedList", 
					nRuns, 
					nNumbers, 
					indexedLinkedList
			);
	indexedLinkedListObject.setGetProb(probGet);
	arrayObject.run();
	indexedLinkedListObject.run();
}
}