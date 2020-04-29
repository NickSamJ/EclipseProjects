package telran.numbers;

import telran.exceptions.RangeException;
import telran.exceptions.RuleException;

public class DividerRule implements Rule {

	private int divider;
	
	public DividerRule(int divider){
		this.divider = divider;
	}
	
	@Override
	public void checkRule(int number, int min, int max) throws RuleException {
		if(min > max || divider > max) {
			throw new RangeException("Impossible to get value in the given range");
		}
		
		int delta = getDelta(number, min, max);
		if (delta != 0) { 
			throw new RuleException(delta);			
		}

	}
	
	private boolean isValidNumber(int min, int max, int number) {
		if(min <= number && number <= max) {
			return true;
		}
		
		return false;
	}
	
	private int getDelta(int number, int min, int max) {
		int lDist = number % divider;
		int rDist = divider - lDist;
		boolean lFits = (number - lDist >= min) ? true : false;
		boolean rFits = number + rDist <= max ? true : false;

		if((number - lDist) / divider < 1) {
			lFits = false;
		}
		if(!lFits && !rFits) {
			
			throw new RangeException("Impossible to get value in the given range");
		}
				
		return lDist < rDist ? (lFits ? -lDist : rDist) : (rFits ? rDist : -lDist);
	}
}
