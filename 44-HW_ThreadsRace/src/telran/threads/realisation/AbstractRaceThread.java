package telran.threads.realisation;

public class AbstractRaceThread extends Thread {
protected static int winner = 0;
String name;

public AbstractRaceThread(String name) {
	super(name);
} 
public static int getWinner() {
	return(winner);
}
public static void resetWinner() {
	winner = 0;
}
}
