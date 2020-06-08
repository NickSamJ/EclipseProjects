package telran.employees.items;

import telran.employees.api.EmployeesService;
import telran.menu.InputOutput;

public class DisplayEmployeesSalary extends EmployeesItem {

	public DisplayEmployeesSalary(EmployeesService employees, InputOutput io) {
		super(employees, io);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String displayName() {
		
		return "Display all employees with given salary";
	}

	@Override
	public void perform() {
		int salaryFrom = io.inputInteger("Enter salary from");
		int salaryTo = io.inputInteger("Enter salary to", salaryFrom, Integer.MAX_VALUE);
		io.displayLine(employees.getEmployeesSalary(salaryFrom, salaryTo));
	}

}
