
package telran.tests.performance;

public abstract class PerformanceTest {
private String testName;

	public PerformanceTest(String testName) {
		super();
		this.testName = testName;
	}
	abstract protected void runTest();
	
	public void run() {
		long starttime = System.currentTimeMillis();

		runTest();
		
		long endtime = System.currentTimeMillis();
		System.out.println("Test name: " + testName);
		System.out.println("Execution time: " + (endtime - starttime));
	};
	
}
