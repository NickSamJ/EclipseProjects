package telran.employees.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import telran.employees.dto.Employee;
import telran.employees.dto.EmployeesReturnCodes;
import telran.employees.service.EmployeesServiceMapsImpl;

class EmployeesServiceMapImplTests {
	private Employee empl1 = new Employee(1, 10000, "Korobko", LocalDate.parse("1979-12-22"), "Grisha");
	private Employee empl2 = new Employee(2, 8000, "Onopke", LocalDate.parse("1968-10-27"), "Misha");
	private Employee empl3 = new Employee(3, 6000, "Twigl", LocalDate.parse("1990-05-05"), "Pasha");
	private Employee empl4 = new Employee(4, 5500, "Korobko", LocalDate.parse("1985-07-14"), "Marina");
	private Employee empl5 = new Employee(5, 5000, "Korobko", LocalDate.parse("1979-10-15"), "Anjelica");
	
	private EmployeesServiceMapsImpl generateFullInstance() {
		EmployeesServiceMapsImpl instance = new EmployeesServiceMapsImpl();
		instance.addEmployee(empl1);
		instance.addEmployee(empl2);
		instance.addEmployee(empl3);
		instance.addEmployee(empl4);
		instance.addEmployee(empl5);
		return instance;
	}
	
	private boolean testEquality(ArrayList<Employee> list1, ArrayList<Employee> list2) {
		list1.sort(new EmployeesComp());
		list2.sort(new EmployeesComp());
		return list1.equals(list2);
	}
	
	private ArrayList<Employee> toArrayEmployees(Employee... employees) {
		ArrayList<Employee> res = new ArrayList<>();
		for(Employee e: employees) {
			res.add(e);
		}
		return res;
	}
	
	private ArrayList<Employee> transforToArrayList(Iterable<Employee> employees){
		ArrayList<Employee> resEmployees = new ArrayList<>();
		for(Employee e : employees) {
			resEmployees.add(e);
		}
		return resEmployees;
	}
	
	@Test
	void testAddGetEmployee() {
		EmployeesServiceMapsImpl instance = new EmployeesServiceMapsImpl();
		instance.addEmployee(empl1);
		EmployeesReturnCodes code = instance.addEmployee(empl2);
		EmployeesReturnCodes codeFalse = instance.addEmployee(empl2);
		
		assertEquals(EmployeesReturnCodes.OK, code);
		assertEquals(EmployeesReturnCodes.EMPLOYEE_ALREADY_EXISTS, codeFalse);
		assertEquals(empl1, instance.getEmployee(1));
	}
	
