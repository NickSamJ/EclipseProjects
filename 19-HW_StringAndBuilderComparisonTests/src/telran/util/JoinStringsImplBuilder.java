package telran.util;

import telran.tests.performance.JoinStringsInterface;

public class JoinStringsImplBuilder implements JoinStringsInterface{

	@Override
	public String join(String[] strings, String delimiter) {
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < strings.length; i++) {
			if(strings.length - 1 == i) {
				res.append(strings[i]);
			}else {
				res.append(strings[i]).append(delimiter);
			}
		}
		
		return res.toString();
	}

}
