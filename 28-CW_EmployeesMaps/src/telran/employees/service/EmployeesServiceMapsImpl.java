package telran.employees.service;

import telran.employees.api.EmployeesService;
import telran.employees.dto.Employee;
import telran.employees.dto.EmployeesReturnCodes;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class EmployeesServiceMapsImpl implements EmployeesService {
private HashMap<Long, Employee> employees = new HashMap<>();
//key-company, value-list of employees working for that company
private HashMap<String, List<Employee>> employeesCompany = new HashMap<>() ;
/**************************************************/
//key - age, value - list of employees with that age
private TreeMap<Integer, List<Employee>> employeesAge = new TreeMap<>();
/*****************************************************/
//key - salary, value - list of employees with that salary
private TreeMap<Integer, List<Employee>> employeesSalary = new TreeMap<>();

	/******************************************************/

	@Override
	public EmployeesReturnCodes addEmployee(Employee empl) {
		Employee res = employees.putIfAbsent(empl.getId(), empl);
		if (res != null) {
			return EmployeesReturnCodes.EMPLOYEE_ALREADY_EXISTS;
		}
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
		Integer age = getAge(empl.getBirthYear());
		List<Employee> listEmployeesAges = employeesAge.getOrDefault(age, new ArrayList<>());
		listEmployeesAges.add(empl);
		employeesAge.putIfAbsent(age, listEmployeesAges);
	}

	private Integer getAge(LocalDate birthYear) {
		return Period.between(birthYear, LocalDate.now()).getYears();
	}

	private void addEmployeeSalary(Employee empl) {
		Integer salary = empl.getSalary();
		List<Employee> listEmployeesSalary = employeesSalary.getOrDefault(salary, new ArrayList<>());
		listEmployeesSalary.add(empl);
		employeesSalary.putIfAbsent(salary, listEmployeesSalary);
		

	}

	@Override
	public EmployeesReturnCodes removeEmployee(long id) {
		Employee empl = employees.get(id);
		if(empl==null) return EmployeesReturnCodes.EMPLOYE_NOT_FOUND;
		employeesAge.get(getAge(empl.getBirthYear())).remove(empl);
		employeesCompany.get(empl.getCompany()).remove(empl);
		employeesSalary.get(empl.getSalary()).remove(empl);
		employees.remove(empl.getId());
		
		return EmployeesReturnCodes.OK;
	}

	@Override
	public Employee getEmployee(long id) {
		
		return employees.get(id);
	}

	@Override
	public Iterable<Employee> getEmployees() {

		return employees.values();
	}

	@Override
	public Iterable<Employee> getEmployeesCompany(String company) {

		return employeesCompany.getOrDefault(company, new ArrayList<>());
	}

	@Override
	public Iterable<Employee> getEmployeesAges(int ageFrom, int ageTo) {
//		TreeMap<Integer, List<Employee>> subAges = (TreeMap<Integer, List<Employee>>) employeesAge.subMap(ageFrom, ageTo);
		NavigableMap<Integer, List<Employee>> subAges = (NavigableMap<Integer, List<Employee>>) employeesAge.subMap(ageFrom,true, ageTo, true);
		ArrayList<Employee> resList = new ArrayList<>();
		for(List <Employee> list : subAges.values()) {
			resList.addAll(list);
		}
		return resList;
	}

	@Override
	public Iterable<Employee> getEmployeesSalary(int salaryFrom, int salaryTo) {
		NavigableMap<Integer, List<Employee>> subSalaries = (NavigableMap<Integer, List<Employee>>) employeesSalary.subMap(salaryFrom, true, salaryTo, true);
		ArrayList<Employee> resList= new ArrayList<>();
		for(List <Employee> list : subSalaries.values()) {
			resList.addAll(list);
		}
		return resList;
	}

	@Override
	public EmployeesReturnCodes updateCompany(long id, String newCompany) {
		Employee employee = getEmployee(id);
		String oldCompany = employee.getCompany();
		if(oldCompany == newCompany) return EmployeesReturnCodes.SAME_COMPANY;
		removeEmployee(id);
		employee.setCompany(newCompany);
		addEmployee(employee);
		return EmployeesReturnCodes.OK;
	}

	@Override
	public EmployeesReturnCodes updateSalary(long id, int newSalary) {
		Employee employee = getEmployee(id);
		int oldSalary = employee.getSalary();
		if(oldSalary == newSalary) return EmployeesReturnCodes.SAME_SALARY;
		removeEmployee(id);
		employee.setSalary(newSalary);
		addEmployee(employee);
		return EmployeesReturnCodes.OK;
	}

}
