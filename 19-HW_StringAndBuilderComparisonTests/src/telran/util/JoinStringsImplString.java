package telran.util;

import telran.tests.performance.JoinStringsInterface;

public class JoinStringsImplString implements JoinStringsInterface {

	@Override
	public String join(String[] strings, String delimiter) {
		if (strings.length == 0) {
			return "";
		}
		String res = strings[0];
		for (int i = 1; i < strings.length; i++) {
			res += delimiter + strings[i] ;
		}
		return res;
	}	
}
