package telran.employees.items;

import telran.employees.api.EmployeesService;
import telran.employees.dto.EmployeesReturnCodes;
import telran.menu.InputOutput;

public class UpdateCompanyItem extends EmployeesItem {

	public UpdateCompanyItem(EmployeesService employees, InputOutput io) {
		super(employees, io);
	}

	@Override
	public String displayName() {
		
		return "Update company";
	}

	@Override
	public void perform() {
		long id = io.inputInteger("Enter employees id [100 0000 - 999 999]", 100_000, 999_999);
		String newCompany = io.inputString("Enter new name of company");
		EmployeesReturnCodes res = employees.updateCompany(id, newCompany);
		io.displayLine(res);
	}

}
