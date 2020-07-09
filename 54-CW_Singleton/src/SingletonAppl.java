
public class SingletonAppl {

	public static void main(String[] args) {
		Configuration config1, config2;
		config1 = SingletonEager.getConfiguration();
		config2 = SingletonEager.getConfiguration();
		
		if(config1 == config2) {
			System.out.println("True");
		}else {
			System.out.println("Not true(");
		}

	}

}
