package telran.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import telran.persons.dto.Person;

class PersonTests {

	@Test
	void testPerson() {
//		Person person = new Person(1234567890, "Vasya", 1290);
		Person person = new Person();
		assertNotNull(person);
		assertNull(person.getName());
		
	}

	@Test
	void testPersonLongStringInt() {
		long id = 1233;
		String name = "Vasya";
		int birthYear = 1920;
		Person person = new Person(id, name, birthYear);
		
		assertEquals(name, person.getName());
		assertEquals(birthYear, person.getBirthYear());
		assertEquals(id, person.getId());
	}

	@Test
	void testSetName() {
		String name = "Ignat";
		Person person = new Person();
		
		person.setName(name);
		
		assertEquals(name, person.getName());
	}

	@Test
	void testEqualObjects() {
		Person person1 = new Person(123, "Ignat", 1239);
		Person person2 = new Person(123, "Ignat", 1239);
		assertEquals(person1, person2);
	}
	@Test
	void testSorting() {
		Person persons[] = {
				new Person(123, "Genry", 1920),
				new Person(131, "Jack", 1220),
				new Person(23, "Edvard", 19700)
		};
		Arrays.sort(persons);
		assertEquals(23, persons[0].getId());
		assertEquals(123, persons[1].getId());
		Arrays.sort(persons, new PersonAgeComparator());
		
	}
}



