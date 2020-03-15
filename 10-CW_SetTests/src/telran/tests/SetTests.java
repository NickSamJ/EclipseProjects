package telran.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.HashSet;
import telran.util.Set;

class SetTests {
	Integer numbers[] = {10, 20, 11, -8, 7, 13};
	Set<Integer> set;

	Integer[] generateActual(Set<Integer> actualSet){
		Integer actual[] = new Integer[actualSet.size()];
		int i = 0;
		Iterator it = actualSet.iterator();
		while (it.hasNext()) {
			actual[i++] = (Integer)it.next();
		}
		
		return actual;
	}
	
	@BeforeEach
	void setUp() {
		set = new HashSet<Integer>();
		for (Integer num : numbers) {
			set.add(num);
		}
	}
	@Test
	void testAddContains() {
		for (Integer num : numbers) {
			assertTrue(set.contains(num));
		};
		assertFalse(set.contains(8));
		assertTrue(set.contains(-8));
		assertTrue(set.add(8));
	}
	
	@Test
	void testIterator() {
		Integer actual[] = generateActual(set);
		Arrays.sort(numbers);
		Arrays.sort(actual);
		
		assertArrayEquals(numbers, actual);
	}
	
	@Test 
	void testIteratorRemove() {
		Integer expected[] = {10, 11, -8, 7,};
		
		Integer currentValue;
		Iterator<Integer> it = set.iterator();
		while (it.hasNext()) {
			currentValue  = it.next();

			if(currentValue> 12) {
				it.remove();
			}
		}

		Integer actual[] = generateActual(set);
		Arrays.sort(actual);
		Arrays.sort(expected);
		
		assertArrayEquals(expected, actual);
	}
	@Test
	void testRemove() {		
		set.add(55);
		set.remove(55);
		Integer actual[] = generateActual(set);
		Arrays.sort(actual);
		Arrays.sort(numbers);
		
		assertArrayEquals(numbers, actual);
		assertNull(set.remove(55));
		
	}
	
	@Test
	void testFilter() {
		Integer expected[] = {11, 7, 13};
		Set<Integer> newSet= set.filter(new OddPredicate());
		
		Integer actual[] = generateActual(newSet);
		Arrays.sort(actual);
		Arrays.sort(expected);
		assertArrayEquals(expected, actual);
	}
}
