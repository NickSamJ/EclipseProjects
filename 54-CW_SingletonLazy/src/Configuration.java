
public class Configuration {
	int x;
	private static Configuration conf;
	
	private Configuration() {}
	public void setX(int x) throws Exception{
		if(x < 0) {
			throw new Exception("Negative value");
		}
		this.x = x;
	}
	public static Configuration getConfiguration(int x) {
		if(conf == null) {
			synchronized (Configuration.class) {
				if(conf == null) {					
					conf = new Configuration();
				}
			}
		}
		return conf;
	}
}
