
public class Racer extends Thread {
private int threadId;
private Race race;

private static int winnerId;

public Racer(int id, Race race) {
	super();
	this.threadId = id;
	this.race = race;
}
public int getThreadId() {
	return threadId;
}
public void setThreadId(int id) {
	this.threadId = id;
}

public static int getWinnerId() {
	return winnerId;
}
public static void setWinnerId(int winnerId) {
	Racer.winnerId = winnerId;
}
@Override
public void run() {
	int sleepDelta = race.max_sleep - race.min_sleep + 1;
	for(int i = 0; i < race.distance; i++) {
		System.out.println(threadId);
		try {
			sleep((long) (race.min_sleep + Math.random() * sleepDelta));
		} catch (InterruptedException e) {
			
		}
	}
	if (winnerId == 0) {
		winnerId = threadId;
	}
}

}
