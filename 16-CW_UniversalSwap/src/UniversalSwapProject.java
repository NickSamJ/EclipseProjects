
public class UniversalSwapProject {
public static void main(String[] args) {
	int a = 10, b = 20;
	StringBuilder d = new StringBuilder("Vasya");
	StringBuilder e = new StringBuilder("Petya");
	swap(d, e);
	System.out.println(d);
	System.out.print(e);
}

public static <T> void swap(T a, T b) {
	T tmp = a;
	a = b;
	b = tmp;
}
}
