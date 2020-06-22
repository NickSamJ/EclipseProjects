package hackerrank.tasks;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MigratoryBirds {

	public static void main(String[] args) {
		List<Integer> list = List.of(1,  2,2,2,2,4, 4, 4,5, 7, 7, 7, 7, 3);
		int res = migratoryBirds(list);
		
		System.out.println(res);
	}

  static int migratoryBirds(List<Integer> arr) {
        HashMap<Integer, Integer> temp = new HashMap<>();

        for(Integer g : arr){
            int oldVal = temp.getOrDefault(g, 0);
            temp.put(g, oldVal+1);
        }
        
        int mx = temp.entrySet()
        			.stream()
        			.map(e -> (int)e.getValue())
        			.reduce(0, (old, cur) -> old < cur ? cur : old);
        
        int res = temp.entrySet()
        			.stream()
        			.filter(e -> (e.getValue() == mx ))
        			.map(e -> (int)e.getKey())
        			.max((a, b) -> b.compareTo(a))
        			.get()
        			;
        return res;

    }
}
