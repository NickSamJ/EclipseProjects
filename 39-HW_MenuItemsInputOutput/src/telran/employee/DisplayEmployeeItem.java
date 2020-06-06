package telran.employee;

import java.util.HashMap;

import telran.menu.InputOutput;

public class DisplayEmployeeItem extends AbstractEmployeesItem {
	InputOutput io;

	public DisplayEmployeeItem(HashMap<String, Employee> employees, InputOutput io) {
		super(employees, io);
		this.io = io;
	}

	@Override
	public String displayName() {
		return "Display Employee";
	}

	@Override
	public void perform() {
		String email = io.inputEmail("Enter email of employee, you are looking for");
		if(employees.containsKey(email)) {
			io.displayLine(employees.get(email) + "\n");
		}else {
			io.displayLine("Employee not found\n");
		}

	}

}
