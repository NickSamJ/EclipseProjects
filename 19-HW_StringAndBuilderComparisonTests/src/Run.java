import telran.tests.performance.JoinStringsPerformance;
import telran.util.JoinStringsImplBuilder;
import telran.util.JoinStringsImplString;

public class Run {
public static void main(String[] args) {
	int nRuns = 2000;
	int nStrings = 2000;
	
	JoinStringsPerformance stringsConcatTest = 
			new JoinStringsPerformance(
					"Join strings with String concatenation", 
					nRuns, 
					nStrings, 
					new JoinStringsImplString()
				);
	
	JoinStringsPerformance stringsBuilderTest = 
			new JoinStringsPerformance(
					"Join strings using StringBuilder append() method", 
					nRuns, 
					nStrings, 
					new JoinStringsImplBuilder()
				);
	
	stringsConcatTest.run();
	stringsBuilderTest.run();
}
}
