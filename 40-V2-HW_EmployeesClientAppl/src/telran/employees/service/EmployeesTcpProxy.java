package telran.employees.service;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static telran.employees.api.EmployeesOperationsApi.*;


import telran.employees.api.EmployeesService;
import telran.employees.dto.*;
import telran.net.TcpClientJava;

public class EmployeesTcpProxy extends TcpClientJava implements EmployeesService {

	public EmployeesTcpProxy(String hostname, int port) {
		super(hostname, port);
	}

	@Override
	public EmployeesReturnCodes addEmployee(Employee empl) {
		return sendRequest(ADD, empl);
	}
	
	@Override
	public Employee getEmployee(long id) {
		return sendRequest(GET_EMPLOYEE, id);
	}
	
	@Override
	public EmployeesReturnCodes removeEmployee(long id) {
		return sendRequest(REMOVE_EMPLOYEE, id);
	}
	
	@Override
	public Iterable<Employee> getEmployeesSalary(int salaryFrom, int salaryTo) {
		List<Integer> salaryFrames = Arrays.asList(salaryFrom,salaryTo);
		return sendRequest(GET_EMPLOYEES_SALARY, (Serializable) salaryFrames);
	}
	
	
	@Override
	public Map<String, List<Employee>> getEmployeesGroupedBySalary(int interval) {
		return sendRequest(GET_EMPLOYEE_GROUP_BY_SALARY, interval);
	}
	@Override
	public Iterable<Employee> getEmployeesAges(int ageFrom, int ageTo) {
		List<Integer> ageFrames = Arrays.asList(ageFrom, ageTo);
		return sendRequest(GET_EMPLOYEES_AGES, (Serializable) ageFrames);
	}
	@Override
	public List<CompanySalary> getCompaniesAvgSalary() {
		return sendRequest(GET_COMPANIES_AVG_SALARY, null);
	}
	@Override
	public List<CompanySalary> getCompaniesGreaterAvgSalary() {
		return sendRequest(GET_COMPANIES_GREATER_AVG_SALARY, null);
	}
	
	@Override
	public EmployeesReturnCodes updateCompany(long id, String newCompany) {
		
		Map<Long, String> mapUpdateCompany = new HashMap<>();
		mapUpdateCompany.put(id, newCompany);
		return sendRequest(UPDATE_COMPANY, (Serializable) mapUpdateCompany);
				
	}
	
	@Override
	public EmployeesReturnCodes updateSalary(long id, int newSalary) {
		Map<Long, Integer> mapUpdateSalary = new HashMap<>();
		mapUpdateSalary.put(id, newSalary);
		return sendRequest(UPDATE_SALARY, (Serializable) mapUpdateSalary);
	}
}
