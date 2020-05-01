import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

public class DateTimeTestAppl {
public static void main(String[] args) {
	LocalDate current = LocalDate.now();
	LocalDate test = LocalDate.now().with(new NextFriday13());
//	LocalDate birthDateASP = LocalDate.parse("1779-06-06");
//	LocalDate barMizvahASP = birthDateASP.plus(13, ChronoUnit.YEARS); 
//	LocalDate britMilahASP = birthDateASP.plus(8, ChronoUnit.DAYS);
//	DateTimeFormatter format1 = DateTimeFormatter.ofPattern("eeee d/MMMM/yyyy ", Locale.forLanguageTag("de-DE"));
//	DateTimeFormatter format2 = DateTimeFormatter.ofPattern("eeee d/MMM/y ");
	
//	System.out.println("A.S.s birthday: " + birthDateASP);
//	System.out.println(barMizvahASP.format(format1) + "A.S.s barMizvah(RFC): ");
//	System.out.println("A.S.s britMilah: " + britMilahASP.format(format2));
	
//	ChronoUnit unit =  	ChronoUnit.DAYS;
//	System.out.printf("Between %s and %s there are %d %s");
	
//	current.with(adjuster);
//	System.out.println(current.with(new NextFriday13()));
	
	ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("GMT+5"));
	System.out.println(zdt);
	
	for(String zoneId: ZoneId.getAvailableZoneIds()) {
//		System.out.println(zoneId);
		
	}
}
}
