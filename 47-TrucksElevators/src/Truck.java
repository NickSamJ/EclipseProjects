
public class Truck extends Thread {
private int load;
private int nLoads;	
private static int elevator1;
private static final Object mutex = new Object();
private static int elevator2;

public Truck(int load, int nLoads) {
	super();
	this.load = load;
	this.nLoads = nLoads;
}
public static int getElevator1() {
	return elevator1;
}
public static int getElevator2() {
	return elevator2;
}

@Override
public void run() {

	for (int i = 0; i < nLoads; i++) {
		loadElev1(load);
		loadElev2(load);
		
	}
}
 private static synchronized void loadElev1(int load) {
		elevator1 += load;
}
 private static  void loadElev2(int load) {
	 synchronized(mutex) {		 
		 elevator2 += load;
	 }	
}
}
