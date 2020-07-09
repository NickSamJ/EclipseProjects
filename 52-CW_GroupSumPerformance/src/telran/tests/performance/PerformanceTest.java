package telran.tests.performance;

public abstract class PerformanceTest {
private String testName;
private Integer nRuns;

	public PerformanceTest(String testName, Integer nRuns) {
		super();
		this.testName = testName;
		this.nRuns = nRuns;
	}
	abstract protected void runTest();
	
	public void run() {
		long starttime = System.currentTimeMillis();
		for (int i = 0; i < nRuns; i++) {			
			runTest();
		}
		long endtime = System.currentTimeMillis();
		System.out.println("Test name: " + testName);
		System.out.println("Execution time: " + (endtime - starttime));
	};
	
}
