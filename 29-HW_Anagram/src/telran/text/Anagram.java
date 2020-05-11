package telran.text;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Anagram {
	public static boolean isAnagram(String word, String anagram) {
		if(word.length() != anagram.length()) return false;
		
		HashMap<Character, Integer> firstArrange = getMapCounts(word);
		HashMap<Character, Integer> secondArrange = getMapCounts(anagram);
		
		
		return firstArrange.equals(secondArrange);
	}
	
	private static	 HashMap<Character, Integer> getMapCounts(String string) {
		HashMap<Character, Integer> resMap = new HashMap<>();
		for (int i = 0; i< string.length(); i++) {
			
			int count = resMap.getOrDefault(string.charAt(i), 0);
			resMap.put(string.charAt(i), count+1);		
			
		}
	
		return resMap;
		
	}
}
