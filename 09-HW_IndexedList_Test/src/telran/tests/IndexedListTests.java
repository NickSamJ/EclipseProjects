package telran.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.Array;
import telran.util.IndexedLinkedList;
import telran.util.IndexedList;


class IndexedListTests {
	int numbers[] = {10, -8, 70, 75, 30};
	IndexedList<Integer> listNumbers;
	@BeforeEach
	void setUp() {
		listNumbers = getList();
	}

	@Test
	void testLinkedListReverse() {
		if (listNumbers instanceof IndexedLinkedList) {
			((IndexedLinkedList<Integer>)listNumbers).reverse();

			int expected[] = {30, 75, 70, -8, 10};
			int actual[] = getActualNumbers(listNumbers);
			assertArrayEquals(expected, actual);
		}
	}
	@Test
	void testAddGetSize() {
		for (int i = 0; i < listNumbers.size(); i++) {
			assertEquals(numbers[i], (int)listNumbers.get(i));
		}
		assertNull(listNumbers.get(listNumbers.size()));
		
	}
	private IndexedList<Integer> getList() {
		IndexedList<Integer> list = new IndexedLinkedList<>(4); //single place of updating code
		
		for (int i = 0; i < numbers.length; i++) {
			list.add(numbers[i]);
		}
		return list;
	}
	@Test
	void testIndexOf() {
		assertEquals(4, listNumbers.indexOf(30));
	}
	@Test
	void testLastIndexOf() {
		assertEquals(1, listNumbers.lastIndexOf(-8));
	}
	@Test
	void testAddAtIndex() {
		int expectedNumbers[] = {-10, 10, -8, 70, -70, 75, 30, -30};
		
		assertTrue(listNumbers.add(0, -10));
		assertTrue(listNumbers.add(4, -70));
		assertTrue(listNumbers.add(7, -30));
		int actualNumbers[] = getActualNumbers(listNumbers);
		assertArrayEquals(expectedNumbers, actualNumbers );
		assertFalse(listNumbers.add(-1, 100));
		assertFalse(listNumbers.add(100, 100));
		
	}

	private int[] getActualNumbers(IndexedList<Integer> list) {
		int res[] = new int[list.size()];
		for (int i = 0; i < res.length; i++) {
			res[i] = (int) list.get(i);
		}
		return res;
	}
	@Test
	void testRemoveAtIndex() {
		int expectedNumbers[] = { -8, 75};
		assertEquals(10, listNumbers.remove(0));
		assertEquals(70, listNumbers.remove(1));
		assertEquals(30, listNumbers.remove(2));
		assertArrayEquals(expectedNumbers, getActualNumbers(listNumbers));
		assertNull(listNumbers.remove(2));
		assertNull(listNumbers.remove(-1));
	}
	@Test
	void testSetAtIndex() {
		int expectedNumbers[] = {-10, -8, -70, 75, -30};
		assertEquals(10, listNumbers.set(0, -10));
		assertEquals(70, listNumbers.set(2, -70));
		assertEquals(30, listNumbers.set(4, -30));
		assertArrayEquals(expectedNumbers, getActualNumbers(listNumbers));
		assertNull(listNumbers.set(-1, 100));
		assertNull(listNumbers.set(100, 100));
	}
	@SuppressWarnings("unchecked")
	@Test
	void testSorting() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
		Person personMoshe = new Person(123, "Moshe", 1980);
		Person personVova = new Person(100, "Vova", 1970);
		
