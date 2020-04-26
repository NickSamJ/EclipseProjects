
public enum WeekDay {
SUN, MON, TUE, WED,THU,FRI, SUT;
	static public int daysDifference(WeekDay wdFrom,WeekDay wdTo) {
		int indFrom = wdFrom.ordinal();
		int indTo = wdTo.ordinal();
		indFrom = indFrom < indTo ? indFrom : indFrom + 7;
		
		return indTo - indFrom;
	}
}
