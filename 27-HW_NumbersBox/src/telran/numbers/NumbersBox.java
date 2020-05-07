package telran.numbers;

public interface NumbersBox extends Iterable<Integer> {
void addNumber(int number);
void removeNumber(int number);
int removeRepeated(); 	// Removing repeated numbers. Returns number of removes
int removeNumbersInRange(int from, int to); 	// Removing numbers in range. Returns number of removes
}
