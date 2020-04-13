import telran.tests.performance.IndexedListOperations;
import telran.util.Array;
import telran.util.IndexedLinkedList;

public class ListOperationsAppl {
public static void main(String[] args) {
	for(int i = 0; i<=100; i+=20 ) {		
		System.out.println("Probability: " + i);
		performanceTest(i);
		System.out.println("___________________");
	}
}

static  void performanceTest(int probGet ) {
	int nRuns = 100000;
	int nNumbers = 100000;

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