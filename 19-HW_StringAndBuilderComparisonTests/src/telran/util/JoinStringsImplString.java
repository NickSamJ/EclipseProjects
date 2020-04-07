package telran.util;

import telran.tests.performance.JoinStringsInterface;

public class JoinStringsImplString implements JoinStringsInterface {

	@Override
	public String join(String[] strings, String delimiter) {
		String res = "";
		for (int i = 0; i < strings.length; i++) {
			if(strings.length-1 == i ) {
				res = res + strings[i];
			}else {				
				res = res + strings[i] + delimiter;
			}
		}
		return res;
	}	
}
