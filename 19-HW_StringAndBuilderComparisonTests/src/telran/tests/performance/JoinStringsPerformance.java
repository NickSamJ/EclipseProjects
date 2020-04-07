package telran.tests.performance;

import telran.tests.performance.JoinStringsInterface;
import telran.util.JoinStringsImplString;

public class JoinStringsPerformance extends PerformanceTest {
	final String CONST_STRING = "Hello";
	
	private String[] strings;
	private JoinStringsInterface joinStrings;
	
	public JoinStringsPerformance(String testName, int nRuns, int nStrings, JoinStringsInterface joinStrings) {
		super(testName, nRuns);
		this.joinStrings = joinStrings;
		generateStrings(nStrings);
	}
	

	@Override
	protected void runTest() {
		joinStrings.join(strings, "-");
		
	}
	
	private void generateStrings(int nStrings) {
		strings = new String[nStrings];
		for (int i = 0; i < nStrings; i++) {
			strings[i] = CONST_STRING;
		}
		
	}
}
