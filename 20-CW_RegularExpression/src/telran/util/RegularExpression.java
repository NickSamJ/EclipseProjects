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
		//TODO
		
//		return "^([a-z, A-Z, $])[a-z, A-Z, 0-9, $, _]*";
//		return "^([])[a-z, A-Z, 0-9, $, _]*";
		return "[\\p{Alpha}$][\\w$]*|_[\\w$]+";
	}
	
	/**
	 * string contains 
	 * @return
	 */
	static public String numberLess256() {
		return "[01]\\d{2}|\\d{2}|\\d|2[0-4]\\d|25[0-5]";
	}
	
	public static String ipV4() {
		return String.format("((%s)\\.){3}(%s)", numberLess256(), numberLess256());
	}
	
	
	public static String emailPattern() {
		String userPart = "(\\p{Alnum}[\\w-.]*\\p{Alnum}|\\p{Alnum})";
		String domainBasePart = "(@\\p{Alnum}[\\p{Alnum}-]*\\p{Alnum}|@\\p{Alnum})";
		String domainHigherLevelPart = "(\\.\\p{Alpha}+(\\.\\p{Alpha}+)?)";
		return userPart + domainBasePart + domainHigherLevelPart;
	}
	public static String israelNumberPattern() {
		return "\\+?972( *-* *\\d){9}";
	}
}
