package telran.tests;

import java.util.Comparator;

public class SortOddEvenComparator implements Comparator<Object> {

	@Override
	public int compare(Object o1, Object o2) {
		int oi1 = (int)o1;
		int oi2 = (int)o2;
//		
//if (oi1%2==1 && oi2%2==0) {
//	return -1;
//}
//if (oi1%2==0 && oi2%2==1) {
//	return 1;
//}
//if (oi1%2==1 && oi2%2==1) {
//	return oi1-oi2;
//}
//return oi2-oi1;
		if(oi1%2!=0 || oi2%2!=0) {
			if (oi1>oi2) {				
				return 1;
			}else {
				return -1;
			}
		}else {
			if (oi1<oi2) {
				return -1;
			}else {
				return 1;
			}
		}
	}
}