			IndexedList<Person> listPersons = listNumbers.getClass() //getting object of Class
				.getConstructor() //getting constructor by default
				.newInstance();
			listPersons.add(personMoshe);
			listPersons.add(personVova);
			listPersons.sort();
			assertEquals(personVova, listPersons.get(0));
			assertEquals(personMoshe, listPersons.get(1));
			listPersons.sort(new PersonAgeComparator());
			assertEquals(personVova, listPersons.get(1));
			assertEquals(personMoshe, listPersons.get(0));
		
	}
	@Test
	void testBinarySearch() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
			String stringsNaturalOrder[]=
				{"abcd","lm", "lmnopr","x","y","z"};
			String stringsLengthOrder[]=
				{"x","y","z","lm","abcd", "lmnopr"};
			Comparator<String> compLength = new StringLengthComparator();
			IndexedList<String> stringsNatural = getListStrings(stringsNaturalOrder);
			IndexedList<String> stringsLength = getListStrings(stringsLengthOrder);
			assertEquals(-3, stringsNatural.binarySearch("lmn"));
			assertEquals(1, stringsNatural.binarySearch("lm"));
			assertEquals(0, stringsNatural.binarySearch("abcd"));
			stringsLength.sort(compLength);
			assertEquals(0, stringsLength.binarySearch("x", compLength));
			assertEquals(-1, stringsLength.binarySearch("a", compLength));
			assertEquals(1, stringsLength.binarySearch("y", compLength));
			assertEquals(-5, stringsLength.binarySearch("lmn", compLength));
			assertEquals(3, stringsLength.binarySearch("lm", compLength));
	}
	
	@SuppressWarnings("unchecked")
	private IndexedList<String> getListStrings(String[] strings) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		//Array<String> array = new Array<>(strings.length);
			//getting an object of the same class as listNumbers
			/*************************************/
			IndexedList<String> list = 
					listNumbers.getClass().getConstructor(int.class)
					.newInstance(strings.length);
			/**************************************/
			for (int i = 0; i < strings.length; i++) {
				list.add(strings[i]);
			}
			
			return list;
	}
	@Test
	void testFilter() {
			int expected[] = {10, -8, 70, 30};
			IndexedList<Integer> listNoEven =
					listNumbers.filter(new EvenNumbersPredicate());
			int actualNumbers[] = getActualNumbers(listNoEven);
			assertArrayEquals(expected, actualNumbers);	
		
	}
	@Test
	void testRemoveIf() {
		//{10, -8, 70, 75, 30};
		listNumbers.add(75);
		int expected[] = {75, 75};
		EvenNumbersPredicate predicateEven = new EvenNumbersPredicate();
		assertTrue(listNumbers.removeIf(predicateEven));
		assertFalse(listNumbers.removeIf(predicateEven));
		assertArrayEquals(expected,
				getActualNumbers(listNumbers));
	}
	@Test
	/**
	 * additional test for sorting array numbers according to the following
	 * all odd numbers should go before the even ones
	 * odd numbers should be sorted in the ascending order
	 * even numbers should be sorted in the descending order
	 */
	void testSortingEvenOdd( ) {
			// {10, -8, 70, 75, 30}
		listNumbers.add(73);
		listNumbers.add(3);
		int []expected = {3, 73, 75, 70, 30, 10, -8};
		listNumbers.sort(new EvenOddComparator());
		assertArrayEquals(expected,
					getActualNumbers(listNumbers));

	}
	@Test
	void testRemoveObject() {
		//{10, -8, 70, 75, 30};
		int expected[]={10, -8,  75, 30};
		listNumbers.remove((Integer)70);
		assertArrayEquals(expected,
				getActualNumbers(listNumbers));
	}
	@Test
	void testIterableSum() {
		int sumExpected = 177;
		int sumActual = 0;
		for(Integer num: listNumbers) {
			sumActual += num;
		}
		assertEquals(sumExpected, sumActual);
	}
	@Test
	void testIterableRemove() {
		listNumbers.add(77);
		listNumbers.add(78);
		listNumbers.add(80);
		int expected[] = {-8,  75, 77};
		//wrong iterating and removing
//		for(Integer num: list) {
//			if (num >= 0 && num % 2 == 0) {
//				list.remove(num);
//			}
//		}
		Iterator<Integer> it = listNumbers.iterator();
		while(it.hasNext()) {
			Integer num = it.next();
			if (num >= 0 && num % 2 == 0) {
				it.remove();
			}
		}
		assertArrayEquals(expected, getActualNumbers(listNumbers));
	}
	
	
	
	
	
	
	
	
	
	

}
