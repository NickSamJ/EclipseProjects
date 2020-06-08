package telran.employees.items;

import telran.employees.api.EmployeesService;
import telran.menu.InputOutput;
import telran.menu.Item;

public abstract class EmployeesItem implements Item {
	protected EmployeesService employees;
	InputOutput io;
	public EmployeesItem(EmployeesService employees, InputOutput io) {
		super();
		this.employees = employees;
		this.io = io;
	}
}
