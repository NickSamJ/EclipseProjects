package telran.menu;

import java.time.LocalDate;
import java.util.Set;
import java.util.function.Function;

public interface InputOutput {
	String inputString(String prompt);

	void display(String obj);

	default <T> T inputObject(String prompt, String errorMessage, Function<String, T> mapper) {
		T res;
		while (true) {
			String str = inputString(prompt);
			try {
				res = mapper.apply(str);
				if (res != null) {
					break;
				}

			} catch (Exception e) {
				displayLine(errorMessage);
			}
		}
		return res;

	}

	default Integer inputInteger(String prompt, Integer min, Integer max) {

		String errorMessage = String.format("The number you've entered not in range %d - %d", min, max);
		Integer result = inputObject(prompt, errorMessage, s -> {
			Integer res = Integer.parseInt(s);
			return res >= min && res <= max ? res : null;
		});
		return result;
	}

	default void displayLine(Object obj) {
		display(obj.toString() + "\n");
	}
	
	default Integer inputInteger(String prompt) {
		String errorMessage = "Please enter any number";
		Integer result = inputObject(prompt, errorMessage, s -> {
			return Integer.parseInt(s);
		});
		return result;
	}
	
	default String inputOptions(String prompt, Set<String> options) {
		String errorMessage = "Entered value not correct";
		Function<String, String> check = e -> options.contains(e) ? e : null;

		return inputObject(prompt, errorMessage, check);
	}
	
	default LocalDate inputDate(String prompt) {
		
		String errorMessage = "Please, enter date in format like 2020-02-25";
		LocalDate res = inputObject(prompt, errorMessage, e -> {
			return LocalDate.parse(e);
		});
		
		return res;
	}
	
	default String inputEmail(String prompt) {
		
		String errorMessage = "Please, enter email";
		String res = inputObject(prompt, errorMessage, e -> {
			
			return e.matches(Patterns.emailPattern()) ? e : null;
		});
		
		return res;
	}
	
	default String inputPhoneNumber(String prompt) {
		String errorMessage = "Please, enter correct phone number";
		Function<String, String> check = e -> e.matches(Patterns.israelNumberPattern()) ? e : null; 
		return inputObject(prompt, errorMessage, check);
	}
	
	default String inputIpV4(String prompt) {
		String errorMessage = "Please, enter correct IPv4";
		Function<String, String> check = e -> e.matches(Patterns.ipV4()) ? e: null;
		return inputObject(prompt, errorMessage, check);
	}
}
