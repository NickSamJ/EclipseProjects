package telran.employees.items;

import java.time.LocalDate;

import telran.employees.api.EmployeesService;
import telran.employees.dto.Employee;
import telran.employees.dto.EmployeesReturnCodes;
import telran.menu.InputOutput;

public class AddEmployee extends EmployeesItem {

	public AddEmployee(EmployeesService employees, InputOutput io) {
		super(employees, io);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String displayName() {
		return "Add employee";
	}

	@Override
	public void perform() {
		long id = io.inputInteger("Enter employees id [100 0000 - 999 999]", 100_000, 999_999);
		Employee employeeTest = employees.getEmployee(id);
		if(employeeTest != null) {
			io.displayLine("Imployee already exists");
			return;
		}
		int salary = io.inputInteger("Input salary [5000 - 50 000]", 5000, 50_000);
		String company = io.inputString("Enter company name");
		LocalDate birthDate = io.inputDate("Enter birth date");
		String name = io.inputString("Enter name");
		Employee empl =  new Employee(id, salary, company, birthDate, name);
		EmployeesReturnCodes res = employees.addEmployee(empl);
		
		io.displayLine(res);

	}

}
