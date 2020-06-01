package telran.util;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class BullsCowsGameImpl implements GuessGame {
	private String prompt = "Enter number: ";
	private boolean isFinished = false;
	
	HashMap<String, int[]> history = new HashMap<>();
	private Character[] number;
	
	public BullsCowsGameImpl() {
		generateNumber();
	}

	@Override
	public String startGame() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String prompt() {
		return prompt;
	}
	
	public String res(){
		StringBuilder res = new StringBuilder();
		for(char c : number) {
			res.append(c);
		}
		return res.toString();
	};

	@Override
	public String move(String userInput) {
		String currentValue = "";
			currentValue = userInput;
			int[] res = checkResult(currentValue);
			if(res[1] == 4) {
				isFinished = true;
			}
			history.put(currentValue, res);
			return res[0] + "cows, " + res[1] + "bulls";
	}

	@Override
	public boolean isFinished() {
		return isFinished;
	}

	
	public void play(){
		
		RunGameProcess();
		saveResults();
	}

	private void generateNumber() {
		this.number = new Random().ints('1', '9'+1).distinct().limit(4).mapToObj(e -> (char)e).toArray(Character[]::new);
		
	}

	private void saveResults() {
		// TODO Auto-generated method stub
		
	}

	private void RunGameProcess() {
		printIntroduction();
		gameProcess();
		printConclusion();
		
	}

	private void printConclusion() {
		// TODO Auto-generated method stub
	}

	private void gameProcess() {
		
		Scanner in = new Scanner(System.in);
		String currentValue = "";
		while(true) {
			currentValue = in.nextLine();
			if(currentValue.toLowerCase().equals("quit")) break;
			if(currentValue.toLowerCase().equals("show")) {
				showAnswer();
				continue;
			};
			if(currentValue.toLowerCase().equals("history")) {
				showHistory();
				continue;
			};
			int[] res = checkResult(currentValue);
			if(res[1] == 4) {
				printWinMesssage();
			}else {
				printCowsBulls(res);
			}
			
			history.put(currentValue, res);
			
		}
		
		
	}

	private void showAnswer() {
		for (int i = 0; i < number.length; i++) {
			System.out.print(number[i]);
		};
		
	}

	private void showHistory() {
		for(String s : history.keySet()) {			
			int[] value = history.get(s);
			System.out.printf("%s (%d cows, %d bulls);", s, value[0], value[1] );
		}
		
	}

	private int[] checkResult(String currentValue) {
		int cows = 0;
		int bulls = 0;
		int limit = currentValue.length() <= 4 ? currentValue.length() : 4;
		for (int i = 0; i < limit; i++) {
			int current = currentValue.indexOf(number[i]);
			if(current>=0) {
				if(current == i) {
					bulls++;
				}else {
					cows++;
				}
			}
			
		}
		return new int[] {cows, bulls};
	}

	private void printCowsBulls(int[] res) {
		System.out.printf("%d cows, %d bulls\n", res[0], res[1]);
		
	}

	private void printWinMesssage() {
		StringBuilder result = new StringBuilder();
		for(int s : number)result.append(Integer.toString(s));
		System.out.println("You won! " );
		showAnswer();
		System.out.println(" was the ritht answer.");
		
	}

	private void printIntroduction() {
		System.out.println("The computer generated 4 random numbers without repeats\n "
				+ "you should guess it\n"
				+ "on each iteration you could enter numbers\n"
				+ "the hint will show : cows if some number from your set exists but not on correct position\n"
				+ "and bulls if on each number that exists in generted set and on correct position\n"
				+ "game goes untill you guess 4 bulls\n ");
		
	}

}
