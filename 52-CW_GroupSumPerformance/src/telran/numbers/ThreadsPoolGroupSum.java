package telran.numbers;

import java.util.Arrays;
import java.util.concurrent.*;

public class ThreadsPoolGroupSum extends GroupSum {
	int nThreads = 3;
	public ThreadsPoolGroupSum(int[][] groups) {
		super(groups);
	}
	public void setNThreads(int n_Threads) {
		this.nThreads = n_Threads;
	}

	@Override
	public Long computeSum() {
		FutureTask<Long>[] tasks = new FutureTask[groups.length];
		ExecutorService executor = Executors.newFixedThreadPool(nThreads);
		fillTask(tasks);
		startTasks(tasks, executor);
		executor.shutdown();
		
		return Arrays.stream(tasks).mapToLong(t -> {
			try {
				return t.get();
			} catch (InterruptedException | ExecutionException e) {
				return 0;
			}
		}).sum();
	}


	private void fillTask(FutureTask<Long>[] tasks) {
		for (int i = 0; i < tasks.length; i++) {
			tasks[i] = new FutureTask<Long>(new OneGroupSum(groups[i]));
		}
		
	}
	private void startTasks(FutureTask<Long>[] tasks, ExecutorService executor) {
		for(FutureTask<Long> task : tasks) {
			executor.execute(task);
//			executor.submit(task);
		}
		
	}

}
