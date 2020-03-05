package telran.util;

public class ArrayInt {
	
	/**
	 * Search an index of given number in array
	 * @param ar - array of numbers
	 * @param number - number for search
	 * @return
	 */
	public static int search(int[] ar, int number) {		
		for(int i=0; i<ar.length; i++) {
			System.out.println();
			
			if (ar[i]==number) {return i;};
		}
		return -1;
		
	}
	/**
	 * Sorting of numbers
	 * @param arr - array of numbers
	 */
	public static void sort(int[] arr) {
		
		boolean sorted = false ;
		int length = arr.length;
		do {
			sorted = true;
			length--;
			for(int i=0; i<length; i++){
				if(arr[i]>=arr[i+1]) {
					int tmp = arr[i];
					arr[i]=arr[i+1];
					arr[i+1]=tmp;
					sorted=false;
				}
			}
		}while(!sorted);
	}
	
	/**
	 * Search an index of given number in a sorted array
	 * @param ar - array of numbers
	 * @param number - number for search
	 * @return
	 */
	public static int binarySearch(int[] arr, int number) {
		
		int left = 0;
		int right =  arr.length-1;
		int middle  = (left+right)/2;
		while(left<=right && arr[middle]!=number) {
			if(number<arr[middle]) {
				right = middle-1;
			}else {
				left = middle+1;
			}
			middle = (right+left)/2;
		}
				
		int result = left > right ? -(left+1) : middle;
		
		if( result>=0) {
			
			// searching for the first indent of number
			while(result >= 0 && arr[result] == number) {
				result--;
			}
			result++;
		}
		return result;
		
		
	}
	
	/**
	 * 
	 * @param arr
	 * @param number
	 * @return number of intentions on number
	 */
	public static int countSorted(int[] arr, int number) {
		int index = binarySearch(arr, number);
		
		int count = 0;
		if (index>=0) {
			while(index<=arr.length && arr[index]==number) {
				count++;
				index++;
			}	
		}
		
		
		return count;
	}
	
	/**
	 * 
	 * @param arr
	 * @param index
	 * @param value
	 * @return array with added number
	 */
	public static int[] insert(int[] arr,  int index, int value) {
		
		if(index<0) {
			return arr;
		}else {
			int[] newArray = new int[arr.length+1];
			
			System.arraycopy(arr, 0, newArray, 0, index);
			newArray[index] = value;
			System.arraycopy(arr, index, newArray, index+1, arr.length - index);
			
			return newArray;
		}
		
	}	
	
	public static int[] deleteElement(int[] arr, int index) {
		int[] res = arr;
		if(index>=0 && index<arr.length) {
			res = new int[arr.length-1];
			System.arraycopy(arr, 0, res, 0, index);
            System.arraycopy(arr, index+1, res, index, res.length-index);
		}
		
		return res;
	}
	
	/**
	 * 
	 * @param arr - array, where the number should be inserted
	 * @param number - number that need to be inserted
	 * @return array with number inserted into it
	 */
	public static int[] insertSorted(int[] arr, int number) {

		int index = binarySearch(arr, number);
		int[] res = new int[arr.length+1];
		if(index<0) {
			index=-(index+1);
		}
		res =  insert(arr, index, number);
		
		return res;

	}	
}



