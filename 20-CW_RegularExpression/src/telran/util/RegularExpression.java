package telran.util;

public class RegularExpression {
	/*
	 * First symbol should be any ASCII Letter or $ or _
	 * if first symbol _ the length can`t be 1
	 * Other symbols may be only either ASCII letter or $ of _ or digit
	 * 
	 *  @return regular expression string
	 */
	static public String variableName() {
		return "[\\p{Alpha}$][\\w$]*|_[\\w$]+";
	}
	
	/*
	 * string contains numbers from 0 to 255
	 * @return
	 */
	static public String numberLess256() {
		return "[01]\\d{2}|\\d{2}|\\d|2[0-4]\\d|25[0-5]";
	}
	
	/*
	 * 
	 * @return regular expression string
	 * 
	 */
	public static String ipV4() {
		return String.format("((%s)\\.){3}(%s)", numberLess256(), numberLess256());
	}
	
	
	/******************************
	 * 
	 * 	Homework regular expressions
	 * 
	 * *****************************/
	
	public static String emailPattern() {
		String userPart = "([^\\s,@]+@)";
		String domainBasePart = "(\\p{Alnum}[\\p{Alnum}-]*\\p{Alnum}|\\p{Alnum})";
		String domainHigherLevelPart = "(\\."+domainBasePart+"){1,4}";
		return userPart + domainBasePart + domainHigherLevelPart;
	}
	
	public static String israelNumberPattern() {
		return "(\\+972-*|0)5[0[2-8]](-*\\d){7}";
	}
	public static String simpleArithmeticExpressionPattern() {
		return"[ \t]*\\d{1,13}([ \\t]*[-\\+/\\*][ \\t]*\\d{1,13})*[ \\t]*";
	}
}
