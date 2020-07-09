import java.lang.reflect.Constructor;

import telran.example.*;

public class ClassReflectionsScenarios {
	private static final String PACKAGE = "telran.example.";

	public static void main(String[] args) {
		if(args.length == 0) {
			System.out.println("No args was passed. Please enter the name of Class");
			return;
			
		}
		ReflectionExampleInterface x = null;
		try {
			Class<?> claszz = Class.forName(PACKAGE + args[0]);
//			System.out.println(claszz.getName());
			
			Constructor constructor = claszz.getConstructor();
			x = (ReflectionExampleInterface) constructor.newInstance();
			x.action();
			
		} catch (ClassNotFoundException e) {
			System.out.println(args[0] + " Not found");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		
		// Govno goes below
//		if(args[0] == "ExampleOneImplementation") {
//			x = new ExampleOneImplementation();
//		}else {
//			x = new ExampleTwoImplementation();
//		}
//		
	}

}
