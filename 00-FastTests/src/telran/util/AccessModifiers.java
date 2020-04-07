package telran.util;

public class AccessModifiers {
	public static void main(String[] args) {
		Employee emp = new Employee(20, 98, "Mechanic");
		System.out.println(emp.weight);
		System.out.println(emp.profession);
		System.out.println("___________");
		System.out.println(Employee.profession);
		
	}
	
}

 class Employee{
	private int age;
	public double weight;
	static String profession;
	
	public Employee(int age, double weight, String profession) {
		super();
		this.age = age;
		this.weight = weight;
		this.profession = profession;
	}
	
}