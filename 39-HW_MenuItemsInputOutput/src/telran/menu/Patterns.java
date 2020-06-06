package telran.menu;

public class Patterns {
	
	public static String emailPattern() {
		String userPart = "([^\\s,@]+@)";
		String domainBasePart = "(\\p{Alnum}[\\p{Alnum}-]*\\p{Alnum}|\\p{Alnum})";
		String domainHigherLevelPart = "(\\."+domainBasePart+"){1,4}";
		return userPart + domainBasePart + domainHigherLevelPart;
	}
	
	public static String israelNumberPattern() {
		return "(\\+972-*|0)5[0[2-8]](-*\\d){7}";
	}
	
	public static String ipV4() {
		return String.format("((%s)\\.){3}(%s)", numberLess256(), numberLess256());
	}
	
	private static String numberLess256() {
		return "[01]\\d{2}|\\d{2}|\\d|2[0-4]\\d|25[0-5]";
	}
}
