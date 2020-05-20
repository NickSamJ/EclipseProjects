import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DronesAppl {
	private static final int N_DRONES = 20;
	private static final int N_HEIGHTS = 15;
	private static final int MIN_PASSENGER_TIME = 1;
	private static final int MAX_PASSENGER_TIME = 15;
	private static final int MODEL_TIME = 240;
	static Drone[] drones;
	static List<Drone> dronesInAir;
	static List<Drone> dronesInQueue;
	static String[] heights = {"one",
								"two",
								"three",
								"four",
								"five",
								"six",
								"seven",
								"eight",
								"nine",
								"ten",
								"eleven",
								"twelve",
								"thirteen",
								"fourteen",
								"fifteen"};
	static HashMap<String, Integer> heightsCounts = new HashMap<>();

	public static void main(String[] args) {
		preProcessing();
		play();
		postProcessing();
		displayResults();

	}

	private static void displayResults() {
		printInfoAboutAllDrones();
		System.out.println("________________________");
		printHeightsStats();
		System.out.println("________________________");
		printHeightsWithMaxFlights();
		
		
		
	}

	private static void printHeightsWithMaxFlights() {
		
		// getting highest amount of flight on one Height
		int maxVal = heightsCounts.entrySet()
					.stream()
					.map(e -> e.getValue())
					.max((e1, e2) -> e1.compareTo(e2))
					.get();
		
		List<String> mostUsed = heightsCounts.entrySet()
				.stream()
				
				.filter(e -> (e.getValue() == maxVal) )
				.map(o -> o.getKey())
				.collect(Collectors.toList());
		System.out.println("The most used heights: " + mostUsed);		
	}

	private static void printHeightsStats() {
		for (String height : heightsCounts.keySet()) {
			System.out.printf("Height %s: %d flights\n", height, heightsCounts.get(height));
		}		
	}

	private static void printInfoAboutAllDrones() {
		for(Drone drone : drones) {
			System.out.printf("Drone #%d was in air %d minutes. It transferred %d passengers. It was in waiting queue %d minutes\n",
					drone.getSeqNumber(),
					drone.getTotalAirIterations(),
					drone.getnPassengers(),
					drone.getTotalQueueIterations()
					);
			
		}
	}

	private static void postProcessing() {
		for (Drone drone : dronesInAir) {
			drone.setTotalAirIterations(drone.getTotalAirIterations() + MODEL_TIME - drone.getStartIteration());
		}
		for(Drone drone : dronesInQueue) {			
			drone.setTotalQueueIterations(drone.getTotalQueueIterations() + MODEL_TIME - drone.getStartQueueIteration());
		}

	}

	private static void play() {
		for (int i = 1; i <= MODEL_TIME; i++) {
			List<String> freedHeights = landingOnIteration(i);
			if(i != MODEL_TIME) {				
				takingOffOnIteration(i, freedHeights);
			}
			
		}

	}

	private static void takingOffOnIteration(int nIteration, List<String> freedHeights) {
		// HW Part
		Iterator<Drone> it = dronesInQueue.iterator();
		for (int i = 0; i < freedHeights.size(); i++) {
			Drone drone = it.next();
			
			it.remove();
			takeOff(drone, nIteration, freedHeights.get(i));
			
		}
		//______END

	}

	private static List<String> landingOnIteration(int nIteration) {
		List<String> res = new ArrayList<>();
		Iterator<Drone> it = dronesInAir.iterator();
		while (it.hasNext()) {
			Drone drone = it.next();

			if (drone.getFinishIteration() == nIteration) {
				it.remove();
				int timeInAir = drone.getFinishIteration() - drone.getStartIteration();
				drone.setTotalAirIterations(drone.getTotalAirIterations() + timeInAir);
				putInQueue(drone, nIteration);
				drone.setnPassengers(drone.getnPassengers()+1);
				res.add(drone.getHeight());
			}
		}
		return res;
	}

	private static void preProcessing() {
		createDrones();
		initializeInAirInQueue();
		startDronesInAir();
		startDronesInQueue();

	}

	private static void initializeInAirInQueue() {
		dronesInAir = new LinkedList<Drone>();
		dronesInQueue = new LinkedList<Drone>();

	}

	private static void startDronesInQueue() {
		for (int i = N_HEIGHTS; i < N_DRONES; i++) {
			putInQueue(drones[i], 0);
		}

	}

	private static void putInQueue(Drone drone, int nIteration) {
		dronesInQueue.add(drone);
		drone.setStartQueueIteration(nIteration);

	}

	private static void startDronesInAir() {
		for (int i = 0; i < N_HEIGHTS; i++) {
			takeOff(drones[i], 0, heights[i]);
		}

	}

	private static void takeOff(Drone drone, int nIteration, String height) {
		dronesInAir.add(drone);
		drone.setStartIteration(nIteration);
		int iterationsInAir = getIterationsInAir();
		drone.setFinishIteration(nIteration + iterationsInAir);
		drone.setHeight(height);
		drone.setTotalQueueIterations(drone.getTotalQueueIterations() + nIteration - drone.getStartQueueIteration() );
		heightsCounts.put(height, heightsCounts.getOrDefault(height, 0)+1);

	}

	private static int getIterationsInAir() {

		return (int) (MIN_PASSENGER_TIME + Math.random() * (MAX_PASSENGER_TIME - MIN_PASSENGER_TIME + 1));
	}

	private static void createDrones() {

		drones = IntStream.range(0, N_DRONES).mapToObj(i -> new Drone(i + 1)).toArray(Drone[]::new);

	}

}
