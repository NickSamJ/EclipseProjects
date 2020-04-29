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
		
		if(min>=max || divider > max ) {
			throw new RangeException("DontContainAnyValue");
		}
		if( number < divider) { 
			throw new RangeException("DividerGreaterThanNumber");
		}
		int delta = 0;

		if(number % divider != 0) {
			int lDist = number % divider;
			int rDist = divider - lDist;
			
			if(number + rDist <= max && number - lDist >= min ) {				
				delta = lDist <= rDist ? (min<=lDist ? -lDist: rDist) : (max>=rDist ? rDist: -lDist);
			}else if(number + rDist > max && number - lDist >= min ) {
				delta = - lDist;
			}else if(number + rDist <= max && number - lDist < min ) {
				delta = rDist;
			}else {
				throw new RangeException("DontContainAnyValue");
			}
			throw new RuleException(delta);
		}
	}
}
