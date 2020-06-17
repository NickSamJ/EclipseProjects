package telran.employees.items;

import telran.employees.api.EmployeesService;
import telran.menu.InputOutput;

public class DisplayEmployeesSalary extends EmployeesItem {

	public DisplayEmployeesSalary(EmployeesService employees, InputOutput io) {
		super(employees, io);
	}

	@Override
	public String displayName() {
		
		return "Display all employees with given salary";
	}

	@Override
	public void perform() {
		int salaryFrom = io.inputInteger("Enter salary from");
		int salaryTo = io.inputInteger("Enter salary to", salaryFrom, Integer.MAX_VALUE);
		employees.getEmployeesSalary(salaryFrom, salaryTo).forEach(e -> io.displayLine(e));
//		io.displayLine(employees.getEmployeesSalary(salaryFrom, salaryTo));
	}

}
