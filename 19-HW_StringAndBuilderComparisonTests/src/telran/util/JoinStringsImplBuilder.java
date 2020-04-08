package telran.util;

import telran.tests.performance.JoinStringsInterface;

public class JoinStringsImplBuilder implements JoinStringsInterface{

	@Override
	public String join(String[] strings, String delimiter) {
		
		if(strings.length == 0) {
			return "";
		}
		StringBuilder res = new StringBuilder();
		res.append(strings[0]);
		for (int i = 1; i < strings.length; i++) {
			res.append(delimiter).append(strings[i]);
		}
		
		return res.toString();
	}

}
