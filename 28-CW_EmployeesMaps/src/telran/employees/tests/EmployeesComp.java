package telran.employees.tests;

import java.util.Comparator;

import telran.employees.dto.Employee;

public class EmployeesComp implements Comparator<Employee>{

	@Override
	public int compare(Employee o1, Employee o2) {
		
		return (int) (o1.getId()-o2.getId());
	}

}
