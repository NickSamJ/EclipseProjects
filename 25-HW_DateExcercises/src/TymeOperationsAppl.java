import java.text.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public class TymeOperationsAppl {
	public static void displayCompleteAge(String birthDate) {
		LocalDate parsedDate = parseIfValid(birthDate);
		LocalDate now =  LocalDate.now();
		Period diff = Period.between(parsedDate, now);

		System.out.printf("Years: %d\nMonthes: %d\nDays: %d", 
				diff.getYears(), diff.getMonths(), diff.getDays());

/******		Old version   ****/
//		int years = (int) ChronoUnit.YEARS.between(parsedDate, now);
//		now = now.minus(years, ChronoUnit.YEARS);
//		int monthes = (int) ChronoUnit.MONTHS.between(parsedDate, now);
//		now = now.minus(monthes, ChronoUnit.MONTHS);
//		int days = (int) ChronoUnit.DAYS.between(parsedDate, now);
		
//		System.out.printf("Years: %d\nMonthes: %d\nDays: %d", years, monthes, days);
	}

    public static LocalDate parseIfValid(String dateStr) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date;
        try {
        	date = LocalDate.parse(dateStr, dateFormatter );
        } catch (Exception e) {
        	date = LocalDate.parse(dateStr);
        }
        return date;
    }
    
    public static void main(String[] args) {
    	displayCompleteAge("1799-06-06");
    }
}
