package telran.test;

import java.util.Comparator;

import telran.persons.dto.Person;

//public class PersonAgeComparator implements Comparator<Person> {
//
//	@Override
//	public int compare(Person o1, Person o2) {
//		return o1.getBirthYear() >= o2.getBirthYear() ? -1 : 1;
//	}
//
//}


public class PersonAgeComparator implements Comparator<Object> {
	
	@Override
	public int compare(Object o1, Object o2) {
		return ((Person)o1).getBirthYear() >= ((Person)o2).getBirthYear() ? -1 : 1;
	}

}