package telran.calculator.net;

import java.io.Serializable;

public class RequestCalculator implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public String type; // + - / * 
public Integer[] numbers;
public RequestCalculator(String type, Integer[] numbers) {
	super();
	this.type = type;
	this.numbers = numbers;
}
}
