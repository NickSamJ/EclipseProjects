package telran.util;

import java.util.ArrayList;
import java.util.Collections;

public class Encoder {
	String encodingString;
	int base;

	public Encoder(String encodingString) {
		super();
		this.encodingString = encodingString;
		base = encodingString.length();
	}

	public String code(int number) {
		String res = "";
		ArrayList<Integer> temp = new ArrayList<>();
		while(number > 0) {
			temp.add(number%base);
			number = number/base;
			
		}
		Collections.reverse(temp);
		for(int i : temp)
			res += encodingString.charAt(i);
		
		return res;
	}

	public Object decode(String string) {
		int res = 0;
		
		for(char symb: string.toCharArray()) {
			int index = encodingString.indexOf(symb);
			if(index < 0) {
				return res;
			}
			res = res*base+index;
		}
		return res;
	}
}
