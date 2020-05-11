package telran.employees.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import telran.employees.api.EmployeesService;
import telran.employees.dto.Employee;
import telran.employees.dto.EmployeesReturnCodes;

public class EmployeesServiceMapImpl implements EmployeesService {
	private HashMap<Long, Employee> employees;
	//key - company, value - list of employees, that work in it
	private HashMap<String, List<Employee>> employeesCompany;  
															
	//key - age, value - list of employees, with that age
	private TreeMap<Integer, List<Employee>> employeesAge;
	
	//key - salary, value - list of employees, with that salary
	private TreeMap<Integer, List<Employee>> employeesSalary;
		
	@Override
	public EmployeesReturnCodes addEmployee(Employee empl) {
		// TODO Auto-generated method stub
		Employee res = employees.putIfAbsent(empl.getId(), empl); 
		if(res != null) return EmployeesReturnCodes.EMPLOYEE_ALREADY_EXISTS;
		addEmployeeSalary(empl);
		addEmployeeAge(empl);
		addEmployeeCompany(empl);
		
		return EmployeesReturnCodes.OK;
	}

	private void addEmployeeCompany(Employee empl) {
		String company = empl.getCompany();
		List<Employee> listEmployeesCompany = employeesCompany.getOrDefault(company, new ArrayList<>()); 
		listEmployeesCompany.add(empl);
		employeesCompany.putIfAbsent(company, listEmployeesCompany);
	}

	private void addEmployeeAge(Employee empl) {
		// TODO Auto-generated method stub
		
	}

	private void addEmployeeSalary(Employee empl) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EmployeesReturnCodes removeEmployee(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee getEmployee(long id) {
		return employees.get(id);
	}

	@Override
	public Iterable<Employee> getEmployees() {
		// TODO Auto-generated method stub
		return employees.values();
	}

	@Override
	public Iterable<Employee> getEmployeesCompany(String company) {
		// TODO Auto-generated method stub
//		return employeesCompany.;
		return null;
	}

	@Override
	public Iterable<Employee> getEmloyeesAges(int ageFrom, int ageTo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Employee> getEmloyeesSalary(int salaryFrom, int salaryTo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeesReturnCodes updateCompany(long id, String newCompany) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeesReturnCodes updateSALARY(long id, int newSalary) {
		// TODO Auto-generated method stub
		return null;
	}

}
