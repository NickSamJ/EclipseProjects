package telran.tests;

import java.util.function.Predicate;

public class OddPredicate implements Predicate<Integer>{

	@Override
	public boolean test(Integer t) {
		return (t % 2) != 0;
	}

}
