package telran.numbers;

import telran.exceptions.RangeException;
import telran.exceptions.RuleException;

public class DividerRule implements Rule {
	
	private int divider;
	
	public DividerRule(int divider) {
		this.divider = divider;
	}

	@Override
	public void checkRule(int number, int min, int max) throws RuleException {
		boolean invalidRange = false;
		if(max - min < divider-1) {
			int counter = min;
			invalidRange = true;
			while(counter <= max) {
				if(counter % divider == 0) {
					invalidRange = false;
					break;
				}
				counter++;
			}
				
		}
		if(min>=max || invalidRange) {
			throw new RangeException("Range exception <-");
		}
		int delta = 0;
		if(number % divider != 0) {
			int lDist = number % divider;
			int rDist = divider - lDist;
			
			delta = lDist <= rDist ? (min<=lDist ? -lDist: rDist) : (max>=rDist ? rDist: -lDist);
			throw new RuleException(delta);
		}
	}

}
