package hackerrank.tasks;

public class DivisibleSumPairs {

	public static void main(String[] args) {
		int[] ar = {1, 3, 2, 6, 1, 2};
		int res = divisibleSumPairs(6, 3, ar );
		System.out.println(res);
	}
	static int divisibleSumPairs(int n, int k, int[] ar) {
        int idn = 0;
        int res = 0;
        for(int i = 0; i <= n; i++){
            for(int j = i; j < n-1; j++){
                if(((ar[i] + ar[j+1])%k) == 0){
                    res++;
                    
                    
                }
            }
        }
    return res;
    }
}
