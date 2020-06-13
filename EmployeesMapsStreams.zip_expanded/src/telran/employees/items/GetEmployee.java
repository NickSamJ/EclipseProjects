package telran.employees.items;

import telran.employees.api.EmployeesService;
import telran.employees.dto.Employee;
import telran.menu.InputOutput;

public class GetEmployee extends EmployeesItem {

	public GetEmployee(EmployeesService employees, InputOutput io) {
		super(employees, io);
	}

	@Override
	public String displayName() {
		return "Receive Employee";
	}

	@Override
	public void perform() {
		long id = io.inputInteger("Enter employees id [100 0000 - 999 999]", 100_000, 999_999);
		Employee employee = employees.getEmployee(id);
		if(employee != null ) {
			io.displayLine(employee);
		}else{
			io.displayLine("Employee not found");
		}
		
		
	}

}
