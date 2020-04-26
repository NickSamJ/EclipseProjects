public class EnumTestAppl {
public static void main(String[] args) {
//	WeekDay wd = WeekDay.FRI;
//	String s = "MON";
	
	for (WeekDay wd1: WeekDay.values()) {
		weekDayComment(wd1);
	}
//	printWeightUnitConversion(1000f, WeightUnit.KG, WeightUnit.MGr);
	System.out.println(WeekDay.daysDifference(WeekDay.MON, WeekDay.SUN));
}

public static void weekDayComment(WeekDay wd) {
//	switch (wd) {
//		case SUN: System.out.println("Day off in whole world"); break;
//		case FRI: System.out.println(wd + " Day off in I"); break;
//		case SUT: System.out.println(wd + " Day off"); break;
//		default: System.out.println(wd + " Regular day"); break;
//	}
	
}
static public void  printWeightUnitConversion(float amount, WeightUnit source, WeightUnit destination) {
	System.out.printf("%f %s equals %f %s \n", amount, source, amount * source.convert(destination), destination);
}
}
