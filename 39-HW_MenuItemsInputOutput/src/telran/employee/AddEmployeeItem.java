package telran.employee;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;

import telran.menu.InputOutput;

public class AddEmployeeItem extends AbstractEmployeesItem {
	private InputOutput io;

	public AddEmployeeItem(HashMap<String, Employee> employees, InputOutput io) {
		super(employees, io);
		this.io = io;
	}
	
	private HashSet<String> allowedPositions(){
		HashSet<String> res = new HashSet<>();
		res.add("Developer");
		res.add("QA_Tester");
		res.add("Development_Manager");
		res.add("QA_Manager");
		return res;
	}
	
	@Override
	public String displayName() {
		
		return "Add Employee";
	}

	@Override
	public void perform() {
		String email = io.inputEmail("Enter employee email");
		if (employees.containsKey(email)) {
			io.displayLine("User already exists/n");
			return;
		}
		String phone = io.inputPhoneNumber("Enter Phone Number");
		LocalDate birthDate = io.inputDate("Enter birth date in format 2000-11-22");
		int salary = io.inputInteger("Enter salary");
		String title = io.inputOptions("Enter Position", allowedPositions());
		
		employees.put(email, new Employee(email, phone, birthDate, salary, title));
		io.displayLine("Added\n");

	}

}
