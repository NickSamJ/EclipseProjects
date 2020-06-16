package telran.employees.items;

import telran.employees.api.EmployeesService;
import telran.employees.dto.EmployeesReturnCodes;
import telran.menu.InputOutput;

public class UpdateEmployeeSalary extends EmployeesItem{

	public UpdateEmployeeSalary(EmployeesService employees, InputOutput io) {
		super(employees, io);
	}

	@Override
	public String displayName() {
		return "Update employees items";
	}

	@Override
	public void perform() {
		long id = io.inputInteger("Enter employees id [100 0000 - 999 999]", 100_000, 999_999);
		int newSalary = io.inputInteger("Input new salary from 2000 to 1 000 000", 2000, 1000000);
		EmployeesReturnCodes code = employees.updateSalary(id, newSalary);
		io.displayLine(code);
	}

}
