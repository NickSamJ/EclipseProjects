import telran.menu.InputOutput;
import telran.menu.Item;

public class StartRace implements Item {
InputOutput inputOutput;

	public StartRace(InputOutput inputOutput) {
	super();
	this.inputOutput = inputOutput;
}

	@Override
	public String displayName() {
		
		return "start race";
	}

	@Override
	public void perform() {
		Racer.setWinnerId(0);
		int nRacers = inputOutput.inputInteger("Enter number of racers", 2, 100);
		int distance = inputOutput.inputInteger("Enter distance", 10, 1000);
		Racer [] racers = new Racer[nRacers];
		Race race = new Race(2, 5, distance);
		startRacers(racers, race);
		waitRacers(racers);
		System.out.printf("Congratulations to thread #%d - wunner\n", Racer.getWinnerId());

	}

	private void waitRacers(Racer[] racers) {
		for(Racer racer: racers) {
			try {
				racer.join();
			} catch (InterruptedException e) {
				
			}
		}
		
	}

	private void startRacers(Racer[] racers, Race race) {
		for (int i = 0; i < racers.length; i++) {
			racers[i] = new Racer(i + 1, race);
			racers[i].start();
		}
		
	}

}
