package telran.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.numbers.ArrayListNumbersBox;
import telran.numbers.HashSetNumbersBox;
import telran.numbers.LinkedListNumbersBox;
import telran.numbers.NumbersBox;
import telran.numbers.TreeSetNumbersBox;

class NumberBoxTests {

	@Test
	void testNumberBox() {
		// Change line below to ArrayListNumbersBox, LinkedListNumbersBox, HashSetNumbersBox,TreeSetNumbersBox
		ArrayListNumbersBox numBox = new ArrayListNumbersBox();
		numBox.addNumber(100);
		numBox.addNumber(40);
		numBox.addNumber(60);
		numBox.addNumber(50);
		numBox.addNumber(80);
		numBox.addNumber(-1);
		
		int[] expected = {100, 40, 60, 50, 80, -1}; 
		compareArrays(expected, numBox);
		
		numBox.removeNumber(80);
		numBox.removeNumber(100);
		
		int[] expected2 = {40, 60, 50, -1}; 
		compareArrays(expected2, numBox);
		
		int delAmount = numBox.removeNumbersInRange(45,65);
		int[] expected3 = {40, -1};
		assertEquals(2, delAmount);
		
		compareArrays(expected3, numBox);
		
		numBox.addNumber(50);
		numBox.addNumber(50);
		numBox.addNumber(50);
		int delRepeatedAmount = numBox.removeRepeated();
		
		if(numBox instanceof Set) {
			assertEquals(0, delRepeatedAmount);
		}
		if(numBox instanceof List) {
			assertEquals(2, delRepeatedAmount);
		}
		
		int[] expected4 = {40, -1, 50};
		compareArrays(expected4, numBox);
		assertTrue(numBox instanceof Iterable);
		
	}

	private void compareArrays(int[] expected, NumbersBox actual) {
		int[] actualArray = new int[expected.length];
		int i = 0;
		for(int number : actual) {
			actualArray[i++] = number;
		}
		Arrays.sort(expected);
		Arrays.sort(actualArray);
		
		assertArrayEquals(expected, actualArray);
 
	}
	
//	private void printArr(NumbersBox box) {
//		for (int i : box) {
//			System.out.println(i);
//		}
//	}
}
