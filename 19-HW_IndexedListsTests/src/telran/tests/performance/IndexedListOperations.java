package telran.tests.performance;

import java.util.Random;

import telran.util.IndexedList;

public class IndexedListOperations extends PerformanceTest{
	private IndexedList<Integer> list;
	private int nNumbers;
	private int probGet;
	
	
	public IndexedListOperations(String testName, int nRuns, int nNumbers, IndexedList<Integer> list,int probGet) {
		super(testName, nRuns);
		this.list = list;
		fillList(nNumbers);		
	}

	@Override
	protected void runTest() {
		int randProb = getRandomNumber(100);
		if(randProb < probGet) {
			runGetAtRandomIndex();
		}else {
			 runRemoveAddFirst();
		}
		
	}
	
	private void fillList(int num) {
		for (int i = 0; i < nNumbers; i++) {
			list.add(100);
		}
	}
	private void runRemoveAddFirst() {
		list.remove(0);
		list.add(0, 100);
	}
	
	private void runGetAtRandomIndex() {
		list.get(getRandomNumber(nNumbers));
		list.get(getRandomNumber(nNumbers));
		
	}
	
	private int getRandomNumber(int num) {
		Random random = new Random();
		return random.nextInt(num); 
	}
}
