package telran.tests.performance;

import java.nio.file.Path;

public class CopyFilePerformance extends PerformanceTest {
	private CopyFileInterface copyFile;
	private Path from;
	private Path to;
	
	
	public CopyFilePerformance(String testName, Path from, Path to, CopyFileInterface copyFile) {
		super(testName);
		this.copyFile = copyFile;
		this.from = from;
		this.to = to;
	}

	@Override
	protected void runTest() {
		copyFile.copy(from, to);
	}

}
