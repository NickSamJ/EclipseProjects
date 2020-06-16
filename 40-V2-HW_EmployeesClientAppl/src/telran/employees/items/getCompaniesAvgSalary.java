package telran.employees.items;

import telran.employees.api.EmployeesService;
import telran.menu.InputOutput;

public class getCompaniesAvgSalary extends EmployeesItem {

	public getCompaniesAvgSalary(EmployeesService employees, InputOutput io) {
		super(employees, io);
	}

	@Override
	public String displayName() {
		return "Get companies avg salary";
	}

	@Override
	public void perform() {
		io.displayLine(employees.getCompaniesAvgSalary());

	}

}
