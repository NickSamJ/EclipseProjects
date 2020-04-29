package telran.numbers;

import telran.exceptions.RangeException;
import telran.exceptions.RuleException;

public class Generator{
	private int max, min;
	Rule rule;
	

	public Generator(int min, int max, Rule rule) {
		super();
		if (max < min) {
			throw new IllegalArgumentException();
		}
		this.max = max;
		this.min = min;	
		this.rule = rule;
	}
	
	public int[] generate(int nNumbers) {
		int[] res = new int[nNumbers];
		int addCounter = 0;
		while(addCounter< nNumbers) {
			int rndNumber = getRndInt(min, max);
			try {
				rule.checkRule(rndNumber, min, max);
				res[addCounter++] = rndNumber;
			} catch(RangeException e) {
				if(e.getMessage() == "DontContainAnyValue") {
					throw new RangeException("Not in range");
				};
			}
			catch (Exception e) {
			}
		}
		return res;
	}
	public void setRule(Rule rule) {
		this.rule = rule;
	}
	public Rule getRule() {
		return this.rule;
	}
	private int getRndInt(int min, int max) {
		return (int) (Math.random() * (max-min+1)) + min;
	}
}
