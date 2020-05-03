package telran.adjusters;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

public class WorkingDaysAdjuster implements TemporalAdjuster{

	private DayOfWeek[] daysOff = new DayOfWeek[] {DayOfWeek.SATURDAY, DayOfWeek.SUNDAY};
	private int workingDays = 0;

	public WorkingDaysAdjuster() {}
	
	public WorkingDaysAdjuster(int workingDays, DayOfWeek[] daysOff) {
		this.workingDays = workingDays;
		this.daysOff = daysOff;
	}
	
	@Override
	public Temporal adjustInto(Temporal temporal) {
		LocalDate res = (LocalDate) temporal;
		if(daysOff.length == 7) {
			return LocalDate.now();
		}
		if(daysOff.length == 0) {
			return(res.plus(workingDays, ChronoUnit.DAYS));
		}
		int counter = 0;
		while (counter <= workingDays) {
			res = res.plus(1,ChronoUnit.DAYS);
			if(!checkIfDayOff(res)) {
				counter++;
			}
		}
		return res;
	}
	private boolean checkIfDayOff(LocalDate res) {
		for(DayOfWeek day : daysOff) {
			if(day.getValue() == res.getDayOfWeek().getValue()) {
				return true;
			}
		}
		return false;
	}
	public static void main(String[] args) {
		WorkingDaysAdjuster a = new WorkingDaysAdjuster();
		System.out.println(a.adjustInto(LocalDate.parse("2020-10-10")).toString());
	}
}


