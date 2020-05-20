package telran.text;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class Anagram {
//	public static boolean isAnagram(String word, String anagram) {
//		if(word.length() != anagram.length()) return false;
//		
//		HashMap<Character, Integer> wordArrange = getMapCounts(word);
//		for (int i = 0; i< anagram.length(); i++) {
//			int count = wordArrange.getOrDefault(anagram.charAt(i), 0);
//			if(count==0) return false;
////			wordArrange.put(anagram.charAt(i), count-1);
//			
//			wordArrange.merge(anagram.charAt(i), 1, (prev, one) -> {
//				return prev - one;
//			});
//		}
//		return true;
//	}
//
//	private static	 HashMap<Character, Integer> getMapCounts(String string) {
//		HashMap<Character, Integer> resMap = new HashMap<>();
//		for (int i = 0; i< string.length(); i++) {
//			
//			int count = resMap.getOrDefault(string.charAt(i), 0);
//			resMap.put(string.charAt(i), count+1);		
//			
//		}
//	
//		return resMap;
//		
//	}
	
	public  static boolean isAnagram(String word, String anagram) {
		
		HashMap<Character, Integer> wordMap= getWordMap(word);
		
		return false;
		
	}

private static HashMap<Character, Integer> getWordMap(String word) {
	// TODO Auto-generated method stub
	return null;
}
}

   