	@Test
	void testGetEmployees() {
		EmployeesServiceMapsImpl instance = generateFullInstance();
		Iterable<Employee> employees = instance.getEmployees();
		ArrayList<Employee> actualEmployees = transforToArrayList(employees);

		ArrayList<Employee> expected = toArrayEmployees(empl1, empl2, empl3, empl4, empl5);
		
		assertTrue(testEquality(expected, actualEmployees));
	}
	@Test
	void testGetEmployeesCompany(){
		EmployeesServiceMapsImpl instance = generateFullInstance();
		
		Iterable<Employee> employees = instance.getEmployeesCompany("Korobko");
		ArrayList<Employee> actualEmployees = transforToArrayList(employees);
		
		ArrayList<Employee>	expected = toArrayEmployees(empl1, empl4, empl5);
		
		assertTrue(testEquality(expected, actualEmployees));
	}
	@Test
	void testGetEmployeesAges() {
		EmployeesServiceMapsImpl instance = generateFullInstance();
		
		Iterable<Employee> employees = instance.getEmployeesAges(29, 36);
		ArrayList<Employee> actualEmployees = transforToArrayList(employees);
		
		ArrayList<Employee>	expected = toArrayEmployees(empl3, empl4);
		
		assertTrue(testEquality(expected, actualEmployees));
	}
	@Test
	void testGetEmployeesSalary() {
		EmployeesServiceMapsImpl instance = generateFullInstance();
		
		Iterable<Employee> employees = instance.getEmployeesSalary(5400, 8100);
		ArrayList<Employee> actualEmployees = transforToArrayList(employees);
		
		ArrayList<Employee>	expected = toArrayEmployees(empl2, empl3, empl4);
		
		assertTrue(testEquality(expected, actualEmployees));
	}
	@Test
	void testRemoveEmployee() {
		EmployeesServiceMapsImpl instance = generateFullInstance();
		EmployeesReturnCodes code = instance.removeEmployee(1);
		EmployeesReturnCodes codeFalse = instance.removeEmployee(1);
		
		Iterable<Employee> employees = instance.getEmployees();
		ArrayList<Employee> actualEmployees = transforToArrayList(employees);
		
		ArrayList<Employee>	expected = toArrayEmployees(empl2, empl3, empl4, empl5);
		
		assertEquals(EmployeesReturnCodes.OK, code);
		assertEquals(EmployeesReturnCodes.EMPLOYE_NOT_FOUND, codeFalse);
		assertTrue(testEquality(expected, actualEmployees));
		
		// check if user was deleted from Employees Company
		employees = instance.getEmployeesCompany("Korobko");
		actualEmployees = transforToArrayList(employees);
		
		expected = toArrayEmployees(empl4, empl5);
		
		assertTrue(testEquality(expected, actualEmployees));
		
		// check if user was deleted from Employees Ages
		instance.removeEmployee(3);
		employees = instance.getEmployeesAges(29, 36);
		actualEmployees = transforToArrayList(employees);
		
		expected = toArrayEmployees(empl4);
		
		assertTrue(testEquality(expected, actualEmployees));
	}
	
	@Test
	void testUpdateCompany() {
		EmployeesServiceMapsImpl instance = generateFullInstance();
		EmployeesReturnCodes code = instance.updateCompany(1, "Onopke");
				
		// Check if Company was changed to user #1
		Iterable<Employee> employees = instance.getEmployeesCompany("Korobko");
		ArrayList<Employee> actualEmployees = transforToArrayList(employees);
		
		ArrayList<Employee>	expected = toArrayEmployees(empl4, empl5);
		
		assertEquals(EmployeesReturnCodes.OK, code);
		assertTrue(testEquality(expected, actualEmployees));
		
		// check if Company was changed to 'Onopke' 
		employees = instance.getEmployeesCompany("Onopke");
		actualEmployees = transforToArrayList(employees);
		code = instance.updateCompany(1, "Onopke");
		
		expected = toArrayEmployees(empl1, empl2);
		
		assertEquals(EmployeesReturnCodes.SAME_COMPANY, code);
		assertTrue(testEquality(expected, actualEmployees));		
	}
	
	@Test
	void testUpdateSalary() {
		EmployeesServiceMapsImpl instance = generateFullInstance();
		EmployeesReturnCodes code = instance.updateSalary(3, 9000);
		
		// Check if Employee don't receive 6000k anymore 
		Iterable<Employee> employees = instance.getEmployeesSalary(5400, 8100);
		ArrayList<Employee> actualEmployees = transforToArrayList(employees);
		
		ArrayList<Employee>	expected = toArrayEmployees(empl2, empl4);
		
		assertEquals(EmployeesReturnCodes.OK, code);
		assertTrue(testEquality(expected, actualEmployees));
		
		// Check if Employee receive 9000k and employees Salary was updated 
		employees = instance.getEmployeesSalary(9000, 10000);
		actualEmployees = transforToArrayList(employees);
		code = instance.updateSalary(3, 9000);
		
		expected = toArrayEmployees(empl1, empl3);
		
		assertEquals(EmployeesReturnCodes.SAME_SALARY, code);
		assertTrue(testEquality(expected, actualEmployees));
				
			
		
	}
}



