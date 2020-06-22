package hackerrank.tasks;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MagicCube {

	public static void main(String[] args) {
		int[][] cube = {{5, 3, 4} , {1, 5, 8}, {6, 4, 2}};
		int[][] cube2 = {{4, 8, 2},{4, 5, 7,}, {6, 1, 6}};
		
		int res = formingMagicSquare(cube2);
		
		System.out.println(res);
	}

	static int formingMagicSquare(int[][] s) {
        ArrayList<Integer> noneUniques = new ArrayList<>();
        HashSet<Integer> keys = new HashSet<>();
        ArrayList<Integer> redundantNumbers = new ArrayList<>();
        int res = 0;

        for(int i = 0; i < 3; i++){
           for(int j = 0; j < 3; j++){
               int val = s[i][j];
               if(keys.contains(val)){
                   noneUniques.add(val);
               }else{
                   keys.add(val);
               }
           }
        }

        int i = 0;
        for(int c = 1; c < 10; c++) {
        	if(!keys.contains(c)) {
        		redundantNumbers.add(c);
        		res = res + Math.abs(noneUniques.get(i++) - c);
        	}
        }

        return res;
    }
}
