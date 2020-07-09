import java.util.Arrays;
import java.util.Random;

import telran.numbers.GroupSum;
import telran.numbers.ThreadsPoolGroupSum;
import telran.numbers.UsualGroupSum;
import telran.tests.performance.PerformanceGroupSumTest;
import telran.tests.performance.PerformanceTest;

public class Run {
	private static final int N_GROUPS = 2000;
	private static final int N_NUMBERS_IN_GROUPS = 100_000;

	private static final int[] N_OF_THREADS = { 1, 3, 10, 100, 1000, N_GROUPS };

	private static final int N_RUNS = 10;

	public static void main(String[] agrs) {
		int[][] groups = generateRandomGroups();
		PerformanceTest testUsual = new PerformanceGroupSumTest("without multithreading", N_RUNS, new UsualGroupSum(groups));
		
		testUsual.run();
		System.out.println("\n");
		System.out.printf("Test info: number of groups = %d; number of numbers %d\n", N_GROUPS, N_NUMBERS_IN_GROUPS);
		Arrays.stream(N_OF_THREADS).forEach(nThreads -> testExecution(nThreads, groups));
	}

	public static int[][] generateRandomGroups() {
		Random rnd = new Random();
		int[][] groups = new int[N_GROUPS][N_NUMBERS_IN_GROUPS];
		for (int i = 0; i < N_GROUPS; i++) {
			for (int j = 0; j < N_NUMBERS_IN_GROUPS; j++) {
				groups[i][j] = rnd.nextInt();
			}
		}
		return groups;
	}

	public static void testExecution(int nThreads, int[][] groups) {
		String testName = "Number of threads: " + nThreads;

		ThreadsPoolGroupSum groupSum = new ThreadsPoolGroupSum(groups);
		groupSum.setNThreads(nThreads);

		PerformanceTest performanceTest = new PerformanceGroupSumTest(testName, N_RUNS, groupSum);
		performanceTest.run();
	}
	
	
	
	
	
	
	
	
	
//	private static final int[] GROUPS = {1 , 3, 10, 100, 10000,};
//	private static final int[] NUMBERS_GROUP = {1 , 3, 10, 100, 10000,};
//	private static final int nThreads = 0;
//	
//	public static void main(String[] args) {
//		PerformanceGroupSumTest[] tests = generateThreads();
//		startTests(tests);
//	}
//
//	private static void startTests(PerformanceGroupSumTest[] tests) {
//		for (PerformanceGroupSumTest test : tests) {
//			test.run();
//		}
//		
//	}
//
//	private static PerformanceGroupSumTest[] generateThreads() {
//		PerformanceGroupSumTest[] res = new PerformanceGroupSumTest[GROUPS.length];
//		for (int i = 0; i < GROUPS.length; i++) {
//			GroupSum groupSum = new ThreadsPoolGroupSum(generateGroup(GROUPS[i], NUMBERS_GROUP[i]));
//			res[i] = new PerformanceGroupSumTest(" Groups: " + GROUPS[i] + " Numbers group: " + NUMBERS_GROUP[i], 100, groupSum);
//			
//		}
//		return res;
//	}
//
//	private static int[][] generateGroup(int N_GROUPS, int N_NUMBERS_GROUP) {
//		int[][] res = new int[N_GROUPS][N_NUMBERS_GROUP];
//		for (int i = 0; i < N_GROUPS; i++) {
//			res[i] = getSubgroup(N_NUMBERS_GROUP);
//		}
//		return res;
//	}
//
//	private static int[] getSubgroup(int N_NUMBERS_GROUP) {
//		int[] subGroup = new int[N_NUMBERS_GROUP];
//		for (int i = 0; i < N_NUMBERS_GROUP; i++) {
//			subGroup[i] = (int) Math.abs(Math.random() * 10);
//		}
//		return subGroup;
//	}
}
