
public class SingletonEager {
	static Configuration config;
	static {
		config = new Configuration(4);
	}
	
	private SingletonEager() {
		
	}
	
	static public Configuration getConfiguration() {
		return config;
	}
	
	public static void setConfiguration(int x ) {
		config.setX(x);
	}
}
