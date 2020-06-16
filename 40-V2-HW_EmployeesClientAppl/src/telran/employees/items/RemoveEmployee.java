package telran.employees.items;

import telran.employees.api.EmployeesService;
import telran.employees.dto.EmployeesReturnCodes;
import telran.menu.InputOutput;

public class RemoveEmployee extends EmployeesItem {

	public RemoveEmployee(EmployeesService employees, InputOutput io) {
		super(employees, io);
	}

	@Override
	public String displayName() {
		return "Remove employee";
	}

	@Override
	public void perform() {
		long id = io.inputInteger("Enter employees id [100 0000 - 999 999]", 100_000, 999_999);		
		EmployeesReturnCodes res = employees.removeEmployee(id);
		System.out.println(res);
	}
}
