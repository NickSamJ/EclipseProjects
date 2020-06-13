package telran.employees.items;

import telran.employees.api.EmployeesService;
import telran.menu.InputOutput;

public class GetEMployeesAgesItem extends EmployeesItem {

	public GetEMployeesAgesItem(EmployeesService employees, InputOutput io) {
		super(employees, io);
	}

	@Override
	public String displayName() {
		return "Get employees ages";
	}

	@Override
	public void perform() {
		int from = io.inputInteger("Enter age from");
		int to = io.inputInteger("Enter age to");
		io.displayLine(employees.getEmployeesAges(from, to));
	}

}
