package telran.employees.api;

import telran.employees.dto.Employee;
import telran.employees.dto.EmployeesReturnCodes;

public interface EmployeesService {
EmployeesReturnCodes addEmployee(Employee empl);
EmployeesReturnCodes removeEmployee(long id);
Employee getEmployee(long id);
Iterable<Employee> getEmployees();
Iterable<Employee> getEmployeesCompany(String company);
Iterable<Employee> getEmloyeesAges(int ageFrom, int ageTo);
Iterable<Employee> getEmloyeesSalary(int salaryFrom, int salaryTo);
EmployeesReturnCodes updateCompany(long id, String newCompany);
EmployeesReturnCodes updateSALARY(long id, int newSalary)	;

}
