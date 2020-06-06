package telran.employee;

import java.util.HashMap;

import telran.menu.InputOutput;

public class RemoveEmployeeItem extends AbstractEmployeesItem {
	InputOutput io;

	public RemoveEmployeeItem(HashMap<String, Employee> employees, InputOutput io) {
		super(employees, io);
		this.io = io;
	}

	
	@Override
	public String displayName() {
		return "Remove employee";
	}

	@Override
	public void perform() {
		String email = io.inputEmail("Enter email of Employee, you want to delete");
		
		if(employees.containsKey(email)) {
			Employee removedEmployee = employees.get(email);
			employees.remove(email);
			io.displayLine("Employee " + removedEmployee + "was removed\n");
		}else {
			io.displayLine("Employee with this email doesn't exist\n");			
		}
		

	}

}
