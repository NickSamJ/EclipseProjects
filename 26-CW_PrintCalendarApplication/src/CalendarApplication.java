import java.time.*;
import java.time.format.TextStyle;
import java.util.Locale;

public class CalendarApplication {
	private static  Locale locale = Locale.UK;
	private static int columnWidth = 4;
	private static String startDay = "MONDAY";

	public static void main(String[] args) {
		try {
			int[] yearMonth = getYearMonth(args); // [0] - year, [1] - month
			if(args.length == 3) {
				startDay = (args[2]);
			}
			printCalendar(yearMonth[0], yearMonth[1]);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void printCalendar(int year, int month) {
		printTitle(year, month);
		printDaysOfWeek();
		printDates(year, month	);
		
	}
	private static void printTitle(int year, int month) {
		Month monthConst = Month.of(month);
		System.out.printf("\t%s, %d\n", monthConst.getDisplayName(TextStyle.FULL, locale), year);;
		
	}

	private static void printDates(int year, int month) {
		int firstDayOfWeek = getFirstDayOfWeek(year, month);
		printOffset((firstDayOfWeek - 1) * columnWidth);
		int lastDay = getLastDay(year, month);
		for(int day = 1; day <= lastDay; day++) {
			System.out.printf("%" + columnWidth + "d", day );
			firstDayOfWeek++;
			if(firstDayOfWeek==8) {
				firstDayOfWeek = 1;
				System.out.println();
			}
		}
	}

	private static int getLastDay(int year, int month) {
		YearMonth yearMonth = YearMonth.of(year, month);
		return yearMonth.lengthOfMonth();
	}

	private static void printOffset(int offset) {
		String spaces =  " ".repeat(offset); 
		System.out.print(spaces);
	}

	private static int getFirstDayOfWeek(int year, int month) {
		LocalDate date = LocalDate.of(year, month, 1);
		int startDayValue = DayOfWeek.valueOf(startDay).ordinal();
		int dateValue = date.getDayOfWeek().getValue();

		if(startDayValue + 1 == dateValue) {
			return date.getDayOfWeek().getValue();
		};
		return dateValue +7 - startDayValue;
	}

	private static void printDaysOfWeek() {
		printOffset(columnWidth /2);
		int start = DayOfWeek.valueOf(startDay).ordinal();
		
		DayOfWeek[] daysValues = DayOfWeek.values();
		
		for(int i = 0; i < 7; i++) {
			if(start == 7) start = 0;
			System.out.print(daysValues[start++].getDisplayName(TextStyle.SHORT, locale) + " ");
		}
		System.out.println();
	}


	/**
	 * 
	 * @param args : if args.length() <  0, year - current, month - current
	 * @return  [0] - year, 
	 * 			[1] - month
	 */
	private static int[] getYearMonth(String[] args) {
		if(args.length < 2) {
			return getCurrentYearMonth();
		}
		int  year = Integer.parseInt(args[0]);
		int  month = Integer.parseInt(args[1]);
		return new int[] {year, month};
	}

	private static int[] getCurrentYearMonth() {
		LocalDate current = LocalDate.now();
		return new int[] {current.getYear(), current.getMonthValue()};
	}
}
