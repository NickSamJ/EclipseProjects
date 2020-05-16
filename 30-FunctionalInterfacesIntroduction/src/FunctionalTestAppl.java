import java.util.Arrays;

public class FunctionalTestAppl {
	public static void main(String[] args) {
		String[] strings = {"p", "lmn","abc", "l", "lmno"};
		
		// sorting with lambda expression by string length
		Arrays.sort(strings, (a, b) -> a.length() - b.length());
		System.out.println(strings.toString());
		
		// sorting with lambda closure sort by string length and natural
		Arrays.sort(strings, (a, b) -> {
			int res = a.length() - b.length();
			return res == 0 ? a.compareTo(b) : res;
		});
		System.out.println(strings.toString());
		
		// sorting with method reference sort by string length and natural
		Arrays.sort(strings, (a, b) -> compareLengthNatural(a, b));
		
		// The same
		Arrays.sort(strings, FunctionalTestAppl::compareLengthNatural);
	}
	private static Integer compareLengthNatural(String s1, String s2) {
		int res = Integer.compare(s1.length(), s2.length());
		return res == 0 ? s1.compareTo(s2) : res;
	}
}
