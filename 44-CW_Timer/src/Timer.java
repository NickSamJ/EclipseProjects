import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Timer extends Thread {
	
int period;
public Timer() {
 this(1000);	
}
public Timer(int period) {
	setDaemon(true);
	this.period = period;
	
}
@Override
public void run() {
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH: mm: ss");
	while(true) {
		System.out.println(LocalTime.now().format(dtf));
		try {
			sleep(period);
		} catch (InterruptedException e) {
//			break;
			System.out.println("Run again");
		}
	}
}
}
