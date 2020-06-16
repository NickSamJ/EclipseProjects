package telran.employees.items;

import telran.employees.api.EmployeesService;
import telran.menu.InputOutput;

public class GetCompaniesGreaterAvgSalaryItem extends EmployeesItem {

	public GetCompaniesGreaterAvgSalaryItem(EmployeesService employees, InputOutput io) {
		super(employees, io);
	}

	@Override
	public String displayName() {
		return "Get companies greater average salary";
	}

	@Override
	public void perform() {
		io.displayLine(employees.getCompaniesGreaterAvgSalary());
	}

}
