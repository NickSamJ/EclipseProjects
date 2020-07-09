package telran.numbers;

import java.util.Arrays;

public class UsualGroupSum extends GroupSum {

	public UsualGroupSum(int[][] groups) {
		super(groups);
	}

	@Override
	public Long computeSum() {
		return Arrays.stream(groups)
					.flatMapToInt(Arrays::stream)
					.asLongStream()
					.parallel()
					.sum();
	
//		int res = 0;
//		for (int i = 0; i < groups.length; i++) {
//			for (int j = 0; j < groups[i].length; j++) {
//				res += groups[i][j];
//			}
//		}
//		return (long)res;
	}
}
