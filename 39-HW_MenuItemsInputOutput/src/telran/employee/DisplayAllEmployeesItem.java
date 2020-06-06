package telran.employee;

import java.util.HashMap;

import telran.menu.InputOutput;

public class DisplayAllEmployeesItem extends AbstractEmployeesItem {
	private InputOutput io; 

	public DisplayAllEmployeesItem(HashMap<String, Employee> employees, InputOutput io) {
		super(employees, io);
		this.io = io;
	}

	@Override
	public String displayName() {
		return "Display all employees";
	}

	@Override
	public void perform() {
		if(employees.size() == 0) {
			io.displayLine("No employees found\n");
		}else { 
			for(String emailKey : employees.keySet()){
				io.displayLine(emailKey + ": " + employees.get(emailKey));
			}
		}
	}

}
