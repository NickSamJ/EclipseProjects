import java.util.*;
import java.util.Map.Entry;

public class WordCountTestAppl {
	public static void main(String[] args) {
		String text = "lmn b abc lmn, lmn:abc - a ab ab";
		displayWordCounts(text);

		// output format
		/**
		 *  lmn -> 3
		 *  ab -> 2
		 *  abc -> 2
		 *  a -> 1
		 *  b -> 1
		 *  
		 */
	}

	private static void displayWordCounts(String text) {
		// Contains only letters, digits, underscores
		String words[] = getWords(text);

		HashMap<String, Integer> mapCounts = getMapCounts(words);
		List<Map.Entry<String, Integer>> listEntries = getListEntries(mapCounts);
		
		listEntries.sort(new StringCoutnsComparator());
		displayListEntries(listEntries);
//		System.out.println(listEntries.toString());
		
	}

	private static void displayListEntries(List<Entry<String, Integer>> listEntries) {
		for (Entry<String, Integer> entry : listEntries){
			System.out.println(entry.getKey() + " -> " + entry.getValue());
		} 
		// TODO Auto-generated method stub
		
	}

	private static List<Entry<String, Integer>> getListEntries(HashMap<String, Integer> mapCounts) {
//		List<Map.Entry<String, Integer>> resList = new ArrayList<>();
//		for (Map.Entry<String, Integer> entry : mapCounts.entrySet()){
//			resList.add(entry);
//		}
//		return resList;
		
		return new ArrayList<>(mapCounts.entrySet());
	}

	private static	 HashMap<String, Integer> getMapCounts(String[] words) {
		HashMap<String, Integer> resMap = new HashMap<>();
		for(String s : words) {
			
			//  getOrDefault;
			
			int count = resMap.getOrDefault(s, 0);
			resMap.put(s, count+1);
//			if(!resMap.containsKey(s)) {
//				resMap.put(s, 1);
//			}else {
//				resMap.put(s, resMap.get(s)+1);
//			}
			
		}
		return resMap;
		
	}

	private static String[] getWords(String text) {
		return text.split("\\W+");
	}
}
