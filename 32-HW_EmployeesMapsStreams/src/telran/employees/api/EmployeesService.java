package telran.employees.api;

import java.util.List;
import java.util.Map;

import telran.employees.dto.Employee;
import telran.employees.dto.EmployeesReturnCodes;

public interface EmployeesService {
EmployeesReturnCodes addEmployee(Employee empl);
EmployeesReturnCodes removeEmployee(long id);
Employee getEmployee(long id);
Map<String, List<Employee>> getEmployeesGroupedBySalary();
Iterable<Employee> getEmployees();
Iterable<Employee> getEmployeesCompany(String company);
Iterable<Employee> getEmployeesAges(int ageFrom, int ageTo);
Iterable<Employee> getEmployeesSalary(int salaryFrom, int salaryTo);
EmployeesReturnCodes updateCompany(long id, String newCompany);
EmployeesReturnCodes updateSalary(long id, int newSalary);

}
