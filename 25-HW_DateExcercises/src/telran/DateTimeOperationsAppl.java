package telran;
import java.text.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.TimeZone;


public class DateTimeOperationsAppl {
	public static void displayCompleteAge(String birthDate) {
		LocalDate parsedDate = parseIfValid(birthDate);
		LocalDate now =  LocalDate.now();
		Period diff = Period.between(parsedDate, now);

		System.out.printf("Years: %d | Monthes: %d | Days: %d\n", 
				diff.getYears(), diff.getMonths(), diff.getDays());
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
    
    public static void displayTimezoneId(String zonePattern) {
    	zonePattern = zonePattern.toLowerCase();
    	String pattern = "\\p{Alpha}*/?"+zonePattern+"/?\\p{Alpha}*";
    	for(String s : ZoneId.getAvailableZoneIds()) {
    		if(s.toLowerCase().matches(pattern)) {
    			System.out.println(s + " : " + ZonedDateTime.now(ZoneId.of(s)));
    		}
    	}
    }
    
    public static void main(String[] args) {
    	displayTimezoneId("CaNaDa");
    	displayCompleteAge("06/06/1799");
    	displayCompleteAge("1799-06-06");
    	displayCompleteAge("06.06.1779");
    }
}
