package telran.numbers;

import telran.exceptions.RangeException;
import telran.exceptions.RuleException;

public class Generator {
	private int min;
	private int max;
	private Rule rule;
	
	public Generator(int min, int max, Rule rule) {
		if(min > max) {
			throw new IllegalArgumentException();
		}
		this.min = min;
		this.max = max;
		this.rule = rule;
	}
	
	public int[] generate(int nNumbers) {
		int[] res  = new int[nNumbers];
		int counter = 0;
		while(counter < res.length){
			int randomInt = getRndInt(min, max);
			try {
				rule.checkRule(randomInt, min, max);
				res[counter++] = randomInt;
			}catch(RuleException e) {
				res[counter++] = randomInt + e.getDelta();
			}
		}
		return (res);
	}
	
	public Rule getRule(){
		return this.rule;
	}
	public void setRule(Rule rule) {
		this.rule = rule;
	}
	
	private int getRndInt(int min, int max) {
		return (int) (Math.random() * (max-min+1)) + min;
	}
}
