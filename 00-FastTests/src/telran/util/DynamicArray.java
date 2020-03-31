package telran.util;

import java.util.ArrayList;
import java.util.Iterator;

public class DynamicArray {
	public static void main(String[] args) {
		
		StringBuilder str1 = new StringBuilder("Vasya");
		StringBuilder str2 = new StringBuilder("Petya");
		StringBuilder str3 = new StringBuilder("Tolik");
		ArrayList<StringBuilder> array = new ArrayList<StringBuilder>();
		array.add(str1);
		array.add(str2);
		array.add(str3);
		
		System.out.println(array.toString());
	}
}
