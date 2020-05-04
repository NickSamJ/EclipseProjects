package telran.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import telran.WorkingDays;


class WorkingDaysTests {

	@Test
	void testDaysOff() {
		DayOfWeek[] daysOff = new DayOfWeek[] {DayOfWeek.SATURDAY, DayOfWeek.SUNDAY};
		checkWorkingDays("2020-05-05", "2020-05-03", 1, daysOff);
	}
	
	@Test
	void testAllDaysAreDaysOff() {
		DayOfWeek[] daysOff = new DayOfWeek[] {
				DayOfWeek.MONDAY,
				DayOfWeek.TUESDAY,
				DayOfWeek.WEDNESDAY,
				DayOfWeek.THURSDAY,
				DayOfWeek.FRIDAY,
				DayOfWeek.SATURDAY, 
				DayOfWeek.SUNDAY,
			};
		checkWorkingDays(LocalDate.now().toString(), LocalDate.now().toString(), 1, daysOff);
		
	}
	
	@Test
	void testTwoWeeks() {
		DayOfWeek[] daysOff = new DayOfWeek[] {DayOfWeek.SATURDAY, DayOfWeek.SUNDAY};
		checkWorkingDays("2020-05-18", "2020-05-01", 10, daysOff);
	}
	
	@Test
	void testNoDaysOff(){
		DayOfWeek[] daysOff = new DayOfWeek[] {};
		checkWorkingDays("2020-05-11", "2020-05-01", 10, daysOff);
		
	}
	void checkWorkingDays(String expected, String startDate, int nWorkingDays, DayOfWeek[] daysOff ) {
		String actual = LocalDate.parse(startDate).with(new WorkingDays(nWorkingDays, daysOff)).toString();
		assertEquals(expected, actual);
	}

}
