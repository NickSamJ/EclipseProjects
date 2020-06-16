package telran.employees.items;

import telran.employees.api.EmployeesService;
import telran.menu.InputOutput;

public class GetEmployeesGroupedBySalaryItem extends EmployeesItem {

	public GetEmployeesGroupedBySalaryItem(EmployeesService employees, InputOutput io) {
		super(employees, io);
	}

	@Override
	public String displayName() {
		
		return "Get employees groupped by salary";
	}

	@Override
	public void perform() {
		int interval = io.inputInteger("Enter interval");
		io.displayLine(employees.getEmployeesGroupedBySalary(interval));

	}

}
