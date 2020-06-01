package telran.util;

import java.io.IOException;

public interface GuessGame {
	public String startGame();
	public String prompt();
	public String move(String userInput);
	public boolean isFinished();
	
}
