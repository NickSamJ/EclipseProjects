import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

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

		Arrays.stream(words).collect(Collectors.groupingBy(s -> s, Collectors.counting()))
		.entrySet().stream().sorted((e1, e2) ->{
			int res = Long.compare(e1.getValue(), e2.getValue());
			return res == 0 ? String.CASE_INSENSITIVE_ORDER.compare(e1.getKey(), e2.getKey()) : res;
		}).forEach(e -> System.out.printf("%s -> %d\n", e.getKey(), e.getValue()));
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
